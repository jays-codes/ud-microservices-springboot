# ud-microservices-springboot
Jay's proj repo for Microservices with SpringBoot, Docker, Kubernetes training (conducted by M. Reddy - udemy)
List below ARE NOT NOTES. They are topics covered as of latest.

repo: ud-microservices-springboot-helm
- branch: helm; deployed all services and microservices in K8s cluster via helm charts; tried debugging issue with tempo ingester going crashloopbackoff;
- branch: helm; implemented Grafana service in K8s via bitnami template, modified values.yaml; configured datasources in grafana values.yaml
- branch: helm; implemented Loki and Tempo service in K8s via bitnami template, modified values.yaml
- branch: helm; implemented Prometheus service in K8s via bitnami template, modified values.yaml
- branch: helm; implemented Kafka service in K8s via bitnami template, modified values.yaml
- branch: helm; implemented Keycloak service in K8s via bitnami template, modified values.yaml
- branch: helm; configured environment-specific helm chart to deploy all microservices; dev, qa, prod
- branch: helm; configured helm charts for other microservices: loans, card, configserver, eurekaserver, gatewayserver, message
- branch: helm; configured accounts microservice helm charts; values.yaml, cart.yaml; linked to dependencies
- branch: helm; created helm chart /template common across jayslabs microservices: service, deployment, configmap 
- branch: helm; Repo with Helm related updates [branch:helm]

repo: ud-microservices-springboot-k8s
- branch:kubernetes; modified minifest files: accounts, configmaps
- branch:kubernetes; added K8s manifest files - eurekaserver, gatewayserver, accounts, loans, cards, keycloak
- branch:kubernetes; added K8s config map file - configmaps.yml
- branch:kubernetes; added K8s manifest file - configserver.yml
- setup local K8s cluster in docker
- repo for Kubernetes config-related yaml files: dashboard-adminuser, dashboard-rolebinding, secret

proj: message (springboot 3.3.1 : jdk21, mvn, jar | Spring Cloud Functions)
- updated for Apache Kafka; added compose entries for Kafka; updated application.yml for kafka entries
- modified application.yml, added binding to publish event to communication-sent queue
- message microservice is subscribe to, and reads from event broker (rabbitmq); only logs event for now
- updated for RabbitMQ, spring cloud stream, spring cloud functions; added sendCommunication() in createAccount() to send event message dto to message broker microservice; Autowired StreamBridge bean in AccountServiceImpl
- added dep:spring-cloud-starter-function-web (temp); modified application.yml
- implemented Functions<> in MessageFunctions
- create AccountsMessageDTO record; MessageFunctions 
- init project setup

repo: ud-microservices-springboot-config
- updated for Apache Kafka; added compose entries for Kafka; updated application.yml for kafka entries
- updated for Event driver microservices, rabbitmq; added compose entries for message microservice and rabbit images
- updated for OAuth2; used Authentication Server; removed port mapping for microservices; updated compose yml file to add keycloak entry
- modified to use OAuth 2.0; modified image versions to v10
- modified compose to add definitions for tempo/opentelemetry
- updated to use OpenTelemetry - pom, app.yml, added logging pattern to generate tags,trace_id,span_id; updated microservices to v9
- updated compose for prometheus; added datasource.yaml to separate grafana ds; linked prometheus to grafana
- fixed docker-compose for prod version; runs grafana, loki, alloy, minio; updated for v7
- updated compose yml for redis section
- updated docker-compose.yml for gatewayserver
- updated docker compose files for EurekaServer, FeignClient updates
- updated docker image version (v4h2)
- H2 version of section 7; app.yml, pom, docker-compose.yml; updated verion from v3 to v3h2
- updated docker-compose.yml, common-config.yml for all env, to set mysql containers
- added common-config.yml for qa and prod env
- refactored compose.yml to separate common properties to another file: common-config.yml
- added definition for rabbitmq; rabbitmq dependency of configserver
- modify compose file to ensure liveness and readiness of configserver; configserver dependency of other microservices
- created default/docker-compose.yml
- created folder for docker-compose
- initial repo setup

proj: all microservices
- updated images for v13; updated for Apache Kafka; added compose entries for Kafka;
- updated images for v12; used compose to run containers for current topic: event driven microservices/rabbitmq
- updated google jib version

proj: gatewayserver (springboot 3.2.6 : jdk21, mvn, jar | Reactive Gateway, Eureka Discovery Client, Config Client, SpringBoot Actuator, SpringBoot Devtools)
- implemented Authorization, KeycloakConverter class, hasRole(), grantedAuthoritiesExtractor(), keycloak roles to SimpleGrantedAuthority instances 
- updated for implementing gatewayserver as Secured Resource Server; dep added: spring-boot-starter-security, spring-security-oauth2-resource-server, spring-security-oatuh2-jose; created SecurityConfig class, springSecurityFilterChain(); modified app.yml to get public cert from auth server
- updated to use OpenTelemetry - pom, app.yml, added logging pattern to generate tags,trace_id,span_id; updated microservices to v9
- micrometer setup; Added dep: micrometer-registry-prometheus to all ms; added prop to all app.yml 
- Implemented RateLimiter in cards microservice; added dependency: spring-boot-starter-data-redis-rective; created redis RateLimiter beans, redisRateLimiter():RedisRateLimiter, userKeyResolver():KeyResolver; added requestRateLimiter() filter in routeConfig(); added rate limiter props in app.yml
- Configured Retry mechanism for Loans
- Configured Global Httpclient Timeouts; set spring.cloud.httpclient.connect-timeout/response-timeout
- added circuitbreaker/openfeign dependency; implemented circuitbreaker inside microservice; created Fallback classes along side FeignClients
- created REST API to return fallback message; FallbackController 
- added resiliency4j dependency; added circuitbreaker code to accounts in gatewayserver; added circuit breaker related prop to gatewayserver application.yml
- Modify microservices to receive correlation Id from custom gatewayserver filter, and pass it to downstream microservices; enabled logging to display correlation id
- Implemented Custom Routing - Tracing and Logging using Spring Cloud Gateway; created RequestTraceFilter, ResponseTraceFilter, FilterUtility; modified logging in yml
- implemented Gatewayserver filter - addResponseHeader
- added routeConfig() in GatewayserverApplication to return RouteLocator; routes(), route(), RouteLocatorBuilder, path(), filters(), uri(); enforce use of custom Route locator
- updated gateway dependency to correct one; update app.yml to set locator.lowerCaseServiceId=true
- updated gatewayserver related properties - application.yml, pom; updated version to v5
- init project setup

proj: eurekaserver (springboot 3.2.5 : jdk21, mvn, jar | Eureka Server, Config Client, spring boot actuator)
- updated to use OpenTelemetry - pom, app.yml, added logging pattern to generate tags,trace_id,span_id; updated microservices to v9
- micrometer setup; Added dep: micrometer-registry-prometheus to all ms; added prop to all app.yml 
- updated docker compose files for EurekaServer, FeignClient updates
- added jib plugin dependency to generate docker image; updated docker image version (v4h2)
- @EnableEurekaServer, modify app.yml, add eurekaserver.yml to gh-config
- initial proj setup

proj: accounts (springboot 3.2.4 : jdk21, mvn, jar | spring web, H2DB, spring data JPA, spring boot actuator, spring boot DevTools, Lombok, Validation)
- updated for Apache Kafka; added compose entries for Kafka; updated application.yml for kafka entries
- created Consumer<> spring cloud function to trigger on event from consumer-sent queue; modified app.yml to subscribe to new queue; Consumer<> function to update a new bool field in accounts - modified related classes: Accounts, AccountServiceImpl...
- updated for RabbitMQ, spring cloud stream, spring cloud functions; added sendCommunication() in createAccount() to send event message dto to event broker/rabbitmq; Autowired StreamBridge bean in AccountServiceImpl
- updated to use OpenTelemetry - pom, app.yml, added logging pattern to generate tags,trace_id,span_id; updated microservices to v9
- micrometer setup; Added dep: micrometer-registry-prometheus to all ms; added prop to all app.yml 
- Implemented RateLimiter in accounts; modified accounts app.yml, accounts api, create getJavaVersionFallback() fallback method
- Configured Retry for Accounts inside microservice ; used @Retry in /build-info API, created getBuildInfoFallback(), retry config in app.yml (accounts); set ignore retry for exceptions via app.yml;
- added circuitbreaker/openfeign dependency; implemented circuitbreaker inside microservice; created Fallback classes along side FeignClients
- enabled liveness, readiness for microservices; updated docker image version
- Modify microservices to receive correlation Id from custom gatewayserver filter, and pass it to downstream microservices; enabled logging to display correlation id
- updated docker image version (v4h2)
- added dependency: OpenFeign; @EnableFeignClients; new package service.client - new interfaces: CardsFeignClient, LoansFeignClient; In accounts, new CustomerDetailsDTO, updated CustomerMapper; ICustomerService, CustomerServiceImpl - inject CardsFeignClient, LoansFeignClient; new CustomerController - fetchCustomerDetails
- updated spring.cloud.version to 2003.0.0 from 2023.0.1 due to spring cloud bug
- added dep: Eureka Discovery Client; modified yml to connect to eurekaserver, added actuator related info
- H2 version of section 7; app.yml, pom, docker-compose.yml; updated verion from v3 to v3h2
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


proj: configserver (springboot 3.2.5 : jdk21, mvn, jar | Config Server, spring boot actuator)
- updated to use OpenTelemetry - pom, app.yml, added logging pattern to generate tags,trace_id,span_id; updated microservices to v9
- micrometer setup; Added dep: micrometer-registry-prometheus to all ms; added prop to all app.yml 
- updated docker image version (v4h2)
- updated spring.cloud.version to 2003.0.0 from 2023.0.1 due to spring cloud bug
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

repo: gh-config-demo
- added dep: spring-cloud-starter-bus-amqp; added rabbitmq configuration
- Updated contactsInfoDTOs to change prop values at runtime via actuator
- modified app.yml for cloud.config.server.git entries
- external github repo hosting yml property files used by microservices

proj: cards (springboot 3.2.4 : jdk21, mvn, jar | spring web, H2DB, spring data JPA, spring boot actuator, spring boot DevTools, Lombok, Validation)
- updated to use OpenTelemetry - pom, app.yml, added logging pattern to generate tags,trace_id,span_id; updated microservices to v9
- micrometer setup; Added dep: micrometer-registry-prometheus to all ms; added prop to all app.yml 
- enabled liveness, readiness for microservices; updated docker image version
- Modify microservices to receive correlation Id from custom gatewayserver filter, and pass it to downstream microservices; enabled logging to display correlation id
- updated docker image version (v4h2)
- Updated Loans and Cards Controller, fetch APIs, to return HttpStatus.OK, same as for fetch API in Customer Controller
- updated spring.cloud.version to 2003.0.0 from 2023.0.1 due to spring cloud bug
- added dep: Eureka Discovery Client; modified yml to connect to eurekaserver, added actuator related info
- H2 version of section 7; app.yml, pom, docker-compose.yml; updated verion from v3 to v3h2
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
- updated to use OpenTelemetry - pom, app.yml, added logging pattern to generate tags,trace_id,span_id; updated microservices to v9
- micrometer setup; Added dep: micrometer-registry-prometheus to all ms; added prop to all app.yml 
- enabled liveness, readiness for microservices; updated docker image version
- Modify microservices to receive correlation Id from custom gatewayserver filter, and pass it to downstream microservices; enabled logging to display correlation id
- updated docker image version (v4h2)
- Updated Loans and Cards Controller, fetch APIs, to return HttpStatus.OK, same as for fetch API in Customer Controller
- updated spring.cloud.version to 2003.0.0 from 2023.0.1 due to spring cloud bug
- added dep: Eureka Discovery Client; modified yml to connect to eurekaserver, added actuator related info
- H2 version of section 7; app.yml, pom, docker-compose.yml; updated verion from v3 to v3h2
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


proj: proj-test-1 (springboot 3.2.4 | jdk21 | mvn | jar)
- removed permanently
- created test project to test new workspace (local git, gh, eclipse)