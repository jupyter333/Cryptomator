package org.cryptomator.common.keychain;


import org.cryptomator.common.settings.Settings;
import org.cryptomator.integrations.keychain.KeychainAccessException;
import org.cryptomator.integrations.keychain.KeychainAccessProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javafx.application.Platform;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class KeychainManagerTest {

	@Test
	public void testStoreAndLoad() throws KeychainAccessException {
		KeychainManager keychainManager = new KeychainManager(new SimpleObjectProperty<>(new MapKeychainAccess()));
		keychainManager.storePassphrase("test", "Test", "asd");
		Assertions.assertArrayEquals("asd".toCharArray(), keychainManager.loadPassphrase("test"));
	}

	@Nested
	public static class WhenObservingProperties {

		@BeforeAll
		public static void startup() throws InterruptedException {
			CountDownLatch latch = new CountDownLatch(1);
			Platform.startup(latch::countDown);
			var javafxStarted = latch.await(5, TimeUnit.SECONDS);
			Assumptions.assumeTrue(javafxStarted);
		}

		@Test
		public void testPropertyChangesWhenStoringPassword() throws KeychainAccessException, InterruptedException {
			KeychainManager keychainManager = new KeychainManager(new SimpleObjectProperty<>(new MapKeychainAccess()));
			ReadOnlyBooleanProperty property = keychainManager.getPassphraseStoredProperty("test");
			Assertions.assertFalse(property.get());

			keychainManager.storePassphrase("test", null,"bar");

			AtomicBoolean result = new AtomicBoolean(false);
			CountDownLatch latch = new CountDownLatch(1);
			Platform.runLater(() -> {
				result.set(property.get());
				latch.countDown();
			});
			Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> latch.await());
			Assertions.assertTrue(result.get());
		}

	}

	/**
	 * test qui efface une entree  du keychain
	 * le tests doit passer si l'entree n'est plus presente
	 * @throws KeychainAccessException
	 */

	@Test
	public void testDeletePassphrase() throws KeychainAccessException {
		KeychainManager keychainManager = new KeychainManager(new SimpleObjectProperty<>(new MapKeychainAccess()));
		keychainManager.storePassphrase("clef-test", "test-delete", "Whisky44");
		keychainManager.deletePassphrase("clef-test");
		Assertions.assertFalse(keychainManager.isPassphraseStored("clef-test"));
	}







}