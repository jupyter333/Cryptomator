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
import com.github.javafaker.Faker;

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

	/*
	 * Creation des configuration initiales de javafaker
	 * pour faire de tests sur la methode serialized
	 */

	private VaultSettings vaultSettings;
	private Faker faker;

	/**
	 * Cette methode genere des entrees a evaluer pour la fonction serialized avec java faker
	 */
	@BeforeEach
	public void setUp() {

		faker = new Faker();

		// Faker genere des valeurs a tester pour la fonction serialized
		VaultSettingsJson json = new VaultSettingsJson();
		json.id = faker.idNumber().valid();
		json.path = faker.file().fileName();
		json.displayName = faker.company().name();
		json.unlockAfterStartup = faker.bool().bool();
		json.revealAfterMount = faker.bool().bool();
		json.usesReadOnlyMode = faker.bool().bool();
		json.mountFlags = faker.lorem().word();
		json.maxCleartextFilenameLength = faker.number().numberBetween(100, 255);
		json.actionAfterUnlock = WhenUnlocked.ASK;
		json.autoLockWhenIdle = faker.bool().bool();
		json.autoLockIdleSeconds = faker.number().numberBetween(300, 600);
		json.mountPoint = faker.file().fileName();
		json.mountService = faker.app().name();
		json.port = faker.number().numberBetween(10000, 60000);

		vaultSettings = new VaultSettings(json);
	}

	/***
	 * Cette methode verifie que serialized donne correctement les parametres
	 * attendus par le VaulSettings.java.
	 * Le test devrait passer si les valeurs correspondent a ceux qu'on a fait dans le
	 * setUp().
	 */
	@Test
	public void testSerialized() {

		VaultSettingsJson serializedJson = vaultSettings.serialized();

		// On fait des assertions sur les valeurs attendus
		assertEquals(vaultSettings.serialized().id, serializedJson.id);
		assertEquals(vaultSettings.serialized().path, serializedJson.path);
		assertEquals(vaultSettings.serialized().displayName, serializedJson.displayName);
		assertEquals(vaultSettings.serialized().unlockAfterStartup, serializedJson.unlockAfterStartup);
		assertEquals(vaultSettings.serialized().revealAfterMount, serializedJson.revealAfterMount);
		assertEquals(vaultSettings.serialized().usesReadOnlyMode, serializedJson.usesReadOnlyMode);
		assertEquals(vaultSettings.serialized().mountFlags, serializedJson.mountFlags);
		assertEquals(vaultSettings.serialized().maxCleartextFilenameLength, serializedJson.maxCleartextFilenameLength);
		assertEquals(vaultSettings.serialized().actionAfterUnlock, serializedJson.actionAfterUnlock);
		assertEquals(vaultSettings.serialized().autoLockWhenIdle, serializedJson.autoLockWhenIdle);
		assertEquals(vaultSettings.serialized().autoLockIdleSeconds, serializedJson.autoLockIdleSeconds);
		assertEquals(vaultSettings.serialized().mountPoint, serializedJson.mountPoint);
		assertEquals(vaultSettings.serialized().mountService, serializedJson.mountService);
		assertEquals(vaultSettings.serialized().port, serializedJson.port);
	}
}
