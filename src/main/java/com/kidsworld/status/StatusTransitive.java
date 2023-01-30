package com.kidsworld.status;

import com.kidsworld.subscription.domain.*;

public interface StatusTransitive {

	void validate(TransitiveName transitiveName, StatusName begin);

	Status handle(TransitiveName transitiveName, SubscriptionUser user);

	StatusTransitive define(TransitiveName transitiveName, StatusName[] beginStatus, TransitiveHandler transitiveHandler);
}
