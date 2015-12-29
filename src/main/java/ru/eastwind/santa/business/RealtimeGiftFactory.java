package ru.eastwind.santa.business;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.eastwind.santa.domain.Gift;

@Component
public class RealtimeGiftFactory implements GiftFactory {

	/**
	 * количество эльфов
	 */
	private static int ELF_NUMBER = 5;

	private static int ELF_LAZINESS = 2;

	private ExecutorService executor;

	private HashMap<String, Future<?>> tasks = new HashMap<>();

	@Autowired
	private GiftStore giftStore;

	public RealtimeGiftFactory() {
		executor = Executors.newFixedThreadPool(ELF_NUMBER);
	}

	private void elfWork(String childName, String giftName) {
		Logger.getGlobal().info("Hard working...");
		Gift gift = createGift(giftName);
		Logger.getGlobal().info("Stick label...");
		gift.setLabel(childName);
		Logger.getGlobal().info("Done!");
		putAtStore(gift);
	}

	private Gift createGift(String giftName) {
		try {
			TimeUnit.SECONDS.sleep(ELF_LAZINESS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Gift(giftName);
	}

	@Override
	public void createGiftForChild(String childName, String giftName) {
		// lambda
		Future<?> future = executor.submit(() -> elfWork(childName, giftName));
		tasks.put(childName, future);
	}

	private void putAtStore(Gift gift) {
		giftStore.put(gift);
	}

	@Override
	public void cancelForChild(String childName) {
		if (tasks.containsKey(childName)) {
			Future<?> future = tasks.get(childName);
			if (!future.isDone())
				future.cancel(true);
		}
		giftStore.unlabelForChild(childName);
	}

}
