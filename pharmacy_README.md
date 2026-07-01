# Pharmacy Management System

A console-based pharmacy management system built in Java demonstrating interfaces, multi-level inheritance, and polymorphism.

The system allows users to view available medicines and purchase them — handling prescription requirements, stock management, and automatic bill calculation with bulk discounts.

## Features

- View full inventory of 10 medicines with details
- Purchase medicines with or without prescription
- Automatic stock update after purchase
- Bill calculation with bulk discounts:
  - More than 100 units → 25% discount
  - More than 10 units → 10% discount
- "Medicine not found" handling for invalid input

## Class Structure

```
Pharmacy (interface)
  └── Medicine (implements Pharmacy)
        ├── Info          — displays medicine details
        └── Buy           — handles purchase quantity
              ├── Prescription       — prescription-required medicines
              ├── WithoutPrescription — OTC medicines
              └── BillMake           — bill & discount calculation
```

## Concepts Demonstrated

- Interface (`Pharmacy`)
- Multi-level inheritance
- Method overriding with `super`
- Encapsulation (private fields + getters)
- Polymorphism
- Enhanced for-each loop

## How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/sangothayan-y/pharmacy-management-system.git
   cd pharmacy-management-system
   ```

2. Compile:
   ```bash
   javac Main.java
   ```

3. Run:
   ```bash
   java Main
   ```

## Example Output

```
================ WELCOME TO PHARMACY SYSTEM ================
What do you want?
1. Show available medicine details
2. Buy medicine
Choose an option: 2

Available Medicines:
[Sertraline / Metformin / Zolpidem / Insulin / Phenytoin / Morphine / Isotretinoin / Prednisolone / Diazepam / Fluoxetine]
Enter medicine name: Metformin
How many units? 15
Is prescription required? (true/false): true
Prescribed by: Dr. Silva
Medicine purchased successfully.
Medicine ID    : M0035
Medicine Name  : Metformin
Price          : Rs. 1000.0
Stock Count    : 100
Type           : Tablet
Prescribed By  : Dr. Silva
Total Bill : Rs. 13500.0
```

## Author

**Sangothayan**
[GitHub](https://github.com/sangothayan-y) · [LinkedIn](https://www.linkedin.com/in/yoganantham-sangothayan-244068396/)
