package myWall.UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import myWall.Game;
import myWall.Main;
import myWall.Object2D;

public class WallBoard extends Application {

    /**
	 * Launch the application.
	 */
	public static void launch(String[] args) {
        Application.launch(args);
	}

	public void start(Stage stage) {
		game = new Game();
        Pane root = new Pane();
		root.setBackground(new Background(new BackgroundImage(new Image(Main.class.getClassLoader().getResource("wallboard.png").toString()),
        	BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          	new BackgroundSize(700, 1000, true, true, true, true))));

		for (Object2D o : game.getObjects()) {
			root.getChildren().add(o.getShape());
		}

        Scene scene = new Scene(root, 700, 1000);
        stage.setTitle("MyWall game");
        stage.setScene(scene);
        stage.show();
    }
		
	private Game game;
	/*

	public WallBoard() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		background = ImageIO.read(new File("./src/main/resources/wallboard.png"));
		setSize(background.getWidth(this), background.getHeight(this));
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


 
	private void DrawObjects(Game game, Graphics2D g)
	{
		for (Object2D object : game.getObjects()) {
			g.draw(object.getShape());
		}
	}
	*/
}
