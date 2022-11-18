package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private String tagName;
    private Map<String, String> tagAttributes = new HashMap<>();
    private String tagText;
    private List<Tag> childTags;
    
    PairedTag(String initialTagName, Map<String, String> initialMap, String initialtagText, List<Tag> initialChildTag) {
        tagName = initialTagName;
        tagAttributes = initialMap;
        tagText = initialtagText;
        childTags = initialChildTag;
    }

    @Override
    public String toString() {
        StringBuilder outputTag = new StringBuilder("");
        outputTag.append(new SingleTag(tagName, tagAttributes));
        outputTag.append(tagText);
        outputTag.append(childTags.stream().map(e -> e.toString()).collect(Collectors.joining("")));
        outputTag.append("</");
        outputTag.append(tagName);
        outputTag.append(">");
        return outputTag.toString();
    }
}
// END
