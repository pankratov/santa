package ru.eastwind.santa.business;

import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.eastwind.santa.domain.Mail;

@Component
public class RealTimeSanta implements Santa {
	
	private static final String SANTA_PHRASE = "Ho ho ho!";

	@Autowired
	private GiftFactory factory;
	
	private static final Integer THRESHOLD = 0;

	@Autowired
	private ScoringCenter scoringCenter;

	private Random random = new Random();

	@Override
	public void askforGift(Mail mail) {
		String childName = mail.getFrom();
		String giftName = mail.getBody();
		Optional<Integer> childScore = scoringCenter.getScore(childName);
		if (isNeutralScore(childScore)) {
			Logger.getGlobal().info(SANTA_PHRASE);
			giveChanceForGift(childName, giftName);
		} else if (childScore.get() > THRESHOLD) {
			factory.createGiftForChild(childName, giftName);
		}
	}

	private boolean isNeutralScore(Optional<Integer> childScore) {
		return !childScore.isPresent() || childScore.get() == THRESHOLD;
	}

	private void giveChanceForGift(String childName, String giftName) {
		if (giveChance()) {
			factory.createGiftForChild(childName, giftName);
		}
	}

	@Override
	public void complain(String childName) {
		if (giveChance()) {
			Logger.getGlobal().info(SANTA_PHRASE);
		} else {
			factory.cancelForChild(childName);
		}
	}

	private boolean giveChance() {

		return random.nextBoolean();
	}

}
