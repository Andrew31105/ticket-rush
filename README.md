🎟️ TicketRush – High Performance Ticket Booking System
📖 Introduction

TicketRush là một backend system mô phỏng nền tảng đặt vé có lượng truy cập cao (flash sale, concert ticket, limited offer).

Dự án tập trung giải quyết các bài toán phổ biến trong distributed system:

Ngăn overselling khi nhiều user đặt vé cùng lúc

Giảm tải database khi traffic tăng đột biến

Đảm bảo bảo mật & phân quyền rõ ràng

Tự động phục hồi tồn kho khi đơn hàng bị treo

🧠 Core Problems & Solutions
Vấn đề	Cách TicketRush xử lý
Race Condition	Redis Lua Script chạy atomic
System Overload	Kafka Event-driven để buffer request
Security	JWT + RBAC (Spring Security)
Data Inconsistency	Scheduler quét & hoàn kho
🗂️ Project Structure
```text
ticket-rush/
├── docker-compose.yml              # Redis, Kafka, Zookeeper, PostgreSQL
├── pom.xml                         # Spring Boot, Security, Redis, Kafka, JWT
├── README.md
└── src/main
    ├── java/com/ticketrush
    │   ├── config
    │   │   ├── AppConfig.java          # Load Redis Lua Script
    │   │   └── SecurityConfig.java     # JWT Filter + RBAC
    │   ├── controller
    │   │   ├── AuthController.java     # Login / Register
    │   │   ├── BookingController.java  # User booking API
    │   │   └── AdminController.java    # Admin inventory API
    │   ├── dto
    │   │   ├── AuthRequest.java
    │   │   └── BookingRequest.java
    │   ├── entity
    │   │   ├── User.java
    │   │   ├── Booking.java
    │   │   └── Role.java               # ADMIN, USER
    │   ├── repository
    │   │   ├── UserRepository.java
    │   │   └── BookingRepository.java
    │   ├── security
    │   │   ├── JwtService.java
    │   │   ├── JwtAuthFilter.java
    │   │   └── UserDetailsImpl.java
    │   ├── service
    │   │   ├── AuthService.java
    │   │   ├── TicketService.java      # Redis Lua + Kafka Producer
    │   │   ├── BookingConsumer.java    # Kafka Consumer → DB
    │   │   └── OrderCleaner.java       # Scheduler xử lý đơn treo
    │   └── TicketRushApplication.java
    └── resources
        ├── application.yml
        └── scripts
            └── deduct_inventory.lua    # Lua script xử lý tồn kho atomic
```text
🏗️ System Architecture
graph TD
    U[User] -->|HTTP + JWT| C[Controller]

    C -->|JWT Check| S[Spring Security Filter]

    S -->|Atomic Decrement| R[Redis + Lua]
    R -- Success --> KP[Kafka Producer]
    R -- Sold Out --> U

    KP --> K[Kafka Topic]
    K --> BC[Booking Consumer]
    BC --> DB[(PostgreSQL)]

    OC[OrderCleaner Scheduler]
    OC --> DB
    OC --> R
```text
🚀 Key Features
1️⃣ High Concurrency Control (Core Feature)

Không dùng DB Lock (pessimistic / optimistic)

Dùng Redis Lua Script chạy atomic

Xử lý hàng nghìn request/giây

Đảm bảo zero overselling

👉 Response time trung bình < 20ms

2️⃣ Asynchronous Processing (Kafka)

API trả về nhanh 202 Accepted

Kafka xử lý ghi DB phía sau

Tránh tình trạng DB bottleneck

Phù hợp mô hình real-world (Shopee / Ticketmaster)

3️⃣ Security & RBAC

JWT Stateless Authentication

Phân quyền rõ ràng:

ROLE_USER: đặt vé

ROLE_ADMIN: quản lý tồn kho

4️⃣ Self-Healing Scheduler

Quét đơn hàng treo / chưa thanh toán

Hủy đơn quá hạn

Hoàn vé về Redis

Đảm bảo cache & DB luôn nhất quán

🛠️ Tech Stack
Layer	Technology
Language	Java 21
Framework	Spring Boot 3.2
Security	Spring Security 6, JWT
Cache	Redis + Lua
Messaging	Apache Kafka
Database	PostgreSQL
DevOps	Docker, Docker Compose
⚡ Getting Started
1️⃣ Prerequisites

Java 21

Maven

Docker & Docker Compose

2️⃣ Start Infrastructure
docker-compose up -d


Chạy:

Redis

Kafka

Zookeeper

PostgreSQL

3️⃣ Initialize Inventory
docker exec -it ticket-rush-redis-1 redis-cli
SET event_tickets:101 10

4️⃣ Run Application
mvn spring-boot:run

🧪 API Overview
🔐 Authentication
POST /api/auth/register
POST /api/auth/login

🎫 Booking (ROLE_USER)
POST /api/bookings?eventId=101
Authorization: Bearer <token>


Responses:

202 Accepted – Đặt vé thành công

409 Conflict – Hết vé

🛠️ Admin (ROLE_ADMIN)
POST /api/admin/restock?eventId=101&amount=50
Authorization: Bearer <admin_token>

📊 Performance Test (JMeter)
Metric	Result
Concurrent Users	100
Available Tickets	10
Successful Orders	10
Overselling	0
Avg Response Time	~15ms
DB Load	Low (Kafka buffered)
🎯 Purpose of This Project

Thực hành System Design thực tế

Mô phỏng backend cho high traffic system
