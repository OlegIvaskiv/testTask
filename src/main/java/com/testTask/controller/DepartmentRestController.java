package com.testTask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.testTask.dto.DepartmentDto;
import com.testTask.dto.DepartmentRegistrationDto;
import com.testTask.dto.Page;
import com.testTask.exceptions.ServiceException;
import com.testTask.model.Department;
import com.testTask.service.DepartmentService;

@RestController
@RequestMapping("/testTask/departments/")
public class DepartmentRestController {
	
	private DepartmentService departmentService;

	@Autowired
	public DepartmentRestController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping("/all")
	public List<Page<Department>> getAll(@RequestParam int pageSize, @RequestParam int pageOffset) throws ServiceException {
		return departmentService.getDepartmentPages(pageSize, pageOffset);
	}

	@GetMapping("/departmentById")
	public Department getById(@RequestParam int id) throws ServiceException {
		return departmentService.getById(id);
	}

	@GetMapping("/departmentByName")
	public Department getByName(@RequestParam String name) throws ServiceException {
		return departmentService.getByName(name);
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody DepartmentRegistrationDto departmentDto) {
		try {
			departmentService.create(departmentDto);
			return ResponseEntity.ok("Department created");
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().body("can not create");
		}
	}


	@DeleteMapping("/department/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		try {
			departmentService.delete(id);
			return ResponseEntity.ok("Department deleted");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Can't delete this department");
		}
	}

	@PutMapping(value = "/department/update/{id}")
	public ResponseEntity<String> update(@PathVariable(value = "id") int id, @RequestBody DepartmentDto departmentDto) {
		try {
			departmentService.update(id, departmentDto);
			return ResponseEntity.ok("Department updated");
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
