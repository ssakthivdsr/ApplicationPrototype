package com.application.internal.applicationinventoryservice.to;

public class DepartmentTO {
	private int departmentId;
	private String departmentName;
	private String departmentOwner;
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
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
}
