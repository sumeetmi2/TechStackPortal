package com.techStackPortal.controller;

import javax.ws.rs.core.MultivaluedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techStackPortal.graph.implementation.GraphDBManager;

@Controller
@RequestMapping(value="/rest")
public class GraphAPI {
	
	
	@Autowired
	GraphDBManager graphDBManager;
	
	@RequestMapping(value="/d/{degree}/q/{query}",method= RequestMethod.GET)
	public String findDegreePath(@PathVariable String degree,@PathVariable String query,ModelMap map){
		try{
			MultivaluedHashMap<String,String> result = graphDBManager.getApiResult(degree, query);
			map.addAttribute("result",result);
		}catch(NumberFormatException e){
			map.addAttribute("invalidDegreeInput","Invalid degree input");
		}catch (Exception e) {
			return "error";
		}
		return "apiResult";
	}
	
}
