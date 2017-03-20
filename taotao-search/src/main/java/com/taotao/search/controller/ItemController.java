package com.taotao.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
/**
 * 索引库维护
 * @author user
 *
 */
@Controller
@RequestMapping("manage")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	@RequestMapping("/importAll")
	@ResponseBody
	public TaotaoResult importData(){
		TaotaoResult result = itemService.importdata();
		return result;
	}
	
	

}
