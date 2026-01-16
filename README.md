# HapifyMe API Automation Project

A Java-based **API automation project** demonstrating a **complete End-to-End user lifecycle** for the HapifyMe application.

---

## Overview

This project is part of my portfolio and showcases my skills in:

- API automation with **Rest-Assured** and **TestNG**  
- Dynamic data handling using **JavaFaker**  
- JSON serialization/deserialization with **Jackson**  
- Asynchronous polling and response waiting using **Awaitility**  
- Organizing a **Maven project** with clear package structure (`models`, `utils`, `tests`)

The project covers the **full user lifecycle**:

1. Registration with dynamically generated data  
2. Email confirmation  
3. Login and obtaining Bearer token  
4. Profile retrieval and validation  
5. Profile update  
6. User deletion and verification  

---

## Highlights

- Complete **End-to-End workflow** for a user  
- Intelligent use of **dynamic test data** and **async polling**  
- Focus on **robust automated testing** and well-structured code  
- Demonstrates both **manual QA mindset** and **automation skills in Java**  

---

## Continuous Integration

- Tests are automatically run using **GitHub Actions** on every push or pull request to the main branch.  
- Test results are generated and uploaded as artifacts for review.  
- Ensures that API workflows are continuously verified and any issues are immediately visible.  

---

## Project Structure

- `models/` → POJOs for requests and responses  
- `utils/` → helper classes (DataGenerator, ApiPoller, ConfigManager)  
- `tests/` → test classes (UserLifecycleTest)  
- `pom.xml` → Maven dependencies and build configuration  

---

This project is a concrete example from my portfolio that demonstrates **how I design and implement robust, complete API tests**.
