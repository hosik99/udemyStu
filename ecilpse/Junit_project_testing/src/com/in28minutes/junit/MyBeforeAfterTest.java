package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyBeforeAfterTest {

	@BeforeAll	//class의 모든 메소드 이전에 실행 -> static
	static void beforeAll() {
		System.out.println("before All");
	}
	
	//각메소드 테스트 전에 실행
	@BeforeEach
	void beforeeach() {
		System.out.println("before");
	}
	
	@Test
	void test1() {
		System.out.println("test1");
	}

	@Test
	void test2() {
		System.out.println("test2");
	}
	
	@Test
	void test3() {
		System.out.println("test3");
	}
	
	@AfterEach
	void aftereach() {
		System.out.println("After");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("After All");
	}
}
