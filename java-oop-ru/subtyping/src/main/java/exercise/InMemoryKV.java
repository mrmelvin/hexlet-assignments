package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    private Map<String, String> basicMap = new HashMap<>();
    InMemoryKV(Map<String, String> initialMap) {
        basicMap.putAll(initialMap);
    }

    @Override
    public void set(String key, String value) {
        basicMap.put(key, value);
    }

    @Override
    public void unset(String key) {
        basicMap.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return basicMap.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {

        return new HashMap<>(basicMap);
    }
}
// END
