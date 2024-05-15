# ud-microservices-springboot
Jay's proj repo for Microservices with SpringBoot, Docker, Kubernetes training (conducted by M. Reddy - udemy)
List below ARE NOT NOTES. They are topics covered as of latest.

proj: configserver (springboot 3.2.5 : jdk21, mvn, jar | Config Server, spring boot actuator)
- removed rabbitmq, spring cloud monitor dependencies from all services; modifed pom, app.yml
- updated other services pom to remove <from>
- removed <from> in jib plugin is this is causing the curl to not be recognized
- updated jib version; added jib dep for configserver
- modified yml to monitor liveness, readiness
- added dependency: spring-cloud-config-monitor; setup github webhook
- read property ymls from external folder
- refactored ymls, moved to Configserver
- modified Application: @EnableConfigServer
- initial project setup and commit

repo: ud-microservices-springboot-config
- added common-config.yml for qa and prod env
- refactored compose.yml to separate common properties to another file: common-config.yml
- added definition for rabbitmq; rabbitmq dependency of configserver
- modify compose file to ensure liveness and readiness of configserver; configserver dependency of other microservices
- created default/docker-compose.yml
- created folder for docker-compose
- initial repo setup

repo: gh-config-demo
- added dep: spring-cloud-starter-bus-amqp; added rabbitmq configuration
- Updated contactsInfoDTOs to change prop values at runtime via actuator
- modified app.yml for cloud.config.server.git entries
- external github repo hosting yml property files used by microservices

proj: cards (springboot 3.2.4 : jdk21, mvn, jar | spring web, H2DB, spring data JPA, spring boot actuator, spring boot DevTools, Lombok, Validation)
- fixed controller constructor to init srvc
- removed h2 dependency; add mysql dep: mysql-connector-j; set application.yml to use mysql properties;
- added Config Client dependency to cards microservice project; modified
app.yml to load profile from Configserver
- Added Springboot profiles for qa, prod env. Set spring.profiles.active
- passed values from app.yml to dto:CardsInfoDTO (java record) property in controller - via @Autowired; created GET REST API call to return ResponseEntity<LoansContactInfoDTO>
- passed values from environment variables to env:Environment property in controller; via @Autowired; created GET REST API call to return value from env.getProperty()
- passed property from app.yml to property in controller; via autoinject @Value; created GET REST API call to return value
- fixed jib to work with jdk 21; enabled jib for all microservices: accounts, loans, cards
- generated docker image via google jib; modified pom, used jdk 17
- Cards Microservice; Delete Card API; modified - controller,
service, repository, deleteByMobileNumber()
- Cards Microservice; Update Card Details API; modified - controller,
service, repository, findByCardNumber(); 
- Cards Microservice; Fetch Card Details API; modified - controller, service
- Cards Microservice; Create Cards API; added controller, repository, service, entity, dto, mapper, exception, constants, audit;
- initial project setup and commit

proj: loans (springboot 3.2.4 : jdk21, mvn, jar | spring web, H2DB, spring data JPA, spring boot actuator, spring boot DevTools, Lombok, Validation)
- fixed controller constructor to init srvc
- removed h2 dependency; add mysql dep: mysql-connector-j; set application.yml to use mysql properties;
- added Config Client dependency to project; modified app.yml to load profile from Configserver
- Added Springboot profiles for qa, prod env. Set spring.profiles.active
- passed values from app.yml to dto:LoansInfoDTO (java record) property in controller - via @Autowired; created GET REST API call to return ResponseEntity<LoansContactInfoDTO>
- passed values from environment variables to env:Environment property in controller; via @Autowired; created GET REST API call to return value from env.getProperty()
- passed property from app.yml to property in controller; via autoinject @Value; created GET REST API call to return value
- fixed jib to work with jdk 21; enabled jib for all microservices: accounts, loans, cards
- modified pom.xml to generate docker image via Buildpacks
- Corrected Update Loan API logic to disallow updating Loan with existing phone number, allow updating to non existing phone number
- Loans Microservice; Delete Loan API; modified - controller,
service, repository, deleteByMobileNumber(); 
- Loans Microservice; Update Loan Details API; modified - controller,
service, repository, findByLoanNumber(); edit AccountsService
- Loans Microservice; Fetch Loan Details API; modified - controller, service
- Loans Microservice; Create Loans API; added controller,  repository, service, entity, dto, mapper, exception, constants, audit; 
- initial project setup and commit

proj: accounts (springboot 3.2.4 : jdk21, mvn, jar | spring web, H2DB, spring data JPA, spring boot actuator, spring boot DevTools, Lombok, Validation)
- fixed controller constructor to init srvc
- removed h2 dependency; add mysql dep: mysql-connector-j; set application.yml to use mysql properties;
- moved docker-compose to central location
- added Config Client dependency to project; modified app.yml to load profile from Configserver
- added Springboot profiles for qa, prod env. Set spring.profiles.active
- passed values from app.yml to dto:AccountsInfoDTO (java record) property in controller - via @Autowired; created GET REST API call to return ResponseEntity<AccountsContactInfoDTO>
- passed values from environment variables to env:Environment property in controller; via @Autowired; created GET REST API call to return value from env.getProperty()
- passed property from app.yml to property in controller; via autoinject @Value; created GET REST API call to return value
- fixed jib to work with jdk 21; enabled jib for all microservices: accounts, loans, cards
- created docker-compose.yml to run accounts, loans, cards microservices
- wrote Dockerfile for Accounts Microservice
- Enhance Documentation: method APIs @Tag, @Operation, @ApiResponse; DTOs @Schema [name, description, example]; Modified update to define @Schema for ErrorReponseDTO
- Wrote documentation via springdoc-openapi; added dep - springdoc-openapi-starter-webmvc-ui; modify AccountsApplication - @OpenAPIDefinition, @Info, @Content, @License, @ExternalDocumentation
- used Spring Data to handle updates to Audit Columns: @CreatedDate, @CreatedBy, @LastModifiedDate, @LastModifiedBy; @Component AuditAwareImpl, getCurrentAuditor(); BaseEntity, @EntityListeners; in SpringBootApp, @EnableJpaAuditing
- added Validation annotations - jakarta.validation.constraints, @NotEmpty, @Size, @Email, @Pattern(regexp); Controller - @Validated, @Valid (for @RequestBody), @Pattern (for @RequestParam); override handleMethodArgumentNotValid() in GlobalExceptionHandler
- Added Global Exception handler, handleGlobalException() for Exception
- Create Delete Account API; modify AccountsService, deleteAccount(Sring): boolean, custrepo.deleteById(Long); modify AccountsRepository add void deleteByCustomerId(), @Transactional;create DELETE API in controller, deleteAccountDetails(@RequestParam mobile): ResponseEntity<ResponseDTO>
- Create update Accounts API; modify AccountsService, updateAccount(CustomerDTO): boolean;  create PUT API in controller, updateAccountDetails(@RequestBody CustomerDTO): ResponseEntity
- Create fetch Account API; modify AccountsService, fetchAccount(String): CustomerDTO; Modify AccountsRepo findByCustomerId(); modify CustomerDTO to add AccountsDTO field; add ResourceNotFoundException + handler; create GET API in controller, fetchAccountDetails(@RequestParam): ResponseEntity<CustomerDTO>
- Write Create Account API (POST); CustomerMapper, mapToCustomerDTO(), mapToCustomer(); AccountsServiceImpl - createAccount(CustomerDTO), private createNewAccount(Customer); Exceptions - CustomerAlreadyExistException; custom CustomerRepository.findByMobileNumber(), Optional<Customer>, isPresent(); GlobalExceptionHandler, @ControllerAdvice, handleCustomException(CustomException, WebRequest), @ExceptionHandler
- define API AccountsController; @RequestMapping; define createAccount() @PostMapping, @RequestBody, ResponseEntity [status(), HttpStatus.CREATED, .body]; Create Service: IAccountsService interface, AccountsServiceImpl, @Service; AccountsConstants; AccountsMapper - mapToAccountsDTO(), mapToAccounts(); 
- created DTO classes: AccountsDTO, CustomerDTO, @Data; ResponseDTO, ErrorResponseDTO
- create Customer, Accounts Repositories: jpa.repository.JpaRepository interface, @Repository
- Entities: BaseEntity @MappedSuperclass, Lombok [@Getter, @Setter, @ToString]; Customer Entity @Entity, Lombok [@AllArgsConstructor, @NoArgsConstructor], @Id, @GeneratedValue, @GenericGenerator; Accounts Entity
- H2 Setup, Modified application.yml, schema.sql (customer, accounts table)
- created @RestController - AccountsController, GET /hworld api: getHWorld(); comfigured lombok
- created first springboot microservice demo project; jayslabs.microservicedemo.accounts

proj: proj-test-1 (springboot 3.2.4 | jdk21 | mvn | jar)
- removed permanently
- created test project to test new workspace (local git, gh, eclipse)