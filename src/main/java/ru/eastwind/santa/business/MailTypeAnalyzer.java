package ru.eastwind.santa.business;

import ru.eastwind.santa.domain.Mail;
import ru.eastwind.santa.domain.MailType;

public interface MailTypeAnalyzer {
	MailType analyse(Mail mail);
}
