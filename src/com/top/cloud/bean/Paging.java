package com.top.cloud.bean;

import java.util.List;

public class Paging {
	private List<TopCloudFile> list;
	private int allPage;
	public Paging() {
	}
	public Paging(List<TopCloudFile> list, int allPage) {
		super();
		this.list = list;
		this.allPage = allPage;
	}
	public List<TopCloudFile> getList() {
		return list;
	}
	public void setList(List<TopCloudFile> list) {
		this.list = list;
	}
	public int getAllPage() {
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	@Override
	public String toString() {
		return "Paging [list=" + list + ", allPage=" + allPage + "]";
	}
}
