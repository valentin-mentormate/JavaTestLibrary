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
}
