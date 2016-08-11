package com.valentin.testLib.tests;

import com.valentin.testLib.annotations.After;
import com.valentin.testLib.annotations.Before;
import com.valentin.testLib.annotations.Init;
import com.valentin.testLib.annotations.Test;
import com.valentin.testLib.classes.Assert;
import com.valentin.testLib.exceptions.TestFailedException;

public class FirstTest {

	@Init
	public void init() {
		System.out.println("INIT");
	}
	
	@Before
	public void before() {
		System.out.println("Before method");
	}
	
	@After
	public void after() {
		System.out.println("After method");
	}
	
	@Test
	public void firstTest() throws TestFailedException {
		Assert.areEqual("FirstTest", "asd", "asd2");
	}
	
	@Test
	public void secondTest() throws TestFailedException {
		Assert.areEqual("SecondTest","xmm", "xmm2");
	}
	
	@Test
	public void thirdTest() throws TestFailedException {
		Assert.areEqual("ThirdTest","xmm", "xmm");
	}
	
	@Test
	public void isTrueSuccess() throws TestFailedException {
		Assert.isTrue("Is True", true);
	}
	
	@Test
	public void isTrueFail() throws TestFailedException {
		Assert.isTrue("isTrueFail", false);
	}
	
	@Test
	public void isFalseFail() throws TestFailedException {
		Assert.isFalse("isFalseFail", true);
	}
	
	@Test
	public void isFalseSuccess() throws TestFailedException {
		Assert.isFalse("isFalseSuccess", false);
	}
	
	@Test
	public void isNullSuccess() throws TestFailedException {
		Assert.isNull("isNullSuccess", null);
	}
	
	@Test
	public void isNullFail() throws TestFailedException {
		Assert.isNull("isNullFail", "");
	}
	
	@Test
	public void isNotNullFail() throws TestFailedException {
		Assert.notNull("isNotNullFail", null);
	}
	
	@Test
	public void isNotNullSuccess() throws TestFailedException {
		Assert.notNull("isNotNullSuccess", "");
	}
	
	@Test
	public void sameFail() throws TestFailedException {
		Integer a = new Integer(1);
		Integer b = new Integer(2);
		Assert.same("isNotNullSuccess", a, b);
	}
	
	@Test
	public void sameSuccess() throws TestFailedException {
		Integer a = new Integer(1);
		Integer b = a;
		Assert.same("sameSuccess", a, b);
	}
	
	@Test
	public void notSameSuccess() throws TestFailedException {
		Integer a = new Integer(1);
		Integer b = new Integer(2);
		Assert.notSame("sameSuccess", a, b);
	}
	
	@Test
	public void notSameFail() throws TestFailedException {
		Integer a = new Integer(1);
		Integer b = a;
		Assert.notSame("sameSuccess", a, b);
	}
}
