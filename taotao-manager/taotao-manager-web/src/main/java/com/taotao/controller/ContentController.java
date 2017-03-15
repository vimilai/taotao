package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	ContentService service;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getlist(int page,int rows,long categoryId) {
		return service.getContentList(page, rows, categoryId);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		TaotaoResult result = service.insertContent(content);
		return result;
	}

}
