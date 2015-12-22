package ru.eastwind.santa.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.eastwind.santa.domain.Mail;

@Component
public class RealTimeSanta implements Santa {
	
	@Autowired
	private GiftFactory factory;
	
	@Override
	public void askforGift(Mail mail) {
		// TODO Auto-generated method stub

	}

	@Override
	public void complain(String childName) {
		// TODO Auto-generated method stub
		
	}

}
