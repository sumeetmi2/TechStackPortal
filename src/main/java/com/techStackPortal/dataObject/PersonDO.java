package com.techStackPortal.dataObject;

import java.util.ArrayList;

public class PersonDO {
	private String id;
	private String name;
	private ArrayList<CompanyDO> companies;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<CompanyDO> getCompanies() {
		return companies;
	}
	public void setCompanies(ArrayList<CompanyDO> companies) {
		this.companies = companies;
	}
}
