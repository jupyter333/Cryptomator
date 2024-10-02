package org.cryptomator.ui.changepassword;

import com.google.common.base.Strings;
import org.cryptomator.common.Environment;
import org.cryptomator.ui.changepassword.PasswordStrengthUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.ResourceBundle;

public class PasswordStrengthUtilTest {

	@Test
	public void testLongPasswords() {
		PasswordStrengthUtil util = new PasswordStrengthUtil(Mockito.mock(ResourceBundle.class), Mockito.mock(Environment.class));
		String longPw = Strings.repeat("x", 10_000);
		Assertions.assertTimeout(Duration.ofSeconds(5), () -> {
			util.computeRate(longPw);
		});
	}

	@Test
	public void testIssue979() {
		PasswordStrengthUtil util = new PasswordStrengthUtil(Mockito.mock(ResourceBundle.class), Mockito.mock(Environment.class));
		int result1 = util.computeRate("backed derrick buckling mountains glove client procedures desire destination sword hidden ram");
		int result2 = util.computeRate("backed derrick buckling mountains glove client procedures desire destination sword hidden ram escalation");
		Assertions.assertEquals(4, result1);
		Assertions.assertEquals(4, result2);
	}

	// Nouveau test jhosim
	@Test
	public void testGetStrengthDescriptionTooShort(){

		String RESSOURCE_PREFIX = "passwordStrength.messageLabel.";

		ResourceBundle fakeBundle = Mockito.mock(ResourceBundle.class);
		Environment fakeEnvironment = Mockito.mock(Environment.class);

		PasswordStrengthUtil util = new PasswordStrengthUtil(fakeBundle, fakeEnvironment);

		Mockito.when(fakeBundle.getString(RESSOURCE_PREFIX + "tooShort")).thenReturn("too short bro");
		Mockito.when(fakeEnvironment.getMinPwLength()).thenReturn(10);

		String test = util.getStrengthDescription(-1);

		Assertions.assertEquals("too short bro", test);

	}

	@Test
	public void testGetStrengthDescriptionValid(){

		String RESSOURCE_PREFIX = "passwordStrength.messageLabel.";

		ResourceBundle fakeBundle = Mockito.mock(ResourceBundle.class);
		Environment fakeEnvironment = Mockito.mock(Environment.class);

		PasswordStrengthUtil util = new PasswordStrengthUtil(fakeBundle, fakeEnvironment);

		Mockito.when(fakeBundle.containsKey(RESSOURCE_PREFIX + "11")).thenReturn(true);
		Mockito.when(fakeBundle.getString(RESSOURCE_PREFIX+"11")).thenReturn("long enough bro");

		String test = util.getStrengthDescription(11);

		Assertions.assertEquals("long enough bro", test);

	}

	@Test
	public void testGetStrengthDescriptionNoKey(){

		String RESSOURCE_PREFIX = "passwordStrength.messageLabel.";

		ResourceBundle fakeBundle = Mockito.mock(ResourceBundle.class);
		Environment fakeEnvironment = Mockito.mock(Environment.class);

		PasswordStrengthUtil util = new PasswordStrengthUtil(fakeBundle, fakeEnvironment);

		Mockito.when(fakeBundle.containsKey(RESSOURCE_PREFIX + "4")).thenReturn(false);

		String test = util.getStrengthDescription(4);

		Assertions.assertEquals("", test);

	}

}
