package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/query")
	public String search(@RequestParam("q")String query,@RequestParam(defaultValue="1")int page,Model model){
		
		try {
			query = new String(query.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SearchResult searchResult = searchService.search(query, page);
		model.addAttribute("page",page );
		model.addAttribute("itemList", searchResult.getItemList());
		return "search";
	}
}
