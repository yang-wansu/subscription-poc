package com.kidsworld.status;

import lombok.Value;

@Value(staticConstructor = "of")
public class StatusName {

	private static final StatusName FIRST = StatusName.of("this is first status");

	public static StatusName first() {
		return FIRST;
	}

	String value;

}
