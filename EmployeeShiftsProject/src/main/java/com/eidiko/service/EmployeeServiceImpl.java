package com.eidiko.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eidiko.entity.Employee;
import com.eidiko.entity.ShiftTiming;
import com.eidiko.exception.UserNotFoundException;
import com.eidiko.repository.EmployeeRepository;
import com.eidiko.repository.ShiftTimingRepository;
import com.eidiko.userRequest.EmployeUserRequest;
import com.eidiko.userRequest.ShiftTimingUserRequest;

import jakarta.validation.Valid;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ShiftTimingRepository shiftTimingRepository;
	@Override
	public List<Employee> getAllActiveEmployees() throws UserNotFoundException {
		
		// TODO Auto-generated method stub
		return employeeRepository.findByStatusAndIsDeleted("active", false);
	}
	@Override
	public List<Employee> getAllInActiveEmployees()throws UserNotFoundException{
			return employeeRepository.findByStatusAndIsDeleted("inactive",true);
	}

	@Override
	public List<Employee> getAllEmployees() throws UserNotFoundException {
		// TODO Auto-generated method stub	
		
		List<Employee> list=employeeRepository.findAll();
		if(list!=null) return list;
		else throw new UserNotFoundException("Data is Empty");

	}

	@Override
	public Employee getEmployeeById(Long id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Employee emp=employeeRepository.findById(id).orElseThrow(()->new UserNotFoundException("id not found"));
		if(emp!=null) {
				emp.getShiftTimings().size();
			return emp;
		}
		else throw new UserNotFoundException("Not Found");
	}

	@Override
	public Employee saveEmployee(EmployeUserRequest user) throws UserNotFoundException {
		// TODO Auto-generated method stu
			//"inactive".equals(user.getStatus()) ? true : false
			Employee employe=new Employee(user.getId(), user.getName(), user.getEmail(), user.getDateJoin(), user.getMobile(),user.getStatus(),	"inactive".equals(user.getStatus()) ? true : false , user.getAbout());
			employeeRepository.save(employe);
			addShiftTimingToEmployee(employe.getId(),user.getShiftTimings());
			return employeeRepository.save(employe);
	}

	@Override
	public Employee updateEmployee(Long id, EmployeUserRequest user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Employee emp=getEmployeeById(id);
		if(emp!=null) {
			Employee employe=new Employee(user.getId(), user.getName(), user.getEmail(), user.getDateJoin(), user.getMobile(), user.getStatus(), "inactive".equals(user.getStatus()) ? true : false, user.getAbout());
			addShiftTimingToEmployee(employe.getId(),user.getShiftTimings());
			return employeeRepository.save(employe);
		}
		else throw new UserNotFoundException("employe id is not found");
	}

	@Override
	public Employee deleteEmployee(Long id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Employee existingEmployee=getEmployeeById(id);
		if (existingEmployee != null) {
	            existingEmployee.setStatus("inactive");
	            existingEmployee.setDeleted(true);
	            return employeeRepository.save(existingEmployee);
	        }
			else throw new UserNotFoundException("employe id is not found");

	}

	//@SuppressWarnings("null")
	@Override
	public Employee addShiftTimingToEmployee(Long employeeId,List<ShiftTimingUserRequest> shiftTiming) throws UserNotFoundException {
		// TODO Auto-generated method stub
		   Employee employee = getEmployeeById(employeeId);
		  List<ShiftTiming> listshift = new ArrayList<>();
	        if (employee != null) {
	        		 for (ShiftTimingUserRequest shiftTimingRequest : shiftTiming) {
	                     ShiftTiming shift = new ShiftTiming(
	                         shiftTimingRequest.getStartDate(),
	                         shiftTimingRequest.getEndDate(),
	                         shiftTimingRequest.getShiftStartTime(),
	                         shiftTimingRequest.getShiftEndTime(),
	                         shiftTimingRequest.getModifiedBy(),
	                         shiftTimingRequest.getWeekOff()
	                     );
	                     shift.setModifiedBy(employeeId);
	                   //  shift.setEmployeId(employeeId);
	                     shift.setEmployee(employee);
	 		            listshift.add(shift);
	 		            employee.setShiftTimings(listshift);
	        		 }
	        		 employeeRepository.save(employee);
			            return employeeRepository.findById(employeeId).get();
	                 
	        		 }

		        else throw new UserNotFoundException("employe id is not found");

	}

	@Override
	public Employee getShiftTimingsByEmployee(Long employeeId) throws UserNotFoundException {

		return getEmployeeById(employeeId);
}
	@Override
	public List<ShiftTiming> getAllEmployeShifts() throws UserNotFoundException {
		// TODO Auto-generated method stub
		return shiftTimingRepository.findAll();
	}
	@Override
	public ShiftTiming updateShift(Long id, ShiftTimingUserRequest shiftTiming) throws UserNotFoundException {
		// TODO Auto-generated method stub

		ShiftTiming shiftTiming1=shiftTimingRepository.findById(id).orElseThrow(()->new UserNotFoundException("id not found"));
		shiftTiming1.setEndDate(shiftTiming.getEndDate());
		shiftTiming1.setShiftStartTime(shiftTiming.getShiftStartTime());
		shiftTiming1.setShiftEndTime(shiftTiming.getShiftEndTime());
		shiftTiming1.setStartDate(shiftTiming.getStartDate());
		shiftTiming1.setWeekOff(shiftTiming.getWeekOff());
		shiftTiming1.setEndDate(shiftTiming.getEndDate());
		return shiftTimingRepository.save(shiftTiming1);
		 
	}
	@Override
	public List<ShiftTiming> getAllDayShifts() {
		// TODO Auto-generated method stub
		
		return shiftTimingRepository.findByShiftStartTime(LocalTime.parse("10:00:00"));
	}
	@Override
	public List<ShiftTiming> getAllNightShifts() {
		// TODO Auto-generated method stub
		return shiftTimingRepository.findByShiftStartTime(LocalTime.parse("07:00:00"));
	}
	@Override
	public ShiftTiming getShiftByid(Long id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return shiftTimingRepository.findById(id).orElseThrow(()->new UserNotFoundException("id not found"));
	}
	@Override
	public List<?> gettodatShiftEmployes() {
		// TODO Auto-generated method stub
		return employeeRepository.findByStatusAndIsDeletedd("active",false);
	}
}
