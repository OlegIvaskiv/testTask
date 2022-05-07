package com.testTask.dao;

import java.util.List;
import com.testTask.exceptions.DaoException;
import com.testTask.model.Department;
import org.springframework.stereotype.Repository;

public interface DepartmentDao {

	Department getById(int id) throws DaoException;

	boolean delete(int id) throws DaoException;

	boolean update(Department department) throws DaoException;

	boolean create(Department department) throws DaoException;

	Department getByName(String name) throws DaoException;

	List<Department> getDepartments(int pageSize, int pageOffset) throws DaoException;
}
