package ru.eastwind.santa.business;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import ru.eastwind.santa.config.TestAppConfig;

/**
 * Integration Test
 * 
 * @author pankratov
 *
 */
@ContextConfiguration(classes = TestAppConfig.class)
public class SantaIT {

	@Test
	public void test() {

	}
}
