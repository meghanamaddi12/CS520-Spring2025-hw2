#  Expense Tracker App

A simple desktop application built using Java Swing and structured with the **Model-View-Controller (MVC)** architecture. This app allows users to add categorized expenses and view them in a summary table with a running total.


##  Project Structure (MVC)

- **Model**
  - `Transaction`: Represents an individual transaction (amount, category, timestamp).
  - `ExpenseTrackerModel`: Manages a list of transactions and provides methods to add/remove/get them.

- **View**
  - `ExpenseTrackerView`: The user interface built using `JFrame`, including text fields, buttons, and a table.

- **Controller**
  - `ExpenseTrackerController`: Connects the model and view, processes input, and applies validation.
  - `InputValidation`: Validates user input (amounts must be numeric and ≤ 1000; category must be one of predefined valid words).


##  Features

- Add expenses with amount and category
- Input validation for amount and category  *(NEW in HW2)*
- Display transactions in a dynamic table
- View running total of all expenses
- Simple error messages via dialog boxes
- Predefined valid categories: `food`, `travel`, `bills`, `entertainment`, `other`
- Separation using MVC pattern  *(Improved in HW2)*
- Test cases for model/controller logic using JUnit  *(NEW in HW2)*


##  Tests Added (JUnit)

All tests are located in `test/TestExample.java`.

-  Add transaction and update total
-  Remove transaction and verify total is updated
-  Validate input range (amount > 1000, invalid category)
-  Verify transaction list size after actions


##  Design Principles Used

- **Model-View-Controller (MVC)**: Separates data, view, and controller responsibilities.
- **Open-Closed Principle**: Code is written to be extensible without modification.
- *(Strategy pattern planned if filters are added)*


##  Java Version

This project was compiled using:

```bash
openjdk version "23.0.2" 2025-01-21

## How To Run the Application
cd expense_tracker/src
javac ExpenseTrackerApp.java
java ExpenseTrackerApp
