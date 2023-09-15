package myWall;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Ball {

    Circle circle = new Circle();

    public Ball()
    {
        Rectangle r = new Rectangle(2,2,4,4);
        circle.setRadius(2.83);
        Shape s = Shape.intersect(r, circle);
        System.out.println(s.getBoundsInLocal().getCenterX() + " " + s.getBoundsInLocal().getCenterY());
        
    }
}
