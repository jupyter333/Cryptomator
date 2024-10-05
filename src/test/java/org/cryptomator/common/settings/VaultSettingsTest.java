/*******************************************************************************
 * Copyright (c) 2016, 2017 Sebastian Stenzel and others.
 * All rights reserved.
 * This program and the accompanying materials are made available under the terms of the accompanying LICENSE file.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.common.settings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VaultSettingsTest {

	@ParameterizedTest(name = "VaultSettings.normalizeDisplayName({0}) = {1}")
	@CsvSource(value = {
			"a\u000Fa,a_a",
			": \\,_ _",
			"汉语,汉语",
			"..,_",
			"a\ta,a\u0020a",
			"'\t\n\r',_"
	})
	public void testNormalize(String test, String expected) {
		assertEquals(expected, VaultSettings.normalizeDisplayName(test));
	}
	private VaultSettings vaultSettings;

	@BeforeEach
	public void setUp() {
		// dummy settings du vault
		VaultSettingsJson json = new VaultSettingsJson();
		json.id = "testId";
		json.path = "/test/path";
		json.displayName = "Test Vault";
		json.unlockAfterStartup = true;
		json.revealAfterMount = true;
		json.usesReadOnlyMode = false;
		json.mountFlags = "rw";
		json.maxCleartextFilenameLength = 255;
		json.actionAfterUnlock = WhenUnlocked.ASK;
		json.autoLockWhenIdle = true;
		json.autoLockIdleSeconds = 600;
		json.mountPoint = "/mount/point";
		json.mountService = "testService";
		json.port = 42427;

		vaultSettings = new VaultSettings(json);
	}

	@Test
	public void testSerialized() {

		VaultSettingsJson serializedJson = vaultSettings.serialized();

		assertEquals("testId", serializedJson.id);
		assertEquals("/test/path", serializedJson.path);
		assertEquals("Test Vault", serializedJson.displayName);
		assertTrue(serializedJson.unlockAfterStartup);
		assertTrue(serializedJson.revealAfterMount);
		assertFalse(serializedJson.usesReadOnlyMode);
		assertEquals("rw", serializedJson.mountFlags);
		assertEquals(255, serializedJson.maxCleartextFilenameLength);
		assertEquals(WhenUnlocked.ASK, serializedJson.actionAfterUnlock);
		assertTrue(serializedJson.autoLockWhenIdle);
		assertEquals(600, serializedJson.autoLockIdleSeconds);
		assertEquals("/mount/point", serializedJson.mountPoint);
		assertEquals("testService", serializedJson.mountService);
		assertEquals(42427, serializedJson.port);
	}
}
