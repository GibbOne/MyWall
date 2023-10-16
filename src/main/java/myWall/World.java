package myWall;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javafx.geometry.Bounds;

public class World {
    private Vector2D gravity = new Vector2D(0, 9.8);

    Vector<Object2D> objects = new Vector<Object2D>();

    public List<Object2D> getObjects()
    {
        return Collections.unmodifiableList(objects);
    }

    public void addObject(Object2D o) {
        objects.add(o);
    }

    private long lastTime = System.currentTimeMillis();
    private double timePassedInMicroSeconds;

    public void update() {
        updateTime();
        moveObjects();
		checkCollisions();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) { }
	}

	private void updateTime() {
		long curTime = System.currentTimeMillis();
		timePassedInMicroSeconds = (curTime - lastTime) / 1000.0;
		lastTime = curTime;
	}

    void moveObjects()
    {
        for (Object2D obj : objects) {
            if (!obj.getFixed())
            {
                obj.getSpeed().add(gravity.getMultiplied(timePassedInMicroSeconds));
                obj.moveOf(obj.getSpeed().x, obj.getSpeed().y);
            }
        }
    }

	void checkCollisions()
    {
        for (Object2D obj1 : objects) {
			if (!obj1.getFixed())
			{
        		for (Object2D obj2 : objects) {
					Bounds intersect = obj1.getCollisionIntersectBounds(obj2);
					if (obj1 != obj2 && intersect != null)
					{
						resolveCollision(obj1, obj2, intersect);
					}
		    	}
			}
        }
    }


	private void resolveCollision(Object2D obj1, Object2D obj2, Bounds intersect)
	{
		var collisionPoint = new Vector2D(intersect.getCenterX(), intersect.getCenterY());
		var collisionOverlap = Math.min(intersect.getHeight(), intersect.getWidth());

		// get the mtd (Minimum Translation Distance vector)
		Vector2D mtd1 = Vector2D.subtract(obj1.getPosition(), collisionPoint);
		// minimum translation distance to push balls apart after intersecting
		mtd1.multiply(collisionOverlap / mtd1.getLength() ); 

		// get the mtd (Minimum Translation Distance vector)
		Vector2D mtd2 = Vector2D.subtract(collisionPoint, obj2.getPosition());
		mtd2.multiply(collisionOverlap / mtd2.getLength() ); 

		// resolve intersection --
		// inverse mass quantities
		float im1 = 1 / obj1.getMass(); 
		float im2 = 1 / obj2.getMass();

		// push-pull them apart based off their mass
		obj1.setPosition(obj1.getPosition().getAdded(mtd1.getMultiplied(im1 / (im1 + im2))));
		obj2.setPosition(obj2.getPosition().getSubtracted(mtd2.getMultiplied(im2 / (im1 + im2))));

		// impact speed
		Vector2D v = (obj1.getSpeed().getSubtracted(obj2.getSpeed()));
		double vn = v.dot(mtd1.getNormalized()); //TODO

		// sphere intersecting but moving away from each other already
		if (vn > 0.0f) return;

		// collision impulse
		double i = (-(1.0f + obj1.getElasticity() + obj2.getElasticity()) * vn) / (im1 + im2);
		Vector2D impulse1 = mtd1.getNormalized().getMultiplied(i);
		Vector2D impulse2 = mtd2.getNormalized().getMultiplied(i);

		// change in momentum
		obj1.setSpeed(obj1.getSpeed().getAdded(impulse1.getMultiplied(im1)));
		obj2.setSpeed(obj2.getSpeed().getSubtracted(impulse2.getMultiplied(im2)));

	}
}
