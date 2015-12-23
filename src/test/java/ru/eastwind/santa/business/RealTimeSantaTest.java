package ru.eastwind.santa.business;

import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Random;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.eastwind.santa.domain.Mail;

public class RealTimeSantaTest {

	@InjectMocks
	private RealTimeSanta santaUT;

	@Mock
	private GiftFactory factory;

	@Mock
	private ScoringCenter scoringCenter;

	@Mock
	private Random random;

	@BeforeMethod
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAskforGift() throws Exception {
		String childName = "Test Child";
		String giftName = "XBox";
		Mail mail = new Mail(childName);
		mail.setBody(giftName);

		when(scoringCenter.getScore(childName)).thenReturn(Optional.of(2));
		santaUT.askforGift(mail);
		verify(factory, only()).createGiftForChild(childName, giftName);
		reset(factory);

		when(scoringCenter.getScore(childName)).thenReturn(Optional.of(-2));
		santaUT.askforGift(mail);
		verify(factory, never()).createGiftForChild(childName, giftName);
		reset(factory);

		when(random.nextBoolean()).thenReturn(true);
		when(scoringCenter.getScore(childName)).thenReturn(Optional.empty());
		santaUT.askforGift(mail);
		verify(factory, only()).createGiftForChild(childName, giftName);
		reset(factory);

		when(random.nextBoolean()).thenReturn(false);
		when(scoringCenter.getScore(childName)).thenReturn(Optional.empty());
		santaUT.askforGift(mail);
		verify(factory, never()).createGiftForChild(childName, giftName);
		reset(factory);

	}

	@Test
	public void testComplain() throws Exception {
		String childName = "Test Child";

		when(random.nextBoolean()).thenReturn(true);
		santaUT.complain(childName);
		verify(factory, never()).cancelForChild(childName);
		reset(factory);

		when(random.nextBoolean()).thenReturn(false);
		santaUT.complain(childName);
		verify(factory, only()).cancelForChild(childName);
		reset(factory);
	}

}
