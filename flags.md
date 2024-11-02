# Tâche #3
**Jhosim Agudelo et César Rodriguez**

Pour ce notre tâche 3 nous avons pris 5 *flags* pour la compilation sur l'action github.

```bash
          # Different types of JVM flags
          - "-XX:+UseSerialGC"            # Type GC
          - "-Xmx512m"                    # Mémoire maximale
          - "-XX:+AlwaysPreTouch"         # Aide à retoucher la memoire pour optimiser son allocation de mémoire
          - "-XX:+PrintCompilation"       # Log de compilation
          - "-XX:+ShowCodeDetailsInExceptionMessages" # Afficher les détails dans les messages d'exception
```

Dans le fichier `test.yml` nous avons ajouté du code dans les blocs suivants :

### On:

On a écrit la ligne `workflow_dispatch:` pour avoir un déclencheur 
qui permet d'exécuter manuellement ce workflow. On a fait ce choix, car
cette action s'exécutait seulement sur **push** ou **pull request**.
Il était plus pratique de travailler sur github directement sans avoir à
faire de *push* à chaque fois.

### Jobs
Ajout du bloc `strategy` qui est un mot clé github pour configurer
des paramètres d'éxecution comme les flags,
`Matrix` pour donner une suite d'arguments et `jmv_flag`
pour nos choix de *flags*.
Ces flags sont lues dans le `env` de l'étape *Build and
Test*.

![image](/1.png)