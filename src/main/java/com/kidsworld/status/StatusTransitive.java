package com.kidsworld.status;

public interface StatusTransitive {

	void validate(TransitiveName transitiveName, StatusName begin);

	StatusName handle(TransitiveName transitiveName, Object ... args);

	StatusTransitive define(TransitiveName transitiveName, StatusName[] beginStatus, TransitiveHandler transitiveHandler);
}
