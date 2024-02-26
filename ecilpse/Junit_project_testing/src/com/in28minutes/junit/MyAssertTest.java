package com.in28minutes.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyAssertTest {

	@Test
	void test() {

	boolean test = true;
		
	assertEquals(true,test,"message when its false");	
	assertTrue("message when its false",test);
	assertFalse("message when its false", test);
	//assertNull, assertNotNull
	assertArrayEquals(new int[] {1,2}, new int[] {2,1}); //false
	
	}
}
