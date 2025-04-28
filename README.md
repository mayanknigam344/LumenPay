# LumenPay

**LumenPay** is a **wallet management system** where users can register, load money, send money to others, and manage/view their transaction history with flexible filtering and sorting.

---

## Features

- **User Registration**
  - Users must register on LumenPay to use the wallet service.

- **Load Money into Wallet**
  - Users can load money using various sources (Credit Card, Debit Card, UPI, etc.).
  - Minimum load amount must be **greater than 0**.
  - *Note:* No real payment gateway integration is needed — success is assumed immediately.

- **Send Money**
  - Users can send money to other users' wallets.
  - Minimum transaction amount must be **greater than 0**.
  - Sender must have **sufficient wallet balance**.

- **Wallet Balance Check**
  - Users can check their wallet balance at any time.
  - Balance includes the effect of both **credit** and **debit** transactions.

- **Transaction History**
  - Users can view their transaction history with:
    - **Sorting**:
      - By Transaction **Amount** (`amount`)
      - By Transaction **DateTime** (`time`)
    - **Filtering**:
      - By Transaction **Type**: `SEND` or `RECEIVE`
  - *Note:* Only **one filter or sort** is applied at a time.

---

## Technology Stack

- Java 17+
- Spring Boot
- Lombok
- In-memory Storage
- Gradle/Maven

---
