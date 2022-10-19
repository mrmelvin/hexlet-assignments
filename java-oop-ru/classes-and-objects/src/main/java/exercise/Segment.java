package exercise;

// BEGIN
public class Segment {

    private Point startPoint;
    private Point endPoint;
    public Segment(Point start, Point end) {
        this.startPoint = start;
        this.endPoint = end;
    }

    public Point getBeginPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        int newXcoordinate = (startPoint.getX() + endPoint.getX()) / 2;
        int newYcoordinate = (startPoint.getY() + endPoint.getY()) / 2;
        return new Point(newXcoordinate, newYcoordinate);
    }
}
// END
