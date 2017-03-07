package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 页面跳转
 * @author user
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.service.ItemService;

@Controller
public class PageController {
	@Autowired
	ItemService itemservice;

	/**
	 * 打开首页
	 */
	@RequestMapping("/index")
	public String showIndex() {

		return "index";
	}

	/**
	 * 跳转页面
	 */
	@RequestMapping("/{url}")
	public String getUrl(@PathVariable String url) {
		return url;
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getlist(int page,int rows) {
		
		return itemservice.getItemList(page, rows);
	}
}
