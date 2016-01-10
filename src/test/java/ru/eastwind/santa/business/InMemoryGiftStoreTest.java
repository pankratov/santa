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
	public void testPutGetAndUnlabel() throws Exception {
		Optional<Gift> giftFromStore = giftStoreUT.takeUnlabeled("testGift");
		assertFalse(giftFromStore.isPresent());
		Gift testGift = new Gift("testGift");
		testGift.setLabel("John");
		giftStoreUT.put(testGift);
		giftFromStore = giftStoreUT.takeUnlabeled("testGift");
		assertFalse(giftFromStore.isPresent());
		giftStoreUT.unlabelForChild("John");
		giftFromStore = giftStoreUT.takeUnlabeled("testGift");
		assertTrue(giftFromStore.isPresent());
		assertEquals(giftFromStore.get().getName(), "testGift");
		giftStoreUT.unlabelForChild("Stephen");
	}
	
	@Test
	public void testPutGetUnlabeled() throws Exception {
		Gift labeledGift = new Gift("labeledGift");
		labeledGift.setLabel("some label");
		Gift unlabeledGift = new Gift("unlabeledGift");
		giftStoreUT.put(labeledGift);
		giftStoreUT.put(unlabeledGift);
		Optional<Gift> giftFromStore = giftStoreUT.takeUnlabeled("labeledGift");
		assertFalse(giftFromStore.isPresent());
		giftFromStore = giftStoreUT.takeUnlabeled("unlabeledGift");
		assertTrue(giftFromStore.isPresent());		
	}

}
