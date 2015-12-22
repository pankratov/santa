package ru.eastwind.santa.business;

import ru.eastwind.santa.domain.Mail;

public interface Santa {
	void askforGift(Mail mail);
	void complain(String childName);
}
