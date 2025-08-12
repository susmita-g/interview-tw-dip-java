// JsonMini.java
// Minimal JSON parser for interview use.

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public final class JsonMini {
    private final String s;
    private int i = 0;

    private JsonMini(String s) { this.s = s; }

    public static Object parse(String json) {
        return new JsonMini(json).parseValue();
    }

    public static Object parseFile(String path) throws IOException {
        try (InputStream in = new FileInputStream(path)) {
            byte[] bytes = in.readAllBytes();
            return parse(new String(bytes, StandardCharsets.UTF_8));
        }
    }

    private void skipWs() {
        while (i < s.length() && Character.isWhitespace(s.charAt(i))) i++;
    }

    private Object parseValue() {
        skipWs();
        if (i >= s.length()) throw new RuntimeException("Unexpected end");
        char c = s.charAt(i);
        if (c == '{') return parseObject();
        if (c == '[') return parseArray();
        if (c == '"') return parseString();
        if (c == 't' || c == 'f') return parseBoolean();
        if (c == 'n') return parseNull();
        return parseNumber();
    }

    private Map<String,Object> parseObject() {
        Map<String,Object> m = new LinkedHashMap<>();
        i++; // {
        skipWs();
        if (s.charAt(i) == '}') { i++; return m; }
        while (true) {
            skipWs();
            String key = parseString();
            skipWs();
            if (s.charAt(i) != ':') throw new RuntimeException("Expected ':'");
            i++; // :
            Object val = parseValue();
            m.put(key, val);
            skipWs();
            char c = s.charAt(i);
            if (c == ',') { i++; continue; }
            if (c == '}') { i++; break; }
            throw new RuntimeException("Expected ',' or '}'");
        }
        return m;
    }

    private List<Object> parseArray() {
        List<Object> a = new ArrayList<>();
        i++; // [
        skipWs();
        if (s.charAt(i) == ']') { i++; return a; }
        while (true) {
            Object val = parseValue();
            a.add(val);
            skipWs();
            char c = s.charAt(i);
            if (c == ',') { i++; continue; }
            if (c == ']') { i++; break; }
            throw new RuntimeException("Expected ',' or ']'");
        }
        return a;
    }

    private String parseString() {
        if (s.charAt(i) != '"') throw new RuntimeException("Expected '\"'");
        i++; // "
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            char c = s.charAt(i++);
            if (c == '"') break;
            if (c == '\\') {
                char e = s.charAt(i++);
                switch (e) {
                    case '"': sb.append('"'); break;
                    case '\\': sb.append('\\'); break;
                    case '/': sb.append('/'); break;
                    case 'b': sb.append('\b'); break;
                    case 'f': sb.append('\f'); break;
                    case 'n': sb.append('\n'); break;
                    case 'r': sb.append('\r'); break;
                    case 't': sb.append('\t'); break;
                    case 'u':
                        int code = Integer.parseInt(s.substring(i, i+4), 16);
                        sb.append((char) code);
                        i += 4;
                        break;
                    default: throw new RuntimeException("Bad escape: \\" + e);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private Boolean parseBoolean() {
        if (s.startsWith("true", i)) { i+=4; return Boolean.TRUE; }
        if (s.startsWith("false", i)) { i+=5; return Boolean.FALSE; }
        throw new RuntimeException("Bad boolean literal");
    }

    private Object parseNull() {
        if (s.startsWith("null", i)) { i+=4; return null; }
        throw new RuntimeException("Bad null literal");
    }

    private Number parseNumber() {
        int start = i;
        if (s.charAt(i) == '-') i++;
        while (i < s.length() && Character.isDigit(s.charAt(i))) i++;
        if (i < s.length() && s.charAt(i) == '.') {
            i++;
            while (i < s.length() && Character.isDigit(s.charAt(i))) i++;
        }
        if (i < s.length() && (s.charAt(i) == 'e' || s.charAt(i) == 'E')) {
            i++;
            if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;
            while (i < s.length() && Character.isDigit(s.charAt(i))) i++;
        }
        String num = s.substring(start, i);
        if (num.contains(".") || num.contains("e") || num.contains("E")) return Double.parseDouble(num);
        long l = Long.parseLong(num);
        if (l <= Integer.MAX_VALUE && l >= Integer.MIN_VALUE) return (int) l;
        return l;
    }
}
