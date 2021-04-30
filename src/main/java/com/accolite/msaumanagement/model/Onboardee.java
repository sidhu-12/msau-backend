package com.accolite.msaumanagement.model;

import java.sql.Date;

public class Onboardee {
	private final long  serialUUID = 1L;
	private String email;
	private String name;
	private long  phoneNo;
	private int demandId;
	private String location;
	private String skills;
	private Date startDate;
	private String backgroundCheckStatus;
	private String onBoardStatus;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Onboardee(String email, String name, long phoneNo, int demandId, String location, String skills,
			Date startDate, String backgroundCheckStatus, String onBoardStatus) {
		super();
		this.email = email;
		this.name = name;
		this.phoneNo = phoneNo;
		this.demandId = demandId;
		this.location = location;
		this.skills = skills;
		this.startDate = startDate;
		this.backgroundCheckStatus = backgroundCheckStatus;
		this.onBoardStatus = onBoardStatus;
	}
	public Onboardee() {}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getDemandId() {
		return demandId;
	}
	public void setDemandId(int demandId) {
		this.demandId = demandId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	public String getBackgroundCheckStatus() {
		return backgroundCheckStatus;
	}
	public void setBackgroundCheckStatus(String backgroundCheckStatus) {
		this.backgroundCheckStatus = backgroundCheckStatus;
	}
	public String getOnBoardStatus() {
		return onBoardStatus;
	}
	public void setOnBoardStatus(String onBoardStatus) {
		this.onBoardStatus = onBoardStatus;
	}
	
	
}
