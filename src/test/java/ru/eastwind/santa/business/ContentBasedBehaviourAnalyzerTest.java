package ru.eastwind.santa.business;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.eastwind.santa.domain.Behaviour;
import ru.eastwind.santa.domain.Mail;

public class ContentBasedBehaviourAnalyzerTest {
	
	private ContentBasedBehaviourAnalyzer analyzerUT;
	private Mail mail;
	
	@BeforeMethod
	public void setUp() {
		analyzerUT = new ContentBasedBehaviourAnalyzer();
		mail = new Mail("name");
	}

	@Test(expectedExceptions = NotSupportedMailException.class)
	public void testAnalyseTitleIsNullException() {
		mail.setBody(null);
		analyzerUT.analyse(mail);
	}
	
	@Test
	public void testAnalyse() throws Exception {
		mail.setBody("He was a good boy");
		assertEquals(analyzerUT.analyse(mail), Behaviour.Good);
		mail.setBody("He was bad");
		assertEquals(analyzerUT.analyse(mail), Behaviour.Bad);
		mail.setBody("She is gOoD");
		assertEquals(analyzerUT.analyse(mail), Behaviour.Good);
		mail.setBody("");
		assertEquals(analyzerUT.analyse(mail), Behaviour.Bad);
		mail.setBody("Something");
		assertEquals(analyzerUT.analyse(mail), Behaviour.Bad);
	}
	
	

}
