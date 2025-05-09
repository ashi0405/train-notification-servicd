# 🚆 Train Ticket Notification System

A Spring Boot-based microservice that automates Indian Railway PNR ticket status tracking and notifies users via email when their tickets get confirmed. Built using Kafka, MySQL, RapidAPI, and JavaMailSender.

---

## 🔧 Tech Stack

- **Spring Boot** – Backend REST API framework
- **Apache Kafka** – Event streaming for ticket status updates
- **MySQL** – Relational database for storing user and ticket information
- **RapidAPI (IRCTC PNR)** – Real-time PNR status lookup
- **JavaMailSender** – Email notification service
- **Spring Scheduler** – Background jobs for ticket checking and cleanup
- **Lenses SQL Studio** – Kafka topic exploration and debugging

---

## 🛠️ Key Features

- ✅ **Real-Time Ticket Alerts**:  
  - Scheduled job runs periodically to check ticket status via RapidAPI.  
  - Sends email to the user **only when ticket status becomes "Confirmed"**.  
  - Publishes the status update event to the Kafka topic `ticket-status-topic`.

- 🧹 **Ticket Cleanup**:  
  - A second scheduled job purges tickets older than the current date to keep the system optimized.

- ⚙️ **Event-Driven Design**:  
  - Kafka used for decoupled communication and potential integration with other consumers or analytics systems.

- 📧 **Reliable Email Notifications**:  
  - Utilizes `JavaMailSender` to deliver confirmation emails with clear status updates.

---

📸 Screenshots
1. Kafka Topic Query in Lenses Studio
Shows consumed ticket data from the ticket-status-topic Kafka topic.

<img width="1646" alt="Screenshot 2025-05-05 at 12 49 30 PM" src="https://github.com/user-attachments/assets/1abf02ca-8978-499a-bccf-680d82bc4814" />

2. Email Notification Output
Sample confirmation email sent to the user when their ticket is confirmed.

<img width="1178" alt="Screenshot 2025-05-05 at 12 49 44 PM" src="https://github.com/user-attachments/assets/2241eb39-332a-4259-985a-42dc7749136e" />

3. PNR response

<img width="1728" alt="Screenshot 2025-05-05 at 1 20 42 PM" src="https://github.com/user-attachments/assets/f3a3d579-c529-4fa3-901f-7fd6b8efdc01" />


