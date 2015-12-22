package ru.eastwind.santa.business;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.eastwind.santa.domain.Mail;
import ru.eastwind.santa.domain.MailType;
import static org.mockito.Mockito.*;


public class SantaMailDispatcherTest {

	@InjectMocks
	private SantaMailDispatcher santaMailDispatcherUT;
	
	@Mock
	private MailTypeAnalyzer analyzer;
	
	@Mock
	private Santa santa;
	
	@Mock
	private ScoringCenter scoring;
	
	@BeforeMethod  
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testDispatch() throws Exception {
		Mail spamMail = new Mail("spam");
		Mail childMail = new Mail("child");
		Mail infoMail = new Mail("info");
		when(analyzer.analyse(childMail)).thenReturn(MailType.FromChild);	
		when(analyzer.analyse(spamMail)).thenReturn(MailType.SPAM);	
		when(analyzer.analyse(infoMail)).thenReturn(MailType.Information);	
		santaMailDispatcherUT.dispatch(childMail);
		verify(santa, only()).askforGift(childMail);
		verify(scoring, never()).consider(childMail);
		santaMailDispatcherUT.dispatch(infoMail);
		verify(santa, never()).askforGift(infoMail);
		verify(scoring, only()).consider(infoMail);
		santaMailDispatcherUT.dispatch(spamMail);
		verify(santa, never()).askforGift(spamMail);
		verify(scoring, never()).consider(spamMail);
	}

	
}
