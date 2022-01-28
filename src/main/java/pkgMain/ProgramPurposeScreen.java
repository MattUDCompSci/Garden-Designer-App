package pkgMain;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;

/**
 * Creates a ProgramPurposeScreen object in the Garden Designer Program.
 * <p>
 * This class is for a screen that displays valuable information about the cause the program intends
 * to serve.
 * @author Bryan Paliska
 *
 */

public class ProgramPurposeScreen {
	
public static Scene scene;
	
	/**
	 * Starting scene Settings
	 */
	
	private double canvasWidth;
	private double canvasHeight;
	private int buttonWidth=200;
	private int buttonHeight=50;
	private Pane buttonPane;
	/**
	 * Button used to move to the Tips Scene
	 */
	public Button tipsButton;
	/**
	 * Set Final Conditions
	 */
	private int layoutX = 0, layoutY = 0, buttonStartX = 0, buttonStartY = 0;
	/**
	 * Setting Font Sizes
	 */
	private int paragraphFont = 32, titleFont = 50, buttonFont = 14;
	/**
	 * The space between each button on the Button Pane.
	 */
	private int buttonSpace=300;
	/**
	 * The X location of the right edge of the back button.
	 */
	private int backButtonXloc=200;
	/**
	 * The Button used to move to the Format Screen
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
	private TextFlow purpose = new TextFlow();
	/**
	 * The title of the screen.
	 */
	private Text title = new Text("About Us\n");
	/**
	 * The paragraph explaining the cause served by the program.
	 */
	private Text paragraph = new Text("Garden Designer is a tool designed to make "+
									  "the creation and organization of your garden easier. "+
								"It was specifically made to help support the proliferation of "+
									  "different species of butterflies and moths, also known as lepidopteras. "
								+ "Based on the environmental conditions of your " +
								"garden and your budget, Garden Designer will help you pick the "+
									  "best native plants to maintain biodiversity. By using this "+
								 "tool, you will make a significant contribution to the mission of "+
									  "conserving our natural habitats for generations to come.");
	
	/**
	 * Class Constructor
	 * @param canvasWidth1 the canvas width of the program window
	 * @param canvasHeight1 the canvas height of the program window
	 */
	public ProgramPurposeScreen(double canvasWidth1, double canvasHeight1) {
		this.canvasWidth = canvasWidth1;
		this.canvasHeight = canvasHeight1;
		this.buttonPane = initializeButtonPane();
		
		title.setFill(Color.GREEN);
		title.setFont(Font.font ("Verdana", FontWeight.BOLD, titleFont));
		paragraph.setFill(Color.BLACK);
		paragraph.setFont(Font.font("Helvetica", paragraphFont));
		purpose.setTextAlignment(TextAlignment.CENTER);
		purpose.setLineSpacing(20.0f);
		
		purpose.getChildren().add(title);
        purpose.getChildren().add(paragraph);

		Image background = new Image(getClass().getResourceAsStream("/img/mt-cuba-third-background.png"));
    	ImageView iv = new ImageView(background);
		iv.setPreserveRatio(true);
		iv.setLayoutX(layoutX);
		iv.setLayoutY(layoutY);
		
	
		this.borderPane = new BorderPane();
		
		this.borderPane.getChildren().add(iv);
		this.borderPane.setBottom(buttonPane);
		this.borderPane.setCenter(purpose);
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
			this.tipsButton = initializeButton(backButtonXloc+buttonSpace, buttonStartY, buttonWidth, buttonHeight, "Gardening Tips");
			this.tipsButton.setStyle("-fx-background-color: lightblue");
			pane.getChildren().addAll(backwardButton, tipsButton, forwardButton);
			pane.setLayoutX(layoutX);
			pane.setLayoutY(canvasHeight-50);
			return pane;
			
		}
		

	/**
	 * Returns a Button that can be added to a ButtonPane. It is the method used to initialize
	 * buttons in the <a href="#{@link}">{@link initializeButtonPane()}</a> method.
	 * 
	 * @param XCoor the x coordinate
	 * @param YCoor the y coordinate
	 * @param Width the width of the button
	 * @param Height the height of the button
	 * @param text the label on the button
	 * @return the Button with the specified characteristics
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
