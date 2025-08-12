// InputLoader.java
// Loads input.json and exposes input1, input2, input3 as Maps.

import java.util.*;
import java.io.*;
import java.nio.file.*;

@SuppressWarnings("unchecked")
public final class InputLoader {
    private final Map<String,Object> root;

    public InputLoader(String path) {
        try {
            Object parsed = JsonMini.parseFile(path);
            if (!(parsed instanceof Map)) throw new RuntimeException("input.json must be a JSON object");
            this.root = (Map<String,Object>) parsed;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input.json: " + e.getMessage(), e);
        }
    }

    public Map<String,Object> input1() {
        return asMap(root.get("input1"));
    }
    public Map<String,Object> input2() {
        return asMap(root.get("input2"));
    }
    public Map<String,Object> input3() {
        return asMap(root.get("input3"));
    }

    private Map<String,Object> asMap(Object o) {
        if (!(o instanceof Map)) throw new RuntimeException("Expected object in input.json");
        return (Map<String,Object>) o;
    }
}