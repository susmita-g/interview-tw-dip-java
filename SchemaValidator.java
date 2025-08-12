// SchemaValidator.java
// Lightweight validation for Exercise 1 output (structure-only).

import java.util.Map;

public final class SchemaValidator {
    private SchemaValidator() {}

    @SuppressWarnings("unchecked")
    public static boolean isValidTransformed(Object obj) {
        if (!(obj instanceof Map)) return false;
        Map<String,Object> root = (Map<String,Object>) obj;
        Object veh = root.get("vehicle");
        if (!(veh instanceof Map)) return false;
        Map<String,Object> vehicle = (Map<String,Object>) veh;

        Object totalPrice = vehicle.get("totalPrice");
        if (!(totalPrice == null || totalPrice instanceof String)) return false;

        Object certified = vehicle.get("certified");
        if (!(certified == null || certified instanceof Boolean)) return false;

        Object buyerObj = vehicle.get("buyer");
        if (!(buyerObj instanceof Map)) return false;
        Map<String,Object> buyer = (Map<String,Object>) buyerObj;

        Object contactID = buyer.get("contactID");
        if (!(contactID == null || contactID instanceof String)) return false;

        Object fullName = buyer.get("fullName");
        if (!(fullName instanceof String) || ((String)fullName).trim().isEmpty()) return false;

        Object isPerson = buyer.get("isPerson");
        if (!(isPerson instanceof Boolean)) return false;

        return true;
    }
}
