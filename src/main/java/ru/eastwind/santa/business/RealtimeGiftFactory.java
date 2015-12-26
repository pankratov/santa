package ru.eastwind.santa.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.eastwind.santa.domain.Gift;

@Component
public class RealtimeGiftFactory implements GiftFactory {

	/**
	 * количество эльфов
	 */
	private static int THREAD_NUMBER = 5;

	@Autowired
	private GiftStore giftStore;

	@Override
	public void createGiftForChild(String childName, String giftName) {
		// TODO Auto-generated method stub
	}

	private void putAtStore(Gift gift) {
		giftStore.put(gift);
	}

	@Override
	public void cancelForChild(String childName) {
		// отменнить текущий процесс или вызывать unlabel
	}

}
