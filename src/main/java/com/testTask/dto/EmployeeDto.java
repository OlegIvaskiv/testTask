package com.testTask.dto;

import java.util.ArrayList;
import java.util.List;

import com.testTask.model.Employee;

public class EmployeeDto {

	private int id;
	private String name;
	private Boolean active;
	private String departmentName;

	public EmployeeDto(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.active = employee.getActive();
		this.departmentName = employee.getDepartment().getName();
	}

	public EmployeeDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDepartment() {
		return departmentName;
	}

	public void setDepartment(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public List <EmployeeDto> toEmployeeDtos(List <Employee> employees){
		List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
		for (Employee employee : employees) {
			if (employee != null) {
				employeeDtos.add(new EmployeeDto(employee));
			}
		}
		return employeeDtos;
	}

}
