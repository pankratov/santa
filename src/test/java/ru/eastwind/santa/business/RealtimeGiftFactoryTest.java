package ru.eastwind.santa.business;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.eastwind.santa.domain.Gift;

// Static Import - очень удобная штука
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

public class RealtimeGiftFactoryTest {
	
	@InjectMocks
	private RealtimeGiftFactory factoryUT;
	private static final String GIFT_NAME = "testGift";
	private static final String CHILD_NAME = "Peter Pan";	
	@Mock
	private GiftBuilder giftBuilder;
	
	@Mock
	private GiftStore giftStore;

	@BeforeMethod
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this);	
	}
	
	@Test(singleThreaded = true)
	public void testCancelForChild() throws Exception {
		Gift testGift = new Gift(GIFT_NAME);
		assertFalse(testGift.isLabeled());
		when(giftBuilder.build(GIFT_NAME)).then(new Answer<Gift>() {
			@Override
			public Gift answer(InvocationOnMock invocation) throws Throwable {
				Thread.currentThread().interrupt();
				return testGift;
			}			
		});

		factoryUT.createGiftForChild(CHILD_NAME, GIFT_NAME);
		verify(giftStore, timeout(100).only()).put(testGift);	
		assertFalse(testGift.isLabeled());	
		
		reset(giftStore);
		when(giftBuilder.build(GIFT_NAME)).then(new Answer<Gift>() {
			@Override
			public Gift answer(InvocationOnMock invocation) throws Throwable {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				return testGift;
			}			
		});
		factoryUT.createGiftForChild(CHILD_NAME, GIFT_NAME);
		verify(giftStore, after(10).never()).put(testGift);
		factoryUT.cancelForChild(CHILD_NAME);
		verify(giftStore, timeout(100).only()).unlabelForChild(CHILD_NAME);
		assertFalse(testGift.isLabeled());	
	}

	@Test
	public void testCreateGiftForChild() throws Exception {
		Gift testGift = new Gift(GIFT_NAME);
		assertFalse(testGift.isLabeled());
		when(giftBuilder.build(GIFT_NAME)).thenReturn(testGift);
		
		factoryUT.createGiftForChild(CHILD_NAME, GIFT_NAME);
		verify(giftBuilder, timeout(100).only()).build(GIFT_NAME);
		verify(giftStore, timeout(100).only()).put(testGift);	
		assertTrue(testGift.isLabeled());
	}

}
