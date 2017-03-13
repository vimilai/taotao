package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.taotao.service.ItemPrarmService;
import com.taotao.common.utils.TaotaoResult;
/**
 * 商品规则参数模板管理
 * @author user
 *
 */

@Controller
@RequestMapping("item/param")
public class ItemParamController {
	@Autowired
	private  ItemPrarmService itemPrarmService;
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) {
		TaotaoResult result = itemPrarmService.getItemParmByCId(itemCatId);
		return result;
	}
}
