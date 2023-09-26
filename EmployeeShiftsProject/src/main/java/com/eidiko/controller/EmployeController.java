package com.eidiko.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.entity.UserRequest;
import com.eidiko.exception.UserNotFoundException;
import com.eidiko.service.EmployeeService;
import com.eidiko.userRequest.EmployeUserRequest;
import com.eidiko.userRequest.ShiftTimingUserRequest;
import com.eidiko.util.jwtUtil;

import jakarta.validation.Valid;

@RestController
public class EmployeController {
	@Autowired
	private EmployeeService empservice;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private jwtUtil jwtUtil;
	//for getting all active employees
	@GetMapping("/activeEmployes")
	public ResponseEntity<Map<String,Object>> getActiveEmployeesByHr() throws UserNotFoundException{
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
		Map<String,Object> map=new HashMap<>();
		List<?> list=empservice.getAllEmployees();

		map.put("result", "success");
		map.put("no of employes", list.size());
		map.put("message",list);
		map.put("status", String.valueOf(HttpStatus.OK.value()));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
		//hiii
	}
	@GetMapping("/getById/{id}")
	public ResponseEntity<Map<String,Object>> getEmployeeByHr(@PathVariable Long id) throws UserNotFoundException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.getEmployeeById(id));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
		//hello
	}	


	@PostMapping("/saveE	mploye")
	public ResponseEntity<Map<String,Object>> save(@Valid  @RequestBody EmployeUserRequest user) throws UserNotFoundException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.saveEmployee(user));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}
	@PutMapping("/updateEmploye/{id}")
	public ResponseEntity<Map<String,Object>> updateByHr(@Valid @RequestBody EmployeUserRequest user,@PathVariable Long id) throws UserNotFoundException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.updateEmployee(id, user));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}

	
	@DeleteMapping("/deleteEmploye/{id}")
	public ResponseEntity<Map<String,Object>> deleteEmployeByEmploye(@PathVariable Long id ) throws UserNotFoundException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.deleteEmployee(id));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  
	}

	@PostMapping("/addShiftTimings/{id}")
	public ResponseEntity<Map<String,Object>> addshift(@PathVariable Long id,@RequestBody List<ShiftTimingUserRequest> shiftTiming) throws UserNotFoundException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", empservice.addShiftTimingToEmployee(id, shiftTiming));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);  

	}

	
	@GetMapping("/listShiftTimingById/{id}")
	public ResponseEntity<Map<String,Object>> listShiftByEmploye(@PathVariable Long id) throws UserNotFoundException{
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
	@PutMapping("/updateShift/{id}")
	public ResponseEntity<Map<String,Object>> updateShifByHr(@RequestBody ShiftTimingUserRequest shiftTimingUser,@RequestParam Long id)throws UserNotFoundException{
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
	@GetMapping("/getShiftByShiftId/{id}")
	public ResponseEntity<Map<String,Object>>GetShift(@PathVariable Long id)throws UserNotFoundException{
		
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
	public ResponseEntity<Map<String,Object>> login(@Valid @RequestBody UserRequest user){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		String token=jwtUtil.generateTocken(user.getUsername());
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("token", token);
		map.put("statuc", String.valueOf(HttpStatus.OK));
		return ResponseEntity.ok(map);
		
	}
	@PostMapping("/Welcome")
	public ResponseEntity<String> accessUserData(Principal p) {
		return ResponseEntity.ok("Hello user:"+p.getName());
	}

}
