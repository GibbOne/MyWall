package myWall.UI;

import java.io.File;
import java.io.IOException;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import myWall.Ball;

public class WallBoard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WallBoard frame = new WallBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Image background;

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public WallBoard() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		background = ImageIO.read(new File("./src/main/resources/wallboard.png"));
		setSize(background.getWidth(this), background.getHeight(this));

		Ball b = new Ball();
	}
	
	private void DrawBall(Graphics2D g, Point point, int radius, Color color)
	{
		g.setColor(color);
        g.fillOval(point.x - radius, point.y - radius, radius * 2, radius * 2);
		
	    float fractions[] = { 0.5f, 1.0f };
        Color colors[] = { new Color(color.getRed(), color.getGreen(), color.getBlue(), 255), color };
        RadialGradientPaint paint = new RadialGradientPaint(point, radius, fractions, colors);
        g.setPaint(paint);
        g.fillOval(point.x - radius, point.y - radius, radius * 2, radius * 2);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(background, 0, 0, this);
		DrawBall((Graphics2D)g, new Point(200, 100), 13, new Color(0, 255, 0, 0));
	}
}
