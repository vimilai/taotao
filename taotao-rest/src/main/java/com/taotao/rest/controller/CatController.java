package com.taotao.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.service.CatService;

@Controller
public class CatController {

	
	@Autowired
	private CatService catService;
	/**
	 * 跨域传输  不能访问数据 但是可以访问脚本 string是一个js函数 function(data) jsonp
	 * js 调用js
	 * @return
	 */
	/*@RequestMapping(value="/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getcatlist(String callback){
		Map catList = catService.catList();
		String json= JsonUtils.objectToJson(catList);
		String result = callback + "(" + json + ");";
		return result;
	}*/
	
	/**
	 * 第二种 spring 4.1.3以后支持
	 * @param callback
	 * @return
	 */
	@RequestMapping(value="/itemcat/list")
	@ResponseBody
	public Object getcatlist(String callback){
		Map catList = catService.catList();
		String json= JsonUtils.objectToJson(catList);
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(json);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
}
