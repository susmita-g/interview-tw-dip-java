// Main.java
// Runs exercise 1 and prints schema-style validation messages.

import java.util.*;

public class Main {
    public static void main(String[] args) {
        InputLoader loader = new InputLoader("input.json");

        // ===== EXERCISE 1 =====
        Map<String,Object> ex1 = Transform.run(loader.input1());
        boolean valid1 = SchemaValidator.isValidTransformed(ex1);
        if (valid1) {
            System.out.println("\n✅ Schema validation passed (Exercise 1)");
        } else {
            System.out.println("\n❌ Schema validation failed (Exercise 1)");
        }
    }
}
