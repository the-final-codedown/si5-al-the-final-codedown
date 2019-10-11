<center>
# Roadmap
</center>

## Étape 1

Produit minimum viable contenant : 
les fonctionnalités basiques de paiement
des tests de charge sur ces opérations.

## Étape 2

- Ajout des fonctionnalités concernant les comptes épargnes et la gestion du plafond
- Tests de charge avec plusieurs utilisateurs effectuant des opérations en parallèle.

## Étape 3
- Déploiement de la solution sur un orchestrateur de conteneurs afin de tester l'adaptation des différents services lors d'une montée en charge.
- Recherche de *bottlenecks* potentiels et modifications de la solution pour en améliorer les performances selon ce qui sera trouvé, axé en particulier sur les ressources CPU et mémoire utilisées.

## Étape 4
- Tests en conditions réelles avec simulation de différents types de réseaux (2G/3G, etc.).
- Analyse de la vitesse et scaling horizontal et amélioration si possible du temps de démarrage des services.

## Étape 5
- Déploiement sur plusieurs machines de la solution afin de tester sa capacité à monter en charge sur un réseau de machines.
- Tests sur le comportement de la solution en cas de crash de composant(s).

## Étape 6
- Analyse des coûts de déploiement d'une telle solution.
- Benchmark rétrospectif des différentes versions.