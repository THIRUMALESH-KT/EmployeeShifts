package com.eidiko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.entity.Employee;
import com.eidiko.entity.ShiftTiming;
import com.eidiko.exception.UserNotFoundException;
import com.eidiko.service.EmployeeService;
import com.eidiko.userRequest.EmployeUserRequest;
import com.eidiko.userRequest.ShiftTimingUserRequest;

import jakarta.validation.Valid;

@RestController
public class EmployeController {
	@Autowired
	private EmployeeService empservice;
	
	@GetMapping("/activeEmployes/{id}")
	public ResponseEntity<List<Employee>> getActiveEmployeesByHr(@Valid @PathVariable Long id) throws UserNotFoundException{
		//hello
		return new ResponseEntity<List<Employee>>(empservice.getAllActiveEmployees(id),HttpStatus.OK);
	}

	@GetMapping("/getAllEmployes/{id}")
	public ResponseEntity<List<Employee>> geAlltEmployeByHr(@Valid @PathVariable Long id) throws UserNotFoundException{
		return new ResponseEntity<List<Employee>>(empservice.getAllEmployees(id),HttpStatus.OK);
	}
	@GetMapping("/getById/{id}/{Hr}")
	public ResponseEntity<Employee> getEmployeeByHr(@PathVariable Long id,@PathVariable(required =false) Long Hr) throws UserNotFoundException{
		return new ResponseEntity<Employee>(empservice.getEmployeeById(id,Hr),HttpStatus.OK);
	}	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Employee> getEmployeeByEmployee(@PathVariable Long id,@PathVariable(required =false) Long Hr) throws UserNotFoundException{
		return new ResponseEntity<Employee>(empservice.getEmployeeById(id,Hr),HttpStatus.OK);
	}

	@PostMapping("/saveEmploye/{Hr}")
	public ResponseEntity<Employee> save(@Valid  @RequestBody EmployeUserRequest user,@PathVariable(required = false) Long Hr) throws UserNotFoundException{
		return new ResponseEntity<Employee>(empservice.saveEmployee(Hr,user),HttpStatus.CREATED);
	}
	@PutMapping("/updateEmploye/{id}/{Hr}")
	public ResponseEntity<Employee> updateByHr(@Valid @RequestBody EmployeUserRequest user,@PathVariable Long id,@PathVariable (required = false) Long Hr) throws UserNotFoundException{
		return new ResponseEntity<Employee>(empservice.updateEmployee(id,Hr, user),HttpStatus.OK);
	}@PutMapping("/updateEmploye/{id}")
	public ResponseEntity<Employee> updateByEmploye(@Valid @RequestBody EmployeUserRequest user,@PathVariable Long id,@PathVariable (required = false) Long Hr) throws UserNotFoundException{
		return new ResponseEntity<Employee>(empservice.updateEmployee(id,Hr, user),HttpStatus.OK);
	}
	@DeleteMapping("/deleteEmploye/{id}/{Hr}")
	public ResponseEntity<Employee> deleteEmployeByHr(@PathVariable Long id,@PathVariable(required = false) Long Hr ) throws UserNotFoundException{
		return new ResponseEntity<Employee>(empservice.deleteEmployee(id,Hr),HttpStatus.OK);
	}@DeleteMapping("/deleteEmploye/{id}")
	public ResponseEntity<Employee> deleteEmployeByEmploye(@PathVariable Long id,@PathVariable(required = false) Long Hr ) throws UserNotFoundException{
		return new ResponseEntity<Employee>(empservice.deleteEmployee(id,Hr),HttpStatus.OK);
	}
	@PostMapping("/addShiftTimings/{id}/{hr}")
	public ResponseEntity<Employee> addshiftByHr(@PathVariable Long id,@PathVariable(required = false) Long hr ,@Valid @RequestBody ShiftTimingUserRequest shiftTiming) throws UserNotFoundException{
		return new ResponseEntity<Employee>(empservice.addShiftTimingToEmployee(id,hr,  shiftTiming),HttpStatus.OK);
	}@PostMapping("/addShiftTimings/{id}")
	public ResponseEntity<Employee> addshift(@PathVariable Long id,@PathVariable(required = false) Long hr ,@RequestBody ShiftTimingUserRequest shiftTiming) throws UserNotFoundException{
		return new ResponseEntity<Employee>(empservice.addShiftTimingToEmployee(id,hr,  shiftTiming),HttpStatus.OK);
	}
	@GetMapping("/listShiftTimingById/{id}/{Hr}")
	public ResponseEntity<List<ShiftTiming>> listShiftByHr(@PathVariable Long id,@PathVariable(required = false) Long Hr) throws UserNotFoundException{
		return new ResponseEntity<List<ShiftTiming>>(empservice.getShiftTimingsByEmployee(id,Hr),HttpStatus.OK);
	}@GetMapping("/listShiftTimingById/{id}")
	public ResponseEntity<List<ShiftTiming>> listShiftByEmploye(@PathVariable Long id,@PathVariable(required = false) Long Hr) throws UserNotFoundException{
		return new ResponseEntity<List<ShiftTiming>>(empservice.getShiftTimingsByEmployee(id,Hr),HttpStatus.OK);
	}
}
