package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;
@Service
public class ContentCategoryServiceImpl  implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EUTreeNode> getContentCategoryList(long parentid) {
		//根据parentid查询内容分类列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentid);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			//判断是否是父节点
			if (tbContentCategory.getIsParent()) {
				node.setState("closed");
			} else {
				node.setState("open");
			}
			resultList.add(node);
		}
		return resultList;
	}
	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		//创建一个pojo
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		//'状态。可选值:1(正常),2(删除)',
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//添加记录
		//添加记录
		contentCategoryMapper.insert(contentCategory);
		//查看父节点的isParent列是否为true，如果不是true改成true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		//判断是否为true
		if(!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		//返回结果
		return TaotaoResult.ok(contentCategory);		
	}
	@Override
	public TaotaoResult deleteContentCategory(long parentId, long id) {
		
		//删除节点
		deleteCategory(id);
		//删除节点之后该节点是否为叶子节点
		//根据parentid查询内容分类列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if(list.size()==0 || list==null){
			TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(parentId);
			category.setIsParent(false);
		}
		
		return TaotaoResult.ok();
	}
	private void deleteCategory(long id) {
		//判断是否是父节点.删除父节点下子节点
		TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
		if(category.getIsParent()){	
			//递归删除节点
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(id);
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
			if(list.size()>0&& list!=null){
				for (TbContentCategory tbContentCategory : list) {
				deleteCategory(tbContentCategory.getId());
				}
			}
						
		}	
		//删除节点
		contentCategoryMapper.deleteByPrimaryKey(id);
	}

}
