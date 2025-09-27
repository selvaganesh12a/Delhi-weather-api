### Delhi Weather Data API

This is a backend application developed to process and serve nearly two decades of weather forecast data for Delhi. The project transforms raw Excel data into a structured format, stores it in a MySQL database, and exposes a robust API for data retrieval and analysis. It was developed as a technical assessment.

-----

### Key Features

  * **Data Processing & Storage**: The application fetches weather data from a excel file, processes it, and stores it efficiently in a MySQL database.
  * **Modular API Design**: The codebase is split into a modular fashion with clear separation of concerns (Controller, Service, Repository, Entity).
  * **Specific API Endpoints**:
      * Retrieve weather details (condition, temperature, humidity, pressure) for a specific month or date.
      * Extract high, median, and minimum temperature statistics for each month of a given year.

-----

### Technologies Used

  * **Backend**: Java, Spring Boot
  * **Database**: MySQL
  * **Build Tool**: Maven

-----

### How to Run Locally

1.  **Clone the repository**:

    ```bash
    git clone https://github.com/your-username/delhi-weather-data.git
    cd delhi-weather-data
    ```

2.  **Database Setup**:

      * Ensure you have MySQL installed and running.
      * Create a database (e.g., `weather_db`).
      * Update the `src/main/resources/application.properties` file with your MySQL credentials and database name:

    <!-- end list -->

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/weather_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Run the Application**:
    You can run the application using your IDE or the Maven command line.

    ```bash
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080`.

-----

### API Endpoints

You can test the API using a tool like Postman or curl.

#### 1\. Get Weather Details by Date or Month

  * **By specific date**:
    `GET /api/weather/date/{datetime}`
    Example: `http://localhost:8080/api/weather/date/2023-01-15T12:00:00`

  * **By specific month**:
    `GET /api/weather/month/{month_number}`
    Example: `http://localhost:8080/api/weather/month/1` (for January)

#### 2\. Get Monthly Temperature Summary

  * `GET /api/weather/summary/{year}`
    Example: `http://localhost:8080/api/weather/summary/2022`
