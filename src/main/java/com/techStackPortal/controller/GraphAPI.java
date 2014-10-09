package com.techStackPortal.controller;

import javax.ws.rs.core.MultivaluedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techStackPortal.graph.implementation.GraphDBManager;

@RestController
@RequestMapping(value="/rest")
public class GraphAPI {
	
	
	@Autowired
	GraphDBManager graphDBManager;
	
	@RequestMapping(value="/d/{degree}/q/{query}",method= RequestMethod.GET)
	@ResponseBody
	public MultivaluedHashMap<String,String> findDegreePath(@PathVariable String degree,@PathVariable String query){
		String resultStr = "";
		MultivaluedHashMap<String,String> result = new MultivaluedHashMap<String,String>();
		try{
			result = graphDBManager.getApiResult(degree, query);
		}catch(NumberFormatException e){
			result.add("error", "Invalid degreee input. Degree input should be a number");
		}catch (Exception e) {
			result.add("error", e.toString());
		}
		return result;
	}
	
}
