package com.in28minutes.learnspringaop.aopexample.data;

import org.springframework.stereotype.Repository;

import com.in28minutes.learnspringaop.aopexample.annotations.TrackTime;

@Repository
public class DataService1 {

	@TrackTime
	public int[] retrieveData() {
//		throw new RuntimeException();
		return new int[] {11,22,33,44,55};
	}
	
}
