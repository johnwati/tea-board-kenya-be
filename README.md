# Tea Board of Kenya Backend

This repository hosts the backend system for the Tea Board of Kenya's database registry.

## Overview
This Spring Boot application is built on Java 17 and is responsible for managing and facilitating the functionalities of the Tea Board of Kenya's database registry.

## Prerequisites
- Java Development Kit (JDK) 17 or later
- Apache Maven or Gradle
- IDE (IntelliJ IDEA, Eclipse, etc.) with Spring Boot support

## Getting Started
1. **Clone the Repository:**
    ```bash
    git clone https://github.com/johnwati/tea-board-kenya-be.git
    cd tea-board-kenya-be
    ```

2. **Build the Project:**
    - Using Maven:
        ```bash
        mvn clean install
        ```
    - Using Gradle:
        ```bash
        gradle clean build
        ```

3. **Run the Application:**
    - Using Maven:
        ```bash
        mvn spring-boot:run
        ```
    - Using Gradle:
        ```bash
        gradle bootRun
        ```

4. **Access the Application:**
   Once the application starts, access it locally by visiting [http://localhost:8080](http://localhost:8080) in your web browser.

## Swagger Documentation
- **Swagger UI:** The API documentation is available using Swagger UI. Access it [here](http://localhost:8080/swagger-ui/index.html) when the application is running.

## Configuration
- **Application Properties:** Modify `application.properties` or `application.yml` to configure database connections, server ports, etc.
- **Profiles:** Define different profiles (`dev`, `prod`, etc.) in `application-{profile}.properties` files for environment-specific configurations.

## Features
- **Data Registry Management:** Handles the database registry operations for the Tea Board of Kenya.
- **REST APIs:** Includes various endpoints to manage and query the registry data.
- **Dependency Injection:** Utilizes Spring's dependency injection for managing beans.
- **Database Connectivity:** Demonstrates database interaction using Spring Data JPA or any other preferred persistence method.
- **Logging:** Configured with logging levels and practices using Logback, Log4j, or Java Util Logging.

## Testing
- **Unit Tests:** Contains unit tests for service classes using JUnit or TestNG.
- **Integration Tests:** Includes integration tests for controllers and repositories.
- **Mocking:** Uses Mockito or any preferred mocking framework for testing.

## Contributing
Feel free to contribute to this project by forking the repository and creating pull requests for new features, bug fixes, or improvements.

## License
This project is licensed under the [MIT License](LICENSE).
