package exercise;

import java.util.LinkedHashMap;
import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    private String tagName;
    private Map<String, String> tagAttributes = new LinkedHashMap<>();

    SingleTag(String initialTagName, Map<String, String> initialMap) {
        tagName = initialTagName;
        tagAttributes = initialMap;
    }

    @Override
    public String toString() {
        StringBuilder outputTag = new StringBuilder("<");
        outputTag.append(tagName);
        for (var elem: tagAttributes.entrySet()) {
            outputTag.append(" ");
            outputTag.append(elem.getKey());
            outputTag.append("=\"");
            outputTag.append(elem.getValue());
            outputTag.append("\"");
        }
        outputTag.append(">");
        return outputTag.toString();
    }
}
// END
