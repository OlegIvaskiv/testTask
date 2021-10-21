package com.testTask.dto;

import java.util.List;

public class Page <T> {
	private int pageId;

	private List <T> elements;

	public List<T> getElements() {
		return elements;
	}

	public void setElements( List<T> elements) {
		this.elements = elements;
	}
	
	public Page(int id, List<T> elements) {
		this.pageId = id;
		this.elements = elements;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public Page() {
		super();
	}

	
	
}
