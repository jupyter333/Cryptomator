# Tâche #3
**Jhosim Agudelo et César Rodriguez**

## 5 flags 

Pour notre tâche 3 nous avons pris 5 *flags*.

```bash
          # Different types of JVM flags
          - "-XX:+UseSerialGC"            # Type GC
          - "-Xmx512m"                    # Mémoire maximale
          - "-XX:+AlwaysPreTouch"         # Aide à retoucher la memoire pour optimiser son allocation de mémoire
          - "-XX:+PrintCompilation"       # Log de compilation
          - "-XX:+ShowCodeDetailsInExceptionMessages" # Afficher les détails dans les messages d'exception
```

Chacune des *flags* s'exécutent sur l'action github `test`. Cette action donne des logs clairs
sur elles.

![image](/1.png)

## Changements dans l'action github.

Dans le fichier `test.yml` nous avons ajouté du code dans les blocs suivants :

### On:

On a écrit la ligne `workflow_dispatch:` pour avoir un déclencheur 
qui permet d'exécuter manuellement ce workflow. On a fait ce choix, car
cette action s'exécutait seulement sur **push** ou **pull request**.
Il était plus pratique de travailler sur github directement sans avoir à
faire de *push* à chaque fois.

### Jobs
Ajout du bloc `strategy`  configurer
des paramètres d'éxecution comme les flags,
`Matrix` pour donner une suite d'arguments, et `jmv_flag`
pour nos choix de *flags*.
Ces flags sont lues dans le `env` dans l'étape *Build and
Test*.

**Tenez compte que cette action est aussi automatique et s'exécute à chaque *push* et
*pull request***. 

## Pourquoi ces flags?

Pour cette tâche. On a cherché de l'information sur internet par rapport aux [options de la JVM](https://bell-sw.com/blog/guide-to-jvm-memory-configuration-options/). 
Pour la plupart de cas, on a essayé des possibles cas limites.

 **Type de flag :**



`-Xmx512m` **Type de flag 

| Flag              |       Type        | Description                                                                                                                                                                                                                                                                                                                      | Observations                                                                                                                          |
|:------------------|:-----------------:|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------| 
| `XX:+UseSerialGC` | Garbage collector | Ce flag active le Serial Garbage Collector, qui utilise un seul thread pour la collecte des ordures. Il est adapté aux petites applications, car il est simple et consomme peu de ressources, bien qu’il puisse ralentir les applications plus grandes lors du nettoyage. Nous l'avons choisi pour tester un possible cas limite | **Dans notre cas particulier ce type de garbage collector n'a pas affecté la compilation, et tout ce passe dans un temps acceptable** |
| `-Xmx512m`        |      Mémoire      | Définit la taille maximale du tas mémoire à 512 Mo (mémoire que le JVM peut utiliser pour stocker les objets). Ce paramètre empêche l'application d’utiliser plus de mémoire que ce qui est défini, ce qui est utile pour contrôler la consommation de mémoire et éviter les erreurs liées au manque de mémoire.                 |
| Codecademy Tee    |       False       | 19.99                                                                                                                                                                                                                                                                                                                            |
| Codecademy Hoodie |       False       | 42.99                                                                                                                                                                                                                                                                                                                            |












## Element d'humeur.

Lorsque la compilation est réussi, il y a un ascii art qui s'affiche, cet ascii se trouve dans le fichier
`humor.txt`

![image](/2.png)

