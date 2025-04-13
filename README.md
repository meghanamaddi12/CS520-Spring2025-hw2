# CS520 Spring 2025 - Homework 2: Expense Tracker

## Overview

This Java-based Expense Tracker application is designed to demonstrate core software engineering principles such as modularity, extensibility, testability, and maintainability using the **Model-View-Controller (MVC)** pattern. The app allows users to add, view, and filter financial transactions based on category and amount.

---

## Features Implemented

### Add Transaction
- Allows the user to add a transaction with a valid amount and predefined category.
- Input validation ensures the amount is within [0, 1000] and the category is one of: `food`, `travel`, `bills`, `entertainment`, or `other`.

### View Transaction List
- Displays a list of all added transactions.
- Automatically calculates and displays the total cost.

### Filter Functionality (Extensibility)
- **Strategy Design Pattern** used to support extensible filtering.
- Filters implemented:
    - By **Amount** (returns transactions with exact match)
    - By **Category** (returns transactions in selected category)
- New filters can be added without modifying the core filtering interface.

### Input Validation
- Invalid inputs (e.g., negative amount or unknown category) are rejected.
- Application handles errors gracefully without crashing or altering stored data.

---

## Software Design Principles Applied

### Modularity: Open-Closed Principle
- Transactions are encapsulated and immutable.
- Internal list of transactions is returned as a **copy** to prevent external modification.

### Extensibility: Strategy Pattern
- `TransactionFilter` interface supports pluggable filter logic.
- New filters can be added independently of the controller logic.

### Testability
- 4 new test cases added:
    1. Add Transaction
    2. Invalid Input Handling
    3. Filter by Amount
    4. Filter by Category
- Existing test cases continue to pass.
- Tests include **print statements** for visual validation of expected behavior.
- JUnit-based test suite included in `test/` folder.

---

## Undo Design Plan (Usability)

Although not implemented, the design for an **Undo functionality** has been proposed and documented in `undo.pdf`, following the MVC structure:
- `Model`: Uses a `Stack<Transaction>` to support undoing the last removed transaction.
- `Controller`: Exposes a `handleUndo()` method.
- `View`: Provides an “Undo” button to trigger the undo action.

---

## Project Structure

hw2_expensetracker:
    .idea/: {}
    expense_tracker/:
        lib/: {}
        src/:
            controller/:
            filter/:        
            model/:
           view/:
        test/:
    jdoc/: # Generated JavaDoc HTML files
    build.xml: {}
    README.md: {}
    hw2.iml: {}
    test_screenshot.png: {}
    undo.pdf: {}



---

## JavaDoc

- Documentation auto-generated using:
  javadoc -d jdoc/ -sourcepath expense_tracker/src controller filter model view
- All public-facing methods are documented. 
- Remaining warnings are due to private GUI components and can be safely ignored

---

## How to Run


1. Compile the project using IntelliJ or `javac` via terminal.
2. Run `ExpenseTrackerApp` to launch the GUI.
3. Use the JUnit test runner or IntelliJ's test window to execute all tests.


---

## Author
Meghana Maddipatla  
CS520 Spring 2025  
University of Massachusetts Amherst

