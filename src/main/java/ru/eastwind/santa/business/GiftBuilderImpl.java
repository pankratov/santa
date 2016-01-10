package ru.eastwind.santa.business;

import java.util.concurrent.TimeUnit;

import ru.eastwind.santa.domain.Gift;

public class GiftBuilderImpl implements GiftBuilder {

	private static final int BUILD_TIME = 2;
	
	@Override
	public Gift build(String giftName) {
		try {
			TimeUnit.SECONDS.sleep(BUILD_TIME);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return new Gift(giftName);
	}

}
