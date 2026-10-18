# Héritage JPA/Hibernate - Projet Heritage Faz

Projet Java JPA/Hibernate démontrant les 3 stratégies d'héritage : `SINGLE_TABLE`, `JOINED` et `TABLE_PER_CLASS`.

## 📸 Captures d'écran

### 1. Création de la table SINGLE_TABLE
*Une seule table pour toute la hiérarchie, avec une colonne discriminante.*

![Création SINGLE_TABLE](imm/creation_table1.png)

### 2. Création des tables JOINED
*Une table par classe (parente + filles), liées par clés étrangères.*

![Création JOINED](imm/creation_table2.png)

### 3. Création des tables TABLE_PER_CLASS
*Une table indépendante par classe concrète (colonnes dupliquées).*

![Création TABLE_PER_CLASS](imm/creation_table3.png)

### 4. Insertion des données
*Insertion des entités dans les différentes tables selon leur stratégie.*

![Insertion des données](imm/insertion.png)

### 5. Lecture polymorphique (SELECT)
*Récupération des entités via des requêtes JPQL polymorphiques.*

![Lecture polymorphique](imm/select.png)

### 6. Tests unitaires JUnit
*Validation des 3 stratégies d'héritage avec 4 tests unitaires.*

![Tests unitaires](imm/test.png)