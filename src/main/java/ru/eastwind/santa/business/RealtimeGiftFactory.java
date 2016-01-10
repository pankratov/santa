package ru.eastwind.santa.business;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.eastwind.santa.domain.Gift;

@Component
public class RealtimeGiftFactory implements GiftFactory {

	/**
	 * количество эльфов
	 */
	private static final int ELF_NUMBER = 5;

	private final ExecutorService executor;

	private final ConcurrentHashMap<String, Future<?>> tasks = new ConcurrentHashMap<>();

	@Autowired
	private GiftStore giftStore;
	
	@Autowired
	private GiftBuilder giftBuilder;

	public RealtimeGiftFactory() {
		executor = Executors.newFixedThreadPool(ELF_NUMBER);
	}

	private void elfWork(String childName, String giftName) {
		Logger.getGlobal().info("Hard working...");
		Gift gift = createGift(giftName);
		Logger.getGlobal().info("Stick label...");
		gift.setLabel(childName);
		// synchronized block ?
		if(Thread.currentThread().isInterrupted()) {
			gift.unlabel();
		}
		giftStore.put(gift);
		//
		Logger.getGlobal().info("Done!");
		tasks.remove(childName);
	}

	private Gift createGift(String giftName) {
		return giftBuilder.build(giftName);
	}

	@Override
	public void createGiftForChild(String childName, String giftName) {
		// lambda
		Future<?> future = executor.submit(() -> elfWork(childName, giftName));
		tasks.putIfAbsent(childName, future);
	}

	@Override
	public void cancelForChild(String childName) {
		Future<?> future = tasks.get(childName);
		if (future != null) {
			future.cancel(true);
		}
		giftStore.unlabelForChild(childName);
	}

}
