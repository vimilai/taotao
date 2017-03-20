package com.taotao.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public TaotaoResult importdata() {
		try {
			//从数据库中取所有商品
			List<Item> list = itemMapper.getsearchList();
				//去每个商品对象
				if(list.size()>0){
					for (Item item : list) {
						//创建document
						//写入item中
						SolrInputDocument document=new SolrInputDocument();
						document.setField("id", item.getId());
						document.setField("item_title", item.getTitle());
						document.setField("item_sell_point", item.getSell_point());
						document.setField("item_price", item.getPrice());
						document.setField("item_image", item.getImage());
						document.setField("item_category_name", item.getCategory_name());
						document.setField("item_desc", item.getItem_des());
						solrServer.add(document);
						}
					}
			
				//提交文档
				solrServer.commit();
				return TaotaoResult.ok();
		}catch (Exception e) {
				return TaotaoResult.build(null, "500", ExceptionUtil.getStackTrace(e));
			} 
	}

}
