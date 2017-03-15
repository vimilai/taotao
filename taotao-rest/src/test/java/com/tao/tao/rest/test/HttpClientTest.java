package com.tao.tao.rest.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

		@Test
		public void doGet() throws Exception{
			
			//创建一个httpclient连接
			
			//创建一个get连接（url）
				//带参数  (uri)
				//URIBuilder uriBuilder = new URIBuilder("http://www.baidu.com/s");
                //uriBuilder.addParameter("wd", "花千骨");
                //HttpGet get = new HttpGet(uriBuilder.build());
			
			//执行get请求 返回一个response对象
			
			//获取的response对象的属性 获取数据
			
			CloseableHttpClient httpClient=HttpClients.createDefault();
			HttpGet get =new HttpGet("http://www.baidu.com");
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity,"utf-8");
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(string);
			response.close();
			httpClient.close();
		}
		@Test
		public void doGetWithParam() throws Exception{
			CloseableHttpClient httpClient=HttpClients.createDefault();
			URIBuilder uriBuilder = new URIBuilder("http://www.baidu.com/s");
			uriBuilder.addParameter("wd", "花千骨");
			HttpGet get = new HttpGet(uriBuilder.build());
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity,"utf-8");
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(string);
			response.close();
			httpClient.close();
		}
}
