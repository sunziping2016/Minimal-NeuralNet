package Shape2;

/**
 * Created by sun on 4/2/16.
 *
 * Square class.
 */
public class Square extends Rectangle {
    public Square() {}
    public Square(double side) {
        super(side, side);
    }
    public Square(double side, String color, boolean filled) {
        super(side, side, color, filled);
    }
    public double getSide() {
        return this.width;
    }
    public void setSide(double side) {
        this.width = this.length = side;
    }
    public void setWidth(double side) {
        setSide(side);
    }
    public void setLength(double side) {
        setSide(side);
    }

    @Override
    public String toString() {
        return "Square";
    }
}
