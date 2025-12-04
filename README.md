# Automation Framework Documentation

## 1. Setup and Installation
**Prerequisites:**
* **Java Development Kit (JDK):** Version 17 or higher.
* **Maven:** Apache Maven 3.8+.
* **IDE:** Eclipse or IntelliJ IDEA (with Cucumber plugin installed).
* **Browser:** Google Chrome (latest version).

**Installation Steps:**
1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git](https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git)
    ```
2.  **Import to IDE:**
    * Open Eclipse > File > Import > Existing Maven Projects.
    * Select the project folder containing `pom.xml`.
3.  **Update Dependencies:**
    * Right-click project > Maven > Update Project.

---

## 2. Writing and Executing Test Cases
We use **Cucumber BDD**, which allows tests to be written in plain English.

**How to Write a New Test:**
1.  **Create a Feature File:** Add a `.feature` file in `src/test/resources/features`.
    ```gherkin
    Feature: Login Functionality
      Scenario: Successful Login
        Given I am on the login page
        When I enter valid credentials
        Then I should be redirected to the dashboard
    ```
2.  **Create Step Definitions:** Map the steps to Java code in `src/test/java/stepdefs`.
    ```java
    @Given("I am on the login page")
    public void navigateToLogin() {
        driver.get("[https://automationteststore.com/login](https://automationteststore.com/login)");
    }
    ```

**How to Execute Tests:**
* **Run via Command Line:**
    ```bash
    mvn test
    ```
* **Run via Eclipse:**
    * Right-click `TestRunner.java` > Run As > JUnit Test.

---

## 3. Design Decisions & Architecture
This framework follows industry-standard design patterns to ensure maintainability.

**A. Page Object Model (POM)**
* **Why used?** To separate test logic from UI locators.
* **Implementation:** Each web page (e.g., `LoginPage`, `ContactUsPage`) has its own Java class. If the UI changes, we only update the Page Class, not the Test Class.

**B. Singleton Pattern (Driver Management)**
* **Why used?** To ensure only one browser instance is active per thread, preventing resource conflicts during execution.

**C. Behavior Driven Development (BDD)**
* **Why used?** To bridge the gap between technical developers and non-technical stakeholders (business requirements).

---

## 4. Data Integration & Reporting

**Data-Driven Testing (Excel Integration):**
* We use **Apache POI** to read test data from external files.
* **Data Source:** `src/test/resources/ContactData.xlsx`.
* **Usage:** The `ExcelUtils` class reads rows from the Excel sheet and feeds them into the Cucumber steps, allowing us to test multiple data sets without changing code.

**Generating Reports:**
* **Surefire Reports:** Generated automatically by Maven after execution.
    * *Location:* `target/surefire-reports/index.html`.
* **Cucumber HTML Reports:** (Optional) Can be configured in the Runner file to produce a visual HTML summary.

---

## 5. GitHub Workflow Guidelines
To maintain code quality, we follow a strict **Feature Branch Workflow**.

**Step-by-Step Workflow:**
1.  **Create a Branch:** Never push directly to `main`.
    ```bash
    git checkout -b feature/login-page
    ```
2.  **Commit Changes:** Keep commit messages clear.
    ```bash
    git commit -m "Added login page locators"
    ```
3.  **Push & Pull Request (PR):**
    * Push the branch to GitHub.
    * Create a "Pull Request" on the GitHub website.
    * Review the code changes and merge only if the CI pipeline passes.
4.  **Issue Tracking:**
    * Use **GitHub Issues** to log bugs found during testing.
    * Label issues clearly (e.g., `bug`, `enhancement`, `wontfix`).