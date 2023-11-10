package com.eidiko.service;

import java.io.ObjectInputFilter.Config;
import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eidiko.entity.Employee;
import com.eidiko.entity.ShiftTiming;
import com.eidiko.exception.UserNotFoundException;
import com.eidiko.repository.EmployeeRepository;
import com.eidiko.userRequest.EmployeUserRequest;
import com.eidiko.userRequest.ShiftTimingUserRequest;


@Service
public class AuthenticationService {

	@Autowired
	private EmployeeRepository repository;
	@Autowired
	private JwtService service;
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public String register(EmployeUserRequest user) throws Exception {
		String mail=repository.findCheckUserName(user.getEmail());
		System.out.println(mail);
		if(mail!=null)throw new Exception("Dublicate user");
		Employee employe=new Employee(user.getId(), user.getName(), user.getEmail(), user.getDateJoin(), user.getMobile(),user.getStatus(),	"inactive".equals(user.getStatus()) ? true : false , user.getAbout());
		employe.setDesignations(user.getDesignations());
		employe.setPassword(encoder.encode(user.getPassword()));
		repository.save(employe);
	
		addShiftTimingToEmployee(employe.getId(),user.getShiftTimings());
		repository.save(employe);		
		 
		 return "User Saved";
	}
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
	        		 repository.save(employee);
			            return repository.findById(employeeId).get();
	                 
	        		 }

		        else throw new UserNotFoundException("employe id is not found");

	}
	public Employee getEmployeeById(Long id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Employee emp=repository.findById(id).orElseThrow(()->new UserNotFoundException("id not found"));
		if(emp!=null) {
				emp.getShiftTimings().size();
			return emp;
		}
		else throw new UserNotFoundException("Not Found");
	}

	
	public Object authenticate(com.eidiko.userRequest.AuthRequest request) throws AuthenticationException {
		System.out.println("start authentication ");
		System.out.println(request.getEmail()+"   -    "+request.getPassword());
		try {
			System.out.println("in try");
				authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getMail(),request.getPassword()) ); 
		}catch(Exception e){
			System.out.println("in catch");
			throw new AuthenticationException("in correct details Authentication failed");
		}
		Employee user=repository.findByEmail(request.getMail());
		String token=service.generateToke(user);
		System.out.println("authentication completed");
		return token;

	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	
}
