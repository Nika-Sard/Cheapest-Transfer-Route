# Cheapest Transfer Route

This is a fully functional Spring Boot application that calculates the cheapest transfer route for a set of transfers, utilizing the 0/1 Knapsack dynamic programming approach with space optimization using a 1D array.

## Features

- REST API for calculating the cheapest transfer route.
- Utilizes the 0/1 Knapsack algorithm to solve the problem.
- Optimized for space using a 1D array for dynamic programming.
- Well-structured into controllers, services, and model layers.
- Unit and integration tests for the API endpoints.

## Technology Stack

- Java (Spring Boot)
- Gradle (for dependency management and build)
- JUnit, Mockito for testing
- Jackson for JSON handling

## Getting Started

Follow these steps to set up and run the application locally:

### Prerequisites

1. **Java 17** or higher installed.
2. **Gradle** installed on your machine.

## Build and Run the Application

### 1. Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/Nika-Sard/Cheapest-Transfer-Route.git
cd Cheapest-Transfer-Route
```

### 2. Build the Application

The project uses Gradle for building and managing dependencies. To build the project, run the following command:

```bash
./gradlew build
```

### 3. Run the Application

Once the application is built, you can run it using:

```bash
./gradlew bootRun
```
This will start the Spring Boot application on the default port (8080).

The application should now be running locally, and you can access the API at http://localhost:8080.

## Example cURL Requests

You can send a `POST` request to the `/api/cheapestTransfer` endpoint with the following example cURL command:

#### Request

```bash
curl -X POST http://localhost:8080/api/cheapestTransfer \
     -H "Content-Type: application/json" \
     -d '{
           "maxWeight": 15,
           "availableTransfers": [
             { "weight": 5, "cost": 10 },
             { "weight": 10, "cost": 20 }
           ]
         }'
```

#### Response

``` bash
{
  "transfers": [
    { "weight": 5, "cost": 10 },
    { "weight": 10, "cost": 20 }
  ],
  "totalCost": 30,
  "totalWeight": 15
}
```
