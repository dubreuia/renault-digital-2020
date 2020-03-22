# Cities

## Installation

Using MySQL:

- Install [docker](https://www.docker.com/get-started)
- Run latest mysql image `docker run --name mysql_renault -e MYSQL_ROOT_PASSWORD=12345 -d mysql:latest`
- Make sure the "mysql_renault" image is running `docker ps` (use port in application.yml)
- Use inspect to find the IP of the running docker `docker inspect mysql_renault` (use IP in application.yml)

Using Postgresql:

- Use Frank's installation

## Start the server

Configure the database in [application.yml](./src/main/resources/application.yml) with chosen database. In this folder, start the Spring Boot server (on [http://localhost:8008](http://localhost:8008)):

```bash
# Linux & MacOS
./gradlew bootRun

# Windows
gradlew bootRun
```

## References

- https://dzone.com/articles/how-does-spring-transactional

## Outline

- spring web
    - context web : https://www.baeldung.com/spring-web-contexts
- hibernate / jpa
    - TODO add @enumerated
    - @NotNull vs @Column(nullable) : https://www.baeldung.com/hibernate-notnull-vs-nullable
    - TODO spring.jpa.hibernate.ddl-auto=create-drop
    - persistance context : https://www.baeldung.com/jpa-hibernate-persistence-context
        - TODO entity manager : root manager
        - TODO entity manager : isDirty
        - TODO transaction scope vs extended scope
        - TODO transaction scope : all component share same ctx
        - TODO make example on that : https://github.com/eugenp/tutorials/tree/master/persistence-modules/hibernate5-2
        - TODO session factory / save / refresh / flush / contains / dirty
        - TODO refresh : reread, usefull for a clear
        - TODO failed to lazily initialize a collection of role: com.renault.entities.User.followedCities, could not initialize proxy - no Session
    - unidirectionnal mapping : https://docs.oracle.com/cd/E19798-01/821-1841/bnbqk/index.html
    - bidirectionnal mapping : https://docs.oracle.com/cd/E19798-01/821-1841/bnbqj/index.html
    - one-to-one : https://www.baeldung.com/jpa-one-to-one
        - owning side : object, referencing side : object
        - @JoinColumn : owning side only
        - join table can be used
    - one-to-many : https://www.baeldung.com/hibernate-one-to-many
        - owning side : list, referencing side : object
        - owning side on the many side with mappedBy
        - owning side on the one side with @JoinColumn
        - owning side : https://www.baeldung.com/jpa-joincolumn-vs-mappedby
        - lazy eager : https://www.baeldung.com/hibernate-lazy-eager-loading
        - TODO use transactions
    - many-to-many : https://www.baeldung.com/jpa-many-to-many
        - also : https://www.baeldung.com/hibernate-many-to-many
        - owning side : list, referencing side : list
        - join table with an attribute
            - https://www.baeldung.com/jpa-many-to-many#many-to-many-using-a-composite-key
            - https://www.baeldung.com/jpa-many-to-many#many-to-many-with-a-new-entity
            - new entity way simpler
        - step 1 : fave city, step 2 : fave city with rating
    - fetchmode : https://www.baeldung.com/hibernate-fetchmode
        - TODO select vs batch size
        - TODO join fetch (eager only)
        - https://www.baeldung.com/hibernate-fetchmode#fetchmode-vs-fetchtype
    - cascading : https://www.baeldung.com/jpa-cascade-types
        - TODO
    - spring object mapping : https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
        - TODO
