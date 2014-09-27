package com.techStackPortal.dataObject;

import java.util.ArrayList;

public class PersonDO {
	private String id;
	private String name;
	private ArrayList<CompanyDO> companies=null;
	private ArrayList<ProjectDO> projects = null;
	
	public ArrayList<ProjectDO> getProjects() {
		if(projects==null){
			projects = new ArrayList<ProjectDO>();
		}
		return projects;
	}
	public void setProjects(ArrayList<ProjectDO> projects) {
		this.projects = projects;
	}
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
		if(companies==null){
			companies = new ArrayList<CompanyDO>();
		}
		return companies;
	}
	public void setCompanies(ArrayList<CompanyDO> companies) {
		this.companies = companies;
	}
}
