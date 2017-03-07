package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.itemCatService;

@Controller
@RequestMapping("item/cat")
public class ItemCatController {
	@Autowired
	private itemCatService catService;
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getItemCat(@RequestParam(value="id",defaultValue="0")long id){
		return catService.getCatList(id);
	}
}
