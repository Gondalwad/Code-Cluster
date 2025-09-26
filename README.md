# 🚀 Code-Cluster

Code-Cluster is a **distributed programming platform** that allows users to practice and enhance their programming skills.  
Users can pick problems ranging from *Easy* to *Hard*, write their solutions, and get them executed **securely inside isolated Docker containers**.
Using docker containers made my code free from direct access with any kind of malicious code.

---

## 🌟 Features

- 📝 **Problem Solving Platform**  
  Users select problems and submit solutions in supported programming languages.

- ⚙️ **Event-Driven Architecture with Kafka**  
  - `Submission Service` publishes jobs to Kafka topics.  
  - `Executor Service` consumes jobs, executes the code in sandboxed containers, and produces results back.

- 📦 **Secure Docker-Based Execution**  
  - Each submission runs in a **lightweight Docker container**.  
  - Strict CPU, memory, and time limits to ensure safe execution.

- 🔄 **Scalable Execution Layer**  
  - Horizontal scaling of executor nodes using Kafka consumer groups.  
  - No code change required to add more executors.

- 🏭 **Factory Design Pattern**  
  Dynamically spins up **language-specific container environments** (Java, Python, etc.).

- 🧪 **Automated Test Case Evaluation**  
  Each submission is tested against predefined problem-specific test cases fetched from the database.

- 📊 **Result & Logs API**  
  API to retrieve job status, execution output, and error logs.

- 🚪 **API Gateway & Security**  
  - Unified entry point for all services using **Spring Cloud Gateway**.  
  - JWT-based authentication handled by `Auth Service`.  
  - Role-based access for users.

- ⚡ **Low Latency**  
  Optimized pipeline → average **5–6 s** turnaround from submission to result.

---

## 🏗️ Architecture Overview

```plaintext
                 ┌───────────────────────┐
                 │       Frontend        │
                 │  (React / Typescript) │
                 └───────────┬───────────┘
                             │
                             ▼
                 ┌───────────────────────┐
                 │      API Gateway      │
                 │ (Spring Cloud Gateway)│
                 └───────────┬───────────┘
         ┌───────────────────┼───────────────────┐
         ▼                   ▼                   ▼
 ┌────────────────┐   ┌────────────────┐   ┌────────────────┐
 │ Auth Service   │   │  User Service  │   │ Problem Service│
 │  (JWT Tokens)  │   │ CRUD user info │   │ Problem +      │
 │                │   │                │   │ Testcases      │
 └────────────────┘   └────────────────┘   └────────────────┘
                             │
                             ▼
                 ┌───────────────────────┐
                 │   Submission Service  │
                 │ Accepts code, creates │
                 │ jobId, publishes job  │
                 │  to Kafka topic       │
                 └───────────┬───────────┘
                             │
                        Kafka Broker
                             │
                             ▼
                 ┌───────────────────────┐
                 │    Executor Service   │
                 │ Consumes job, runs in │
                 │ Docker container,     │
                 │ fetches testcases     │
                 │ from Problem Service  │
                 │ and stores result     │
                 └───────────┬───────────┘
                             │
                             ▼
                 ┌───────────────────────┐
                 │  Result Database      │
                 │ Stores job status,    │
                 │ output, logs, runtime │
                 └───────────────────────┘
