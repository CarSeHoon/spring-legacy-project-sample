package kr.co.edu.test;

public class TestClass {
	
	// static으로 만들면 처음에 객체 생성을 해서 Test.java에서 new를 쓰지 않아도 된다. 객체생성을 단 한번만 하고 singleton pattern = Bean
	public int sum (int firstValue, int secondValue) {
		return firstValue + secondValue;
	}
}
