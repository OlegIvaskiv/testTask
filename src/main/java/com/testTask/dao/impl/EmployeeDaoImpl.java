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
import com.testTask.dao.EmployeeDao;
import com.testTask.exceptions.DaoException;
import com.testTask.mapper.EmployeeMapper;
import com.testTask.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private EmployeeMapper employeeMapper;

	private static final String SQL_FIND_EMPLOYEE_BY_ID = "SELECT"
			+ " tbl_employees.emp_id,"
			+ " tbl_employees.emp_name, tbl_employees.emp_active,"
			+ " tbl_employees.emp_password, tbl_departments.dp_id,"
			+ " tbl_departments.dp_name, tbl_roles.rl_id, tbl_roles.rl_name FROM tbl_employees"
			+ " LEFT JOIN tbl_departments ON tbl_employees.dp_id = tbl_departments.dp_id"
			+ " LEFT JOIN tbl_roles ON tbl_employees.rl_id = tbl_roles.rl_id "
			+ " WHERE tbl_employees.emp_id = ?;";
	
	private static final String SQL_FIND_EMPLOYEE_BY_NAME = "SELECT"
			+ " tbl_employees.emp_id,"
			+ " tbl_employees.emp_name, tbl_employees.emp_active,"
			+ " tbl_employees.emp_password, tbl_departments.dp_id,"
			+ " tbl_departments.dp_name, tbl_roles.rl_id, tbl_roles.rl_name FROM tbl_employees"
			+ " LEFT JOIN tbl_departments ON tbl_employees.dp_id = tbl_departments.dp_id"
			+ " LEFT JOIN tbl_roles ON tbl_employees.rl_id = tbl_roles.rl_id "
			+ " WHERE tbl_employees.emp_name = ?;";
	private static final String SQL_DELETE_EMPLOYEE = "DELETE"
			+ " FROM tbl_employees"
			+ " WHERE"
			+ " tbl_employees.emp_id = ?;";
	private static final String SQL_UPDATE_EMPLOYEE = "UPDATE"
			+ " tbl_employees"
			+ " SET  "
			+ "emp_name = ?,"
			+ " emp_active = ?,"
			+ " dp_id = ?,"
			+ " emp_password = ? ,"
			+ "rl_id = ?"
			+ " WHERE tbl_employees.emp_id = ?;";
	private static final String SQL_INSERT_EMPLOYEE = "INSERT INTO"
			+ " tbl_employees( emp_name, emp_active, dp_id, emp_password, rl_id)"
			+ " VALUES(?,?,?,?,?);";
	
	private static final String SQL_GET_EMPLOYEES = "SELECT tbl_employees.emp_id,"
	        + "tbl_employees.emp_name,"
			+ "tbl_employees.emp_active, " 
	        + "tbl_employees.emp_password," 
			+ "tbl_departments.dp_id, "
			+ "tbl_departments.dp_name,"
			+ "tbl_roles.rl_id, "
			+ " tbl_roles.rl_name " 
			+ "FROM tbl_employees "
			+ " LEFT JOIN tbl_departments "
			+ "ON tbl_employees.dp_id = tbl_departments.dp_id "
			+ "LEFT JOIN tbl_roles ON tbl_employees.rl_id = tbl_roles.rl_id "
			+ " ORDER BY tbl_employees.emp_id LIMIT ? "
			+ " OFFSET  ?; ";

	@Autowired
	public EmployeeDaoImpl(DataSource dataSource, EmployeeMapper employeeMapper) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.employeeMapper = employeeMapper;
	}

	@Override
	public Employee getById(int id) throws DaoException {
		LOGGER.debug("getting employee by id : {}", id);
		try {
			Employee employee = jdbcTemplate.queryForObject(SQL_FIND_EMPLOYEE_BY_ID, employeeMapper, new Object[] { id });
			LOGGER.debug("returned employee : {}", employee);
			return employee;
		} catch (DataAccessException e) {
			throw new DaoException("wrong id");
		}
	}
	
	@Override
	public Employee getByName(String name) throws DaoException {
		LOGGER.debug("getting employee by name : {}", name);
		try {
			Employee employee = jdbcTemplate.queryForObject(SQL_FIND_EMPLOYEE_BY_NAME, employeeMapper, new Object[] { name });
			LOGGER.debug("returned employee : {}", employee);
			return employee;
		} catch (DataAccessException e) {
			throw new DaoException("wrong name");
		}
	}

	@Override
	public List<Employee> getEmployees(int pageSize, int pageOffset) throws DaoException {
		LOGGER.debug("getting employees");
		List<Employee> employees = Collections.emptyList();
		try {
			employees = jdbcTemplate.query(SQL_GET_EMPLOYEES, employeeMapper, new Object[] { pageSize, pageOffset });
			LOGGER.debug("returned employees : {}", employees);
			return employees;
		} catch (DataAccessException e) {
			throw new DaoException("can't get emloyees");
		}
	}

	@Override
	public boolean delete(int id) throws DaoException {
		LOGGER.debug("deletion employee by id : {}", id);
		try {
			return jdbcTemplate.update(SQL_DELETE_EMPLOYEE, id) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("employee is not deleted");
		}
	}

	@Override
	public boolean update(Employee employee) throws DaoException {
		LOGGER.debug("updating employee : {}", employee);
		try {
			return jdbcTemplate.update(SQL_UPDATE_EMPLOYEE, employee.getName(), employee.getActive(),
					employee.getDepartment().getId(), employee.getPassword(), employee.getId(), employee.getRole().getId()) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("employee is not updated");
		}
	}

	@Override
	public boolean create(Employee employee) throws DaoException {
		LOGGER.debug("creating employee : {}", employee);
		try {
			return jdbcTemplate.update(SQL_INSERT_EMPLOYEE, employee.getName(), employee.getActive(),
					employee.getDepartment().getId(), employee.getPassword(), employee.getRole().getId()) > 0;
		} catch (DataAccessException e) {
			throw new DaoException("employee is not created");
		}
	}

}
