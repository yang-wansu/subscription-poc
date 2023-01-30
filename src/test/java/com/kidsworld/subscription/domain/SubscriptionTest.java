package com.kidsworld.subscription.domain;

import com.kidsworld.status.GeneralStatusTransitive;
import com.kidsworld.status.StatusName;
import com.kidsworld.status.StatusTransitive;
import com.kidsworld.status.TransitiveName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static com.kidsworld.subscription.domain.Status.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;


@MockitoSettings
class SubscriptionTest {

	@Mock
	SubscriptionUser user;

	StatusTransitive transitive;

	Subscription sut;

	@BeforeEach
	void setUp() {
		transitive = new GeneralStatusTransitive();
		transitive.define(new StatusName[]{StatusName.of("READY"), StatusName.of("INVALID_USER")}, TransitiveName.of("subscribe"), new SubscriptionTransitiveHandler());
		sut = new Subscription();

		assertThat(sut.getStatus()).isEqualTo(READY);
	}

	@Test
	@DisplayName("User 가 올바르지 않다면 구독하지 않는다.")
	void ready_to_invalid_user() {
		given(user.isInvalid()).willReturn(true);

		sut.doProcess("subscribe",transitive,user);

		assertThat(sut.getStatus()).isEqualTo(INVALID_USER);
	}

	@Test
	@DisplayName("구독 성공")
	void ready_to_subscribe() {
		given(user.isInvalid()).willReturn(false);

		sut.doProcess("subscribe",transitive, user);

		assertThat(sut.getStatus()).isEqualTo(SUBSCRIBED);
	}

	@Test
	@DisplayName("구독 중인 상태를 구독 할 수 없다")
	void subscribed_to_subscribed() {
		given(user.isInvalid()).willReturn(false);
		sut.doProcess("subscribe",transitive,user);
		assertThat(sut.getStatus()).isEqualTo(SUBSCRIBED);

		assertThatExceptionOfType(SubscribeRuntimeException.class)

				.isThrownBy(()->sut.doProcess("subscribe",transitive,user));

	}

	@Test
	void invalid_user_to_subscribed() {
		given(user.isInvalid()).willReturn(true);
		sut.doProcess("subscribe",transitive,user);

		given(user.isInvalid()).willReturn(false);
		sut.doProcess("subscribe",transitive,user);

		assertThat(sut.getStatus()).isEqualTo(SUBSCRIBED);
	}
}