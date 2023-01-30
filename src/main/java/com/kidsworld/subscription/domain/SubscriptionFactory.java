package com.kidsworld.subscription.domain;

public interface SubscriptionFactory {
	Subscription create(CreateSubscriptionCommand command);
}
