package com.taotao.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelper {
	
	@Test
	public void testPage(){
			@SuppressWarnings("resource")
			ApplicationContext actx=new ClassPathXmlApplicationContext("classpath:resources/spring/applicationContext-*.xml");
			TbItemMapper tbItemMapper = actx.getBean(TbItemMapper.class);
			TbItemExample tbItemExample=new TbItemExample();
			PageHelper.startPage(1, 10);
			List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);
			for (TbItem tbItem : list) {
				System.out.println(tbItem.getTitle());
			}
			PageInfo<TbItem> items=new PageInfo<TbItem>(list);
			System.out.println(items.getTotal());
			
	}
}
