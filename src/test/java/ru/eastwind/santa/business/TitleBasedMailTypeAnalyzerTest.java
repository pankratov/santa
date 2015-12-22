package ru.eastwind.santa.business;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ru.eastwind.santa.domain.Mail;
import ru.eastwind.santa.domain.MailType;

// Статический импорт - очень полезная штука. Теперь можно писать просто assertEquals
import static org.testng.Assert.*;


public class TitleBasedMailTypeAnalyzerTest {
	
	// UT - under test. CUT (class under test) - тестируемый класс
	private TitleBasedMailTypeAnalyzer analyzerUT;
	private Mail mail;
	
	// выполняется перед каждым тестом
	@BeforeTest
	public void setUp() {
		analyzerUT = new TitleBasedMailTypeAnalyzer();
		mail = new Mail("test sender");
	}

	@Test(expectedExceptions = NotSupportedMailException.class)
	public void testAnalyseTitleIsNullException() {
		mail.setTitle(null);
		analyzerUT.analyse(mail);
	}
	
	@Test
	public void testAnalyse() throws Exception {

		mail.setTitle("asdf asdf hgh");
		assertEquals(analyzerUT.analyse(mail), MailType.SPAM);
		mail.setTitle("Dear SaNta");
		assertEquals(analyzerUT.analyse(mail), MailType.FromChild);
		mail.setTitle("buy sell change borrow");
		assertEquals(analyzerUT.analyse(mail), MailType.SPAM);
		mail.setTitle("Little Johny behaviour rEport");
		assertEquals(analyzerUT.analyse(mail), MailType.Information);
		mail.setTitle("Something else");
		assertEquals(analyzerUT.analyse(mail), MailType.SPAM);
		mail.setTitle("");
		assertEquals(analyzerUT.analyse(mail), MailType.SPAM);
		mail.setTitle("Any report");
		assertEquals(analyzerUT.analyse(mail), MailType.Information);
	}
	
}
