package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemByid(long id) {
		//TbItem tbitem = itemMapper.selectByPrimaryKey(id);第一种直接查询
		TbItemExample tbItemExample=new TbItemExample();//第二种根据查询条件查询
		Criteria criteria = tbItemExample.createCriteria();
		criteria.andIdEqualTo(id);
		List<TbItem> items = itemMapper.selectByExample(tbItemExample);
		if(items!=null&& items.size()>0){
			return items.get(0);
		}
		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		TbItemExample example=new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		EUDataGridResult dataGridResult=new EUDataGridResult();
		PageInfo<TbItem> info=new PageInfo<>(list);
		dataGridResult.setTotal(info.getTotal());
		dataGridResult.setRows(list);
		return dataGridResult;
	}
	
}
