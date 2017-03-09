package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

public class PictureServiceImpl implements PictureService {

	@Value("ftp_address")
	private String ftp_address;
	@Value("ftp_port")
	private Integer ftp_port;
	@Value("ftp_username")
	private String ftp_username;
	@Value("ftp_password")
	private String ftp_password;
	@Value("ftp_basebath")
	private String ftp_basebath;
	@Value("image_base_url")
	private String image_base_url;
	
	@Override
	public Map uploadPicture(MultipartFile multipartFile) {
		Map map=new HashMap<>();
		//获取旧文件的名字
		String filename = multipartFile.getOriginalFilename();		
		//创建新文件的名字
		String newname = filename.substring(filename.lastIndexOf("."))+IDUtils.genImageName();
		String imagepath = new DateTime().toString("/yyyy/MM/dd");
		//图片上传
		try {
			boolean result = FtpUtil.uploadFile(ftp_address, ftp_port, ftp_username, ftp_password, ftp_basebath,
					imagepath, newname, multipartFile.getInputStream());
			if(!result){
				map.put("error", 1);
				map.put("message", "文件上传失败！");
				return map;
			}
			map.put("error", 0);
			map.put("url", image_base_url+"/"+imagepath+"/"+newname);
			return  map;
		} catch (IOException e) {
			map.put("error", 1);
			map.put("message", "文件上传失败！");
			return map;
		}
	}

}
