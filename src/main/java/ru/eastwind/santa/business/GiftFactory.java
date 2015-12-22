package ru.eastwind.santa.business;

public interface GiftFactory {
	// поля по умолчанию в интерфейсе - public
	void createGiftForChild(String childName, String giftName);
	void cancelForChild(String childName);
}
