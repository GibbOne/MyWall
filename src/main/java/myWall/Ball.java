package myWall;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class Ball extends Object2D{

    private Color color;

    public Ball(World world, float xCenter, float yCenter, float radius, float mass, Color color) 
    {
        super(world, new Circle(0, 0, radius), new Vector2D(xCenter, yCenter), mass, 0.5f); 
        setColor(color);
    }

    public Circle getCircle()
    {
        return (Circle)getShape();
    }

    public void setColor(Color color)
    {
        this.color = color;
        RadialGradient gradient = new RadialGradient(
            0, .1, 
            getCircle().getCenterX(), getCircle().getCenterY(), getCircle().getRadius(), 
            false, CycleMethod.REFLECT,
            new Stop(0, color), new Stop(0.75, color), new Stop(1, Color.TRANSPARENT));
        getCircle().setFill(gradient);
    }

    public Color getColor()
    {
        return color;
    }
}
