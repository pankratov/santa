package ru.eastwind.santa.business;

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

	private Map<String, Gift> gifts = new HashMap<>();
	private Map<String, Integer> giftNumber = new HashMap<>();

	@Override
	public synchronized Optional<Gift> getIfExists(String giftName) {
		Integer number = giftNumber.getOrDefault(giftName, 0);
		if(number > 0) {
			giftNumber.put(giftName, number-1);
			return Optional.of(gifts.get(giftName));
		}
		return Optional.empty();
	}

	@Override
	public synchronized void put(Gift gift) {
		Integer number = giftNumber.get(gift);
		if(number != null) {
			giftNumber.put(gift.getName(), number+1);
		} else {
			giftNumber.put(gift.getName(), 1);
			gifts.put(gift.getName(), gift);
		}
	}

}
