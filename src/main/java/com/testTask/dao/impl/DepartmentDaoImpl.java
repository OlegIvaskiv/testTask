package com.testTask.dao.impl;

import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.testTask.dao.DepartmentDao;
import com.testTask.exceptions.DaoException;
import com.testTask.mapper.DepartmentMapper;
import com.testTask.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private DepartmentMapper departmentMapper;

	private static final String SQL_FIND_DEPARTMENT_BY_ID = "SELECT" 
			+ " tbl_departments.dp_id,"	
			+ " tbl_departments.dp_name FROM tbl_departments"
			+ " WHERE tbl_departments.dp_id = ?;";

	private static final String SQL_FIND_DEPARTMENT_BY_NAME = "SELECT"
			+ " tbl_departments.dp_id,"
			+ " tbl_departments.dp_name FROM tbl_departments"
			+ " WHERE tbl_departments.dp_name = ?;";
	private static final String SQL_DELETE_DEPARTMENT = "DELETE" + " FROM tbl_departments" + " WHERE"
			+ " tbl_departments.dp_id = ?;";
	private static final String SQL_UPDATE_DEPARTMENT = "UPDATE" + " tbl_departments" + " SET  " + " dp_name = ?"
			+ " WHERE dp_id = ?;";
	private static final String SQL_INSERT_DEPARTMENT = "INSERT INTO tbl_departments(dp_name) "
			+ "VALUES(?);";
	private static final String SQL_GET_DEPARTMENTS = "SELECT " + " tbl_departments.dp_id, "
			+ " tbl_departments.dp_name " + " FROM tbl_departments " + " ORDER BY tbl_departments.dp_id LIMIT ?"
			+ " OFFSET  ? ;";

	@Autowired
	public DepartmentDaoImpl(DataSource dataSource, DepartmentMapper departmentMapper) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.departmentMapper = departmentMapper;
	}

	@Override
	public Department getById(int id) throws DaoException {
		LOGGER.debug("getting department by id : {}", id);
		try {
			Department department = jdbcTemplate.queryForObject(SQL_FIND_DEPARTMENT_BY_ID, departmentMapper,
					new Object[] { id });
			LOGGER.debug("returned department : {}", department);
			return department;
		} catch (DataAccessException e) {
			throw new DaoException("wrong id");
		}
	}

	@Override
	public Department getByName(String name) throws DaoException {
		LOGGER.debug("getting department by name : {}", name);
		try {
			Department department = jdbcTemplate.queryForObject(SQL_FIND_DEPARTMENT_BY_NAME, departmentMapper,
					new Object[] { name });
			LOGGER.debug("returned department : {}", department);
			return department;
		} catch (DataAccessException e) {
			throw new DaoException("wrong name");
		}
	}

	@Override
	public List<Department> getDepartments(int pageSize, int pageOffset) throws DaoException {
		LOGGER.debug("getting departments");
		List<Department> departments = Collections.emptyList();
		try {
			departments = jdbcTemplate.query(SQL_GET_DEPARTMENTS, departmentMapper,
					new Object[] { pageSize, pageOffset });
			LOGGER.debug("returned departments : {}", departments);
			return departments;
		} catch (DataAccessException e) {
			throw new DaoException("can't get departments");
		}
	}

	@Override
	public boolean delete(int id) throws DaoException {
		LOGGER.debug("deletion department by id : {}", id);
		try {
			return jdbcTemplate.update(SQL_DELETE_DEPARTMENT, id) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("department is not deleted");
		}
	}

	@Override
	public boolean update(Department department) throws DaoException {
		LOGGER.debug("updating department : {}", department);
		try {
			return jdbcTemplate.update(SQL_UPDATE_DEPARTMENT, department.getName(), department.getId()) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("department is not updated");
		}
	}

	@Override
	public boolean create(Department department) throws DaoException {
		LOGGER.debug("creating department : ", department);
		try {
			return jdbcTemplate.update(SQL_INSERT_DEPARTMENT, department.getName()) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("can not create department");
		}
	}

}
