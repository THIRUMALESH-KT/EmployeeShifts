package com.eidiko.service;

import java.util.List;

import com.eidiko.entity.Employee;
import com.eidiko.entity.ShiftTiming;
import com.eidiko.exception.UserNotFoundException;
import com.eidiko.userRequest.EmployeUserRequest;
import com.eidiko.userRequest.ShiftTimingUserRequest;

public interface EmployeeService {

	public List<Employee> getAllActiveEmployees(Long id) throws UserNotFoundException ;
	 public List<Employee> getAllEmployees(Long id) throws UserNotFoundException;
	 public Employee getEmployeeById(Long id,Long Hr) throws UserNotFoundException;
	 public Employee saveEmployee(Long Hr,EmployeUserRequest employee) throws UserNotFoundException;

	 public Employee updateEmployee(Long id,Long Hr, EmployeUserRequest updatedEmployee) throws UserNotFoundException;
	 public Employee deleteEmployee(Long id,Long Hr) throws UserNotFoundException;
	 public Employee addShiftTimingToEmployee(Long employeeId,Long hr, ShiftTimingUserRequest shiftTiming) throws UserNotFoundException;
	 public List<ShiftTiming> getShiftTimingsByEmployee(Long employeeId,Long Hr) throws UserNotFoundException;
}
