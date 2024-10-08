## org.cryptomator.common.keychain

Dans ce package, nous avons travaillé sur le fichier `KeychainManagerTest.java` concernant la méthode `deletePassphrase()`. Nous avons effectué un test unitaire simple pour vérifier que cette fonction supprime correctement une clé. Nous commençons par stocker une clé dans le `keychainManager`, puis nous la supprimons et effectuons une assertion pour confirmer qu'elle n'est plus présente.

## org.cryptomator.common.settings

Dans ce package, nous avons travaillé sur le fichier `VaultSettingsTest`, plus précisément sur la méthode `serialize()`. Cette méthode permet de stocker les configurations du `Vault` sous format JSON.
Pour notre test unitaire, nous avons créé un JSON fictif grâce à la dépendance `javafaker` dans notre fonction `setUp()`, puis nous avons fait des assertions sur chacun des paramètres attendus.



## org.cryptomator.ui.addvaultwizard

Dans ce package, nous avons travaillé sur le fichier `ReadMeGeneratorTest`, qui s'occupe de créer les fichiers README dans le `Vault` (README d'accueil). Plus spécifiquement, nous avons testé les méthodes `createVaultStorageLocationReadmeRtf()` et `createVaultAccessLocationReadmeRtf()`. Pour ce test, nous avons utilisé des mocks et des assertions simples pour garantir le bon fonctionnement du code.
Nous pensons qu'il était pertinent de tester ces méthodes puisque c'est un document contenant des informations importantes pour l'usager. Donc nous voulions tester que le format des informations était adéquat.

[testCreateVaultAccessLocationReadmeRtf](www.todojouterref.ca)

[testCreateVaultStorageLocationReadmeRtf()]()

## org.cryptomator.ui.common

Dans ce package, nous avons réalisé deux tests pour couvrir trois méthodes du `VaultService`. Le premier test couvre le service `createRevealTask`, tandis que le second concerne les méthodes `lock` et `reveal` d'un `Vault`. Ces tâches consistent à verrouiller et déverrouiller un `Vault` dans Cryptomator.

## org.cryptomator.ui.changepassword

Les méthodes qu'on a choisi de tester dans ce package sont `getStrengthDescription(Number score)` et `fulfillsMinimumRequirements(CharSequence password)`.
Nous pensons qu'il était pertinent de tester ces fonctions car celles ci sont reliés directement à la cybersécurité de l'usager et a son interaction avec l'application. Plus précisement elles vérifient 
la force du mot  de passe de l'usager et aussi s'il respecte la longueur minimale.

[testGetStrengthDescriptionTooShort()](www.todojouterref.ca)

[testGetStrengthDescriptionValid()]()

[testGetStrengthDescriptionNoKey()]()

[testFullfillsMinimumrequirements()]()