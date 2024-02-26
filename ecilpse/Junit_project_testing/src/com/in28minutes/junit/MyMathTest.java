package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*같은 패키지 경로에 있어야*/

class MyMathTest {

	MyMath math = new MyMath();
	
	@Test
	void calculateSum_() {
		assertEquals(6,math.calculateSum(new int[] {1,2,3}));	//비
	}

	@Test
	void test1() {
		int[] numbers = {};
		
		int result = math.calculateSum(numbers);
		
		int expectedResult = 0;
		assertEquals(expectedResult,result);	//비
	}
	
}
