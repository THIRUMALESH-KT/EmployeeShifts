package com.eidiko.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.exception.UserNotFoundException;
import com.eidiko.service.EmployeeService;
import com.eidiko.userRequest.EmployeUserRequest;
import com.eidiko.userRequest.ShiftTimingUserRequest;

import jakarta.validation.Valid;


@RestController
public class EmployeController {
	@Autowired
	private EmployeeService empservice;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private  com.eidiko.service.AuthenticationService authenticationService;
	//for getting all active employees
	@GetMapping("/activeEmployes")
	public ResponseEntity<Map<String,Object>> getActiveEmployeesByHr() throws UserNotFoundException{
		System.out.println("inside activeEmployes()");

		List<?> list=empservice.getAllActiveEmployees();
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("no of employes",list.size());
		map.put("message", list);
		map.put("status", String.valueOf(HttpStatus.OK.value()));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
		//
	}
	@GetMapping("/inactiveEmployes")
	public ResponseEntity<Map<String,Object>> getInactiveEmployeesByHr()throws UserNotFoundException{
		List<?> list=empservice.getAllInActiveEmployees();

		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("no of employes", list.size());

		map.put("message", list);
		map.put("status", String.valueOf(HttpStatus.OK.value()));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}

	@GetMapping("/getAllEmployes")
	public ResponseEntity<Map<String,Object>> geAlltEmployeByHr() throws UserNotFoundException{
		System.out.println("inside getAllEmployes()");
		Map<String,Object> map=new HashMap<>();
		List<?> list=empservice.getAllEmployees();

		map.put("result", "success");
		map.put("no of employes", list.size());
		map.put("message",list);
		map.put("status", String.valueOf(HttpStatus.OK.value()));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
		//hiii
	}
	@GetMapping("/getById")
	public ResponseEntity<Map<String,Object>> getEmployeeByHr(@RequestParam("id") Long id) throws UserNotFoundException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.getEmployeeById(id));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
		//hello
	}	


	
	@PutMapping("/updateEmploye")
	public ResponseEntity<Map<String,Object>> updateByHr(@Valid @RequestBody EmployeUserRequest user,@RequestParam("id") Long id) throws UserNotFoundException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.updateEmployee(id, user));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}

	
	@DeleteMapping("/deleteEmploye")
	public ResponseEntity<Map<String,Object>> deleteEmployeByEmploye(@RequestParam("id") Long id ) throws UserNotFoundException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.deleteEmployee(id));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}

	@PostMapping("/addShiftTimings")
	public ResponseEntity<Map<String,Object>> addshift(@RequestParam("id") Long id,@RequestBody List<ShiftTimingUserRequest> shiftTiming) throws UserNotFoundException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.addShiftTimingToEmployee(id, shiftTiming));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  

	}

	
	@GetMapping("/listShiftTimingById")
	public ResponseEntity<Map<String,Object>> listShiftByEmploye(@RequestParam("id") Long id) throws UserNotFoundException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.getShiftTimingsByEmployee(id));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}@GetMapping("/listAllShifts")
	public ResponseEntity<Map<String,Object>> listAllShifts()throws UserNotFoundException{
		List<?> list=empservice.getAllEmployeShifts();
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("no of shifts", list.size());
		map.put("message", list);
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}
	@PutMapping("/updateShift")
	public ResponseEntity<Map<String,Object>> updateShifByHr(@RequestBody ShiftTimingUserRequest shiftTimingUser,@RequestParam("id") Long id)throws UserNotFoundException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.updateShift(id,shiftTimingUser));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
		
	}
			@GetMapping("/dayShifts")
	public ResponseEntity<Map<String,Object>> DayShifts(){
		 List<?> list=empservice.getAllDayShifts();
	    Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("no of shifts", list.size());
		map.put("message", list);
		map.put("status", String.valueOf(HttpStatus.OK));
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}
	@GetMapping("/nightShifts")
		public ResponseEntity<Map<String,Object>> NightShifts(){
		List<?> list=empservice.getAllNightShifts();

		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("no of shifts", list.size());

		map.put("message",list);
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}
	@GetMapping("/getShiftByShiftId")
	public ResponseEntity<Map<String,Object>>GetShift(@RequestParam("id") Long id)throws UserNotFoundException{
		
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.getShiftByid(id));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}
	//for today all shifts Employes(day and night)
	@GetMapping("/todayshifemployes")
	public ResponseEntity<Map<String,Object>> todayshiftEployes(){
		List<?> list=empservice.gettodatShiftEmployes();
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("no of shifts", list.size());

		map.put("message",list);
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}
	@GetMapping("/login")
	//@PreAuthorize("hasRole('Employe')")
	public ResponseEntity<Map<String,Object>> login(){
		Map<String,Object> map=new HashMap<>();
		map.put("result", "sucess");
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@PostMapping("/welcome")
	public ResponseEntity<String> accessUserData() {
		return ResponseEntity.ok("Hello welcome page");
	}
	@PostMapping("/register")
	public ResponseEntity<Map<String,Object>> register(@Valid @RequestBody EmployeUserRequest request) throws Exception{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "sucess");
		map.put("Massage",authenticationService.register(request) );
		map.put("status",  String.valueOf(HttpStatus.OK.value()));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK	);
	}
	@GetMapping("/generateToken")
	public ResponseEntity<Map<String, Object>> generateToken(@RequestBody com.eidiko.userRequest.AuthRequest request) throws AuthenticationException{
		System.out.println("entering into generateToken() fist method");
		Map<String,Object> map=new HashMap<>();
		map.put("result", "sucess");
		map.put("Massage",authenticationService.authenticate(request) );
		map.put("status",  String.valueOf(HttpStatus.OK.value()));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK	);
		
	}
	@GetMapping("/AccessDenied")
	public Map<String,Object> accessDenide(){
		Map<String,Object> map=new  HashMap<>();
		map.put("result", "failed");
		map.put("eroor", "your are not authorized person for this page");
		map.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
		return map;
	}
}
