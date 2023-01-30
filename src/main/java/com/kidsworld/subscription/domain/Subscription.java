package com.kidsworld.subscription.domain;

import com.kidsworld.status.StatusName;
import com.kidsworld.status.StatusTransitive;
import com.kidsworld.status.TransitiveName;
import lombok.Getter;

import static com.kidsworld.subscription.domain.Status.READY;

public class Subscription {

	@Getter
	private Status status;

	Subscription() {
		setStatus(READY);
	}

	public void doProcess(String process, StatusTransitive transitive, SubscriptionUser user) {
		transitive.validate(TransitiveName.of(process), StatusName.of(getStatus().name()));

		final Status next = transitive.handle(TransitiveName.of(process), user);

		setStatus(next);
	}

	private void setStatus(Status status) {
		this.status = status;
	}
}
