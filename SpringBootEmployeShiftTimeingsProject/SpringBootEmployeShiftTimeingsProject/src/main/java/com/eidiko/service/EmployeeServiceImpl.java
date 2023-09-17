package com.eidiko.service;

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
	public List<Employee> getAllActiveEmployees(Long id) throws UserNotFoundException {
		
		// TODO Auto-generated method stub
		if(id==222)
		return employeeRepository.findByStatusAndIsDeleted("active", false);
		else throw new UserNotFoundException("you are not Hr");
	}

	@Override
	public List<Employee> getAllEmployees(Long id) throws UserNotFoundException {
		// TODO Auto-generated method stub		
		if(id==222) {
					List<Employee> list=employeeRepository.findAll();
		if(list!=null) return list;
		else throw new UserNotFoundException("Data is Empty");
		}
		else throw new UserNotFoundException("Hr Only Can see Employe Data");

	}

	@Override
	public Employee getEmployeeById(Long id,Long hr) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Employee emp=employeeRepository.findById(id).orElseThrow(()->new UserNotFoundException("id not found"));
		if(emp!=null) {
			if(hr!=null) {
				if(hr==222)return emp;
				else throw new UserNotFoundException("Employe Or Hr Only Can Do Operations On Data");
			}
			else
			return emp;
		}
		else throw new UserNotFoundException("Not Found");
	}

	@Override
	public Employee saveEmployee(Long Hr,EmployeUserRequest user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(Hr==222) {
			//"inactive".equals(user.getStatus()) ? true : false
			Employee employe=new Employee(user.getId(), user.getName(), user.getEmail(), user.getDateJoin(), user.getMobile(),user.getStatus(),	"inactive".equals(user.getStatus()) ? true : false , user.getAbout(), user.getShiftTimings());
		    new ShiftTiming().setEmployeId(user.getId());
			return employeeRepository.save(employe);
		}else throw new UserNotFoundException("Only Hr can create an Employe");
	}

	@Override
	public Employee updateEmployee(Long id,Long Hr, EmployeUserRequest user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Employee emp=getEmployeeById(id,Hr);
		if(emp!=null) {
			Employee employe=new Employee(user.getId(), user.getName(), user.getEmail(), user.getDateJoin(), user.getMobile(), user.getStatus(), "inactive".equals(user.getStatus()) ? true : false, user.getAbout(), user.getShiftTimings());
			return employeeRepository.save(employe);
		}
		else throw new UserNotFoundException("employe id is not found");
	}

	@Override
	public Employee deleteEmployee(Long id,Long Hr) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Employee existingEmployee=getEmployeeById(id,Hr);
		if (existingEmployee != null) {
			if(Hr!=222 | Hr==null) throw new UserNotFoundException("Only Hr can perform operation on an Employe");
	            existingEmployee.setStatus("inactive");
	            existingEmployee.setDeleted(true);
	            return employeeRepository.save(existingEmployee);
	        }
			else throw new UserNotFoundException("employe id is not found");

	}

	@Override
	public Employee addShiftTimingToEmployee(Long employeeId,Long hr, ShiftTimingUserRequest shiftTiming) throws UserNotFoundException {
		// TODO Auto-generated method stub
		   Employee employee = getEmployeeById(employeeId,hr);
	        if (employee != null) {
	        	ShiftTiming shift=new ShiftTiming(shiftTiming.getStartDate(), shiftTiming.getEndDate(),
	        			shiftTiming.getShiftStartTime(), shiftTiming.getShiftEndTime(), shiftTiming.getModifiedBy(),shiftTiming.getWeekOff());
	        	if(hr!=null) {
	        		if(hr==222) {
	        	   shift.setModifiedBy(hr);
	        	   shift.setEmployeId(employeeId);
		            shiftTiming.setEmployee(employee);
		            employee.setShiftTimings(shift);
		            return employeeRepository.save(employee);
	        		}else throw new UserNotFoundException("Hr or Employe only can add or modify shift timings ");
	        	}
	        	else
		        	shift.setModifiedBy(employeeId);
	        	   shift.setEmployeId(employeeId);
	            shiftTiming.setEmployee(employee);
	            employee.getShiftTimings().add(shift);
	            return employeeRepository.save(employee);
	        }
	        else throw new UserNotFoundException("employe id is not found");
	}

	@Override
	public List<ShiftTiming> getShiftTimingsByEmployee(Long employeeId,Long Hr) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Employee employee=getEmployeeById(employeeId,Hr);
		if(employee!=null) {
			//return (List<ShiftTiming>) shiftTimingRepository.findById(employeeId).get();
			return shiftTimingRepository.findByEmployee(employee) ;
	}
		else throw new UserNotFoundException("employe id is not found");


}
}