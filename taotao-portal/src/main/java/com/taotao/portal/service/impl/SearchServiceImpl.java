package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {

	
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchResult search(String query, int page) {
		Map< String, String> map=new HashMap< String, String>();
		map.put("q", query);
		map.put("page", page+"");
		String result = HttpClientUtil.doGet(SEARCH_BASE_URL,map);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, SearchResult.class);
		SearchResult searchResult = (SearchResult) taotaoResult.getData();
		return searchResult;
	}

}
