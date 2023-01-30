package com.kidsworld.status;

import com.kidsworld.subscription.domain.Status;
import com.kidsworld.subscription.domain.SubscriptionUser;
import org.valid4j.Assertive;
import org.valid4j.Validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GeneralStatusTransitive implements StatusTransitive {
	private final Map<TransitiveName, TransitiveDefinition> defines;

	public GeneralStatusTransitive() {
		this.defines = new HashMap<>();
	}

	@Override
	public void validate(TransitiveName transitiveName, StatusName current) {
		TransitiveDefinition definition = this.getDefinition(transitiveName);
		Validation.validate(Arrays.asList(definition.getBeginStatus()).contains(current),
				new StatusTransitiveException());

	}

	private TransitiveDefinition getDefinition(TransitiveName transitiveName) {
		TransitiveDefinition definition = this.defines.get(transitiveName);
		Assertive.ensure(definition != null, "%s define 을 찾을 수 없습니다. ", transitiveName);
		return definition;
	}

	@Override
	public Status handle(TransitiveName transitiveName, SubscriptionUser user) {
		TransitiveDefinition definition = this.defines.get(transitiveName);
		return definition.getTransitiveHandler().handle(user);
	}

	@Override
	public StatusTransitive define(TransitiveName transitiveName, StatusName[] beginStatus, TransitiveHandler transitiveHandler) {
		this.defines.put(transitiveName, TransitiveDefinition.of(transitiveName, beginStatus, transitiveHandler));
		return this;
	}
}
