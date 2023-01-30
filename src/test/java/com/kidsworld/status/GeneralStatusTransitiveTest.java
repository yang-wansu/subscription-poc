package com.kidsworld.status;

import com.kidsworld.subscription.domain.SubscriptionTransitiveHandler;
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
		sut.define(new StatusName[]{StatusName.first()}, someTransitive, mock(TransitiveHandler.class));
		sut.define(new StatusName[]{StatusName.first() , StatusName.of("someStatus") }, someTransitive, new SubscriptionTransitiveHandler());
	}

	@Test
	void name() {
		assertDoesNotThrow(()->sut.validate(someTransitive, StatusName.first()));
		assertThrows(StatusTransitiveException.class,
				() -> sut.validate(someTransitive, StatusName.of("illegal Status")));
	}

	@Test
	void name2() {
		assertDoesNotThrow(()->sut.validate(someTransitive, StatusName.first()));
		assertDoesNotThrow(()->sut.validate(someTransitive, StatusName.of("someStatus")));
	}
}
