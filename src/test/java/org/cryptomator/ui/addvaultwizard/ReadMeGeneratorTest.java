package org.cryptomator.ui.addvaultwizard;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.ResourceBundle;

public class ReadMeGeneratorTest {

	@SuppressWarnings("SpellCheckingInspection")
	@ParameterizedTest
	@CsvSource({ //
			"test,test", //
			"t\u00E4st,t\\u228st", //
			"t\uD83D\uDE09st,t\\u55357\\u56841st", //
	})
	public void testEscapeNonAsciiChars(String input, String expectedResult) {
		ReadmeGenerator readmeGenerator = new ReadmeGenerator(null);

		String actualResult = readmeGenerator.escapeNonAsciiChars(input);

		Assertions.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testCreateDocument() {
		ReadmeGenerator readmeGenerator = new ReadmeGenerator(null);
		Iterable<String> paragraphs = List.of( //
				"Dear User,", //
				"\\b please don't touch the \"d\" directory.", //
				"Thank you for your cooperation \uD83D\uDE09" //
		);

		String result = readmeGenerator.createDocument(paragraphs);

		MatcherAssert.assertThat(result, CoreMatchers.startsWith("{\\rtf1\\fbidis\\ansi\\uc0\\fs32"));
		MatcherAssert.assertThat(result, CoreMatchers.containsString("{\\sa80 Dear User,}\\par"));
		MatcherAssert.assertThat(result, CoreMatchers.containsString("{\\sa80 \\b please don't touch the \"d\" directory.}\\par "));
		MatcherAssert.assertThat(result, CoreMatchers.containsString("{\\sa80 Thank you for your cooperation \\u55357\\u56841}\\par"));
		MatcherAssert.assertThat(result, CoreMatchers.endsWith("}"));
	}

    // Nouveau test jhosim
	@Test
	public void createVaultAccessLocationReadmeRtfTest(){
		String HEADING = "\\fs40\\qc %s";
		String EMPTY_PAR = "";
		ResourceBundle fakeBundle = Mockito.mock(ResourceBundle.class);
		ReadmeGenerator readmeGenerator = new ReadmeGenerator(fakeBundle);

		Mockito.when(fakeBundle.getString("addvault.new.readme.accessLocation.1")).thenReturn("loc1");
		Mockito.when(fakeBundle.getString("addvault.new.readme.accessLocation.2")).thenReturn("loc2");
		Mockito.when(fakeBundle.getString("addvault.new.readme.accessLocation.3")).thenReturn("loc3");
		Mockito.when(fakeBundle.getString("addvault.new.readme.accessLocation.4")).thenReturn("loc4");

		String test = readmeGenerator.createVaultAccessLocationReadmeRtf();
		String expectedResult = readmeGenerator.createDocument(List.of(String.format(HEADING, "loc1"), "loc2", EMPTY_PAR, "loc3", EMPTY_PAR, "loc4"));

		Assertions.assertEquals(test, expectedResult);

	}


}
