# ZIMRA FDMS Mock Server in Java

This is a Java based implementation of a mock server designed to simulate requests and responses as specified in the ZIMRA FDMS API documentation: https://fdmsapitest.zimra.co.zw/swagger/index.html

> [!NOTE]
> For a Typescript based implementation, kindly refer to the repo by takumade at [takumade/zimra-fdms-mock-server](https://github.com/takumade/zimra-fdms-mock-server)


## Technology used

- Java 21
- Maven
- SpringBoot
- Docker

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/bruce-mig/zimra-fdms-mock-server-java.git
   cd zimra-fdms-mock-server-java
   ```

2. Build the application jar:
  - Using maven:
     ```sh
     ./mvnw clean package
     ```

3. Test the application:
  - Using maven:
     ```sh
     ./mvnw test
     ```

4. Start the server:
   - Using maven:
     ```sh
     ./mvnw spring-boot:run
     ```
   - Using java runtime:
     ```sh
     java -jar target/zimra-fdms-mock-server-java-1.0.0.jar
     ```

NB: The server will start running on `http://localhost:3000`.

The Swagger UI is at `http://localhost:3000/swagger-ui/index.html`


### Docker 

To run the ZIMRA FDMS Mock Server using Docker, run the following command:

```bash
docker run -p 3000:3000 bmigeri/zimra-fdms-mock-server-java:latest
```

The server will be accessible at `http://localhost:3000`.

The Swagger UI is at `http://localhost:3000/swagger-ui/index.html`


> [!NOTE]
> This mock server is particularly useful for developers who do not have immediate access to a fiscalized ID. However, please note that it is recommended to test with the original server whenever possible, as the responses may differ from those provided by this mock implementation.


## Contribution
This project is open source and contributions are welcome. Please fork the repository and submit pull requests. 

> [!NOTE]
> Given that it's a mock server, contributions are most welcome in the form of bug fixes and realism enhancements.

[//]: # (### Todo)

[//]: # (✅ Device endpoints  <br>)

[//]: # (✅ Public endpoints  <br>)

[//]: # (✅ User endpoints  <br>)

[//]: # (✅ ProductsStock endpoints  <br>)

[//]: # (⏳ Make mock server as realistic as possible  <br>)

[//]: # ()
[//]: # (> [!NOTE])

[//]: # (> Most of the endpoints are mocked but I want it to be as realistic as possible. An average user shouldnt tell the difference between the mock server and the original server. 😊 For more info see [TODO.md]&#40;TODO.md&#41; )

[//]: # ()
[//]: # ()
