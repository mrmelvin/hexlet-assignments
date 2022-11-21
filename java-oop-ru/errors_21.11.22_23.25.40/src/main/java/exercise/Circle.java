package exercise;

// BEGIN
public class Circle {

    private Point centerOfCircle;
    private int radius;

    Circle(Point initialPoint, int initialRadius) {
        centerOfCircle = initialPoint;
        radius = initialRadius;
    }

    public Integer getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Радиус должен быть больше нуля!");
        } else {
            return Math.PI * Math.pow(radius, 2);
        }
    }
}
// END
