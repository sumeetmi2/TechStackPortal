package com.techStackPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
	
	@ModelAttribute("person")
	public PersonDO getPerson(){
		return new PersonDO();
	}
	
	 @RequestMapping(method = RequestMethod.GET)
	   public ModelAndView createEmployeeForm(@ModelAttribute PersonDO person,Model model) {
	      return new ModelAndView("inputInfo","command",new PersonDO());
	   }
	 
	 @RequestMapping(method = RequestMethod.POST)
	 public String showAdded(@ModelAttribute("SpringWeb") PersonDO person, ModelMap map){
		 graphDBManager.addPersonNodeInGraph(person);
		 map.addAttribute("name",person.getName());
		 map.addAttribute("id",person.getId());
		 map.addAttribute("projects",person.getProjects());
		 return "result";
	 }
}
