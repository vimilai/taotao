package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.service.CatService;

@Service
public class CatServiceImpl implements CatService {

	private final static long PARENT_ID = 0;

	@Autowired
	private TbItemCatMapper catmapper;

	/**
	 * 分类详情
	 */
	@Override
	public Map catList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", getcatlist(PARENT_ID));
		return map;
	}

	private List getcatlist(long parentId) {

		// 查询父类id为parentid下的所有目录
		// 遍历 目录
			//如果是父目录 就是一个catode，setname、url、item(也是个catnode)
				//如果是一级目录 parnet=0则name不一样。
			//不是则是叶子节点，就是一个name
		//全部装进一个list。

		TbItemCatExample catExample = new TbItemCatExample();
		Criteria criteria = catExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List list = new ArrayList();
		List<TbItemCat> cats = catmapper.selectByExample(catExample);
		if (cats.size() > 0 && cats != null) {
			for (TbItemCat tbItemCat : cats) {
				if (tbItemCat.getIsParent()) {
					CatNode node = new CatNode();
					node.setUrl("/products/" + tbItemCat.getId() + ".html");
					if (tbItemCat.getParentId() == 0) {
						node.setName(
								"<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
					} else {
						node.setName(tbItemCat.getName());
					}
					node.setItem(getcatlist(tbItemCat.getId()));
					list.add(node);
				}
				else{
					list.add( "/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
				}
			}

		}
		return list;
	}

}
