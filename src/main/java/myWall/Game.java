package myWall;

import java.util.List;

import javafx.scene.paint.Color;

public class Game {
    private World world = new World();

    public Game() 
    {
        // left and right border
        for (int i = 0; i < 125; i++)
        {
            world.addObject(new Peg(world, 0, i*8, 4));
            world.addObject(new Peg(world, 710, i*8, 4));
        }
        // buttom slots 
        for (int i = 0; i < 24; i++)
        {
            for (int j = 1; j < 17; j++)
            {
                world.addObject(new Peg(world, j*47, 820 + i*8, 4));
            }
        }
        // buttom border
        for (int j = 1; j < 17; j++)
        {
            world.addObject(new Peg(world, j*47 - 23, 1000, 4));
        }

        // inner pegs
        for (int j = 4; j < 13; j++)
        {
            world.addObject(new Peg(world, j*45 - 5, 232, 4));
        }
        for (int j = 3; j < 15; j++)
        {
            world.addObject(new Peg(world, j*45 - 28, 278, 4));
        }
        for (int j = 2; j < 15; j++)
        {
            world.addObject(new Peg(world, j*45 - 5, 320, 4));
        }
        for (int j = 2; j < 16; j++)
        {
            world.addObject(new Peg(world, j*45 - 28, 366, 4));
        }
        for (int j = 1; j < 16; j++)
        {
            world.addObject(new Peg(world, j*45 - 5, 412, 4));
        }
        for (int j = 2; j < 16; j++)
        {
            world.addObject(new Peg(world, j*45 - 28, 456, 4));
        }
        for (int j = 1; j < 16; j++)
        {
            world.addObject(new Peg(world, j*45 - 5, 502, 4));
        }
        for (int j = 2; j < 16; j++)
        {
            world.addObject(new Peg(world, j*45 - 28, 548, 4));
        }
        for (int j = 1; j < 16; j++)
        {
            world.addObject(new Peg(world, j*45 - 5, 594, 4));
        }
        for (int j = 2; j < 16; j++)
        {
            world.addObject(new Peg(world, j*45 - 28, 640, 4));
        }
        for (int j = 1; j < 16; j++)
        {
            world.addObject(new Peg(world, j*45 - 5, 686, 4));
        }
        for (int j = 2; j < 16; j++)
        {
            world.addObject(new Peg(world, j*45 - 28, 732, 4));
        }
        for (int j = 1; j < 16; j++)
        {
            world.addObject(new Peg(world, j*45 - 5, 778, 4));
        }
        //world.addObject(new Ball(world, 266, 130, 17, 10, new Color(0, 1, 0, 1) ));
    }

    public List<Object2D>  getObjects()
    {
        return world.getObjects();
    }

	public void update() {
		world.update();
        ComputeBallsPerSlot();
	}

    public Ball AddBallOnPipe(int pipeNumber, Color color)
    {
        int offset = (pipeNumber == 4 ? 1 : 0);
        var ball = new Ball(world, offset + 3+(pipeNumber + 4)*44, 132, 17, 10, color);
        world.addObject(ball);
        return ball;
    }

    /* 
    protected ArrayList<BallOnSlotEventListener> listenerList = new ArrayList<BallOnSlotEventListener>();

    public void addMyEventListener(BallOnSlotEventListener listener) {
        listenerList.add(listener);
    }
    public void removeMyEventListener(BallOnSlotEventListener listener) {
        listenerList.remove(listener);
    }
    */
    private int[] ballsPerSlot = new int[15];

    private void ComputeBallsPerSlot()
    {
        for (int i = 0; i < ballsPerSlot.length; i++)
            ballsPerSlot[i] = 0;

        for (Object2D b: world.objects)
        {
            if (b instanceof Ball && b.getPosition().y > 820)
            {
                int index = (int)(b.getPosition().x / 47);
                if (index > -1 && index < ballsPerSlot.length)
                {
                    if (((Ball)b).getColor().equals(new Color(0,1,0,1)))
                        ballsPerSlot[index]++;
                    else
                        ballsPerSlot[index]--;
                }
            }
        }
    }

    public int GetSlotsCount()
    {
        return ballsPerSlot.length;
    }

    public int GetBallsInSlot(int index)
    {
        return ballsPerSlot[index];
    }
}

/* 
class BallOnSlotEvent extends EventObject {
  public BallOnSlotEvent(Game source) {
    super(source);
  }
}

interface BallOnSlotEventListener extends EventListener {
  public void myEventOccurred(BallOnSlotEvent evt);
}
*/