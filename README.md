# ud-microservices-springboot
Jay's proj repo for Microservices with SpringBoot, Docker, Kubernetes training (conducted by M. Reddy - udemy)
List below ARE NOT NOTES. They are topics covered as of latest.

proj: cards (springboot 3.2.4 : jdk21, mvn, jar | spring web, H2DB, spring data JPA, spring boot actuator, spring boot DevTools, Lombok, Validation)
- initial project setup and commit

proj: loans (springboot 3.2.4 : jdk21, mvn, jar | spring web, H2DB, spring data JPA, spring boot actuator, spring boot DevTools, Lombok, Validation)
- Loans Microservice; Delete Loan API; modified - controller,
service, repository, deleteByMobileNumber(); 
- Loans Microservice; Update Loan Details API; modified - controller,
service, repository, findByLoanNumber(); edit AccountsService
- Loans Microservice; Fetch Loan Details API; modified - controller, service
- Loans Microservice; Create Loans API; added controller,  repository, service, entity, dto, mapper, exception, constants, audit; 
- initial project setup and commit

proj: accounts (springboot 3.2.4 : jdk21, mvn, jar | spring web, H2DB, spring data JPA, spring boot actuator, spring boot DevTools, Lombok, Validation)
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
- created test project to test new workspace (local git, gh, eclipse)