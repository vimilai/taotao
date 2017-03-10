package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.service.PictureService;

@Controller
public class PictureController {
	@Autowired
	private PictureService service;
	
	
//	/**
//	 * 返回的是json对象
//	 * @param uploadFile
//	 * @return
//	 */
//	@RequestMapping("pic/upload")
//	@ResponseBody
//	public Map pictureUpload(MultipartFile uploadFile){
//		return service.uploadPicture(uploadFile);
//	}
	/**
	 * 返回json字符串
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("pic/upload")
	@ResponseBody
	public String pictureUpload(MultipartFile uploadFile){
		String string = JSONUtils.toJSONString(service.uploadPicture(uploadFile));
		return string;
	}
	
}
