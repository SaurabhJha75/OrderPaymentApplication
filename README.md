# OrderPaymentApplication

## Overview

**OrderPaymentApplication** is a Spring Boot microservices project that demonstrates an event-driven architecture using RabbitMQ. It implements an order-payment workflow where orders are sent to a message queue and processed asynchronously. This project serves as a practical example of integrating RabbitMQ with Spring Boot applications.

---

## Features

- **Asynchronous Messaging**: Uses RabbitMQ for decoupled communication between services.
- **Microservices Architecture**: Separate services for order creation and payment processing.
- **JSON Message Handling**: Converts messages to and from JSON using Jackson.
- **Scalability**: Supports dynamic addition of consumers and handlers for the queue.

---

## Technologies Used

- **Java 17**
- **Spring Boot 3.4**
- **RabbitMQ**
- **Spring AMQP**
- **Jackson**

---

## Project Structure

1. **`com.example.config`**
   - Configures RabbitMQ, including the exchange, queue, bindings, and message converters.
   
2. **`com.example.producer`**
   - Handles order creation and sends order messages to RabbitMQ.
   
3. **`com.example.consumer`**
   - Processes payment requests by consuming messages from the queue.

---

## Prerequisites

### Required Tools:
- **Java 17+**
- **Maven**
- **RabbitMQ** (can be run using Docker)

### Setting up RabbitMQ:
Run RabbitMQ using Docker:
```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

## Installation

1. **Clone the repository**:  
   Clone the project repository from GitHub to your local machine.
   ```bash
   git clone https://github.com/your-username/OrderPaymentApplication.git
   cd OrderPaymentApplication
   
2. **Build the project**:
```bash
mvn clean install 
```
3. **Run the application**:
```bash
mvn spring-boot:run
```

## Configuration

The RabbitMQ configurations are defined in `RabbitMQConfig.java`:

- **Exchange Name**: `OrderExchange`  
- **Queue Name**: `OrderQueue`  
- **Routing Key**: `OrderRoutingKey`  

You can modify these settings based on your requirements by updating the `RabbitMQConfig` class.  
Make sure the changes are reflected in the RabbitMQ broker to avoid runtime issues.

## Workflow

### Order Producer:
- Sends order details (e.g., order ID and amount) to RabbitMQ.

### Payment Consumer:
- Listens for messages on `OrderQueue`.
- Processes payments for the received orders.

### Message Flow:
1. **Producer** →  
2. **RabbitMQ** (`OrderExchange`) →  
3. **OrderQueue** →  
4. **Consumer**

## Endpoints

### Order Service (Producer)
**POST** `/orders`  
Accepts order details and sends them to the queue.

#### Request Example:
```json
{
    "orderId": "123",
    "amount": 150.50
}
```
## Running the application

1. Start the RabbitMQ server (using Docker or installed manually).
2. Build and run the project.
3. Use Postman or a similar tool to test the API endpoints.

## Contributing
Contributions are welcome! Follow these steps:

1. Fork the repository.
2. Create a new branch.
3. Commit your changes.
4. Submit a pull request.

## Licence
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact
For any queries, feel free to reach out to me at [saurabhkumarjha525@gmail.com].
