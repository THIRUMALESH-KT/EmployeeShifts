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
	public List<Employee> getAllActiveEmployees(Long id) throws UserNotFoundException {
		
		// TODO Auto-generated method stub
		if(id==222)
		return employeeRepository.findByStatusAndIsDeleted("active", false);
		else throw new UserNotFoundException("you are not Hr");
	}
	@Override
	public List<Employee> getAllInActiveEmployees(Long id)throws UserNotFoundException{
		if(id==222)
			return employeeRepository.findByStatusAndIsDeleted("inactive",true);
		else throw new UserNotFoundException("Only Hr can Perform Operation Data");
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
				if(hr==222) {
					emp.getShiftTimings().size();
					return emp;
				}
				else throw new UserNotFoundException("Employe Or Hr Only Can Do Operations On Data");
			}
			else {
				emp.getShiftTimings().size();
			return emp;
			}
		}
		else throw new UserNotFoundException("Not Found");
	}

	@Override
	public Employee saveEmployee(Long Hr,EmployeUserRequest user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(Hr==222) {
			//"inactive".equals(user.getStatus()) ? true : false
			Employee employe=new Employee(user.getId(), user.getName(), user.getEmail(), user.getDateJoin(), user.getMobile(),user.getStatus(),	"inactive".equals(user.getStatus()) ? true : false , user.getAbout());
			employeeRepository.save(employe);
			addShiftTimingToEmployee(employe.getId(), Hr, user.getShiftTimings());
			return employeeRepository.save(employe);
		}else throw new UserNotFoundException("Only Hr can create an Employe");
	}

	@Override
	public Employee updateEmployee(Long id,Long Hr, EmployeUserRequest user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Employee emp=getEmployeeById(id,Hr);
		if(emp!=null) {
			Employee employe=new Employee(user.getId(), user.getName(), user.getEmail(), user.getDateJoin(), user.getMobile(), user.getStatus(), "inactive".equals(user.getStatus()) ? true : false, user.getAbout());
			addShiftTimingToEmployee(employe.getId(), Hr, user.getShiftTimings());
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

	//@SuppressWarnings("null")
	@Override
	public Employee addShiftTimingToEmployee(Long employeeId,Long hr, List<ShiftTimingUserRequest> shiftTiming) throws UserNotFoundException {
		// TODO Auto-generated method stub
		   Employee employee = getEmployeeById(employeeId,hr);
		  List<ShiftTiming> listshift = new ArrayList<>();
	        if (employee != null) {
	        	 if(hr!=null) {
	        		if(hr==222) {
	            for (ShiftTimingUserRequest shiftTimingRequest : shiftTiming) {
                    ShiftTiming shift = new ShiftTiming(
                        shiftTimingRequest.getStartDate(),
                        shiftTimingRequest.getEndDate(),
                        shiftTimingRequest.getShiftStartTime(),
                        shiftTimingRequest.getShiftEndTime(),
                        shiftTimingRequest.getModifiedBy(),
                        shiftTimingRequest.getWeekOff()
                    );
                   
	        	   shift.setModifiedBy(hr);
	        	   //shift.setEmployeId(employeeId);
		            shift.setEmployee(employee);
		            listshift.add(shift);
	        		}
                    shiftTimingRepository.saveAll(listshift);
             
		            return employeeRepository.findById(employeeId).get();
	            }else throw new UserNotFoundException("Hr or Employe only can add or modify shift timings ");
	        	
	            }
	            else {
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
	        		 }
	        		 shiftTimingRepository.saveAll(listshift);
			            return employeeRepository.findById(employeeId).get();
	                 }
	        		 }

		        else throw new UserNotFoundException("employe id is not found");

	}

	@Override
	public Employee getShiftTimingsByEmployee(Long employeeId,Long Hr) throws UserNotFoundException {
		// TODO Auto-generated method stub
//		Employee employee=getEmployeeById(employeeId,Hr);
//		if(employee!=null) {
//			//return (List<ShiftTiming>) shiftTimingRepository.findById(employeeId).get();
//			return shiftTimingRepository.findByEmployee(employee) ;
//	}
//		else throw new UserNotFoundException("employe id is not found");
		return getEmployeeById(employeeId,Hr);
}
	@Override
	public List<ShiftTiming> getAllEmployeShifts(Long hr) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(hr==222) {
		return shiftTimingRepository.findAll();
		}else throw new UserNotFoundException("Only Hr can do operation on All Employee Shifts");
	}
	@Override
	public ShiftTiming updateShift(Long id, ShiftTimingUserRequest shiftTiming) throws UserNotFoundException {
		// TODO Auto-generated method stub
		//
//			getEmployeeById(id, hr);
//		if(hr!=null) {
//			ShiftTiming shiftTiming2=shiftTimingRepository.findById(shiftId).orElseThrow(()->new UserNotFoundException("shift id not found"));
//			shiftTiming2.setEndDate(shiftTiming.getEndDate());
//			shiftTiming2.setModifiedBy(hr);
//			shiftTiming2.setShiftEndTime(shiftTiming.getShiftEndTime());
//			shiftTiming2.setShiftStartTime(shiftTiming.getShiftStartTime());
//			shiftTiming2.setStartDate(shiftTiming.getStartDate());
//			shiftTiming2.setWeekOff(shiftTiming.getWeekOff());
//			
//			shiftTimingRepository.save(shiftTiming2);
//			return employeeRepository.findById(id).get();
//		}else {
//			ShiftTiming shiftTiming2=shiftTimingRepository.findById(shiftId).orElseThrow(()->new UserNotFoundException("shift id not found"));
//			shiftTiming2.setEndDate(shiftTiming.getEndDate());
//			shiftTiming2.setModifiedBy(id);
//			shiftTiming2.setShiftEndTime(shiftTiming.getShiftEndTime());
//			shiftTiming2.setShiftStartTime(shiftTiming.getShiftStartTime());
//			shiftTiming2.setStartDate(shiftTiming.getStartDate());
//			shiftTiming2.setWeekOff(shiftTiming.getWeekOff());
//			shiftTimingRepository.save(shiftTiming2);
//			return employeeRepository.findById(id).get();
//		}
		
		ShiftTiming shiftTiming1=shiftTimingRepository.findById(id).orElseThrow(()->new UserNotFoundException("id not found"));
		shiftTiming1.setEndDate(shiftTiming.getEndDate());
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
}
