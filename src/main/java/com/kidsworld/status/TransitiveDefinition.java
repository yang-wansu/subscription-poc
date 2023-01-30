package com.kidsworld.status;

import lombok.Value;

@Value(staticConstructor = "of")
public class TransitiveDefinition {

	TransitiveName transitiveName;
	StatusName[] beginStatus;
	TransitiveHandler transitiveHandler;
}
