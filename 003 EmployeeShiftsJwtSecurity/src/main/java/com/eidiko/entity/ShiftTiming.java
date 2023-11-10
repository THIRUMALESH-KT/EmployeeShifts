package com.eidiko.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ShiftTiming {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long shiftTimeId;
	   // private Long EmpId;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private LocalTime shiftStartTime;
	    private LocalTime shiftEndTime;
	    private Long modifiedBy;
	    private String weekOff;
	    
	    @ManyToOne
	    @JoinColumn(name = "employee_id")
	    @JsonBackReference
	     private Employee employee;

		public ShiftTiming() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ShiftTiming(LocalDate startDate, LocalDate endDate, LocalTime shiftStartTime,
				LocalTime shiftEndTime, Long modifiedBy, String weekOff ) {
			super();
			this.startDate = startDate;
			this.endDate = endDate;
			this.shiftStartTime = shiftStartTime;
			this.shiftEndTime = shiftEndTime;
			this.modifiedBy = modifiedBy;
			this.weekOff = weekOff;
		}
		 @JsonProperty("employeId")
		    public Long getEmployeeId() {
		        return employee != null ? employee.getId() : null;
		    }

		public Long getShiftTimeId() {
			return shiftTimeId;
		}

		public void setShiftTimeId(Long shiftTimeId) {
			this.shiftTimeId = shiftTimeId;
		}

//		public Long getEmpId() {
//			return EmpId;
//		}
//
//		public void setEmployeId(Long employeId) {
//			this.EmpId = employeId;
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
