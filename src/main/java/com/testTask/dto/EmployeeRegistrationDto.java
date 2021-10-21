package com.testTask.dto;

public class EmployeeRegistrationDto {
	private String name;
	private String departmentName;
	private String password;
	
	public EmployeeRegistrationDto( String name, String depatmentName, String password) {
		this.name = name;
		this.departmentName = depatmentName;
		this.password = password;
	}

	public EmployeeRegistrationDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
