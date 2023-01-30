package com.kidsworld.status;

import com.kidsworld.subscription.domain.*;

public interface StatusTransitive {

	void validate(TransitiveName transitiveName, StatusName begin);

	Status handle(TransitiveName transitiveName, SubscriptionUser user);

	StatusTransitive define(StatusName[] beginStatus, TransitiveName transitiveName, TransitiveHandler transitiveHandler);
}
