package com.techStackPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.techStackPortal.dataObject.PersonDO;
import com.techStackPortal.graph.implementation.GraphDBManager;

@Controller
@RequestMapping(value="/addEmployee")
public class AddEmployeeController {
	
	@Autowired
	GraphDBManager graphDBManager;
	
	 @RequestMapping(method = RequestMethod.GET)
	   public ModelAndView createEmployeeForm() {
	      return new ModelAndView("inputInfo","command",new PersonDO());
	   }
	 
	 @RequestMapping(method = RequestMethod.POST)
	 public String showAdded(@ModelAttribute("SpringWeb") PersonDO person){
		try{
			graphDBManager.addPersonNodeInGraph(person);
		}catch(Exception e){
			return "error";
		}
		 return "search";
	 }
}
