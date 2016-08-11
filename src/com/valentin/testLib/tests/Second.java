package com.valentin.testLib.tests;

import com.valentin.testLib.annotations.Test;
import com.valentin.testLib.classes.Assert;
import com.valentin.testLib.exceptions.TestFailedException;

public class Second {
	
	@Test
	public void notSameSuccess() throws TestFailedException {
		Integer a = new Integer(1);
		Integer b = new Integer(2);
		Assert.notSame("sameSuccess", a, b);
	}
}
