package com.eidiko.service;

import java.util.List;

import com.eidiko.entity.Employee;
import com.eidiko.entity.ShiftTiming;
import com.eidiko.exception.UserNotFoundException;
import com.eidiko.userRequest.EmployeUserRequest;
import com.eidiko.userRequest.ShiftTimingUserRequest;

public interface EmployeeService {

	public List<Employee> getAllActiveEmployees() throws UserNotFoundException ;
	public List<Employee> getAllInActiveEmployees() throws UserNotFoundException;
	 public List<Employee> getAllEmployees() throws UserNotFoundException;
	 public Employee getEmployeeById(Long id) throws UserNotFoundException;
	 public Employee saveEmployee(EmployeUserRequest employee) throws UserNotFoundException;

	 public Employee updateEmployee(Long id,EmployeUserRequest updatedEmployee) throws UserNotFoundException;
	 public Employee deleteEmployee(Long id) throws UserNotFoundException;
	 public Employee addShiftTimingToEmployee(Long employeeId,List<ShiftTimingUserRequest> shiftTiming) throws UserNotFoundException;
	 public Employee getShiftTimingsByEmployee(Long employeeId) throws UserNotFoundException;
	public List<ShiftTiming> getAllEmployeShifts()throws UserNotFoundException;
	public ShiftTiming updateShift(Long id, ShiftTimingUserRequest shiftTiming) throws UserNotFoundException;
	public List<ShiftTiming> getAllDayShifts();
	public List<ShiftTiming> getAllNightShifts();
	public ShiftTiming getShiftByid(Long id) throws UserNotFoundException;
	public List<?> gettodatShiftEmployes();
}
