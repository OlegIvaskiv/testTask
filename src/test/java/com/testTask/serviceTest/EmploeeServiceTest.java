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
import com.testTask.dao.EmployeeDao;
import com.testTask.dto.EmployeeDto;
import com.testTask.dto.Page;
import com.testTask.exceptions.ServiceException;
import com.testTask.model.Department;
import com.testTask.model.Employee;
import com.testTask.model.Role;
import com.testTask.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmploeeServiceTest {

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	private EmployeeDao employeeDao;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void When_Get_Employee_By_Not_Existing_Id_Then_Get_Exception() {
		assertThrows(Exception.class, () -> {
			when(employeeServiceImpl.getById(133)).thenThrow(new ServiceException("can not get employee by this id"));
		});
	}
	
	@Test
	public void When_Delete_Employee_By_Not_Existing_Id_Then_Exception() {
		assertThrows(Exception.class, () -> {
			when(employeeServiceImpl.delete(16)).thenThrow(new ServiceException("can not delete employee by this id"));
		});
	}

	@Test
	void When_Get_Employees_Then_True() {

		Department department = new Department(1, "HR");
		Role role = new Role(1, "Employee");

		EmployeeDto employee1 = new EmployeeDto(new Employee(1, "Tom", true, department, "pass", role));
		EmployeeDto employee2 = new EmployeeDto(new Employee(2, "Elon", true, department, "pass", role));
		EmployeeDto employee3 = new EmployeeDto(new Employee(3, "Arthur", true, department, "pass", role));

		List<EmployeeDto> expectedEmployees = new LinkedList<EmployeeDto>();
		
		expectedEmployees.add(employee1);
		expectedEmployees.add(employee2);
		expectedEmployees.add(employee3);

		Page<EmployeeDto> page = new Page<EmployeeDto>(1, expectedEmployees);

		List<Page<EmployeeDto>> pages = new ArrayList<>();

		pages.add(page);

		

		assertDoesNotThrow(() -> {
			Mockito.lenient().when(employeeServiceImpl.getEmployeePages(3, 1)).thenReturn(pages);
		});
	}

}
