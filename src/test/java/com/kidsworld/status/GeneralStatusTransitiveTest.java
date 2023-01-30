package com.kidsworld.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class GeneralStatusTransitiveTest {


	GeneralStatusTransitive sut = new GeneralStatusTransitive();
	TransitiveName someTransitive = TransitiveName.of("someTransitive");

	@BeforeEach
	void setUp() {
		sut.define(someTransitive, new StatusName[]{StatusName.first() , StatusName.of("someStatus") }, mock(TransitiveHandler.class));
	}

	@Test
	void validate() {
		assertDoesNotThrow(()->sut.validate(someTransitive, StatusName.first()));
		assertThrows(StatusTransitiveException.class,
				() -> sut.validate(someTransitive, StatusName.of("illegal Status")));
		assertDoesNotThrow(()->sut.validate(someTransitive, StatusName.first()));
		assertDoesNotThrow(()->sut.validate(someTransitive, StatusName.of("someStatus")));
	}


	@Test
	void handler() {
		//sut.handle(someTransitive, )
	}
}
