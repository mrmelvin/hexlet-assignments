package exercise;

// BEGIN
public class App {
    public static String printSquare(Circle circle) {
        StringBuilder str = new StringBuilder("");
        try {
            int square = (int) Math.round(circle.getSquare());
            str.append(square);
        } catch (NegativeRadiusException e) {
            str.append("\nНе удалось посчитать площадь");
        } finally {
            str.append("\nВычисление окончено");
        }
        return str.toString();
    }
}
// END
