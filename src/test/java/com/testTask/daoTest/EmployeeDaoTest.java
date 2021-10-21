package com.testTask.daoTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.testTask.config.AppConfiguration;
import com.testTask.dao.EmployeeDao;
import com.testTask.model.Department;
import com.testTask.model.Employee;
import com.testTask.model.Role;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfiguration.class })
@SpringBootTest
public class EmployeeDaoTest {

	private EmployeeDao employeeDao;

	@Autowired
	public EmployeeDaoTest(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Employee_By_Id_Then_True() {
		Employee expectedEmployee = new Employee();
		Department expectedDepartment = new Department();

		expectedDepartment.setId(1);
		expectedDepartment.setName("HR");

		expectedEmployee.setId(1);
		expectedEmployee.setName("Tom");
		expectedEmployee.setPassword("pass");
		expectedEmployee.setActive(true);
		expectedEmployee.setDepartment(expectedDepartment);

		assertDoesNotThrow(() -> {
			assertEquals(expectedEmployee, employeeDao.getById(1));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_GetAll_Employees_Then_True() {
		Department department = new Department(1, "HR");
		Role role = new Role(1, "Employee");

		Employee employee1 = new Employee(1, "Tom", true, department, "pass", role);
		Employee employee2 = new Employee(2, "Elon", true, department, "pass", role);
		Employee employee3 = new Employee(3, "Arthur", true, department, "pass", role);

		List<Employee> expectedEmployees = new LinkedList<Employee>();
		expectedEmployees.add(employee1);
		expectedEmployees.add(employee2);
		expectedEmployees.add(employee3);

		assertDoesNotThrow(() -> {
			assertEquals(expectedEmployees, employeeDao.getEmployees(3, 0));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Create_Employee_Then_True() {
		Department department = new Department(1, "HR");
		Role role = new Role(1, "Employee");
		Employee employee = new Employee(12, "Tomas", true, department, "somePass", role);

		assertDoesNotThrow(() -> {
			assertTrue(employeeDao.create(employee));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Employee_By_Id_Then_True() {
		assertDoesNotThrow(() -> {
			assertTrue(employeeDao.delete(3));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Employee_Then_True() {
		Department department = new Department(1, "HR");
		Role role = new Role(1, "Employee");
		Employee updatedEmployee = new Employee(1, "Tomas", true, department, "passwordPassword", role);
		assertDoesNotThrow(() -> {
			assertTrue(employeeDao.update(updatedEmployee));
			assertEquals(updatedEmployee, employeeDao.getById(1));
		});

	}

}
