package myWall;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

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
		// get the mtd
		Vector2D mtd = Vector2D.subtract(obj1.getPosition(), obj2.getPosition());
		// minimum translation distance to push balls apart after intersecting
		mtd.multiply(Math.min(intersect.getHeight(), intersect.getWidth()) / mtd.getLength() ); 
		
		// resolve intersection --
		// inverse mass quantities
		float im1 = 1 / obj1.getMass(); 
		float im2 = 1 / obj2.getMass();

		// push-pull them apart based off their mass
		obj1.setPosition(obj1.getPosition().getAdded(mtd.getMultiplied(im1 / (im1 + im2))));
		obj2.setPosition(obj2.getPosition().getSubtracted(mtd.getMultiplied(im2 / (im1 + im2))));

		// impact speed
		Vector2D v = (obj1.getSpeed().getSubtracted(obj2.getSpeed()));
		double vn = v.dot(mtd.getNormalized());

		// sphere intersecting but moving away from each other already
		if (vn > 0.0f) return;

		// collision impulse
		double i = (-(1.0f + obj1.getElasticity() + obj2.getElasticity()) * vn) / (im1 + im2);
		Vector2D impulse = mtd.getNormalized().getMultiplied(i);

		// change in momentum
		obj1.setSpeed(obj1.getSpeed().getAdded(impulse.getMultiplied(im1)));
		obj2.setSpeed(obj2.getSpeed().getSubtracted(impulse.getMultiplied(im2)));

	}

/* 
	private void initializeConstForces() {
		constForces.add(new Accel(0.0, Main.GRAVITY));
	}

	private synchronized void applyConstForces() {
		double xAccel = 0, yAccel = 0;
		// Find the total acceleration of all const forces.
		for (int i = 0; i < constForces.size(); i++) {
			xAccel += constForces.get(i).ax();
			yAccel += constForces.get(i).ay();
		}
		// Apply the sum acceleration to each entity.
		for (int i = 0; i < Main.living.size(); i++) {
			Spawn s = Main.living.get(i);
			s.addAccel(new Accel(xAccel, yAccel));
		}
	}

	private synchronized void sumForces() {
		for (int i = 0; i < Main.living.size(); i++) {
			Spawn s = Main.living.get(i);
			// Get the sum of all accelerations acting on object.
			Accel theAccel = s.sumAccel();
			// Apply the resulting change in velocity.
			double vx = s.vx() + (theAccel.ax() * timeFraction);
			double vy = s.vy() + (theAccel.ay() * timeFraction);
			s.updateVelocity(vx, vy);
			// Apply drag coefficient
			s.applyDrag(1.0 - (timeFraction * Main.DRAG));
		}
	}

	private synchronized void moveEnts() {
		for (int i = 0; i < Main.living.size(); i++) {
			Spawn s = Main.living.get(i);
			// Get the initial x and y coords.
			double oldX = s.getX(), oldY = s.getY();
			// Calculate the new x and y coords.
			double newX = oldX + (s.vx() * timeFraction);
			double newY = oldY + (s.vy() * timeFraction);
			s.updatePos(newX, newY);
			checkWallCollisions(s);
		}
		checkCollisions();
	}

	private synchronized void checkCollisions() {
		for (int i = 0; i < Main.living.size() - 1; i++) {
			Spawn s = Main.living.get(i);
			Point2D sCenter = s.getCenter();
			for (int j = i + 1; j < Main.living.size(); j++) {
				Spawn t = Main.living.get(j);
				if (t == null)
					break;
				Point2D tCenter = t.getCenter();
				double distBetween = sCenter.distance(tCenter);
				double bigR = s.getRadius() > t.getRadius() ? s.getRadius()
						: t
								.getRadius();
				if (distBetween < (bigR * 2))
					collide(s, t, distBetween);
			}
		}
	}

	private synchronized void collide(Spawn s, Spawn t, double distBetween) {
		// Get the relative x and y dist between them.
		double relX = s.getX() - t.getX();
		double relY = s.getY() - t.getY();
		// Take the arctan to find the collision angle.
		double collisionAngle = Math.atan2(relY, relX);
		// if (collisionAngle < 0) collisionAngle += 2 * Math.PI;
		// Rotate the coordinate systems for each object's velocity to align
		// with the collision angle. We do this by supplying the collision angle
		// to the vector's rotateCoordinates method.
		Vector2D sVel = s.velVector(), tVel = t.velVector();
		sVel.rotateCoordinates(collisionAngle);
		tVel.rotateCoordinates(collisionAngle);
		// In the collision coordinate system, the contact normals lie on the
		// x-axis. Only the velocity values along this axis are affected. We can
		// now apply a simple 1D momentum equation where the new x-velocity of
		// the first object equals a negative times the x-velocity of the
		// second.
		double swap = sVel.x;
		sVel.x = tVel.x;
		tVel.x = swap;
		// Now we need to get the vectors back into normal coordinate space.
		sVel.restoreCoordinates();
		tVel.restoreCoordinates();
		// Give each object its new velocity.
		s.updateVelocity(sVel.x * Main.BOUNCE, sVel.y * Main.BOUNCE);
		t.updateVelocity(tVel.x * Main.BOUNCE, tVel.y * Main.BOUNCE);
		// Back them up in the opposite angle so they are not overlapping.
		double minDist = s.getRadius() + t.getRadius();
		double overlap = minDist - distBetween;
		double toMove = overlap / 2;
		double newX = s.getX() + (toMove * Math.cos(collisionAngle));
		double newY = s.getY() + (toMove * Math.sin(collisionAngle));
		s.updatePos(newX, newY);
		newX = t.getX() - (toMove * Math.cos(collisionAngle));
		newY = t.getY() - (toMove * Math.sin(collisionAngle));
		t.updatePos(newX, newY);
	}

	private synchronized void checkWallCollisions(Spawn s) {
		int maxY = 480 - s.dimY();
		int maxX = 640 - s.dimX();
		if (s.getY() > maxY) {
			s.updatePos(s.getX(), maxY);
			s.updateVelocity(s.vx(), (s.vy() * -Main.BOUNCE));
		}
		if (s.getX() > maxX) {
			s.updatePos(maxX, s.getY());
			s.updateVelocity((s.vx() * -Main.BOUNCE), s.vy());
		}
		if (s.getX() < 1) {
			s.updatePos(1, s.getY());
			s.updateVelocity((s.vx() * -Main.BOUNCE), s.vy());
		}
	}
    */
}
