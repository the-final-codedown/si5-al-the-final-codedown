# Status

## Health

- Week 07 : ![](green.png)

- Week 06 : ![](yellow.png)

- Week 05 : ![](green.png)

- Week 04 : ![](yellow.png)

- Week 03 : ![](green.png)

- Week 46 : ![](green.png)

- Week 45 : ![](yellow.png) 

- Week 44 : ![](green.png)

- Week 43 : ![](green.png)

- Week 42 : ![](yellow.png)

- Week 41 : ![](green.png)

- Week 40 : ![](yellow.png)


## Week 39

- Délimitation du scope
- Création d'un diagramme use case

## Week 40

- Ajout du fichier `architecture.pdf`
- Recentrage du scope pour se concentrer sur moins de fonctionnalités que prévu initialement et plus sur la partie non fonctionnelle des tests de scaling


## Week 41

- Deuxième rendu d'un scope fonctionnel plus limité et un scope non fonctionnel plus intéressant pour la mise à l'échelle
- Création du diagramme de composants, de séquences et d'interface, de la roadmap et du plan détaillé

## Week 42

- Initialisation des repository et répartition des tâches au sein de l'équipe.
- Début du développement, partage des connaissances sur Spring/Go pour bien débuter avec des mini démo sur certains composants de l'appli.
- Changement dans l'architecture, création d'un composant monolithe.
  L'autre partie ayant un besoin de scalabilité sera implémenté en micro-services.

## Week 43

- Création du micro-service Cap Updater en Go
- Premier test Gatling sur Cap Updater
- Mise sur Docker Compose du micro-service Cap Updater
- Dockerisation du monolithe Spring
- Ajout d'un bus Kafka et du Rolling History


## Week 44

- Mise en place de tests d'interfaces sur le module Spring
- Tests de charge avec analyse des résultats sur le module Cap updater : recherches sur l'origine d'un bottleneck du côté de go (baisse des performances après 40 secondes de benchmark)
- Suppression du framework go-micro du projet

## Week 45 

- Mise en place du module *Savings* avec les tests
- Ajout du *Pretty Dump* 
- Ajout des consumer et producer kafka dans les services Go
- Suppression des mocks du monolithe afin d'établir une communication avec les micro-services en Go 
- Création d'une team Postman afin de partager les requêtes

## Week 46

- Ajout des requêtes Postman essentielles pour la démonstration
- Mise à jour du Pretty Dump pour être plus explicite sur le statut du système
- Travail sur la démonstration (création des slides et répétition)
- Walking Skeleton prêt

## Week 03

- Design d'une nouvelle architecture orientée micro-service
- Mise en place d'un cluster Kubernetes dans l'IBM Cloud

## Week 04
- Mise en place d'une base du nouveau micro-service Profile
- Premier micro-service du découpage du monolithe spring fonctionnelle
- Tests d'interface sur Profile 

## Week 05

- Mise en place de scripts de déploiements pour les deux architectures
- Utilisation de docker pour la compilation des micro-services en go
- Pretty dump mis dans un micro-service à part
- Amélioration de la gestion d'erreurs en cas de compte inexistant pour le paiement
- Correction de bug suite aux tests sur Profile
- Création du micro-service Savings

## Week 06

- Création du micro-service RollingHistory pour mettre à jour la fenêtre glissante sur les 7 derniers jours pour chacun des comptes
- Test de charge sur les parties impactées par la séparation en micro-services
- Facilitation du déploiement d'une ou l'autre des architecture grâce à des scripts et de la composition de docker-compose
- Correction d'un bug de concurrence à la première écriture des cap (duplication en base de données) 

## Week 07

- Uniformisation des schémas de base de données entre architecture micro et hybride
- Uniformisation de certaines requêtes et réponses entre les deux architectures : un test identique fonctionnel sous les deux architectures.