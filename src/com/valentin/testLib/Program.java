package com.valentin.testLib;

import com.valentin.testLib.classes.TestStarter;
import com.valentin.testLib.tests.FirstTest;

public class Program {

	public static void main(String[] args) {
		TestStarter<FirstTest> starter = new TestStarter<>(FirstTest.class);
		starter.start();
	}
}
