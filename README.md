# Library Management System (APRO1)

## 1. Project description

This Java project implements a simple library management system. The application supports:

- storing books and members
- loaning a book to a member
- returning a book
- displaying books, members, and loans in the console

## 2. OOP concepts used

- **Encapsulation:** all model fields are private and accessed through public methods (e.g., `Book`, `Loan`, `Person`).
- **Inheritance:** `PrintedBook` and `EBook` extend `Book`; `Member` extends `Person`.
- **Polymorphism:** `Book.getLoanDuration()` is implemented differently in `PrintedBook` and `EBook`, allowing loan rules to vary by book type without changing service logic.
- **Abstraction:** `Book` and `Person` are abstract base classes.

## 3. Design pattern: Repository (DAO-style)

The project uses the **Repository pattern** to separate business logic from data access.

### Interfaces (contracts)

- `BookRepository`
- `MemberRepository`
- `LoanRepository`

### Concrete implementations

Two different implementations exist to show the advantage of the pattern:

- **File-based persistence** (text files):
  - `FileBookRepository`
  - `FileMemberRepository`
  - `FileLoanRepository`
- **In-memory storage** (lists in RAM; no persistence):
  - `InMemoryBookRepository`
  - `InMemoryMemberRepository`
  - `InMemoryLoansRepository`

### Benefit

`LibraryService` depends only on the repository interfaces, so the data storage strategy can be swapped (file vs. memory) without modifying business logic.

## 4. Persistence

In file mode the application persists data in:

- `src/data/books.txt`
- `src/data/members.txt`
- `src/data/loans.txt`

## 5. Error handling (exceptions)

The service layer throws `IllegalArgumentException` for invalid IDs / missing data and handles invalid states (e.g., trying to loan an unavailable book).
File repositories throw runtime exceptions when file operations fail (I/O errors), so failures are visible and can be handled by the application.

## 6. How to run

Run `src.app.Main`.

### File mode (default)

Runs using file-based repositories and persists to `src/data/*.txt`.

### Memory mode

Run with the flag:

- `--memory`

In memory mode, all data is kept only in RAM (Java lists) and resets when the program is restarted.

## 7. UML class diagram

See the attached UML class diagram in the submission (or include an exported image/PDF in the zip file).
