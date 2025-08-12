# Data Transformation Exercises
This repository contains **four exercises** designed to evaluate your ability to:
- Transform and validate JSON data against a schema
- Extract and flatten fields from nested objects
- Write clean JavaScript code
- REST APIs

---

## ✅ Prerequisites
- **Java 17+** installed (`java -version`)

---

### Folder Structure
├── input.json                # Input objects (`input1`, `input2`, `input3`)
├── schema.avsc               # Upstream Schema (Exercise 1)
├── Transform.java            # Write your transformation logic here (Exercise 1)
├── Flatten.java              # Write your flattening logic here (Exercise 2)
├── VehicleDates.java         # Write your date formattig logic here (Exercise 3)
├── JsonMini.java             # Minimal JSON loader 
├── InputLoader.java          # Minimal JSON loader 
├── Main.java                 # Runs each exercise and prints validation

---

# 🧪 Exercise 1: Schema-Based Transformation

### 📋 Objective

Return data for totalPrice (price+fee), certified, contactID, fullName, isPerson
Transform input1 from **input.json** to match the schema in **schema.avsc**. Your output will be validated against the given schema.

## ✅ Tasks
- Read the input1 from `input.json`
- Write transformation logic in `Transform.java`
- Output must match `schema.avsc`
- Write test cases in a separate test file


---


# Exercise 2: Flatten the Nested JSON

### 📋 Objective

Transform the given input object into the expected output as shown below.

## ✅ Tasks
- Read the input2 from `input.json`
- Write transformation logic in `Flatten.java`

## Expected Output:
{
  engine: "2.0L Turbo",
  fuel: "Gasoline",
  features: "Sunroof|Leather Seats|Keyless Entry",
  retail_price: "23000"
};


---


# Exercise 3: Filter and Sort Dates

### 📋 Objective

Process the provided vehicle dates to reformat them as per the requirements below.

## ✅ Tasks
- Read the input3 from `input.json`
- Convert all `lastModifiedDate` values from `dd/MM/yyyy HH:mm:ss` to `MM/dd/yyyy HH:mm:ss`.
- Return the current year dates with latest dates first.
- Write transformation logic in `VehicleDates.java`

## Expected Output:
"vehicle": {
    "dates": [
        {
            "lastModifiedDate": "30/12/2025 10:33:07"
        },
        {
            "lastModifiedDate": "25/06/2025 20:12:00"
        }
    ]
}


---


## Run the code
```bash
javac *.java
java Main
```

--
