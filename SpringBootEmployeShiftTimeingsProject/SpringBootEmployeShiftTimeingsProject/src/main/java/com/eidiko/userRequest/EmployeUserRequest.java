package com.eidiko.userRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import com.eidiko.entity.ShiftTiming;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder.Default;
import lombok.Data;

@Data
public class EmployeUserRequest {

	   @NotNull(message = "id must not empty")
	    private Long id;
	   @NotEmpty(message = "name must not empty")
	   @Pattern(regexp = "^[A-z]+$",message = "Name Allows Only Characters")
	    private String name;
	   @NotNull(message="mail id must not null")
	   @Email(message = "invalid mail Address")
	    private String email;
	   @NotNull(message = "date must not null")
	    private LocalDate dateJoin;
	   @NotNull(message = "mobile number must not null")
	   @Pattern(regexp = "^[6789][0-9]{9}$",message = "invalid mobile number")
	    private String mobile;
	   @Value(value="active")
	    private String status="active"; 
	    private boolean isDeleted;
	    private String about;
	    private List<ShiftTiming> shiftTimings = new ArrayList<>();
	    
		public EmployeUserRequest() {
			super();
			// TODO Auto-generated constructor stub
		}

		public EmployeUserRequest(Long id, String name, String email, LocalDate dateJoin, String mobile, String status,
				boolean isDeleted, String about, List<ShiftTiming> shiftTimings) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.dateJoin = dateJoin;
			this.mobile = mobile;
			this.status = status;
			this.isDeleted = isDeleted;
			this.about = about;
			this.shiftTimings = shiftTimings;
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
			if(status==null)this.status="active";
			else
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

		public void setShiftTimings(List<ShiftTiming> shiftTimings) {
			this.shiftTimings = shiftTimings;
		}
	    
}
