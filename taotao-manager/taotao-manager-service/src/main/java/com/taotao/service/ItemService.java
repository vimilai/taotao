package com.taotao.service;


import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
		
	TbItem getItemByid(long id);
	EUDataGridResult getItemList(int page,int rows);
	TaotaoResult itemSave(TbItem item);
}
