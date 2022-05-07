package com.testTask.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.testTask.config.security.CustomEmployeeDetails;
import com.testTask.dao.DepartmentDao;
import com.testTask.dao.EmployeeDao;
import com.testTask.dao.RoleDao;
import com.testTask.dto.EmployeeDto;
import com.testTask.dto.EmployeeRegistrationDto;
import com.testTask.dto.EmployeeUpdateDto;
import com.testTask.dto.Page;
import com.testTask.exceptions.DaoException;
import com.testTask.exceptions.ServiceException;
import com.testTask.model.Department;
import com.testTask.model.Employee;
import com.testTask.model.Role;
import com.testTask.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

	private EmployeeDao employeeDao;
	private DepartmentDao departmentDao;
	private RoleDao roleDao;

	private Employee authorizedEmployee;

	@Autowired
	public EmployeeServiceImpl(EmployeeDao employeeDao, DepartmentDao departmentDao, RoleDao roleDao) {
		this.employeeDao = employeeDao;
		this.departmentDao = departmentDao;
		this.roleDao = roleDao;
	}

	public EmployeeServiceImpl() {
	}

	@Override
	public EmployeeDto getById(int id) throws ServiceException {
		try {
			Employee employee = employeeDao.getById(id);
			return new EmployeeDto(employee);
		} catch (DaoException e) {
			throw new ServiceException("can not find employee by id : " + id);
		}
	}

	@Override
	public List<Page<EmployeeDto>> getEmployeePages(int pageSize, int pageAmount) throws ServiceException {
		try {
			List<Page<EmployeeDto>> pages = new ArrayList<>();
			EmployeeDto employeeDto = new EmployeeDto();

			for (int i = 0; i < pageAmount; i++) {

				List<Employee> employees = employeeDao.getEmployees(pageSize, pageSize * i);
				List<EmployeeDto> employeeDtos = employeeDto.toEmployeeDtos(employees);

				if (employeeDtos.size() == 0) {
					break;
				} else if (employeeDtos.size() < pageSize && employeeDtos.size() > 0) {
					Page<EmployeeDto> page = new Page<EmployeeDto>();
					page.setPageId(i + 1);
					page.setElements(employeeDtos);
					pages.add(page);
					break;
				} else {
					Page<EmployeeDto> page = new Page<EmployeeDto>();
					page.setPageId(i + 1);
					page.setElements(employeeDtos);
					pages.add(page);
				}
			}

			return pages;

		} catch (DaoException e) {
			throw new ServiceException("can not find employees ");
		}
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		try {
			if (employeeDao.getById(id) == null) {
				throw new ServiceException("can not find employee by id : " + id);
			}
			return employeeDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("can not delete employee by id : " + id);
		}
	}

	@Override
	public boolean update(int id, EmployeeUpdateDto employeeDto) throws ServiceException {
		try {
			Employee employee = employeeDao.getById(id);
			
			Department department = departmentDao.getByName(employeeDto.getDepartmentName());

			employee.setName(employeeDto.getName());
			employee.setActive(employeeDto.getActive());
			employee.setName(employeeDto.getName());
			employee.setDepartment(department);

			return employeeDao.update(employee);
		} catch (DaoException e) {
			throw new ServiceException("can find department by name or employee by id ");
		}
	}

	@Override
	public boolean create(EmployeeRegistrationDto employeeDto) throws ServiceException {
		try {
			Employee employee = new Employee();
			Department department = departmentDao.getByName(employeeDto.getDepartmentName());

			Role role = roleDao.getByName("Employee");

			employee.setName(employeeDto.getName());

			employee.setDepartment(department);
			employee.setActive(true);
			employee.setPassword(employeeDto.getPassword());
			employee.setRole(role);

			return employeeDao.create(employee);
		} catch (DaoException e) {
			throw new ServiceException("can not create employee ");
		}
	}

	public Employee getAuthorizedEmployee() {
		return authorizedEmployee;
	}

	public void setAuthorizedEmployee(Employee authorizedEmployee) {
		this.authorizedEmployee = authorizedEmployee;
	}

	@Override
	public UserDetails loadUserByUsername(String employeeName) {

		Employee employee;
		try {
			employee = employeeDao.getByName(employeeName);
		} catch (DaoException e) {
			throw new UsernameNotFoundException("Emloyee with name : " + employeeName + " was not found");
		}

		if (employee != null) {
			setAuthorizedEmployee(employee);
			return new CustomEmployeeDetails(employee);
		} else {
			throw new UsernameNotFoundException("Emloyee with name : " + employeeName + " was not found");
		}
	}

}
