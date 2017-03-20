package com.tao.tao.rest.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {

	@Test
	public void addDocument() throws Exception{
			//创建链接
			SolrServer server=new HttpSolrServer("http://192.168.100.134:8080/solr");
			//添加文档
			SolrInputDocument document=new SolrInputDocument();
			document.addField("id", "aaaa001");
			document.addField("item_title", "123213");
			//把文档写入索引库
			server.add(document);
			//提交
			server.commit();
	}
	@Test
	public void delDocument() throws Exception{
			//创建链接
			SolrServer server=new HttpSolrServer("http://192.168.100.134:8080/solr");
			//删除文档
			server.deleteById("aaaa001");
			//server.deleteByQuery("id:aaaa001");
			//提交
			server.commit();
	}
	@Test
	public void querySolr() throws Exception{
		//创建链接
		SolrServer server=new HttpSolrServer("http://192.168.100.134:8080/solr");
		SolrQuery query=new SolrQuery();
		query.setQuery("*:*");
		query.setRows(50);
		query.setStart(4);
		QueryResponse queryResponse = server.query(query);
		SolrDocumentList documentList = queryResponse.getResults();
		documentList.getNumFound();
		for (SolrDocument solrDocument : documentList) {
			System.out.println(solrDocument.get("id"));
		}
		
	}
}
