# Transactions
Transactions processor for Processing streaming transactions from Kafka. It also has the Reports API.

## usage Start Application Kafka Streaming
- Docker Compose:
    ``` docker compose up ```
- Gradle Build:
    ``` ./gradlew build```
- Change Configuration for bootRun . Toggle the property in application.yml ```bootstrap.kafka.testing=true```. This will publish Messages to the Kafka Topic
    ```bootstrap.kafka.testing=true```
- Start Application
    ``` ./gradlew bootRun ```

## usage Reports API
````
```
    [Please ensure to boot run the application and define in applicatio.yml  ```bootstrap.kafka.testing=true```]. This will add entries to Transactions table necessary for Report API
```
````

###  Report User
- Login Report user
    Use below curl command to Get access token
    ```
        curl --location 'localhost:8082/auth/login' \
        --header 'Content-Type: application/json' \
        --header 'Cookie: JSESSIONID=150637DF3667C64458766187742E8F11' \
        --data '{
            "username": "report",
            "password": "pass"
            }'
    ```
- Cost Per Customer Report Replace <Access_token> placeholder with Access Token Fetched in [Report] user Login Command
    ```
        curl --location 'localhost:8082/reports/costPerCustomer' \
        --header 'Authorization: Bearer <Access_token>' \
        --header 'Cookie: JSESSIONID=150637DF3667C64458766187742E8F11'
    ```

- Cost Per Product   Report Replace <Access_token> placeholder with Access Token Fetched in [Report] user Login Command
    ```
        curl --location 'localhost:8082/reports/costPerProduct' \
        --header 'Authorization: Bearer <Access_token>' \
        --header 'Cookie: JSESSIONID=150637DF3667C64458766187742E8F11'
    ```

###s Admin User
- Login Admin User
    Use below curl command to Get access token
    ```
        curl --location 'localhost:8082/auth/login' \
        --header 'Content-Type: application/json' \
        --header 'Cookie: JSESSIONID=150637DF3667C64458766187742E8F11' \
        --data '{
            "username": "admin",
            "password": "pass"
            }'
    ```

- [Cost Per Product]   Report Replace <Access_token> placeholder with Access Token Fetched in [Admin] User Login Command
    ```
        curl --location 'localhost:8082/reports/transactionsCount' \
        --header 'Authorization: Bearer <Access_token>' \
        --header 'Cookie: JSESSIONID=150637DF3667C64458766187742E8F11'
    ```
