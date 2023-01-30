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
		setStatus(StatusName.of(READY.name()));
	}

	public void doProcess(String process, StatusTransitive transitive, SubscriptionUser user) {
		transitive.validate(TransitiveName.of(process), StatusName.of(getStatus().name()));

		final StatusName next = transitive.handle(TransitiveName.of(process), user);

		setStatus(next);
	}

	private void setStatus(StatusName statusName) {
		this.status = Status.valueOf(statusName.getValue());
	}
}
