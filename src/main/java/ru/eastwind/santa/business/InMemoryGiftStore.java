package ru.eastwind.santa.business;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ru.eastwind.santa.domain.Gift;

/*
 *  TODO: Напишите другую компонетну TheWorstGiftStore, 
 *  который ничего не сохраняет, а всегда говорит, что подарков нет
 *  сделайте так, чтобы он автоматически связывался только при тестировании
 */
@Component
public class InMemoryGiftStore implements GiftStore {

	private HashMap<String, Gift> gifts = new HashMap<>();
	private HashMap<String, Integer> unlabeledGiftNumber = new HashMap<>();

	@Override
	public synchronized Optional<Gift> takeUnlabeled(String giftName) {
		Integer number = unlabeledGiftNumber.getOrDefault(giftName, 0);
		if(number > 0) {
			unlabeledGiftNumber.put(giftName, number-1);
			return Optional.of(new Gift(giftName));
		}
		return Optional.empty();
	}

	@Override
	public synchronized void put(Gift gift) {
		gifts.put(gift.getLabel(), gift);
	}

	@Override
	public void unlabelForChild(String childName) {

		if (gifts.containsKey(childName)) {
			Gift gift = gifts.remove(childName);
			Integer number = unlabeledGiftNumber.get(gift);
			if (number != null) {
				unlabeledGiftNumber.put(gift.getName(), number + 1);
			} else {
				unlabeledGiftNumber.put(gift.getName(), 1);
			}
		}
	}

}
