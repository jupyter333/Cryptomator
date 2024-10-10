# Documentation Tâche 2 (Cryptomator)

#### Par Jhosim Agudelo et César Rodriguez

## Liste de tests ajoutés par notre équipe
## org.cryptomator.common.keychain

Dans ce package, nous avons travaillé sur le fichier `KeychainManagerTest.java` sur la méthode `deletePassphrase()`. 
Nous avons choisi ce package car le `keychainManager` est une classe très importante pour la sécurité du Vault, puis la méthode `deletePassPhrase` est essentielle pour le bon fonctionnement de Cryptomator.

Nous avons effectué un test unitaire simple pour vérifier que cette fonction supprime correctement une clé. Nous commençons par stocker une clé dans le `keychainManager`, puis nous la supprimons et effectuons une assertion pour confirmer qu'elle n'est plus présente.

[testDeletePassphrase()](https://github.com/jupyter333/Cryptomator/blob/6b933fce646fd14ab7660912e7a3206f3419cc0d/src/test/java/org/cryptomator/common/keychain/KeychainManagerTest.java#L76)

## org.cryptomator.common.settings

Dans ce package, nous avons travaillé sur le fichier `VaultSettingsTest`, plus précisément sur la méthode `serialized()`. On a choisi cette méthode particulierement pour le bonus de `javafaker` car elle permet de simuler les attributs du `VaultSettings`.
La méthode `serialized()` permet de stocker les configurations du `Vault` sous format JSON.

Pour faire notre test unitaire: nous avons créé un JSON fictif dans notre fonction `setUp()`, puis nous avons fait des assertions sur chacun des paramètres attendus.

[testSerialized()](https://github.com/jupyter333/Cryptomator/blob/6b933fce646fd14ab7660912e7a3206f3419cc0d/src/test/java/org/cryptomator/common/settings/VaultSettingsTest.java#L78)

## org.cryptomator.ui.addvaultwizard

Dans ce package, nous avons travaillé sur le fichier `ReadMeGeneratorTest`, qui s'occupe de créer les fichiers README dans le `Vault` (README d'accueil). Plus spécifiquement, nous avons testé les méthodes `createVaultStorageLocationReadmeRtf()` et `createVaultAccessLocationReadmeRtf()`. Pour ce test, nous avons utilisé des mocks et des assertions simples pour garantir le bon fonctionnement du code.
Nous pensons qu'il était pertinent de tester ces méthodes puisque c'est un document contenant des informations importantes pour l'usager. Donc nous voulions tester que le format des informations était adéquat.

[testCreateVaultAccessLocationReadmeRtf](https://github.com/jupyter333/Cryptomator/blob/6b933fce646fd14ab7660912e7a3206f3419cc0d/src/test/java/org/cryptomator/ui/addvaultwizard/ReadMeGeneratorTest.java#L61)

[testCreateVaultStorageLocationReadmeRtf()](https://github.com/jupyter333/Cryptomator/blob/6b933fce646fd14ab7660912e7a3206f3419cc0d/src/test/java/org/cryptomator/ui/addvaultwizard/ReadMeGeneratorTest.java#L114)

## org.cryptomator.ui.common

Dans ce package, nous avons réalisé deux tests pour couvrir trois méthodes du `VaultService.java`. Le premier test couvre le service `createRevealTask`, tandis que le second concerne les méthodes `lock` et `reveal` d'un `Vault`. Ces tâches consistent à verrouiller et déverrouiller un `Vault`.

On a choisi ce package car nous avons remarqué qu'il y avait beacoup de méthodes non couverts. Puis, `VaultService.java` est une classe interessante qui s'occupe des services de verrouillage du vault.
faire des tests sur ce package nous a permis de mieux comprendre le comportement du vault.

[testCreateReveal()](https://github.com/jupyter333/Cryptomator/blob/6b933fce646fd14ab7660912e7a3206f3419cc0d/src/test/java/org/cryptomator/ui/common/VaultServiceTest.java#L54)
[testLockAndReveal()](https://github.com/jupyter333/Cryptomator/blob/6b933fce646fd14ab7660912e7a3206f3419cc0d/src/test/java/org/cryptomator/ui/common/VaultServiceTest.java#L66)

## org.cryptomator.ui.changepassword

Les méthodes qu'on a choisi de tester dans ce package sont `getStrengthDescription(Number score)` et `fulfillsMinimumRequirements(CharSequence password)` dans le fichier `PasswordStrengthUtil.java`.
Nous pensons qu'il était pertinent de tester ces fonctions car celles ci sont reliés directement à la cybersécurité de l'usager et a son interaction avec l'application. Plus précisement, le premiere fourni une description sur la force du mot de l'usager et la deuxième s'il respecte la longueur minimale.

[testGetStrengthDescriptionTooShort()](https://github.com/jupyter333/Cryptomator/blob/8be9e1d279152967adf9dbbf1fcf64e07eea1791/src/test/java/org/cryptomator/ui/changepassword/PasswordStrengthUtilTest.java#L37)

[testGetStrengthDescriptionValid()](https://github.com/jupyter333/Cryptomator/blob/8be9e1d279152967adf9dbbf1fcf64e07eea1791/src/test/java/org/cryptomator/ui/changepassword/PasswordStrengthUtilTest.java#L55)

[testGetStrengthDescriptionNoKey()](https://github.com/jupyter333/Cryptomator/blob/8be9e1d279152967adf9dbbf1fcf64e07eea1791/src/test/java/org/cryptomator/ui/changepassword/PasswordStrengthUtilTest.java#L73)

[testFullfillsMinimumrequirements()](https://github.com/jupyter333/Cryptomator/blob/8be9e1d279152967adf9dbbf1fcf64e07eea1791/src/test/java/org/cryptomator/ui/changepassword/PasswordStrengthUtilTest.java#L90)
