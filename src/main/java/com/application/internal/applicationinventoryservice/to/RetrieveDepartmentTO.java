package com.application.internal.applicationinventoryservice.to;

public class RetrieveDepartmentTO {
	private int id;
	private String departmentName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentOwner() {
		return departmentOwner;
	}
	public void setDepartmentOwner(String departmentOwner) {
		this.departmentOwner = departmentOwner;
	}
	private String departmentOwner;

}