package com.techStackPortal.dataObject;

import java.util.ArrayList;

public class CompanyDO {
	private String id;
	private String name;
	private ArrayList<ProjectDO> projects;
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
	public ArrayList<ProjectDO> getProjects() {
		return projects;
	}
	public void setProjects(ArrayList<ProjectDO> projects) {
		this.projects = projects;
	}
}
