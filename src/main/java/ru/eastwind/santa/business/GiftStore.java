package ru.eastwind.santa.business;

import java.util.Optional;

import ru.eastwind.santa.domain.Gift;

public interface GiftStore {
	Optional<Gift> takeUnlabeled(String giftName);
	void put(Gift gift);
	void unlabelForChild(String childName);
}
