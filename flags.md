# Tâche #3
**Jhosim Agudelo et César Rodriguez**

Pour ce notre tâche 3 nous avons pris 5 *flags* pour la compilation sur l'action github.

```bash
          # Different types of JVM flags
          - "-XX:+UseSerialGC"            # Type GC
          - "-Xmx512m"                    # Mémoire maximale
          - "-XX:+AlwaysPreTouch"          # Ensures all memory is pre-touched to help optimize memory allocation and avoid page faults
          - "-XX:+PrintCompilation"       # Log de compilation
          - "-XX:+ShowCodeDetailsInExceptionMessages" # Afficher les détails dans les messages d'exception
```
Ces actions s'executent sur l'action github dans