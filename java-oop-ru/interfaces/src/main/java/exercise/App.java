package exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

// BEGIN
public class App {
    public static List<String> buildAppartmentsList(List<Home> appartments, int buldingCout) {
        return appartments.stream()
                .sorted(Comparator.comparing(building -> building.getArea()))
                .limit(buldingCout)
                .map(building -> building.toString())
                .collect(Collectors.toList());
    }
}
// END
