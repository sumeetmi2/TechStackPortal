package com.techStackPortal.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestCaseRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestCases.class);
		for(Failure fail: result.getFailures()){
			System.out.println(fail.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}
