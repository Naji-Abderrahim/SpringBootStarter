# Contract API

## !THIS IS JUST A TEST PROJECT ALL THE SOURCES ARE USED IN LOCAL

Mini projet de test technique Spring Boot - Gestion des contrats.

## Endpoints CRUD pour les Contrats

    ✅ POST /contracts: Permet de créer un nouveau contrat.

    ✅ GET /contracts: Permet de lister tous les contrats existants.

    ✅ GET /contracts/{id}: Permet d'afficher les détails d'un contrat spécifique par son ID.

    ✅ PUT /contracts/{id}: Permet de mettre à jour un contrat existant par son ID.

    ✅ DELETE /contracts/{id}: Permet de supprimer un contrat par son ID.


## Règles de Validation Implémentées

    ✅ Le titre du contrat et le nom du client sont des champs obligatoires.

    ✅ La date de fin du contrat ne peut pas précéder la date de début.

## Bonus

    ✅ Filtrage des contrats par status via '?status='
    ✅ Api Documetation via Swagger-ui
    ✅ Tests unitaires simples sur la couche service

## clone le projet
```bash
git clone https://github.com/Naji-Abderrahim/SpringBootStarter
cd SpringBootStarter
```
## Lancer le projet
```bash
mvn spring-boot:run

```
## Lancer les tests unitaires
```bash
mvn test
```

## API Documetation
vous pouvez accéder à la documentation interactive de l'API via Swagger UI à l'adresse : [http://127.0.0.1:8080/swagger-ui.html]
