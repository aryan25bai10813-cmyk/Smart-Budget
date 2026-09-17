SmartBudget – Java Personal Expense & Budget Manager
Overview
SmartBudget is a console-based Java application that helps students record expenses, manage a monthly budget, search transactions, and generate monthly spending reports.
The project demonstrates Java OOP, collections, file I/O, exception handling, validation, streams, enums, date/time APIs, and modular package design.
Major Modules
Expense Management – add, view, delete, and search expenses.
Budget Management – set a monthly budget and calculate remaining budget/usage.
Reporting & Analytics – monthly totals, category-wise spending, largest expense, and budget warnings.
Technologies
Java 17+
OOP
Collections Framework
Streams
java.time API
Buffered file I/O
Exception handling
Git/GitHub
Project Structure
```text
SmartBudget/
├── src/smartbudget/
│   ├── SmartBudgetApp.java
│   ├── Expense.java
│   ├── Category.java
│   ├── Budget.java
│   ├── DataStore.java
│   ├── ExpenseManager.java
│   ├── ReportService.java
│   └── InputValidator.java
├── tests/SmartBudgetTest.java
├── data/expenses.txt
├── docs/
├── statement.md
└── README.md
```
Run
From the project root:
```bash
mkdir -p out
javac -d out src/smartbudget/*.java
java -cp out smartbudget.SmartBudgetApp
```
On Windows PowerShell:
```powershell
mkdir out
javac -d out src\smartbudget\*.java
java -cp out smartbudget.SmartBudgetApp
```
Run Tests
```bash
javac -cp out -d out tests/SmartBudgetTest.java
java -ea -cp out SmartBudgetTest
```
Expected:
`All validation tests passed.`
Data
Expense records are stored in `data/expenses.txt` using a simple pipe-delimited format. The application creates the file/directory if needed.
GitHub
Suggested commands:
```bash
git init
git add .
git commit -m "Initial SmartBudget project"
git branch -M main
git remote add origin <YOUR_GITHUB_REPOSITORY_URL>
git push -u origin main
```

