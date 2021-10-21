package com.testTask.dto;

public class DepartmentRegistrationDto {

	private String departmentName;
	
	

	public DepartmentRegistrationDto() {
	}

	public DepartmentRegistrationDto(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


}
