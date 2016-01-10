package ru.eastwind.santa.business;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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

	private final Map<String, Gift> gifts = Collections.synchronizedMap(new HashMap<>());
	private final Map<String, Integer> unlabeledGiftNumber = Collections.synchronizedMap(new HashMap<>());

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
		if(gift.isLabeled()) {
			gifts.put(gift.getLabel(), gift);
		} else {
			putUnlabeled(gift);
		}
	}
	
	private void putUnlabeled(Gift gift) {
		Integer number = unlabeledGiftNumber.getOrDefault(gift, 0);
		unlabeledGiftNumber.put(gift.getName(), number + 1);		
	}

	@Override
	public synchronized void unlabelForChild(String childName) {
		Gift gift = gifts.remove(childName);
		if (gift != null) {
			gift.unlabel();
			putUnlabeled(gift);
		}
	}

}
