package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;

import redis.clients.jedis.Jedis;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_CONTENT_HEY}")
	private String REDIS_CONTENT_HEY;
	@Override
	public synchronized List<TbContent> getContentList(long contentCid) {
		
		//先从缓存中查找 如果没有则查找数据库
		String hget = jedisClient.hget(REDIS_CONTENT_HEY, contentCid+"");
		if(hget!=null&&!hget.isEmpty()){
			return JsonUtils.jsonToList(hget, TbContent.class);
		}
		//根据内容分类id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		
		//将查找到的录入redis中
		jedisClient.hset(REDIS_CONTENT_HEY, contentCid+"", JsonUtils.objectToJson(list));
		
		return list;
	}
	 public  TaotaoResult delCacheByContentId(String contentCid){
		 try {
			 jedisClient.hdel(REDIS_CONTENT_HEY,contentCid);
			 return TaotaoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		
		
	}

}
