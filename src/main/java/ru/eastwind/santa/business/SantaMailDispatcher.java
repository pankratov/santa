package ru.eastwind.santa.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.eastwind.santa.domain.Mail;
import ru.eastwind.santa.domain.MailType;

// TODO: Перенести эту компоненту в AppConfig
@Component
public class SantaMailDispatcher implements MailDispatcher {

	/*
	 * TODO: написать анализатор по телу письма и подменить его при помощи Qualifier
	 */
	@Autowired
	private MailTypeAnalyzer typeAnalyzer;

	@Autowired
	private Santa santa;
	
	@Autowired
	private ScoringCenter scoringCenter;
	
	public void dispatch(Mail mail) {
		MailType type = typeAnalyzer.analyse(mail);
		switch (type) {
			case FromChild:
				santa.askforGift(mail);
				break;
			case Information:
				scoringCenter.consider(mail);
				break;
			case SPAM:
				throwOut(mail);		
		}
	}
	
	
	private void throwOut(Mail mail) {

		// do nothing
	}

}
