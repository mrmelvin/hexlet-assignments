package exercise;

// BEGIN
public class Flat implements Home {

    private double flatArea;
    private double balconyFlatArea;
    private int flatFloor;


    Flat(double area, double balconyArea, int floor) {
        flatArea = area;
        balconyFlatArea = balconyArea;
        flatFloor = floor;
    }


    public double getArea() {
        return flatArea + balconyFlatArea;
    }


    public int compareTo(Home anotherHome) {
        if (anotherHome.getArea() < flatArea) {
            return 1;
        } else if (anotherHome.getArea() > flatArea) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + flatFloor + " этаже";
    }
}
// END
