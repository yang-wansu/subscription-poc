package com.kidsworld.subscription.domain;

import com.kidsworld.status.StatusName;
import com.kidsworld.status.TransitiveHandler;

import static com.kidsworld.subscription.domain.Status.INVALID_USER;
import static com.kidsworld.subscription.domain.Status.SUBSCRIBED;

public class SubscriptionTransitiveHandler implements TransitiveHandler {
	@Override
	public StatusName handle(Object ... args) {
		SubscriptionUser user = (SubscriptionUser) args[0];
		if (user.isInvalid()) {
			return StatusName.of(INVALID_USER.name());
		} else {
			return StatusName.of(SUBSCRIBED.name());
		}
	}
}
