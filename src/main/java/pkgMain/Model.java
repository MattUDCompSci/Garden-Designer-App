package pkgMain;


import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.*; 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;  

/**
 * Model Class, keeps track of all the Entered Garden Values and Plants and Leps present for the Garden
 * @author zehezack, mattweis, mnlight, bnewlin, brypal
 */

public class Model {
	
	private int testInt = 0;
	private int testInt2 = 0;

	/**
	 * The current scene index
	 */
	public int sceneIndex = 0;

	/**
	 * A String value representing a Condition chosen for the Garden
	 */
	public String sunValue, soilValue, moistureValue;
	/**
	 * The budget of the Garden
	 */
	public int budget;
	/**
	 * The cost of the Garden
	 */
	public int cost;
	/**
	 * The lep count of the Garden
	 */
	public int lepCount;
	/**
	 * The Height of the Garden
	 */
	public int gardenHeight;
	/**
	 * The Width of the Garden
	 */
	public int gardenWidth;
	/**
	 * List of plants read in from the plantData.csv file
	 */
	public ArrayList<Plant> initialPlants;
	/**
	 * List of plants that meet the specified Garden conditions, empty initially
	 */
	public ArrayList<Plant> conditionPlants;
	/**
	 * List of Plant picture file names
	 */
	public ArrayList<String> plantPicNames;
	/**
	 * ArrayList of Plants Contained in the Garden
	 */
	public ArrayList<Plant> addedPlants;
	/**
	 * List of Leps supported by the Garden
	 */
	public ArrayList<Lep> lepidopteras;
	/**
	 * The current line being read in by the BufferedReader
	 */
	public String line;
	/**
	 * The values on a given line in the plantData.csv file
	 */
	public String[] values;
	/**
	 * The passed in double value to create a plant with a correct price when reading from the csv file
	 */
	public double plantPrice;
	/**
	 * The passed in integer value to create a plant with a correct lep count when reading from the csv file
	 */
	public int plantLepCount;
	/**
	 * The Comma split value to seperate each line value in the csv file
	 */
	final public String splitby = ",";
	/**
	 * The file location of the plant Data
	 */
	final public String plantData = "src/main/java/pkgMain/plantData.csv";
	/**
	 * The file location of the lep Data
	 */
	final public String lepData = "src/main/java/pkgMain/lepData.csv";
	
	/**
	 * Translate values of the Garden pane within the GardenScreens borderPane
	 */
	public double TranslateX, TranslateY = 0;
	

	/**
	 * The preferred Garden Length
	 */
	public double prefGardenLength;
	
	/**
	 * The preferred Garden Width
	 */
	public double prefGardenWidth;
	
	/**
	 * The Garden Panes
	 */
	public Pane pane, pane2;

	/**
	 * Budget value kept track of on the GardenScreen
	 */
    public int fbudget;
    /**
     * Lep count value kept track of on the GardenScreen 
     */
	public int fleps;
	private Pane paneOriginal;
	/**
	 * Pixel length of the longest side of the garden
	 */
	public double Longer;
	/**
	 * Pixel width of the shortest side of the garden
	 */
	public double Shorter;
	/**
	 * Ratio of the two Garden sides (length and width)
	 */
	public double Ratio;
	/**
	 * Variable used to check the value of the longest garden side in feet
	 */
	public double longerInFeet;
	/**
	 * Variable used to check the value of the shortest garden side in feet
	 */
	public double shorterInFeet;

	/**
	 * Class Constructors, initializes ArrayLists of Plants
	 * @throws IOException
	 */
	public Model() {
		initialPlants = new ArrayList<Plant>();
		conditionPlants = new ArrayList<Plant>();
		addedPlants = new ArrayList<Plant>();
		lepidopteras = new ArrayList<Lep>();
	}
	/**
	 * Sets the values of the Garden to the passed in Values
	 * @param gardenHeight the passed in value of the Garden Height
	 * @param gardenWidth the passed in value of the Garden Width
	 */	
	public void setModel (int gardenHeight, int gardenWidth) {
		this.gardenHeight = gardenHeight;
		this.gardenWidth = gardenWidth;
	}
	
	/**
	 * Increases the Scene index
	 */
	public void INCsceneIndex() {
		this.sceneIndex = this.sceneIndex + 1;
	}
	
	/**
	 * Decreases the Scene index
	 */
	public void DECsceneIndex() {
		this.sceneIndex = this.sceneIndex - 1;
	}
	
	/**
	 * Returns to the TitleScreen from Tips or ProgramPurpose Screens
	 */
	public void getBackToTitle() {
		this.sceneIndex = 0;
	}
	
	/**
	 * Changes Scene index to TipScreens
	 */

	public void getToTipScreen() {
		this.sceneIndex = 6;
	}
	
	/**
	 * Sets the scene index to 7 which corresponds to the purpose screen. 
	 */
	public void getToPurposeScreen() {
		this.sceneIndex = 7;
	}
	
	/**
	 * Sets the scene index to 1 which corresponds to the format screen. 
	 */
	public void getToFormatScreen() {
		this.sceneIndex = 1;
	}
	
	/**
	 * Initializes the List of plants from the plantData.csv file
	 * @throws IOException 
	 */
	public void initializePlants() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(plantData));
		while ((line = br.readLine()) != null /*&& testInt <= 5*/){ //(line = br.readLine()) != null
			values = line.split(splitby);
			plantPrice = (int)Integer.parseInt(values[6]);
			plantLepCount = (int)Integer.parseInt(values[7]);
			initialPlants.add(new Plant(values[0], values[1], values[2], values[5], values[4], values[3], 
					plantPrice, plantLepCount, Double.parseDouble(values[8]), new Lep(values[9], values[10]), values[11], values[12]));
			testInt = testInt + 1;
		}
	}
	
	/**
	 * Places the Plants supported by the Garden conditions in the conditionPlants list
	 */
	public void conditionPlantsSet() {
		for(Plant p : initialPlants) {
			if(p.getConditions(soilValue, sunValue, moistureValue) && !conditionPlants.contains(p) && p.spread <= shorterInFeet) {
				conditionPlants.add(p);
			}
		}
		this.plantPicNames = new ArrayList<String>();
		for(Plant p : conditionPlants) {
			plantPicNames.add(p.scientificName);
		}
	}
	
	/**
	 * Gets the index of the current scene
	 * @return the integer value of the current scene
	 */
	public int getSceneIndex() {
		return this.sceneIndex;
	}
	
	/**
	 * Sets the translated x coordinate of the ImageView in the Drag and Drop on the GardenLayoutScreen to the new coordinate
	 * @param x the new x coordinate of the ImageView
	 */
	public void setTranslateX(double x) {
		this.TranslateX = x;
	}

	/**
	 * Sets the translated y coordinate of the ImageView in the Drag and Drop on the GardenLayoutScreen to the new coordinate
	 * @param y the new y coordinate of the ImageView
	 */
	public void setTranslateY(double y) {
		this.TranslateY = y;
	}
	

	public void setScreenIndex(int index) {
		this.sceneIndex = index;
	}
	
	/**
	 * Sets the Garden budget value for the model
	 * @param inputBudget the budget String value input into the TextField on ConditionsScreen
	 */
	public void setBudget(String inputBudget) throws NumberFormatException{
		try {
			budget = Integer.parseInt(inputBudget);
		}
		catch(NumberFormatException e){
			budget = 100;
		}
		System.out.println(budget);
	}
	
	/**
	 * Sets the Garden Sun value for the model
	 * @param sunChoice the chosen value for the sun from the RadioButtons on ConditionsScreen
	 */
	public void setSun(String sunChoice) {
		this.sunValue = sunChoice;
		System.out.println(sunChoice);
	}
	
	/**
	 * Sets the Garden Soil value for the model
	 * @param soilChoice the chosen value for the soil from the RadioButtons on ConditionsScreen
	 */
	public void setSoil(String soilChoice) {
		this.soilValue = soilChoice;
		System.out.println(soilChoice);
	}
	
	/**
	 * Sets the Garden Moisture value for the model
	 * @param moistureChoice the chosen value for the moisture from the RadioButtons on ConditionsScreen
	 */
	public void setMoisture(String moistureChoice) {
		this.moistureValue = moistureChoice;
		System.out.println(moistureChoice);
	}

	/**
	 * Gets the x coordinate of the ImageView as it is moved in the Drag and Drop on GardenLayoutScreen
	 * @return the double value of the translated x coordinate
	 */
	public double getTranslateX() {
		return this.TranslateX;
	}
	
	/**
	 * Gets the y coordinate of the ImageView as it is moved in the Drag and Drop on GardenLayoutScreen
	 * @return the double value of the translated y coordinate
	 */
	public double getTranslateY() {
		return this.TranslateY;
	}
	
	/**
	 * Sets the Preferred length of the garden
	 * @param y the passed in length value
	 */
	public void setPrefLength(double y) {
		this.prefGardenLength = y;
	}
	
	/**
	 * Sets the Preferred width of the garden
	 * @param x the passed in width values
	 */
	public void setPrefWidth(double x) {
		this.prefGardenWidth = x; // Height
	}
	
	/**
	 * Sets the Garden pane for the model to the passed in pane
	 * @param pane1 the passed in garden pane
	 */
	public void setPane(Pane pane1) {
		this.pane = pane1;
		this.paneOriginal = pane1;
	}
	
	/**
	 * Clears the Garden pane when the clear button on the GardenScreen is pushed
	 */
	public void clearPane() {
		pane = paneOriginal;
	}
	
	/**
	 * Sets up the Garden pane from the FormatScreen
	 * @param pane2 the Garden pane from the Format Screen
	 */
	public void setPane2(Pane pane2) {
		this.pane2 = pane2;
	}
	
	/**
	 * Sets up the lep count and budget variables visible on the GardenScreen
	 * @param budget the new budget left of the garden
	 * @param leps the new lep count of the garden
	 */
	public void setBandL(int budget, int leps) {
		this.fbudget = budget;
		this.fleps = leps;
	}

	/**
	 * Sets up the values of the Garden Lengths and width used on screen
	 * @param longer the length of the longest garden side
	 * @param shorter the length of the shorter garden side
	 * @param ratio the ratio between the length and the width
	 */
	public void setLandS(double longer, double shorter, double ratio) {
		
		this.Longer = longer;
		this.Shorter = shorter;
		this.Ratio = ratio;
		
	}
}