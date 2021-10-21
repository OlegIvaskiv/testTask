package com.testTask.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.testTask.model.Employee;

@Component
public class EmployeeMapper implements RowMapper<Employee> {
	private DepartmentMapper departmentMapper;
	private RoleMapper roleMapper;
	
	@Autowired
	public EmployeeMapper(DepartmentMapper departmentMapper, RoleMapper roleMapper) {
		this.departmentMapper = departmentMapper;
		this.roleMapper = roleMapper;
	}

	@Override
	public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
		Employee employee = new Employee();
		employee.setId(resultSet.getInt("emp_id"));
		employee.setName(resultSet.getString("emp_name"));
		employee.setActive(resultSet.getBoolean("emp_active"));
		employee.setPassword(resultSet.getString("emp_password"));
		employee.setDepartment(departmentMapper.mapRow(resultSet, i));
		employee.setRole(roleMapper.mapRow(resultSet, i));
		return employee;
	}
}
