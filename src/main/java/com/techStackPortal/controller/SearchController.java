package com.techStackPortal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.techStackPortal.dataObject.PersonDO;
import com.techStackPortal.dataObject.QueryDO;
import com.techStackPortal.graph.implementation.GraphDBManager;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	GraphDBManager graphDBManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getSearchPage(){
		return new ModelAndView("search","command", new QueryDO());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String getSearchResult(@ModelAttribute("SpringWeb") QueryDO queryObj, ModelMap map){
		 QueryDO resultObj = graphDBManager.getSearchResult(queryObj);
		 ArrayList<PersonDO> persons = resultObj.getResult();
		 map.addAttribute("persons",persons);
		return "result";
	}
}
