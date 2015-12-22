package ru.eastwind.santa.business;

import ru.eastwind.santa.domain.Mail;

public interface MailDispatcher {
	
	public void dispatch(Mail mail);

}
