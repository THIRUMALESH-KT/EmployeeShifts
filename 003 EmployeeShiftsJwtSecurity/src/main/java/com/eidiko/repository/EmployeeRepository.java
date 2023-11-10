package com.eidiko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eidiko.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByStatusAndIsDeleted(String status, boolean isDeleted);
    @Query("SELECT e FROM Employee e JOIN e.shiftTimings s " +
    	       "WHERE CURDATE() BETWEEN s.startDate AND s.endDate " +
    	       "AND CURDATE() <= s.endDate " + 
    	       "AND e.status = :status AND e.isDeleted = :isDeleted")
    List<Employee> findByStatusAndIsDeletedd(String status,boolean isDeleted);
    Employee findByName(String name);
	Employee findByEmail(String mail);
	@Query("select e.email from Employee e where email=:email")
	String findCheckUserName(String email);

}
