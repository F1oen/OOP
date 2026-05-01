# University Hall of Residence Manager

A Java-based GUI application for managing students and staff in a university hall of residence system.

## 📋 Description

The University Hall of Residence Manager is an object-oriented Java application designed to streamline the management of residents and staff in university halls. The system automates student and employee registration, hall allocation, dietary preference tracking, and resident data management.

## ✨ Features

- **Student Management**
  - Register students with personal details (name, date of birth, registration date)
  - Track academic courses
  - Record special needs and dietary preferences
  - Automatic hall assignment based on needs

- **Employee Management**
  - Register staff members with job titles and salary information
  - Allocate staff to the Staff Hall

- **Hall Management**
  - Multiple hall types with capacity management:
    - Ground Floor Hall (5 capacity)
    - Dietary Support Hall (10 capacity)
    - Standard Hall (20 capacity)
    - Staff Hall (10 capacity)
  - Smart allocation system based on student needs

- **Data Persistence**
  - Save and load records from files
  - Maintain data integrity across sessions

- **User Interface**
  - Intuitive Swing-based GUI
  - Real-time data display and summary
  - Easy form-based data entry

## 📁 Project Structure

```
src/
├── Main.java                  # Application entry point
├── gui/
│   └── MainFrame.java        # Main GUI window and controls
└── model/
    ├── Person.java           # Base class for all residents
    ├── Student.java          # Student entity with course and dietary info
    ├── Employee.java         # Employee entity with job details
    ├── Store.java            # Core data management and hall allocation
    ├── Hall.java             # Hall entity and capacity management
    └── Payment.java          # Payment tracking (optional)
```

## 🛠️ Technologies Used

- **Language**: Java (Java 8+)
- **GUI Framework**: Swing
- **Data Persistence**: File I/O
- **Architecture**: Object-Oriented Programming (OOP)

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code with Java extensions, etc.)

### Installation

1. Clone or download the project:
```bash
git clone <repository-url>
cd OOP L5
```

2. Compile the Java files:
```bash
javac -d bin src/**/*.java
```

3. Run the application:
```bash
java -cp bin Main
```

Or if using an IDE, open the project and run `Main.java` directly.

## 💻 Usage

1. **Launch the Application**: Run the Main class to open the GUI window
2. **Add Records**: 
   - Enter personal details (first name, last name, date of birth)
   - Select person type (Student or Employee)
   - Fill in type-specific information
   - Click "Add" to register
3. **View Records**: The display area shows all current residents
4. **Summary**: View occupancy and allocation statistics
5. **Save/Load**: Use file operations to persist data between sessions

## 📝 Class Overview

- **Person**: Abstract base class for all residents with common attributes
- **Student**: Extends Person; includes course and dietary preference tracking
- **Employee**: Extends Person; includes job title and salary information
- **Hall**: Manages hall information, capacity, and resident allocation
- **Store**: Central management class handling record storage and hall assignments
- **MainFrame**: GUI components and user interaction handling
- **Payment**: Handles payment-related operations

## 🎯 Key Concepts Demonstrated

- Object-Oriented Programming (Inheritance, Polymorphism, Encapsulation)
- GUI Development with Swing
- File I/O and Data Persistence
- Collection Management (ArrayList)
- Date Handling and Formatting
- Event-Driven Programming
