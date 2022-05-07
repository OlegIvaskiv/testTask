package com.testTask.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.testTask.dao.DepartmentDao;
import com.testTask.dto.DepartmentDto;
import com.testTask.dto.DepartmentRegistrationDto;
import com.testTask.dto.Page;
import com.testTask.exceptions.DaoException;
import com.testTask.exceptions.ServiceException;
import com.testTask.model.Department;
import com.testTask.service.DepartmentService;

@Component
public class DepartmentServiceImpl implements DepartmentService {
	
	private DepartmentDao departmentDao;

	@Autowired
	public DepartmentServiceImpl(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public Department getById(int id) throws ServiceException {
		try {
			return departmentDao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException("can not find dapartment by id : " + id);
		}
	}

	@Override
	public List<Page<Department>> getDepartmentPages(int pageSize, int pageAmount) throws ServiceException {
		try {
			List<Page<Department>> pages = new ArrayList<>();

			for (int i = 0; i < pageAmount; i++) {
				List<Department> departments = departmentDao.getDepartments(pageSize, pageSize * i);

				if (departments.size() == 0) {
					break;
				} else if (departments.size() < pageSize && departments.size() > 0) {
					Page<Department> page = new Page<Department>();
					page.setPageId(i + 1);
					page.setElements(departments);
					pages.add(page);
					break;
				} else {
					Page<Department> page = new Page<Department>();
					page.setPageId(i + 1);
					page.setElements(departments);
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
			if (departmentDao.getById(id) == null) {
				throw new ServiceException("can not find dapartment by id : " + id);
			}

			return departmentDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("can not delete dapartment by id : " + id);
		}
	}

	@Override
	public void update(int id, DepartmentDto departmentDto) throws ServiceException {
		try {
			Department department = departmentDao.getById(id);
			if (department == null) {
				throw new ServiceException("cant update because cant find department with this id :" + id);
			} else {
				department.setName(departmentDto.getName());
				departmentDao.update(department);
			}
		} catch (DaoException e) {
			throw new ServiceException("can not update, something went wrong");
		}
	}

	@Override
	public boolean create(DepartmentRegistrationDto departmentDto) throws ServiceException {
		try {
			Department department = new Department();
			department.setName(departmentDto.getDepartmentName());
			return departmentDao.create(department);
		} catch (DaoException e) {
			throw new ServiceException("can not create department");
		}
	}
	

	@Override
	public Department getByName(String name) throws ServiceException {
		try {
			return departmentDao.getByName(name);
		} catch (DaoException e) {
			throw new ServiceException("can not find dapartment by name : " + name);
		}
	}

}
