package com.valentin.testLib.classes;

import java.util.List;

public class ExecutePackage {

	public static void start(String packagePath) {
		List<Class<?>> testClasses = ClassFinder.find(packagePath);
		for (Class<? extends Object> testClass : testClasses) {
			System.out.println("TESTS FROM " + testClass.getName() + ":");
			TestStarter starter;
			try {
				starter = new TestStarter(Class.forName(testClass.getName()));
				starter.start();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("");
			System.out.println("");
		}
	}
}
