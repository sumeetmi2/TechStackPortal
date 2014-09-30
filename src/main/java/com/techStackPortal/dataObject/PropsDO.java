package com.techStackPortal.dataObject;

/**
 * @author SumeetS
 *
 */
public class PropsDO {
	private String name;
	private String [] label;
	public String[] getLabel() {
		return label;
	}
	public void setLabel(String[] label) {
		this.label = label;
	}
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
