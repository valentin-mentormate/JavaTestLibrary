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
		throw new TestFailedException(message);
	}
}
