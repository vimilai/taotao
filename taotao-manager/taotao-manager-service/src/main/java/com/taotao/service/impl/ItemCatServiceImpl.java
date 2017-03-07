package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.itemCatService;
@Service
public class ItemCatServiceImpl implements itemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public List<EUTreeNode> getCatList(long id) {
		TbItemCatExample catExample=new TbItemCatExample();
		Criteria criteria = catExample.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<EUTreeNode> nodes=new ArrayList<EUTreeNode>();
		List<TbItemCat> list = itemCatMapper.selectByExample(catExample);
		if(list!=null && list.size()>0){
			for (TbItemCat tbItemCat : list) {
				EUTreeNode node=new EUTreeNode();
				node.setId(tbItemCat.getId());
				node.setText(tbItemCat.getName());
				node.setState(tbItemCat.getIsParent()?"closed":"open");
				nodes.add(node);
			}
		}
		return nodes;
	}

}
