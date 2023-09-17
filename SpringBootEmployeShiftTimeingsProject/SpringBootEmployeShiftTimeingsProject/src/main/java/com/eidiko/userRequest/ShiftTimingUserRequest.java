package com.eidiko.userRequest;

import java.time.LocalDate;
import java.time.LocalTime;

import com.eidiko.entity.Employee;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class ShiftTimingUserRequest {
	    private LocalDate startDate;
	    @NotNull(message = "Please Enter id Enter id ")
	    private Long empId;
	    private LocalDate endDate;
	    private LocalTime shiftStartTime;
	    private LocalTime shiftEndTime;
	    private Long modifiedBy;
	    private String weekOff;
	    private Employee employee;
		public ShiftTimingUserRequest() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ShiftTimingUserRequest(LocalDate startDate, LocalDate endDate,
				LocalTime shiftStartTime, LocalTime shiftEndTime, Long modifiedBy, String weekOff,
				Employee employee) {
			super();
			this.startDate = startDate;
			this.endDate = endDate;
			this.shiftStartTime = shiftStartTime;
			this.shiftEndTime = shiftEndTime;
			this.modifiedBy = modifiedBy;
			this.weekOff = weekOff;
			this.employee = employee;
			this.empId=employee.getId();
		}

//		public Long getShiftTimeId() {
//			return shiftTimeId;
//		}
//
//		public void setShiftTimeId(Long shiftTimeId) {
//			this.shiftTimeId = shiftTimeId;
//		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}

		public LocalDate getEndDate() {
			return endDate;
		}

		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}

		public LocalTime getShiftStartTime() {
			return shiftStartTime;
		}

		public void setShiftStartTime(LocalTime shiftStartTime) {
			this.shiftStartTime = shiftStartTime;
		}

		public LocalTime getShiftEndTime() {
			return shiftEndTime;
		}

		public void setShiftEndTime(LocalTime shiftEndTime) {
			this.shiftEndTime = shiftEndTime;
		}

		public Long getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(Long modifiedBy) {
			this.modifiedBy = modifiedBy;
		}

		public String getWeekOff() {
			return weekOff;
		}

		public void setWeekOff(String weekOff) {
			this.weekOff = weekOff;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}
	    
}
