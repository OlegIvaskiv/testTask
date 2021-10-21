package com.testTask.service;

import java.util.List;

import com.testTask.dto.DepartmentDto;
import com.testTask.dto.DepartmentRegistrationDto;
import com.testTask.dto.Page;
import com.testTask.exceptions.ServiceException;
import com.testTask.model.Department;

public interface DepartmentService {
	
	Department getById(int id) throws ServiceException;

	boolean delete(int id) throws ServiceException;

	Department getByName(String name) throws ServiceException;
	
	void update(int id, DepartmentDto departmentDto) throws ServiceException;

	boolean create(DepartmentRegistrationDto departmentDto) throws ServiceException;

	List<Page<Department>> getDepartmentPages(int pageSize, int pageAmount) throws ServiceException;
}
