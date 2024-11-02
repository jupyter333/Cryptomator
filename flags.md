# Tâche #3
**Jhosim Agudelo et César Rodriguez**

## 5 flags 

Pour notre tâche 3 nous avons pris 5 *flags*.

```bash
          # Different types of JVM flags
          - "-XX:+UseSerialGC"            # Type GC
          - "-Xmx512m"                    # Mémoire maximale
          - "-XX:TieredStopAtLevel=1"     # Type de compilation C1
          - "-XX:+PrintCompilation"       # Journalisation de la compilation (logging)
          - "-XX:+UnlockExperimentalVMOptions" # Optimisation expérimentale
```

Chacune des *flags* s'exécutent sur l'action github `test`. En donnant clairement laquelle s'exécute.

![image](/1.png)

## Changements dans l'action github.

Dans le fichier `test.yml` nous avons ajouté du code dans les blocs suivants :

### On:

On a écrit la ligne `workflow_dispatch:` pour avoir un déclencheur 
qui permet d'exécuter manuellement ce workflow. On a fait ce choix, car
cette action s'exécutait seulement sur **push** ou **pull request**.
Il était un choix nécessaire pour vérifier notre workflow sans toucher au code.

### Jobs
Ajout du bloc `strategy` pour permettre de configurer
les paramètres d'exécution sur le JVM,
`Matrix` pour donner une suite d'arguments, et `jmv_flag`
pour nos choix de *flags*.
Ces flags sont lues dans le `env` dans l'étape *Build and
Test*.


## Pourquoi ces flags?

Pour cette tâche. On a lu les ressources données dans les consignes,
mais aussi, on a cherché de l'information sur internet par rapport aux [options de la JVM](https://bell-sw.com/blog/guide-to-jvm-memory-configuration-options/). 

Pour la plupart de cas, on a essayé des possibles cas limites.

| Flag                              |             Type             | Description                                                                                                                                                                                                                                                                                                      | Observations                                                                                                                                                                                                                                                                                      |
|:----------------------------------|:----------------------------:|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| `XX:+UseSerialGC`                 |      Garbage collector       | Ce flag active le Serial Garbage Collector, qui utilise un seul thread pour la collecte des ordures. Il est adapté aux petites applications, car il est simple et consomme peu de ressources, bien qu’il puisse ralentir les applications plus grandes lors du nettoyage.                                        | On a choisi cette configuration pour tester un possible cas limite de conflit de threads. Dans notre cas particulier, ce type de garbage collector n'a pas affecté la compilation, et tout ce passe dans un temps acceptable.                                                                     |
| `Xmx512m`                         |           Mémoire            | Définit la taille maximale du tas mémoire à 512 Mo (mémoire que le JVM peut utiliser pour stocker les objets). Ce paramètre empêche l'application d’utiliser plus de mémoire que ce qui est défini, ce qui est utile pour contrôler la consommation de mémoire et éviter les erreurs liées au manque de mémoire. | Nous avons remarqué que la taille maximale de mémoire allouée a eu peu d'impacte sur notre github action, On a testé avec les tailles 64, 128, 256 et 512 donnant comme résultat des changement d'ordre d'à peine quelques secondes. On a choisi ce flag pour tester le maximum de mémoire        |
| `XX:TieredStopAtLevel=1`          |         Compilation          | Limite la compilation JIT de la JVM au niveau 1, qui active uniquement le mode d'interprétation avec un profilage basique, sans optimisations avancées. Cela permet de réduire le temps de démarrage et de minimiser les optimisations, ce qui est utile pour le profilage, le débogage ou les tests.            | Nous avons choisi cet option pour voir un type de compilation C1 qui était expliqué dans un des annexes pour cette tâche, la compilation se fait sans problèmes.                                                                                                                                  |
| `XX:+PrintCompilation`            |           Logging            | Ce flag donne des logs sur la compilation: Horodatage (Timestamp), Numero de séquence, niveau d'optimisation (1,2,3.. ou n aucun), Type de la méthode compilée.                                                                                                                                                  | Puisque ce projet est assez large les logs le sont aussi, le logging donne des détails avancés sur la compilation, mais nous remarquons que les types de compilation peuvent varier. La compilation s'exécute sans problèmes. Ce flag aide a voir un exemple de journalisation sur notre workflow |
| `XX:+UnlockExperimentalVMOptions` | Optimisations Expérimentales | Ce flag permet l'utilisation des options en phase de tests pour la version courant de Java                                                                                                                                                                                                                       | On voit que la compilation se réalise sans aucun problème. On a choisi ce type de *flag* pour voir si le programme fonctionnait bien avec des optimisation expérimentales                                                                                                                         | 

## Element d'humeur.

Lorsque la compilation est réussi, il y a un ascii art qui s'affiche, cet ascii se trouve dans le fichier
`humor.txt`

![image](/2.png)

