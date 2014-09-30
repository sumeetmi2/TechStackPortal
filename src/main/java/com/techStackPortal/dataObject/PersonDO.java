package com.techStackPortal.dataObject;

import java.util.ArrayList;


/**
 * @author SumeetS
 *
 */
public class PersonDO {
	private String id;
	private String name;
	private String firstName;
	private String lastName;
	private ArrayList<PropsDO> props= null;
	
	public ArrayList<PropsDO> getProps() {
		if(props == null){
			props = new ArrayList<PropsDO>();
		}
		return props;
	}
	public void setProps(ArrayList<PropsDO> props) {
		this.props = props;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
}
