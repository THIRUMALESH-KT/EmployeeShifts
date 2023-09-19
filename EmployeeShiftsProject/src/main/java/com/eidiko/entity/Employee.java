package com.eidiko.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Employee {

	  @Id
	  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String name;
	    private String email;
	    private LocalDate dateJoin;
	    private String mobile;
	    private String status; // active or inactive
	    private boolean isDeleted;
	    private String about;

	    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JsonManagedReference 
	    private List<ShiftTiming> shiftTimings = new ArrayList<>();

		public Employee() {
			super();
			// TODO Auto-generated constructor stub
		}

		public  Employee(Long id, String name, String email, LocalDate dateJoin, String mobile, String status,
				boolean isDeleted, String about ) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.dateJoin = dateJoin;
			this.mobile = mobile;
			this.status = status;
			this.isDeleted = isDeleted;
			this.about = about;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public LocalDate getDateJoin() {
			return dateJoin;
		}

		public void setDateJoin(LocalDate dateJoin) {
			this.dateJoin = dateJoin;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public boolean isDeleted() {
			return isDeleted;
		}

		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public String getAbout() {
			return about;
		}

		public void setAbout(String about) {
			this.about = about;
		}

		public List<ShiftTiming> getShiftTimings() {
			return shiftTimings;
		}

		public void setShiftTimings(List<ShiftTiming> shift) {
			this.shiftTimings =  shift;
		}
 
}
