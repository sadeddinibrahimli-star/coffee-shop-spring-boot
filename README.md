# Coffee Shop — Spring Boot Application

A Spring Boot coffee shop application demonstrating **10 design patterns**, **multithreading with Spring's async capabilities**, **chat functionality with JPA/Hibernate**, and a **full JUnit 5 test suite**.

---

## Task

Build a complete coffee shop backend using Spring Framework that lets customers choose coffee types, apply decorators for extras, use different pricing strategies, receive order notifications, and chat with baristas — all while demonstrating proper design pattern usage with Spring's dependency injection, configuration, and web capabilities.

---

## Description

### Part 01 — Design Patterns (10 Patterns)

| Pattern | Package | Implementation |
|---------|---------|---------------|
| **Singleton** | `singleton` | `CoffeeShop` — `@Component` managing orders globally |
| **Factory** | `factory` | `CoffeeFactory` — Map-based factory producing coffee from `CoffeeType` enum |
| **Observer** | `observer` | `OrderReadyEvent` + `OrderEventListener` — Spring `ApplicationEventPublisher` for order notifications |
| **Strategy** | `strategy` | `PricingStrategy` interface with `RegularPricing`, `GoldPricing` (30% off), `SilverPricing` (10% off) |
| **Decorator** | `decorator` | `CoffeeDecorator` abstract base with `MilkDecorator` (+$0.50) and `SugarDecorator` (+$0.25) |
| **Command** | `command` | `OrderCommand` interface with `PlaceOrderCommand` and `CancelOrderCommand` (`@Scope("prototype")`) |
| **Adapter** | `adapter` | `PaymentAdapter` wrapping `ExternalPaymentGateway` third-party service |
| **Facade** | `facade` | `CoffeeShopFacade` simplifying order + payment + pricing into one call |
| **Prototype** | `prototype` | `PrototypeOrder` using Spring's `@Scope("prototype")` for deep-cloning orders |
| **Template** | `template` | `CoffeePreparationTemplate` abstract class with concrete `EspressoPreparation`, `LattePreparation`, `CappuccinoPreparation` |

### Part 02 — Multithreading with Spring

- **`OrderQueue`** — Thread-safe `BlockingQueue<Order>` for managing concurrent order submissions
- **`BaristaService`** — `@Async` service that processes orders in a background thread with simulated preparation time
- **`@EnableAsync`** on `CoffeeShopSpringApplication` to activate Spring's async execution

### Part 03 — Chat with Spring Data JPA

- **`ChatMessage`** — JPA entity persisted to PostgreSQL (`chat_messages` table)
- **`ChatRepository`** — Spring Data JPA repository with custom `findBySender()` query
- **`ChatService`** — Service layer handling message creation and retrieval
- **`ChatController`** — REST endpoints at `/api/chat` for sending and reading messages

### REST API Endpoints

**Coffee endpoints** (`/api/coffee`):

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/order/{type}/{customerName}/{pricingType}` | Place an order (pricingType: regular/gold/silver) |
| GET | `/orders` | Get all orders |
| POST | `/clone/{orderId}` | Clone an existing order (prototype pattern) |

**Chat endpoints** (`/api/chat`):

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/send` | Send message (JSON body: `{sender, content}`) |
| GET | `/messages/{sender}` | Get messages by sender |
| GET | `/messages` | Get all messages |

### Test Coverage

3 test classes with JUnit 5 + Mockito:

- **`OrderQueueTest`** — unit tests for thread-safe queue operations
- **`ChatServiceTest`** — Mockito-based tests mocking `ChatRepository`
- **`CoffeeShopTest`** — `@SpringBootTest` integration tests for order placement

---

## Installation

### Prerequisites

- **Java 17+**
- **Maven 3.6+**
- **PostgreSQL** running at `localhost:5432`
- Database named `coffeeshop` must exist

### Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd coffee-shop-spring
```

2. Create the database (if not exists):
```sql
CREATE DATABASE coffeeshop;
```

3. Configure database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/coffeeshop
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

> **Note:** `application.properties` is in `.gitignore` — your credentials are never pushed to GitHub. Use `application-example.properties` as a template.

4. Build the project:
```bash
mvn clean package
```

---

## Usage

### Run the application
```bash
java -jar target/coffee-shop-spring-0.0.1-SNAPSHOT.jar
```

### Run only the tests
```bash
mvn clean test
```

### Run without building a JAR
```bash
mvn spring-boot:run
```

### Test the API

Place an order:
```
GET http://localhost:8080/api/coffee/order/ESPRESSO/John/gold
```

Get all orders:
```
GET http://localhost:8080/api/coffee/orders
```

Send a chat message:
```
POST http://localhost:8080/api/chat/send
Body: {"sender": "Customer", "content": "Can I get a latte?"}
```

Get all messages:
```
GET http://localhost:8080/api/chat/messages
```

---

## Project Structure

```
src/main/java/com/coffeeshop/coffee_shop_spring/
├── CoffeeShopSpringApplication.java    # Main entry + @EnableAsync
├── model/                               # Entities & domain objects
│   ├── Coffee.java                      # Interface
│   ├── Espresso.java, Cappuccino.java, Latte.java
│   ├── CoffeeType.java                  # Enum
│   ├── Order.java                       # JPA entity
│   ├── Customer.java                    # JPA entity
│   └── ChatMessage.java                 # JPA entity
├── factory/
│   └── CoffeeFactory.java               # Map-based factory
├── singleton/
│   └── CoffeeShop.java                  # Core singleton service
├── observer/
│   ├── OrderReadyEvent.java             # Spring event
│   └── OrderEventListener.java          # Event handler
├── strategy/
│   ├── PricingStrategy.java             # Interface
│   ├── RegularPricing.java, GoldPricing.java, SilverPricing.java
├── decorator/
│   ├── CoffeeDecorator.java             # Abstract base
│   ├── MilkDecorator.java, SugarDecorator.java
├── command/
│   ├── OrderCommand.java                # Interface
│   ├── PlaceOrderCommand.java, CancelOrderCommand.java
├── adapter/
│   ├── PaymentService.java              # Interface
│   ├── ExternalPaymentGateway.java      # Third-party (no Spring)
│   └── PaymentAdapter.java              # Adapter
├── facade/
│   └── CoffeeShopFacade.java            # Simplified API
├── prototype/
│   └── PrototypeOrder.java              # Deep-cloning order
├── template/
│   ├── CoffeePreparationTemplate.java   # Abstract template
│   ├── EspressoPreparation.java, LattePreparation.java, CappuccinoPreparation.java
├── queue/
│   └── OrderQueue.java                  # BlockingQueue wrapper
├── service/
│   ├── BaristaService.java              # @Async processor
│   └── ChatService.java                 # Chat business logic
├── repository/
│   └── ChatRepository.java              # Spring Data JPA
└── controller/
    ├── CoffeeController.java            # REST API
    └── ChatController.java              # Chat REST API
```

---

## Known Issues / Areas for Improvement

- **No authentication** — chat endpoints are open; any sender name works
- **In-memory order list** — `CoffeeStore` orders are not persisted to the database (only chat messages are)
- **No Swagger/OpenAPI docs** — API documentation would help frontend integration
- **No WebSocket** — chat is request/response only, not real-time
- **Package-private fields** — some model fields lack explicit access modifiers

---

## Authors

**Sadaddin Ibrahimli**
