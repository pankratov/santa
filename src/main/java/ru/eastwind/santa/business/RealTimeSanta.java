package ru.eastwind.santa.business;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.eastwind.santa.domain.Mail;

@Component
public class RealTimeSanta implements Santa {
	
	@Autowired
	private GiftFactory factory;
	
	private static final Integer THRESHOLD = -1;

	@Autowired
	private ScoringCenter scoringCenter;

	private Random random = new Random();

	@Override
	public void askforGift(Mail mail) {
		String childName = mail.getFrom();
		String giftName = mail.getBody();
		Optional<Integer> childScore = scoringCenter.getScore(childName);
		if (!childScore.isPresent()) {
			giveChanceForGift(childName, giftName);
		} else if (childScore.get() > THRESHOLD) {
			factory.createGiftForChild(childName, giftName);
		}
	}

	private void giveChanceForGift(String childName, String giftName) {
		if (giveChance()) {
			factory.createGiftForChild(childName, giftName);
		}
	}

	@Override
	public void complain(String childName) {
		if (!giveChance()) {
			factory.cancelForChild(childName);
		}
	}

	private boolean giveChance() {

		return random.nextBoolean();
	}

}
