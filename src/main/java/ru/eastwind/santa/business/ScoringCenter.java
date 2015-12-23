package ru.eastwind.santa.business;

import java.util.Optional;

import ru.eastwind.santa.domain.Mail;

public interface ScoringCenter {
	void consider(Mail mail);

	Optional<Integer> getScore(String childName);
}
