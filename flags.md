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
Ajout du bloc `strategy`  configurer
des paramètres d'éxecution comme les flags,
`Matrix` pour donner une suite d'arguments, et `jmv_flag`
pour nos choix de *flags*.
Ces flags sont lues dans le `env` dans l'étape *Build and
Test*.

**Tenez compte que cette action est aussi automatique et s'exécute à chaque *push*,
*pull request***. Cette action calcule le taux de couverture comme dans la tâche 2; 
elle donne clairement chaque execution de flag comme il est montré dans l'image suivant.

## Element d'humeur.
On a mis un fichier contenant du ascii art, il s'agit d'un bonhomme de southpark, lorsque
le workflow est reussi sans problème il s'affiche 

![image](/1.png)