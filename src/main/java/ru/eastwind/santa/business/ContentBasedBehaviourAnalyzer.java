package ru.eastwind.santa.business;

import ru.eastwind.santa.domain.Behaviour;
import ru.eastwind.santa.domain.Mail;

public class ContentBasedBehaviourAnalyzer implements BehaviourAnalyzer {

	private static final String GOOD_PATTERN = "good";
	
	public Behaviour analyse(Mail mail) {
		if(mail.getBody() == null) throw new NotSupportedMailException("Body can't be empty");
		
		String mailBody = mail.getBody();
		if(mailBody.toLowerCase().contains(GOOD_PATTERN)) {
			return Behaviour.Good;
		}
		return Behaviour.Bad;
	}

}
