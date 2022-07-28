# Test task - Order Platform
## Run and build
### To build: mvn clean install

### To run: java -jar target/order-platform.jar or ./mvnw spring-boot:run or mvn spring-boot:run

### To access API via Swagger use http://localhost:9999/swagger-ui/index.html

## Details:
### Stack: Java 11, SpringBoot, Embedded Tomcat, h2, liquibase, Swagger, Mapstruct, lombok, mvn, git
### Spring dev active profile (see spring.profiles.active:dev in application.yml) is used to add temp test data(see com.budzko.orderplatform.dev.TestDataService)