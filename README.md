# **Expense Tracker API**

A Spring Boot API designed to help users manage daily expenses, track spending habits, and gain insights into their financial behavior. Core features include user authentication, expense management, expense tracking, and financial insights.

## **Table of Contents**
[Description](#Description)
[Features](#Features)
[Installation](#Installation)

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
1. Clone the repository:
2. 
