package ru.eastwind.santa.business;

import java.util.Optional;

import ru.eastwind.santa.domain.Gift;

public interface GiftStore {
	Optional<Gift> getIfExists(String giftName);
	void put(Gift gift);
}
