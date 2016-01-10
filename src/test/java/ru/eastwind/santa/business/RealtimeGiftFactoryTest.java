package ru.eastwind.santa.business;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.eastwind.santa.domain.Gift;

import static org.mockito.Mockito.*;


public class RealtimeGiftFactoryTest {
	
	@InjectMocks
	private RealtimeGiftFactory factoryUT;
	
	@Mock
	private GiftBuilder giftBuilder;

	@BeforeMethod
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this);
		String giftName = "testGift";
		when(giftBuilder.build(giftName)).thenReturn(new Gift(giftName));
	}
	
	@Test
	public void testCancelForChild() throws Exception {
		
	}

	@Test
	public void testCreateGiftForChild() throws Exception {

	}
	
	
}
