package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SomebusinessImplMockTest_aunotaion {

	@Mock
	private DataService dataServiceMock;
	
	@InjectMocks
	private SomeBusinessImpl businessImpl;
	
	@Test
	void findTheGreatestFromAllData_basicScenario2() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25,15,5});
		assertEquals(25,businessImpl.findTheGreatestFromAllData());
	}
	
//	@Test
//	void findTheGreatestFromAllData_basicScenario() {
//		DataService dataServiceMock = mock(DataService.class);
//		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25,15,5});
//		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
//		assertEquals(25,businessImpl.findTheGreatestFromAllData());
//	}
}
