package com.techStackPortal.dataObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ProjectDO {
	private String name;
	private String id;
	private ArrayList<String> technologies=null;
	private String techStr;
	
	public String getTechStr() {
		return techStr;
	}
	public void setTechStr(String techStr) {
		this.techStr = techStr;
		if(techStr != null){
			String [] tmp = techStr.split(",");
			setTechnologies(new ArrayList<String>(Arrays.asList(tmp)));
		}
	}
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
		if(technologies == null){
			technologies = new ArrayList<String>();
		}
		return technologies;
	}
	public void setTechnologies(ArrayList<String> technologies) {
		this.technologies = technologies;
	}
}
