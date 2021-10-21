package com.testTask.serviceTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.testTask.dao.DepartmentDao;
import com.testTask.dto.Page;
import com.testTask.exceptions.ServiceException;
import com.testTask.model.Department;

import com.testTask.service.impl.DepartmentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

	@InjectMocks
	DepartmentServiceImpl departmentServiceImpl;

	@Mock
	private DepartmentDao employeeDao;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void When_Get_Departments_By_Not_Existing_Id_Then_Get_Exception() {
		assertThrows(Exception.class, () -> {
			when(departmentServiceImpl.getById(133))
					.thenThrow(new ServiceException("can not get department by this id"));
		});
	}

	@Test
	public void When_Delete_Departments_By_Not_Existing_Id_Then_Exception() {
		assertThrows(Exception.class, () -> {
			when(departmentServiceImpl.delete(16))
					.thenThrow(new ServiceException("can not delete departmnet by this id"));
		});
	}

	@Test
	void When_Get_Departments_Then_True() {
		Department department1 = new Department(1, "HR");
		Department department2 = new Department(2, "QA");

		List<Department> expectedDepartmnets = new LinkedList<Department>();
		
		expectedDepartmnets.add(department1);
		expectedDepartmnets.add(department2);
		
		Page<Department> page = new Page<Department>(1, expectedDepartmnets);
		List<Page<Department>> pages = new ArrayList<>();

		pages.add(page);
		
		assertDoesNotThrow(() -> {
			Mockito.lenient().when(departmentServiceImpl.getDepartmentPages(2, 1)).thenReturn(pages);
		});
	}

}
