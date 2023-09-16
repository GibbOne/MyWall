package myWall;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Object2D{

    Circle circle;

    public Ball(World world, float x, float y, float radius, float mass) 
    {
        super(world, new Circle(x, y, radius), mass, 0.1f); 
    }

    public Circle getCircle()
    {
        return (Circle)getShape();
    }

    public void setColor(Color color)
    {
        circle.fillProperty().setValue(color);
    }
}
