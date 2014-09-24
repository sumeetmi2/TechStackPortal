package com.techStackPortal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddEmployeeController {

	@RequestMapping(value = "/addEmployeeInfo", method = RequestMethod.GET)
	public String addEmployeePage() {
		return "inputInfo";
	}

}
