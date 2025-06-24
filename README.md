#  HappyFox Selenium Test Automation — Code Review

##  Overview

This repository contains a **reviewed and annotated Selenium automation script** targeting the HappyFox support portal.

---

## 🔍 Key Areas Reviewed

| Category | What To Improve |
|----------|-------------------|
|  Code Structure | Break down monolithic class into modular, readable blocks |
|  Secrets Handling | Replace hardcoded credentials with config recommendation |
| Locator Strategy | Replace fragile XPaths with maintainable strategies |
|  Waits | Flag `Thread.sleep()` and use `WebDriverWait` |
|  Assertions | Recommended meaningful validations post actions |
|  Framework Design | Promoted usage of Page Object Model and Browser Factory |
|  Robot Class | Flagged as flaky and suggested better alternatives |
|  Page Classes | Recommended separate Java files for maintainability |

---

## 🏗️ Suggested Folder Structure

```
src/
│
├── pages/
│   ├── LoginPage.java
│   ├── HomePage.java
│   └── TablePage.java
│
├── tests/
│   └── HappyFoxStatusTests.java
│
├── utils/
│   ├── WebDriverFactory.java
│   └── ConfigReader.java
```

## Reviewer Perspective
This code review is focusing on:

- **Maintainability** for long-term projects

- **Scalability** for CI/CD and cloud-based test grids

- Best practices for l**ocator strategies, secrets handling, and modular architecture**


## Assignment Done By -
Tanishq Arora
QA Automation Engineer | 3+ Years Experience
Skilled in Java, Selenium, Rest Assured, CI/CD with GitLab
