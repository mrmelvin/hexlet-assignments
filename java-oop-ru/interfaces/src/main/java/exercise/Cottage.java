package exercise;

// BEGIN
public class Cottage implements Home {

    private double cottageArea;
    private int cottageFloor;

    Cottage(double area, int floor) {
        cottageArea = area;
        cottageFloor = floor;
    }


    public double getArea() {
        return cottageArea;
    }


    public int compareTo(Home anotherHome) {
        if (anotherHome.getArea() < cottageArea) {
            return 1;
        } else if (anotherHome.getArea() > cottageArea) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return cottageFloor + " этажный коттедж площадью " + getArea() + " метров";
    }
}
// END
