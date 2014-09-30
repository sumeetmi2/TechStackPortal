package com.techStackPortal.dataObject;

import java.util.ArrayList;

/**
 * @author SumeetS
 *
 */
public class ResultDO {
	
	private String relType;
	private ArrayList<PropsDO> result;
	
	public String getRelType() {
		return relType;
	}
	public void setRelType(String relType) {
		this.relType = relType;
	}
	public ArrayList<PropsDO> getResult() {
		return result;
	}
	public void setResult(ArrayList<PropsDO> result) {
		this.result = result;
	}
}
