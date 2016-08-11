package com.valentin.testLib.classes;

import com.valentin.testLib.exceptions.TestFailedException;

public class Assert {

	public static void areEqual(String testName, Object actual, Object expected) throws TestFailedException {
		if(actual == null && expected == null) {
			return;
		}
		
		if(actual.equals(expected)) {
			return;
		}

		String message = String.format("%s failed. Assert are equal failed. Expected: \"%s\" Actual: \"%s\"", testName, expected.toString(), actual.toString());
		fail(message);
	}
	
	public static void isTrue(String testName, boolean statemant) throws TestFailedException {
		if(!statemant) {
			String message = String.format("%s failed. Expected: \"%s\" Actual: \"%s\"", testName, !statemant, statemant);
			fail(message);
		}
	}
	
	public static void isFalse(String testName, boolean statemant) throws TestFailedException {
		if(statemant) {
			String message = String.format("%s failed. Expected: \"%s\" Actual: \"%s\"", testName, !statemant, statemant);
			fail(message);
		}
	}
	
	public static void notNull(String testName, Object object) throws TestFailedException{
		if(object == null) {
			String message = String.format("%s failed. The object is null", testName);
			fail(message);
		}
	}
	
	public static void isNull(String testName, Object object) throws TestFailedException{
		if(object != null) {
			String message = String.format("%s failed. The object is not null", testName);
			fail(message);
		}
	}
	
	public static void same(String testName, Object object, Object object2) throws TestFailedException{
		if(object != object2) {
			String message = String.format("%s failed. The objects are different", testName);
			fail(message);
		}
	}
	
	public static void notSame(String testName, Object object, Object object2) throws TestFailedException{
		if(object == object2) {
			String message = String.format("%s failed. Same object", testName);
			fail(message);
		}
	}
	
	public static void fail(String message) throws TestFailedException {
		throw new TestFailedException(message);
	}
}
