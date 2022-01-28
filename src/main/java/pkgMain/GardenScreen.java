package pkgMain;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

/**
 * The Garden Screen Class
 * <p>
 * This class handles all actions that take place on the Garden screen, including the initialization of the Garden Layout pane
 * @author Zack Luan, Matthew Weis, Ben Newlin, Mayson Light, Bryan Paliska
 *
 */
public class GardenScreen {
	/**
	 * The Scene for the Garden Screen
	 */
	public static Scene scene;
	
	private double canvasWidth;
	private double canvasHeight;
	
	
	private Pane buttonPane;
	/**
	 * Size of Plant View Scale;
	 */
	public double plantViewScale;
	/**
	 * Size of the circle clip for the ListView of Plants
	 */
	public double plantViewCircleScale;
	/**
	 * The Button to advance to the next screen
	 */
	public Button forwardButton;
	/**
	 * The Button to move back to the previous screen
	 */
	public Button backwardButton; 
	
	/**
	 * Setting Button Dimensions
	 */
	
	private int buttonStartX = 0, buttonStartY = 0, buttonEndX = 200, buttonEndY = 50, padding = 10, ratio1 = 530;
	
	/**
	 * Setting pane
	 */
	public BorderPane borderPane;
	
	/**
	 * ArrayList of All Condition Plants
	 */
	public ArrayList<Plant> plants;
	/**
	 * ImageViews of each plant
	 */
	public ArrayList<ImageView> plantPictures;
	/**
	 * ListView of All Condition Plants ImageViews
	 */
	public ListView<ImageView> plantPics;
	/**
	 * Observable ListView used with ListView of plantPics
	 */
	public ObservableList<ImageView> backingList;
	
	/**
	 * GridPane used for the Garden
	 */
	GridPane GardenLayoutPane;
	/**
	 * Normal Pane used as the garden
	 */
	Pane Garden;
	
	/**
	 * Pane containing Garden Condition
	 */
	Pane Condition;
	
	/**
	 * Integer value of the Budget
	 */
	int iBudget;
	/**
	 * Integer value of the Cost
	 */
	int iCost;
	/**
	 * Double value of Side Length in feet
	 */
	double iLength;
	/**
	 * Double value of Side Width in feet
	 */
	double iWidth;
	/**
	 * String Value of the Budget
	 */
	String sBudget;
	/**
	 * String Value of the current Garden's cost
	 */
	String sCost;
	/**
	 * Initial Lep Count
	 */
	int iLep;
	/**
	 * String Value of the Lep Count
	 */
	String sLep;
	
	/**
	 * Text Used to display Budget
	 */
	Text tBudget;
	/**
	 * Text used to display Cost
	 */
	Text tCost;
	/**
	 * Text used to display Lep Count
	 */
	Text tLep;
	
	/**
	 * Text representing Sun value
	 */
	Text sun;
	/**
	 * Text representing Soil value
	 */
	Text soil;
	/**
	 * Text representing Mositure value
	 */
	Text moisture;
	/**
	 * Text Representing the Information of the selected Plant
	 */
	Text plantInfo;
	/**
	 * Text representing Garden's length
	 */
	Text length;
	/**
	 * Text representing Garden's width
	 */
	Text width;
	/**
	 * Button to Clear the Garden
	 */
	Button testClear = new Button("Clear");
	/**
	 * Test button to make sure budget changing works
	 */
	Button testBudget = new Button("Budget");
	
	
	/**
	 * Class Constructor
	 * @param canvasWidth1 width of the Garden
	 * @param canvasHeight1 height of the Garden
	 */
	public GardenScreen(double canvasWidth1, double canvasHeight1) {
		
		this.canvasWidth = canvasWidth1;
		this.canvasHeight = canvasHeight1;
		this.buttonPane = initializeButtonPane();
		
		this.borderPane = new BorderPane(null, null, null, buttonPane, null);	
		this.borderPane.setStyle("-fx-background-color:lightblue");
		this.scene = new Scene(borderPane, canvasWidth, canvasHeight);
		
	}
	
	

/**
 * Default Initialize Button for switching scene, you can change it, as long as there is a button pointed to the
 * public Button forwardButton line.
 */
	private Pane initializeButtonPane() { //used for initializing Button Pane used in all classes..
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color:lightGreen");
		pane.setPrefSize(canvasWidth, 50);

		this.backwardButton = initializeButton(0, 0, 200, 50, "Back");		
		this.forwardButton = initializeButton(canvasWidth-200, 0, 200, 50, "Next");
		this.forwardButton.setStyle("-fx-background-color: green");
		this.plantInfo = new Text();
		this.plantInfo.setTextAlignment(TextAlignment.CENTER);
		this.plantInfo.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		this.plantInfo.setFill(Color.WHITE);
		this.plantInfo.setTranslateX(canvasWidth/2 - 100);
		this.plantInfo.setTranslateY(pane.getLayoutY()+25);
		pane.getChildren().addAll(backwardButton, forwardButton, plantInfo);
		pane.setLayoutX(0);
		pane.setLayoutY(canvasHeight-buttonEndY);
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
		button.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 14));
		button.setLayoutX(XCoor);
		button.setLayoutY(YCoor);
		button.setClip(new Circle(Width/2.0, Height/2.0, Width/3.0));
		button.setPrefSize(Width, Height);
		button.setStyle("-fx-background-color: red");
		return button;
	}
	
	/**
	 * Initializes the Garden Screen
	 * @param plants the ArrayList of Plants meeting the Sun, Soil, and Moisture Conditions
	 * @param plantPictureNames the ArrayList of Strings of the Plant names meeting garden conditions
	 * @param budget the budget for the Garden
	 * @param sunValue the Set Sun Value
	 * @param soilValue the Set Soil Value
	 * @param moistureValue the Set Moisture Value
	 * @param garden the Garden Pane initialized from the Format Screen
	 * @param Width the Width of the Garden
	 * @param Height the Height of the Garden
	 * @param lepCount the initial lep count when entering the garden screen
	 * @param cost the total cost of the garden occured so far
	 * @param longerInFeet the Length of the Garden in feet
	 * @param shorterInFeet the Width of the Garden in feet
	 */
	public void Initial(ArrayList<Plant> plants, ArrayList<String> plantPictureNames,
			int budget, String sunValue, String soilValue, String moistureValue, 
			Pane garden, double Width, double Height, int lepCount, int cost, double longerInFeet, double shorterInFeet) {
		/*
		 * PlantList
		 */
		backingList = null;
		this.plantPics = null;
		this.plantViewScale = canvasWidth / 12.0;
		this.plantViewCircleScale = plantViewScale / 2.0;;
		this.plants = plants;
		this.plantPictures = new ArrayList<ImageView>();
		for(String s : plantPictureNames) {
			ImageView iv = new ImageView(new Image(getClass().getResourceAsStream("/img/plants/" + s + ".png")));
			iv.setFitHeight(plantViewScale);
			iv.setFitWidth(plantViewScale);
			iv.setClip(new Circle(plantViewCircleScale, plantViewCircleScale, plantViewCircleScale));
			Tooltip t = new Tooltip(plants.get(plantPictureNames.indexOf(s)).toString());
			Tooltip.install(iv, t);
			this.plantPictures.add(iv);
		}
		backingList = FXCollections.observableArrayList(this.plantPictures);
		for(ImageView iv : backingList) {
			Tooltip t = new Tooltip("Hello there");
			Tooltip.install(iv, t);
		}
		this.plantPics = new ListView<>(backingList);
		this.plantPics.setTooltip(new Tooltip(""));
		plantPics.setLayoutX(0);
		plantPics.setLayoutY(0);
		plantPics.setPrefSize(200, 550);
		this.borderPane.setLeft(this.plantPics);
		
		/*
		 * Garden
		 */
		
		this.Garden = garden;
		
		System.out.println("This is the background" + Garden.getBackground());
		
		
		if (Garden.getBackground() == null) {
			if(soilValue.equals("Dry")) {
				this.Garden.setBackground(new Background(new BackgroundFill(Color.web("sandybrown"), CornerRadii.EMPTY, Insets.EMPTY)));
			}
			else if(soilValue.equals("Wet")) {
				this.Garden.setBackground(new Background(new BackgroundFill(Color.web("brown"), CornerRadii.EMPTY, Insets.EMPTY)));
			}
			else {
				this.Garden.setBackground(new Background(new BackgroundFill(Color.web("saddlebrown"), CornerRadii.EMPTY, Insets.EMPTY)));
			}
		}
		this.GardenLayoutPane = new GridPane();
		this.GardenLayoutPane.setPrefSize(800, 550);
		this.GardenLayoutPane.setLayoutX(200);
		this.GardenLayoutPane.setLayoutY(0);
		this.GardenLayoutPane.setStyle("-fx-border-color: black");
		
		Garden.setLayoutX(0);
		Garden.setLayoutY(0);
		Garden.setPrefSize(Width, Height);
		
		GardenLayoutPane.setPadding(new Insets(10, 10, 10, 10));
		GardenLayoutPane.setAlignment(Pos.CENTER);	
		GardenLayoutPane.getChildren().add(Garden);
		
		this.borderPane.setCenter(GardenLayoutPane);
		
		/*
		 * Condition
		 */
		
		Condition = new Pane();
		Condition.setPrefSize(200, 550);
		Condition.setLayoutX(1000);
		Condition.setLayoutY(0);
		Condition.setStyle("-fx-border-color: black");
		
		iLep = lepCount;
		iBudget = budget;
		iCost = cost;
		iLength = longerInFeet;
		iWidth = shorterInFeet;
		
		sLep = String.valueOf(iLep);
		sCost = String.valueOf(iCost);
		sBudget = String.valueOf(iBudget);
		
		tBudget = new Text("Budget Left: " + sBudget);
		tCost = new Text("Cost: " + sCost);
		tLep = new Text("Leps Support: " + sLep);
		length = new Text("Length: " + iLength + " ft"); 
		width = new Text("Width: " + iWidth + " ft");
		
		sun = new Text("Sun type: " + sunValue);
		soil = new Text("Soil type: " + soilValue);
		moisture = new Text("Moisture type: " + moistureValue);
		
		tBudget.setStyle("-fx-font: normal bold 12px ' serif' ");
		tCost.setStyle("-fx-font: normal bold 12px ' serif' ");
		tLep.setStyle("-fx-font: normal bold 12px ' serif' ");
		sun.setStyle("-fx-font: normal bold 12px ' serif' ");
		soil.setStyle("-fx-font: normal bold 12px ' serif' ");
		moisture.setStyle("-fx-font: normal bold 12px ' serif' ");
		length.setStyle("-fx-font: normal bold 12px ' serif' ");
		width.setStyle("-fx-font: normal bold 12px ' serif' ");
		testClear.setStyle("-fx-background-color: red");
		testClear.setTextFill(Color.WHITE);
		
		VBox vb1 = new VBox();

		
		vb1.getChildren().addAll(sun, soil, moisture, tBudget, tCost, tLep, length, width, testClear);
		vb1.setAlignment(Pos.CENTER_LEFT);
		vb1.setPadding(new Insets(10, 10, 10, 10));
		vb1.setSpacing(40);
		
		vb1.setLayoutX(0);
		vb1.setLayoutY(0);
		Condition.setBackground(new Background(new BackgroundFill(Color.web("lightgreen"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		Condition.getChildren().add(vb1);
		
		this.borderPane.setRight(Condition);
		
	}
	
	/**
	 * Changes the Parameters of Budget and LepCount on Garden Screen when a plant is added
	 * @param ChangeofBudget the budget of the added plant
	 * @param ChangeOfLeps the number of leps supported by the new plant
	 * @param clear boolean that makes sure clear button has or has not been pressed (true if not pressed)
	 * @param originalB the original budget passed into the Garden Screen
	 */
	public void ChangeBandL(int ChangeofBudget, int ChangeOfLeps, boolean clear, int originalB) {
		
		Condition = new Pane();
		Condition.setPrefSize(200, 550);
		Condition.setLayoutX(1000);
		Condition.setLayoutY(0);
		Condition.setStyle("-fx-border-color: black");
		
		if (clear) {
			iLep = iLep + ChangeOfLeps;
			iBudget = iBudget - ChangeofBudget;
		} else {
			iLep = 0;
			iBudget = originalB;
		}
		iCost += ChangeofBudget;
		
		sLep = String.valueOf(iLep);
		sBudget = String.valueOf(iBudget);
		sCost = String.valueOf(iCost);
		
		tBudget = new Text("Budget Left: " + sBudget);
		tLep = new Text("Leps Support: " + sLep);
		tCost = new Text("Cost: " + sCost);
		length = new Text("Length: " + iLength + " ft"); 
		width = new Text("Width: " + iWidth + " ft");
		
		tBudget.setStyle("-fx-font: normal bold 12px ' serif' ");
		tCost.setStyle("-fx-font: normal bold 12px ' serif' ");
		tLep.setStyle("-fx-font: normal bold 12px ' serif' ");
		length.setStyle("-fx-font: normal bold 12px ' serif' ");
		width.setStyle("-fx-font: normal bold 12px ' serif' ");
		testClear.setStyle("-fx-background-color: red");
		testClear.setTextFill(Color.WHITE);
		
		VBox vb1 = new VBox();

		
		vb1.getChildren().addAll(sun, soil, moisture, tBudget, tCost, tLep, length, width, testClear);
		vb1.setAlignment(Pos.CENTER_LEFT);
		vb1.setPadding(new Insets(10, 10, 10, 10));
		vb1.setSpacing(40);
		
		vb1.setLayoutX(0);
		vb1.setLayoutY(0);
		
		Condition.getChildren().add(vb1);
		Condition.setBackground(new Background(new BackgroundFill(Color.web("lightgreen"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		this.borderPane.setRight(Condition);
	}
	
	/**
	 * Clears the garden and resets it's parameters to it's original values
	 * @param garden the initial garden pane passed into Garden Screen
	 * @param W the width of the Garden
	 * @param H the length of the Garden
	 * @param originalBudget the original budget passed in to the Garden Screen
	 */
	public void ChangeGarden(Pane garden, double W, double H, int originalBudget) {
		
		Garden.getChildren().removeIf(node -> node instanceof ImageView);
		
		ChangeBandL(0, 0, false, originalBudget);

	}

}

