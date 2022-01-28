package pkgMain;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextField;

/**
 * Creates the Conditions Screen for the Garden Designer
 * <p>
 * Initializes the RadioButtons for each of the Soil Conditions and places them in the appropriate ToggleGroups.
 * Initializes the Budget TextField to get the users input budget. Saves the values to Model class when Scene is
 * changed forward.
 * 
 * @author mweis
 * 
 */
public class ConditionScreen {
	
	/**
	 * The ConditionScreen Scene
	 */
	public static Scene scene;
	
	/**
	 * The Canvas Width and Height
	 */
	
	private double canvasWidth, canvasHeight;
	
	/**
	 * The Y coordinate column
	 */
	
	final private double titleRow = 1.0/8, sunRow = 1.0/4, soilRow = 1.0/2, moistureRow = 3.0/4, budgetInputRow = 1.0/2;
	
	/**
	 * The X coordinate column
	 */
	
	private double leftColumn = 3.0/14, middleColumn = 5.0/14, rightColumn = 1.0/2, budgetColumn = 2.5/4, labelColumn = 1.0/14;
	
	/**
	 * Setting Font Sizes
	 */
	
	private int titleFont = 25, conditionFont = 20, buttonFont = 14;
	
	/**
	 * Setting Button Dimensions
	 */
	
	private int buttonStartX = 0, buttonStartY = 0, buttonEndX = 200, buttonEndY = 50;
	
	/**
	 * The Pane used in the scene
	 */
	private Pane buttonPane;
	
	/**
	 * Label used to inform user about choosing their gardens conditions
	 */
	
	Label conditionsTitle = new Label("Enter your Garden Conditions Here");
	
	/**
	 * Label used to inform user to enter their desired budget
	 */
	Label budgetTitle = new Label("Enter your Garden Budget Here");
	
	/**
	 * Label used to inform user of which row each set of Radio Buttons and Conditions corresponds to
	 */
	Label Sun = new Label("Sun"), Soil = new Label("Moisture"), Moisture = new Label("Soil");
	
	/**
	 * A Radio Button used to select your sun condition
 	 */
	
	RadioButton fullSun = new RadioButton("Full Sun"), partShade = new RadioButton("Part Shade"), fullShade = new RadioButton("Full Shade");;
	
	/**
	 * A Radio Button used to select your soil condition
 	 */
	RadioButton dry = new RadioButton("Dry"), medium = new RadioButton("Medium"), wet = new RadioButton("Wet");
	
	/**
	 * A Radio Button used to select your soil condition
 	 */
	RadioButton sandy = new RadioButton("Sandy"), loam = new RadioButton("Loamy"), clay = new RadioButton("Clay");
	
	/**
	 * The RadioButton variable used to determine the selected RadioButton for the respective condition once exiting the Scene
	 */
	
	RadioButton sunChoice, soilChoice, moistureChoice;
	
	/**
	 * The Toggle Group to make sure only one Radio Button can be selected at a time for the respective condition
	 */
	ToggleGroup sun = new ToggleGroup(), soil = new ToggleGroup(), moisture = new ToggleGroup();
	
	/**
	 * ArrayList containing all Radio Buttons for sun Radio Buttons
	 */
	ArrayList<RadioButton> sunButtons = new ArrayList<RadioButton>() {{add(fullSun); add(partShade); add(fullShade);}};
	
	/**
	 * ArrayList containing all Radio Buttons for soil Radio Buttons
	 */
	ArrayList<RadioButton> soilButtons = new ArrayList<RadioButton>(){{add(dry); add(medium); add(wet);}};
	
	/**
	 * ArrayList containing all Radio Buttons for moisture Radio Buttons
	 */
	ArrayList<RadioButton> moistureButtons = new ArrayList<RadioButton>(){{add(sandy); add(loam); add(clay);}};
	
	/**
	 * The TextField variable used to get the users input text
	 */
	
	TextField budgetInput = new TextField("100");
	
	/**
	 * Forward and Backward Buttons to change scenes
	 */
	
	public Button forwardButton, backwardButton; 
	
	/**
	 * Setting Pane 
	 */
	
	private Pane conditionsPane;
	
	/**
	 * Class Constructor
	 * <p>
	 * Displays all the appropriate variables to the scene at the right coordinates and adds them
	 * to the conditions Pane
	 * @param canvasWidth1 the Garden Designer's Canvas Width
	 * @param canvasHeight1 the Garden Designer's Canvas Height
	 */
	
	public ConditionScreen(double canvasWidth1, double canvasHeight1) {
		
		this.canvasWidth = canvasWidth1;
		this.canvasHeight = canvasHeight1;
		this.buttonPane = initializeButtonPane();
	
		this.conditionsPane = new Pane();
		
		conditionsTitle.setTextAlignment(TextAlignment.CENTER);
		conditionsTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, titleFont));
		conditionsTitle.setTranslateX(canvasWidth1*leftColumn - (conditionsTitle.getLayoutBounds().getWidth()/2.0));
		conditionsTitle.setTranslateY(canvasHeight1*titleRow);
		
		Sun.setTranslateX(canvasWidth1*labelColumn);
		Sun.setTranslateY(canvasHeight1*sunRow);
		Sun.setTextAlignment(TextAlignment.CENTER);
		Sun.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, conditionFont));
		Sun.setTextFill(Color.WHITE);
		
		Soil.setTranslateX(canvasWidth1*labelColumn);
		Soil.setTranslateY(canvasHeight1*soilRow);
		Soil.setTextAlignment(TextAlignment.CENTER);
		Soil.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, conditionFont));
		Soil.setTextFill(Color.WHITE);
		
		Moisture.setTranslateX(canvasWidth1*labelColumn);
		Moisture.setTranslateY(canvasHeight1*moistureRow);
		Moisture.setTextAlignment(TextAlignment.CENTER);
		Moisture.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, conditionFont));
		Moisture.setTextFill(Color.WHITE);
		
		fullSun.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 15));
		fullSun.setTextFill(Color.WHITE);
		partShade.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 15));
		partShade.setTextFill(Color.WHITE);
		fullShade.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 15));
		fullShade.setTextFill(Color.WHITE);
		sandy.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 15));
		sandy.setTextFill(Color.WHITE);
		clay.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 15));
		clay.setTextFill(Color.WHITE);
		loam.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 15));
		loam.setTextFill(Color.WHITE);
		dry.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 15));
		dry.setTextFill(Color.WHITE);
		medium.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 15));
		medium.setTextFill(Color.WHITE);
		wet.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 15));
		wet.setTextFill(Color.WHITE);
		
		Image background = new Image(getClass().getResourceAsStream("/img/conditions.png"));
    	ImageView iv = new ImageView(background);
    	iv.setFitHeight(600);
    	iv.setFitWidth(1200);
		iv.setLayoutX(0);
		iv.setLayoutY(0);
		this.conditionsPane.getChildren().add(iv);
		
		for(RadioButton b : sunButtons) {
			this.conditionsPane.getChildren().add(b);
			b.setTranslateY(canvasHeight1*sunRow);
			b.setToggleGroup(sun);
		}
		
		for(RadioButton b : soilButtons) {
			this.conditionsPane.getChildren().add(b);
			b.setTranslateY(canvasHeight1*soilRow);
			b.setToggleGroup(soil);
		}
		
		for(RadioButton b : moistureButtons) {
			this.conditionsPane.getChildren().add(b);
			b.setTranslateY(canvasHeight1*moistureRow);
			b.setToggleGroup(moisture);
		}
		fullSun.setTranslateX(canvasWidth1*leftColumn);
		partShade.setTranslateX(canvasWidth1*middleColumn);
		fullShade.setTranslateX(canvasWidth1*rightColumn);
		dry.setTranslateX(canvasWidth1*leftColumn);
		medium.setTranslateX(canvasWidth1*middleColumn);
		wet.setTranslateX(canvasWidth1*rightColumn);
		sandy.setTranslateX(canvasWidth1*leftColumn);
		loam.setTranslateX(canvasWidth1*middleColumn);
		clay.setTranslateX(canvasWidth1*rightColumn);
		
		partShade.setSelected(true);
		medium.setSelected(true);
		loam.setSelected(true);
		
		budgetTitle.setTextAlignment(TextAlignment.CENTER);
		budgetTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, titleFont));
		budgetTitle.setTranslateX(canvasWidth1*budgetColumn);
		budgetTitle.setTranslateY(canvasHeight1*titleRow);
		
		budgetInput.setTranslateX(canvasWidth1*budgetColumn + 100);
		budgetInput.setTranslateY(canvasHeight1*soilRow);
		
		this.conditionsPane.getChildren().addAll(buttonPane, budgetTitle, conditionsTitle,
				Sun, Soil, Moisture, budgetInput);
		this.conditionsPane.setStyle("-fx-background-color:lightblue");
		this.scene = new Scene(conditionsPane, canvasWidth, canvasHeight);
		
	}
	
	

/**
 * Default Initialize Button for switching scene, you can change it, as long as there is a button pointed to the
 * public Button forwardButton line.
 */
	private Pane initializeButtonPane() { //used for initializing Button Pane used in all classes..
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color:lightgreen");
		pane.setPrefSize(canvasWidth, 50);
		this.backwardButton = initializeButton(buttonStartX, buttonStartY, buttonEndX, buttonEndY, "Back");		
		this.forwardButton = initializeButton(canvasWidth-buttonEndX, buttonStartY, buttonEndX, buttonEndY, "Next");
		this.forwardButton.setStyle("-fx-background-color: green");
		pane.getChildren().addAll(backwardButton, forwardButton);
		pane.setLayoutX(0);
		pane.setLayoutY(canvasHeight-50);
		return pane;
		
	}
	
	/**
	 * Used to Initialize the placement of each button onto the 
	 * @param XCoor x coordinate of the button
	 * @param YCoor y coordinate of the button
	 * @param Width width of the button
	 * @param Height height of the button
	 * @param text text to be displayed within the button
	 * @return Button used for moving scene forward or backward
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

	/**
	 * Get the text in the Budget TextField
	 * @return String value of budget
	 */
	public String getInputBudget() {
		return budgetInput.getText();
	}
	
	/**
	 * Get the text in the selected RadioButton in the sun ToggleGroup
	 * @return String value of selected RadioButton
	 */
	public String getSunCondition() {
		sunChoice = (RadioButton)sun.getSelectedToggle();
		return sunChoice.getText();
	}
	
	/**
	 * Get the text in the selected RadioButton in the soil ToggleGroup
	 * @return String value of selected RadioButton
	 */
	public String getSoilCondition() {
		soilChoice = (RadioButton)soil.getSelectedToggle();
		return soilChoice.getText();
	}
	
	/**
	 * Get the text in the selected RadioButton in the moisture ToggleGroup
	 * @return String value of selected RadioButton
	 */
	public String getMoistureCondition() {
		moistureChoice = (RadioButton)moisture.getSelectedToggle();
		return moistureChoice.getText();
	}
	

}