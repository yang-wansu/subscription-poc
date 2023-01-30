package com.kidsworld.status;

import com.kidsworld.subscription.domain.Status;
import com.kidsworld.subscription.domain.SubscriptionUser;

public interface TransitiveHandler {
	Status handle(SubscriptionUser user);
}
