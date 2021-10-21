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
import com.testTask.dto.EmployeeDto;
import com.testTask.dto.EmployeeRegistrationDto;
import com.testTask.dto.EmployeeUpdateDto;
import com.testTask.dto.Page;
import com.testTask.exceptions.ServiceException;
import com.testTask.service.EmployeeService;

@RestController
@RequestMapping("/testTask/employees/")
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/all")
	public List<Page<EmployeeDto>> getAll(@RequestParam int pageSize, @RequestParam int pageAmount) throws ServiceException {
		return employeeService.getEmployeePages(pageSize, pageAmount);
	}

	@GetMapping("/employee")
	public EmployeeDto getById(@RequestParam int id) throws ServiceException {
		return employeeService.getById(id);
	}

	@PostMapping("/registration")
	public ResponseEntity<String> registration(@RequestBody EmployeeRegistrationDto employeeDto) {
		try {
			employeeService.create(employeeDto);
			return ResponseEntity.ok("Employee was registred");
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/employee/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		try {
			employeeService.delete(id);
			return ResponseEntity.ok("Employee was deleted");
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = "/department/update/{id}")
	public ResponseEntity<String> update(@PathVariable(value = "id") int id,
			@RequestBody EmployeeUpdateDto employeeUpdateDto) {
		try {
			employeeService.update(id, employeeUpdateDto);
			return ResponseEntity.ok("Employee updated");
		} catch (ServiceException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
