package com.kidsworld.subscription.domain;

import com.kidsworld.status.TransitiveHandler;

import static com.kidsworld.subscription.domain.Status.INVALID_USER;
import static com.kidsworld.subscription.domain.Status.SUBSCRIBED;

public class SubscriptionTransitiveHandler implements TransitiveHandler {
	@Override
	public Status handle(SubscriptionUser user) {
		if (user.isInvalid()) {
			return INVALID_USER;
		} else {
			return SUBSCRIBED;
		}
	}
}
