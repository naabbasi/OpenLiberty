To start the postgres server using docker
``` /bin/sh
docker run --rm --name postgres-pg15 -e POSTGRES_PASSWORD=p@ssw@rd -e POSTGRES_USER=postgres -e POSTGRES_DB=postgres -p 5432:5432 postgres:15.2-alpine
```