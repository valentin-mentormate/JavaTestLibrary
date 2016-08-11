package com.valentin.testLib.classes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import com.valentin.testLib.annotations.After;
import com.valentin.testLib.annotations.Before;
import com.valentin.testLib.annotations.Init;
import com.valentin.testLib.annotations.Test;
import com.valentin.testLib.annotations.ThrowExceptionTest;
import com.valentin.testLib.exceptions.TestFailedException;

import java.lang.annotation.Annotation;

public class TestStarter<T> {
	
	private Class<T> testClass;
	
	public TestStarter(Class<T> testClass) {
		this.setTestClass(testClass);
	}
	
	public void start() {
		T testClassInstance = this.instanceClass();
		Method[] methods = this.getMehtods(this.getTestClass());
		Method initMethod = this.getMethodByAnnotation(methods, Init.class);
		Method beforeMethod = this.getMethodByAnnotation(methods, Before.class);
		Method afterMethod = this.getMethodByAnnotation(methods, After.class);
		this.ivokeInitMethod(initMethod, testClassInstance);
		Method[] testMethods = this.getMethodsByAnnotation(methods, Test.class);
		this.invokeTestMethods(testMethods, beforeMethod, afterMethod, testClassInstance);
		
		Method[] throwExceptionMethods = this.getMethodsByAnnotation(methods, ThrowExceptionTest.class);
		this.invokeThrowExeceptionTests(throwExceptionMethods, testClassInstance);
	}
	
	private void invokeThrowExeceptionTests(Method[] throwExceptionMethods, T testClassInstance) {
		for(Method method: throwExceptionMethods) {
			Annotation annotation = method.getAnnotation(ThrowExceptionTest.class);
			String expectedExceptionName = ((ThrowExceptionTest)annotation).exceptionName();
			
			try {
				method.invoke(testClassInstance);
				this.printErrorMessage("Method didn't throw exception");
			} catch (InvocationTargetException  e) {
				String currentExceptionName = e.getTargetException().getClass().getName();
				if(currentExceptionName.equals(expectedExceptionName)) {
					this.printSuccessfulMessage(method.getName());
				} else {
					this.printErrorMessage("Method throwed different exception");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void ivokeInitMethod(Method initMethod, T testClassInstance) {
		if(initMethod != null) {
			try {
				initMethod.invoke(testClassInstance);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void invokeTestMethods(Method[] testMethods, Method beforeMethod, Method afterMethod, T testClassInstance) {
		for(Method method : testMethods) {
			try {
				if(beforeMethod != null) {
					beforeMethod.invoke(testClassInstance);
				}
				method.invoke(testClassInstance);
				this.printSuccessfulMessage(method.getName());
				if(afterMethod != null) {
					afterMethod.invoke(testClassInstance);
				}
			} catch (InvocationTargetException  e) {
				if (e.getCause() instanceof TestFailedException) {
					this.printErrorMessage(e.getCause().getMessage());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	private <A extends Annotation> Method getMethodByAnnotation(Method[] methods, Class<A> annotationClass) {
		for(Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			for(Annotation annotation : annotations) {
				String annotationType = annotation.annotationType().toString();
				String initAnnotationName = annotationClass.getName();
				if(annotationType.endsWith(initAnnotationName)) {
					return method;
				}
			}
		}
		
		return null;
	}
	
	private void printErrorMessage(String message) {
		System.out.println(message);
	}
	
	private void printSuccessfulMessage(String methodName) {
		System.out.println(methodName + " passed the test");
	}
	
	private <A extends Annotation> Method[] getMethodsByAnnotation(Method[] methods, Class<A> annotationClass) {
		ArrayList<Method> testMethods = new ArrayList<>();
		for(Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			for(Annotation annotation : annotations) {
				String annotationType = annotation.annotationType().toString();
				String annotationName = annotationClass.getName();
				if(annotationType.endsWith(annotationName)) {
					testMethods.add(method);
				}
			}
		}
		
		return testMethods.toArray(new Method[testMethods.size()]);
	}
	
	private Method[] getMehtods(Class<T> classParam) {
		Method[] methods = classParam.getDeclaredMethods();
		return methods;
	}
	
	private T instanceClass() {
		try {
			return this.getTestClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	private Class<T> getTestClass() {
		return testClass;
	}

	private void setTestClass(Class<T> testClass) {
		this.testClass = testClass;
	}
}