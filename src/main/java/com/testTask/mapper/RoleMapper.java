package com.testTask.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.testTask.model.Role;
@Component
public class RoleMapper implements RowMapper<Role>{

	@Override
	public Role mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Role role = new Role();
		role.setId(resultSet.getInt("rl_id"));
		role.setName(resultSet.getString("rl_name"));
		return role;
	}

}
