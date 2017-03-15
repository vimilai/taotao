package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	TbContentMapper tbContentMapper;
	@Override
	public EUDataGridResult getContentList(int page,int rows,long categoryId) {
		TbContentExample contentExample=new TbContentExample();
		Criteria criteria = contentExample.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page, rows);
		List<TbContent> list = tbContentMapper.selectByExample(contentExample);
		PageInfo<TbContent> pageInfo=new PageInfo<>(list);
		EUDataGridResult dataGridResult=new EUDataGridResult();
		dataGridResult.setRows(list);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;
	}
	@Override
	public TaotaoResult insertContent(TbContent content) {
				//补全pojo内容
				content.setCreated(new Date());
				content.setUpdated(new Date());
				tbContentMapper.insert(content);
				
				return TaotaoResult.ok();
	}

}
