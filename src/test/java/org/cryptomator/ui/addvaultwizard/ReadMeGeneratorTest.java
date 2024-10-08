package org.cryptomator.ui.addvaultwizard;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
		ResourceBundle fakeBundle = mock(ResourceBundle.class);
		ReadmeGenerator readmeGenerator = new ReadmeGenerator(fakeBundle);

		when(fakeBundle.getString("addvault.new.readme.accessLocation.1")).thenReturn("loc1");
		when(fakeBundle.getString("addvault.new.readme.accessLocation.2")).thenReturn("loc2");
		when(fakeBundle.getString("addvault.new.readme.accessLocation.3")).thenReturn("loc3");
		when(fakeBundle.getString("addvault.new.readme.accessLocation.4")).thenReturn("loc4");

		String test = readmeGenerator.createVaultAccessLocationReadmeRtf();
		String expectedResult = readmeGenerator.createDocument(List.of(String.format(HEADING, "loc1"), "loc2", EMPTY_PAR, "loc3", EMPTY_PAR, "loc4"));

		Assertions.assertEquals(test, expectedResult);

	}

	// cesar tests
	private ResourceBundle mockResourceBundle;
	private ReadmeGenerator readmeGenerator;

	@BeforeEach
	public void setUp() {

		mockResourceBundle = mock(ResourceBundle.class);

		// Setup mocked responses for the resource keys
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.1")).thenReturn("Vault Storage Location");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.2")).thenReturn("This is where your vault will be stored.");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.3")).thenReturn("Don't forget");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.4")).thenReturn("Make sure it's secure.");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.5")).thenReturn("Store in a safe place.");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.6")).thenReturn("Additional Information:");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.7")).thenReturn("You can find more details here.");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.8")).thenReturn("Documentation is available.");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.9")).thenReturn("Ensure regular backups.");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.10")).thenReturn("For more information, visit %s");

		// Create the ReadmeGenerator instance with the mocked ResourceBundle
		readmeGenerator = new ReadmeGenerator(mockResourceBundle);
	}
	@Test
	public void testCreateVaultStorageLocationReadmeRtf() {

		String result = readmeGenerator.createVaultStorageLocationReadmeRtf();

		// Verify that the result starts and ends with correct RTF headers/footers
		assertTrue(result.startsWith("{\\rtf1\\fbidis\\ansi\\uc0\\fs32\n"));
		assertTrue(result.endsWith("}"));

		// Verify that the content includes the expected formatted values
		assertTrue(result.contains("\\fs40\\qc Vault Storage Location"));
		assertTrue(result.contains("This is where your vault will be stored."));
		assertTrue(result.contains("\\b Don't forget"));
		assertTrue(result.contains("    Make sure it's secure."));
		assertTrue(result.contains("    Store in a safe place."));
		assertTrue(result.contains("Additional Information:"));
		assertTrue(result.contains("    You can find more details here."));
		assertTrue(result.contains("    Documentation is available."));
		assertTrue(result.contains("    Ensure regular backups."));
		assertTrue(result.contains("{\\field{\\*\\fldinst HYPERLINK \"http://docs.cryptomator.org/\""));


		verify(mockResourceBundle, times(10)).getString(anyString());
	}


}
