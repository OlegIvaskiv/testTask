package com.testTask.dto;

import com.testTask.model.Department;

public class DepartmentDto {
	
	private String name;

	public DepartmentDto(Department department) {
		this.name = department.getName();
	}

	public DepartmentDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
