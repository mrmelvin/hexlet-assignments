package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private String path;
    private Map<String, String> basicMap = new HashMap<>();

    FileKV(String pathToFile, Map<String, String> initialMap) {
        basicMap = initialMap;
        path = pathToFile;
        Utils.writeFile(path, Utils.serialize(basicMap));
    }

    @Override
    public void set(String key, String value) {
        basicMap.put(key, value);
        Utils.writeFile(path, Utils.serialize(basicMap));
    }

    @Override
    public void unset(String key) {
        basicMap.remove(key);
        Utils.writeFile(path, Utils.serialize(basicMap));
    }

    @Override
    public String get(String key, String defaultValue) {
        return Utils.unserialize(Utils.readFile(path)).getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(Utils.unserialize(Utils.readFile(path)));
    }
}
// END
