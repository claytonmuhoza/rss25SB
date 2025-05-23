# rss25SB
## installation

La base de donnée POSTGRESQL est placé dans un conteneur docker, il faut d’abord lancer la commande:
```
docker-compose up -d
```

cette commande va permettre de lance la base de donne
pour le backend docke, il faut créer un fichier .env qui va contenir les variables d’environnement qui vont permettre à l’application de savoir quel et y insérer les instructions suivantes:
```
POSTGRES_HOST=localhost
POSTGRES_USER=postgres
POSTGRES_PASSWORD=rss25SB
POSTGRES_DB=rss25db
POSTGRES_PORT=5430
```

et on lance l’application spring-boot avec la commande

```
export $(cat .env | xargs) && ./mvnw spring-boot:run
```