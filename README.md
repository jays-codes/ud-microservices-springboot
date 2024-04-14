# ud-microservices-springboot
Jay's proj repo for Microservices with SpringBoot, Docker, Kubernetes training (conducted by M. Reddy - udemy)
List below ARE NOT NOTES. They are topics covered as of latest.

proj: accounts (springboot 3.2.4 : jdk21, mvn, jar | spring web, H2DB, spring data JPA, spring boot actuator, spring boot DevTools, Lombok, Validation)
- Entities: BaseEntity @MappedSuperclass, Lombok [@Getter, @Setter, @ToString]; Customer Entity @Entity, Lombok [@AllArgsConstructor, @NoArgsConstructor], @Id, @GeneratedValue, @GenericGenerator; Accounts Entity
- H2 Setup, Modified application.yml, schema.sql (customer, accounts table)
- created @RestController - AccountsController, GET /hworld api: getHWorld(); comfigured lombok
- created first springboot microservice demo project; jayslabs.microservicedemo.accounts

proj: proj-test-1 (springboot 3.2.4 | jdk21 | mvn | jar)
- created test project to test new workspace (local git, gh, eclipse)