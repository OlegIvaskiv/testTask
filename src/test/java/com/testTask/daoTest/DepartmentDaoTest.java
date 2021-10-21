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
import com.testTask.dao.DepartmentDao;
import com.testTask.model.Department;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfiguration.class })
@SpringBootTest
public class DepartmentDaoTest {

	private DepartmentDao departmentDao;

	@Autowired
	public DepartmentDaoTest(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Department_By_Id_Then_True() {
		Department expectedDepartment = new Department();
		expectedDepartment.setId(1);
		expectedDepartment.setName("HR");

		assertDoesNotThrow(() -> {
			assertEquals(expectedDepartment, departmentDao.getById(1));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Get_Department_By_Name_Then_True() {
		Department expectedDepartment = new Department();
		expectedDepartment.setId(1);
		expectedDepartment.setName("HR");

		assertDoesNotThrow(() -> {
			assertEquals(expectedDepartment, departmentDao.getByName("HR"));
		});
	}
	
	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_GetAll_Departments_Then_True() {
		Department department1 = new Department(1, "HR");
		Department department2 = new Department(2, "QA");

		List<Department> expectedDepartmnets = new LinkedList<Department>();
		expectedDepartmnets.add(department1);
		expectedDepartmnets.add(department2);
		
		assertDoesNotThrow(() -> {
			assertEquals(expectedDepartmnets, departmentDao.getDepartments(2, 0));
		});
	}

	@Test
	@Sql({ "/test-tables.sql" })
	void When_Create_Department_Then_True() {
		Department department = new Department();
		department.setName("same name");
		
		assertDoesNotThrow(() -> {
			assertTrue(departmentDao.create(department));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Delete_Department_By_Id_Then_True() {
		assertDoesNotThrow(() -> {
			assertTrue(departmentDao.delete(2));
		});
	}

	@Test
	@Sql({ "/test-tables.sql", "/test-data.sql" })
	void When_Update_Departmnet_Then_True() {
		Department updatedDepartment = new Department(2, "BA");
		assertDoesNotThrow(() -> {
			assertTrue(departmentDao.update(updatedDepartment));
			assertEquals(updatedDepartment, departmentDao.getById(2));
		});

	}

}
