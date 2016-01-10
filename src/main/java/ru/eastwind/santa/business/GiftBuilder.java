package ru.eastwind.santa.business;

import ru.eastwind.santa.domain.Gift;

public interface GiftBuilder {
	Gift build(String giftName);
}
