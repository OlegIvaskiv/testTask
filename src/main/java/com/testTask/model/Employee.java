package com.testTask.model;

import java.util.Objects;

public class Employee {

	private int id;
	private String name;
	private Boolean active;
	private Department department;
	private String password;
	private Role role;

	public Employee(int id, String name, Boolean active, Department department, String password, Role role) {
		this.id = id;
		this.name = name;
		this.active = active;
		this.department = department;
		this.password = password;
		this.role = role;
	}

	public Employee(String name, Boolean active, Department department, String password) {
		this.name = name;
		this.active = active;
		this.department = department;
		this.password = password;
	}

	public Employee() {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, department, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(active, other.active) && Objects.equals(department, other.department)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	public boolean hasRole(String roleName) {
		return role.getName().equals(roleName);
	}

}
