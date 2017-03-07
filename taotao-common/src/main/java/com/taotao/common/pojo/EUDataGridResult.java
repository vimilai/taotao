package com.taotao.common.pojo;

import java.util.List;

/**
 * 存放easyui datagrid插件的bean
 * 
 * @author user
 *
 */
public class EUDataGridResult {
	private long total ;
	
	private List<?> rows;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
