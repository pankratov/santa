package ru.eastwind.santa.business;

import static org.testng.Assert.*;

import java.util.Optional;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.eastwind.santa.domain.Gift;

public class InMemoryGiftStoreTest {
	
	private InMemoryGiftStore giftStoreUT;
	
	@BeforeMethod
	public void setUp() {
		giftStoreUT = new InMemoryGiftStore();
	}

	@Test
	public void testPutAndGet() throws Exception {
		Optional<Gift> giftFromStore = giftStoreUT.takeIfExists("testGift");
		assertFalse(giftFromStore.isPresent());
		Gift testGift = new Gift("testGift");
		giftStoreUT.put(testGift);
		giftFromStore = giftStoreUT.takeIfExists("testGift");
		assertTrue(giftFromStore.isPresent());
		assertEquals(giftFromStore.get(), testGift);		
	}

}
