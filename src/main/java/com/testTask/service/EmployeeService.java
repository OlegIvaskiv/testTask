package com.testTask.service;

import java.util.List;
import com.testTask.dto.EmployeeDto;
import com.testTask.dto.EmployeeRegistrationDto;
import com.testTask.dto.EmployeeUpdateDto;
import com.testTask.dto.Page;
import com.testTask.exceptions.ServiceException;

public interface EmployeeService {
	
	EmployeeDto getById(int id) throws ServiceException;

	boolean delete(int id) throws ServiceException;

	boolean update(int id, EmployeeUpdateDto t) throws ServiceException;

	boolean create(EmployeeRegistrationDto employee) throws ServiceException;

	List<Page<EmployeeDto>> getEmployeePages(int pageSize, int pageAmount) throws ServiceException;


}
