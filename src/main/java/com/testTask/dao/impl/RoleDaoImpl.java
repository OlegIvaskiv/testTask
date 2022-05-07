package com.testTask.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.testTask.dao.RoleDao;
import com.testTask.exceptions.DaoException;
import com.testTask.mapper.RoleMapper;
import com.testTask.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private RoleMapper roleMapper;

	@Autowired
	public RoleDaoImpl(JdbcTemplate jdbcTemplate, RoleMapper roleMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.roleMapper = roleMapper;
	}

	private static final String SQL_FIND_ROLE_BY_ID = "SELECT tbl_roles.rl_id, tbl_roles.rl_name FROM tbl_roles WHERE rl_id = ?;";
	private static final String SQL_FIND_ROLE_BY_NAME = "SELECT tbl_roles.rl_id, tbl_roles.rl_name FROM tbl_roles WHERE rl_name = ?;";

	@Override
	public Role getById(int id) throws DaoException {
		LOGGER.debug("getting role by id : {}", id);
		try {
			Role role = jdbcTemplate.queryForObject(SQL_FIND_ROLE_BY_ID, roleMapper, new Object[] { id });
			LOGGER.debug("returned role : {}", role);
			return role;
		} catch (DataAccessException e) {
			throw new DaoException("wrong id");
		}
	}
	

	@Override
	public Role getByName(String name) throws DaoException {
		LOGGER.debug("getting role by name : {}",  name);
		try {
			Role role = jdbcTemplate.queryForObject(SQL_FIND_ROLE_BY_NAME, roleMapper, new Object[] { name });
			LOGGER.debug("returned role : {}", role);
			return role;
		} catch (DataAccessException e) {
			throw new DaoException("wrong name");
		}
	}

}
