package com.techStackPortal.dataObject;

import java.util.ArrayList;

public class QueryDO {
	private String personName;
	private String projName;
	private String technologyName;
	private ArrayList<PersonDO> result = null;
	
	public ArrayList<PersonDO> getResult() {
		if(result == null){
			result = new ArrayList<PersonDO>();
		}
		return result;
	}
	public void setResult(ArrayList<PersonDO> result) {
		this.result = result;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		if(personName != null && personName.trim().length() >0){
			this.personName = personName;
		}
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		if(projName !=null && projName.trim().length() > 0){
			this.projName = projName;
		}
	}
	public String getTechnologyName() {
		return technologyName;
	}
	public void setTechnologyName(String technologyName) {
		if(technologyName != null && technologyName.trim().length() >0){
			this.technologyName = technologyName;
		}
	}
	
}
