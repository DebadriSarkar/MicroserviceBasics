package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;

@RestController
@RequestMapping(value = "rest")
public class EmployeeController {

	public static Map<Integer, Employee> employeeRepository = new HashMap<Integer, Employee>();
	
	static {
		Employee employee1 = new Employee();
		employee1.setId(1);
		employee1.setName("Debadri Sarkar");
		employee1.setDesignation("SDE-1");
		
		Employee employee2 = new Employee();
		employee2.setId(2);
		employee2.setName("Janty Sarkar");
		employee2.setDesignation("SDE-2");
		
		employeeRepository.put(1, employee1);
		employeeRepository.put(2, employee2);
	}
	
	
	@RequestMapping(value = "search-by-id/{id}",method=RequestMethod.GET,headers="Content-type=application/json")
	public ResponseEntity<Employee> searchById(@PathVariable int id){
		return new ResponseEntity<Employee>(employeeRepository.get(id), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "create-employee",method = RequestMethod.POST,headers="Content-type=application/json")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee){
		employeeRepository.put(3, employee);
		return new ResponseEntity<Object>("Successfully created", HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "update-employee",method = RequestMethod.PUT,headers="Content-type=application/json")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee,@RequestParam int id){
		employeeRepository.get(id);
		employeeRepository.put(id, employee);
		return new ResponseEntity<Object>("Successfully updated", HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "find-all-employee",method = RequestMethod.GET,headers="Content-type=application/json")
	public ResponseEntity<Object> findAll(){
		return new ResponseEntity<Object>(employeeRepository.values(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "delete-employee/{id}",method = RequestMethod.DELETE,headers="Content-type=application/json")
	public ResponseEntity<Object> deleteEmployee(@PathVariable int id){
		employeeRepository.remove(id);
		return new ResponseEntity<Object>("Successfully deleted", HttpStatus.OK);
	}
	
}
