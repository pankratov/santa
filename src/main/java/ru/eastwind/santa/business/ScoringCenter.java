package ru.eastwind.santa.business;

import ru.eastwind.santa.domain.Mail;

public interface ScoringCenter {
	void consider(Mail mail);
}
