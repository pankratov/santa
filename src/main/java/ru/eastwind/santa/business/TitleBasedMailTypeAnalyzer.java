package ru.eastwind.santa.business;

import ru.eastwind.santa.domain.Mail;
import ru.eastwind.santa.domain.MailType;

public class TitleBasedMailTypeAnalyzer implements MailTypeAnalyzer {

	private static final String FROM_CHILD_PATTERN = "dear santa";
	private static final String INFO_PATTERN = "report";
	
	
	public MailType analyse(Mail mail) {
		if(mail.getTitle() == null) throw new NotSupportedMailException("Title can't be null");
		
		String title = mail.getTitle().toLowerCase();
		
		if(title.contains(FROM_CHILD_PATTERN)) {
			return MailType.FromChild;
		}
		if(title.contains(INFO_PATTERN)) {
			return MailType.Information;
		}
		
		return MailType.SPAM;
	}

}
