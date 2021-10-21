package com.testTask.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.testTask.model.Department;

@Component
public class DepartmentMapper implements RowMapper<Department> {
	@Override
	public Department mapRow(ResultSet resultSet, int i) throws SQLException {
		Department department = new Department();
		department.setId(resultSet.getInt("dp_id"));
		department.setName(resultSet.getString("dp_name"));
		return department;
	}
}
