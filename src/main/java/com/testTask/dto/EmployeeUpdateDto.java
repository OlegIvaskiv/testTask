package com.testTask.dto;

public class EmployeeUpdateDto {

	private String name;
	private Boolean active;
	private String departmentName;
	
	
	public EmployeeUpdateDto(String name, Boolean active, String departmentName) {
		this.name = name;
		this.active = active;
		this.departmentName = departmentName;
	}


	public EmployeeUpdateDto() {
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
