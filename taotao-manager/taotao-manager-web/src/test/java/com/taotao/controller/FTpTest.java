package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTpTest {
	@Test
	public void testFtp()throws Exception{
		//创建一个ftpclinet对象
		FTPClient client=new FTPClient();
		//创建ftp连接
		client.connect("192.168.100.134", 21);
		//登陆gtp服务其，登陆
		client.login("ftpuser","ftpuser");
		//上传文件
		//打开文件输出流：
		FileInputStream fileInputStream=new FileInputStream(new File("D:\\workspace\\oss\\src\\main\\webapp\\common\\frame\\adminlte\\dist\\img\\user1-128x128.jpg"));
		//设置上传的路径
		client.changeWorkingDirectory("/home/ftpuser/www/images");
		//设置文件的类型二进制类型
		client.setFileType( FTP.BINARY_FILE_TYPE);
		//上传文件
		client.storeFile("hello.jpg",fileInputStream );
		//关闭链接
		client.logout();
	}
}
