# **Expense Tracker API**

A Spring Boot API designed to help users manage daily expenses, track spending habits, and gain insights into their financial behavior. Core features include user authentication, expense management, expense tracking, and financial insights.

## **Description**
This API allows users to securely manage and analyze their expenses. With features like expense recording, category-based breakdowns, and monthly summaries, users can gain insight into their spending habits.

The API is hosted on AWS and can be accessed directly via the server or run locally using Docker. Full documentation for each endpoint is maintained in [Notion](https://obtainable-clavicle-371.notion.site/API-Documentation-129664ac6a40809ebe96e6e32aeacb10?pvs=4.)

## **Features**
- **User Authentication**: Secure user login and registration using Spring Security with JWT for token-based authentication and authorization.
- **Expense Management**: Add, update, delete, filter and categorize expenses.
- **Financial Insights**: View expense summaries, category breakdowns, and monthly spending.
- **Deployment**: Available on AWS for production, with Docker support for local testing and development.
- **Data Persistence**: Stores data using PostgreSQL.
- **Unit and Integration Testing**: Comprehensive tests using JUnit, Mockito, MockMvc, and Testcontainers for database reliability.

## **Installation**
**Prerequisites**
- Java 17
- Maven
- Docker (optional, for running with Docker Compose)

**Steps**
- 
1. Clone the repository: git@github.com:mokgadiphasha/ExpenseTracker.git
2. Access the ExpenseTracker - (app directory) directory
3. Run: mvn clean package -Dmaven.test.skip=true
4. Run: docker-compose up --build

## **PLEASE NOTE** 
The project is no longer hosted on AWS due to the free tier expiration and can only be run locally at the moment. Feel free to peruse the Notion Documentation.
You can access the documentation of the deployed API on AWS, although the API is inaccessible on AWS, refer to [Notion documentation](https://obtainable-clavicle-371.notion.site/API-Documentation-129664ac6a40809ebe96e6e32aeacb10?pvs=4.).

## **Usage**
Once running, the API will be accessible at http://localhost:8080 (or via the AWS server). Use a tool like Postman to interact with the endpoints or integrate it into a frontend application.

## **Testing**
Testing includes both unit and integration tests. The test suite leverages:
- **JUnit** – For structured test cases
- **Mockito** – For mocking dependencies
- **MockMvc** – For testing MVC endpoints
- **AssertJ** – For fluent assertions
- **Testcontainers** – For running PostgreSQL in Docker during integration tests

Run tests with:
- mvn test

## **Contact Information**
For questions or feedback, please reach out to helenaphasha@gmail.com.




   
