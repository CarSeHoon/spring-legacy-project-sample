package kr.co.edu.test;

public class Test {
	public static void main(String[] args) {
		
		TestClass testClass = new TestClass();	//new -> TestClass를 지속적으로 사용하기 위해
		int result = testClass.sum(1, 1);
		System.out.print("result : " + result);
	}
}
