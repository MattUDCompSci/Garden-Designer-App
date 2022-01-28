package pkgMain;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class TipScreen {
	
public static Scene scene;

	/**
	 * Starting scene settings
	 */
	
	private double canvasWidth;
	private double canvasHeight;
	private int buttonWidth=200;
	private int buttonHeight=50;
	private Pane buttonPane;
	/**
	 * The space between each button on the Button Pane.
	 */
	private int buttonSpace=300;
	/**
	 * The X location of the right edge of the back button.
	 */
	private int backButtonXloc=200;
	/**
	 * Setting Button Dimensions
	 */
	private int buttonStartX = 0, buttonStartY = 0, buttonEndX = 200, buttonEndY = 50, padding = 10, ratio1 = 530;
	/**
	 * Setting Font Sizes
	 */
	private int titleFont = 50, paragraphFont = 30, buttonFont = 14;
	/**
	 * Setting Final Conditions
	 */
	
	private int layoutX = 0, layoutY = 0;
	/**
	 * Button used to move to the ProgramPurpose Scene
	 */
	public Button infoButton;
	/**
	 * The button to move to the Format Screen
	 */
	public Button forwardButton;
	/**
	 * The Backward button to return to Title Screen
	 */
	public Button backwardButton; 
	/**
	 * The BorderPane for the scene.
	 */
	private BorderPane borderPane;
	/**
	 * The TextFlow containing text being displayed on the screen.
	 */
	private TextFlow tipText = new TextFlow();
	/**
	 * The title of the screen.
	 */
	private Text title = new Text("Top 7 Gardening Tips for Beginners\n");
	/**
	 * The 7 gardening tips in another Text object
	 */
	private Text paragraph = new Text("1. Know what and where to plant your desired plants\n" +
									  "2. Test your soil to find the best plants for your conditions.\n" +
									  "3. Map out your garden to optimize your space.\n" + 
									  "4. Get some tools i.e a watering can, gloves and a gardening rake.\n" +
									  "5. Try using as many native plants as possible to better the ecosystem.\n" +
									  "6. Give the plants space to reach reach thier own maturity.\n" +
									  "7. Start small to make sure you can care for your plants.\n");
	
	/**
	 * Class Constructor
	 * @param canvasWidth1 the canvas width of the program window
	 * @param canvasHeight1 the canvas height of the program window
	 */
	public TipScreen(double canvasWidth1, double canvasHeight1) {
		this.canvasWidth = canvasWidth1;
		this.canvasHeight = canvasHeight1;
		this.buttonPane = initializeButtonPane();
		
		title.setFill(Color.GREEN);
		title.setFont(Font.font ("Verdana", FontWeight.BOLD,  titleFont));
		paragraph.setFill(Color.BLACK);
		paragraph.setFont(Font.font("Helvetica", paragraphFont));
		tipText.setTextAlignment(TextAlignment.CENTER);
		tipText.setLineSpacing(20.0f);
		
		tipText.getChildren().add(title);
        tipText.getChildren().add(paragraph);
        
        Image background = new Image(getClass().getResourceAsStream("/img/mt-cuba-third-background.png"));
    	ImageView iv = new ImageView(background);
		iv.setPreserveRatio(true);
		iv.setLayoutX(layoutX);
		iv.setLayoutY(layoutY);
		
	
		this.borderPane = new BorderPane();
		
		this.borderPane.getChildren().add(iv);
		this.borderPane.setBottom(buttonPane);
		this.borderPane.setCenter(tipText);
		this.borderPane.setStyle("-fx-background-color:lightblue");
		this.scene = new Scene(borderPane, canvasWidth, canvasHeight);
	}
	
	/**
	 * Returns a Button pane containing the buttons used to navigate to preceding and following
	 * screens. 
	 * <p>
	 * This a default method used across all screens. For this screen, the pane only
	 * contains a Back button since this is an annex screen.
	 * @return the Button pane with the Back button on the left corner
	 * 
	 */
		private Pane initializeButtonPane() { 
			Pane pane = new Pane();
			pane.setStyle("-fx-background-color:lightgreen");
			pane.setPrefSize(canvasWidth, 50);
			this.backwardButton = initializeButton(buttonStartX, buttonStartY, buttonWidth, buttonHeight, "Back");
			this.forwardButton = initializeButton(canvasWidth-buttonWidth, buttonStartY, buttonWidth, buttonHeight, "Next");
			this.forwardButton.setStyle("-fx-background-color: green");
			this.infoButton = initializeButton(backButtonXloc+buttonSpace, buttonStartY, buttonWidth, buttonHeight, "About us");
			this.infoButton.setStyle("-fx-background-color: lightblue");
			pane.getChildren().addAll(backwardButton, forwardButton, infoButton);
			pane.setLayoutX(layoutX);
			pane.setLayoutY(canvasHeight-buttonEndY);
			return pane;
			
		}
		
		/**
		 * Initializes a button to be put on the button pane
		 * @param XCoor the x coordinate of the button
		 * @param YCoor the y coordinate of the buttons
		 * @param Width the width of the button
		 * @param Height the height of the button
		 * @param text the text displayed on the button
		 * @return button
		 */
		private Button initializeButton(double XCoor, double YCoor, 
				double Width, double Height, String text) { 
			Button button = new Button(text);
			button.setTextFill(Color.WHITE);
			button.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, buttonFont));
			button.setLayoutX(XCoor);
			button.setLayoutY(YCoor);
			button.setClip(new Circle(Width/2.0, Height/2.0, Width/3.0));
			button.setPrefSize(Width, Height);
			button.setStyle("-fx-background-color: red");
			return button;
		}

}