package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        for (var e: storage.toMap().entrySet()) {
            storage.set(e.getValue(), e.getKey());
            storage.unset(e.getKey());
        }
    }
}
// END
