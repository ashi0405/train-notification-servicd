🚆 Train Ticket Notification System
A Spring Boot-based microservice that automates the process of checking Indian Railway PNR ticket status and sends real-time email alerts to users when their ticket status changes — powered by Kafka, MySQL, and RapidAPI.

🔧 Tech Stack
Spring Boot – Backend framework

Apache Kafka – Event streaming platform for handling ticket status events

MySQL – Persistent storage for tickets and user info

RapidAPI – For real-time IRCTC PNR status lookup

JavaMailSender – To send email notifications

Spring Scheduler – For periodic PNR status checks

Lenses SQL Studio – For querying Kafka topics

📸 Screenshots
1. Kafka Topic Query in Lenses Studio
Shows consumed ticket data from the ticket-status-topic Kafka topic.

<img width="1646" alt="Screenshot 2025-05-05 at 12 49 30 PM" src="https://github.com/user-attachments/assets/1abf02ca-8978-499a-bccf-680d82bc4814" />



2. Email Notification Output
Sample confirmation email sent to the user when their ticket is confirmed.

<img width="1178" alt="Screenshot 2025-05-05 at 12 49 44 PM" src="https://github.com/user-attachments/assets/2241eb39-332a-4259-985a-42dc7749136e" />

3. PNR response

   <img width="1728" alt="Screenshot 2025-05-05 at 1 20 42 PM" src="https://github.com/user-attachments/assets/f3a3d579-c529-4fa3-901f-7fd6b8efdc01" />


