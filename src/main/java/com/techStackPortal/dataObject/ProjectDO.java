package com.techStackPortal.dataObject;

import java.util.ArrayList;

public class ProjectDO {
	private String name;
	private String id;
	private ArrayList<String> technologies;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<String> getTechnologies() {
		return technologies;
	}
	public void setTechnologies(ArrayList<String> technologies) {
		this.technologies = technologies;
	}
}
