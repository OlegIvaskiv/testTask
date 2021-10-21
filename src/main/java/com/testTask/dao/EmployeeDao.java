package com.testTask.dao;

import java.util.List;
import com.testTask.exceptions.DaoException;
import com.testTask.model.Employee;

public interface EmployeeDao {

	Employee getById(int id) throws DaoException;
	
	Employee getByName(String name) throws DaoException;

	boolean delete(int id) throws DaoException;

	boolean update(Employee t) throws DaoException;

	boolean create(Employee t) throws DaoException;

	List<Employee> getEmployees(int pageSize, int pageOffset) throws DaoException;

}
