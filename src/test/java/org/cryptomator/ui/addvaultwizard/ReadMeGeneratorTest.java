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
	// Teste si la méthode createVaultAccessLocationReadmeRtf génère correctement un document RTF
	// avec les localisations d'accès au vault en utilisant les valeurs du bundle de ressources.
	@Test
	public void testCreateVaultAccessLocationReadmeRtf(){
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

	/**
	 * methode qui met en place des valeurs attendus par le mock
	 */

	@BeforeEach
	public void setUp() {

		mockResourceBundle = mock(ResourceBundle.class);

		// creation des chaines de caracteres qui devraient etre presentes dans le readMe
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.1")).thenReturn("Voici un Vault");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.2")).thenReturn("Ceci est un test fait par");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.3")).thenReturn("Jhosim et Cesar");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.4")).thenReturn("Pour tester la generation des README");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.5")).thenReturn("Dans le projet");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.6")).thenReturn("Cryptomator");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.7")).thenReturn("Plus de details ici.");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.8")).thenReturn("Dans le site web");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.9")).thenReturn("officiel");
		when(mockResourceBundle.getString("addvault.new.readme.storageLocation.10")).thenReturn(":) %s");

		// Create the ReadmeGenerator instance with the mocked ResourceBundle
		readmeGenerator = new ReadmeGenerator(mockResourceBundle);
	}

	/**
	 * Methode qui test les valeurs mis en place dans le setUp,
	 * la methode evalue le bon fonctionnement du generateur du CreateVaultStorageLocationReadmeRtf.
	 * Les tests devront passer si le document a le bon format avec le contenu fait dans le setUp
	 */
	@Test
	public void testCreateVaultStorageLocationReadmeRtf() {

		String result = readmeGenerator.createVaultStorageLocationReadmeRtf();

		// On verifie la bonne indentation qui est mis dans le fichier ReadMeGenerator.java
		assertTrue(result.startsWith("{\\rtf1\\fbidis\\ansi\\uc0\\fs32\n"));
		assertTrue(result.endsWith("}"));

		// Verify que chaque ligne contient le bon format et les valeurs fait dans le setUp
		assertTrue(result.contains("\\fs40\\qc Voici un Vault"));
		assertTrue(result.contains("Ceci est un test fait par"));
		assertTrue(result.contains("\\b Jhosim et Cesar"));
		assertTrue(result.contains("    Pour tester la generation des README"));
		assertTrue(result.contains("    Dans le projet"));
		assertTrue(result.contains("Cryptomator"));
		assertTrue(result.contains("    Plus de details ici."));
		assertTrue(result.contains("    Dans le site web"));
		assertTrue(result.contains("    officiel"));
		assertTrue(result.contains("{\\field{\\*\\fldinst HYPERLINK \"http://docs.cryptomator.org/\""));


		verify(mockResourceBundle, times(10)).getString(anyString());
	}


}
