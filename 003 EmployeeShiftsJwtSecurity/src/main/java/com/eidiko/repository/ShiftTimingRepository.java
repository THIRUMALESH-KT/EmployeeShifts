package com.eidiko.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eidiko.entity.Employee;
import com.eidiko.entity.ShiftTiming;

public interface ShiftTimingRepository extends JpaRepository<ShiftTiming, Long> {
  List<ShiftTiming> findByEmployee(Employee employee);
	// TODO Auto-generated method stub

List<ShiftTiming> findByShiftStartTime(LocalTime localTime );



}
