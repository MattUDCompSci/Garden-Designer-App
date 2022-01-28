package pkgMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
* Creates a Plant object to be used in the Garden Designer Program 
* <p>
* This class creates Plant objects based on read-in values from the plantData.csv
* file.
* @author mweis
*/

public class Plant{
	
	/**
	 * The Scientific name of the Plant
	 */
	public String scientificName;
	/**
	 * The common name of the Plant
	 */
	public String commonName;
	/**
	 * A List containing all soil conditions the Plant can grow in
	 */
	public List<String> soil;
	/**
	 * A List containing all sun conditions the Plant can grow in
	 */
	public List<String> sun;
	/**
	 * A List containing all moisture conditions the Plant can grow in
	 */
	public List<String> moisture;
	/**
	 * The Plants price ($20 for woody, $6 for herbaceous)
	 */
	public double price;	
	/**
	 * The number of lep's supported by the plant
	 */
	public int lepCount;
	/**
	 * Spread of the Plant
	 */
	public double spread;
	/**
	 * String used to seperate supported conditions passed in
	 */
	public String splitby = "-";
	/**
	 * ArrayList of 1-2 featured leps supported
	 */
	public ArrayList<Lep> featuredLeps;
	
	/**
	 * Class constructor that specifies what value to set each variable of the Plant
	 * @param scientificName the passed in scientific name
	 * @param commonName the passed in common name
	 * @param url the String containing a url to the image used for the ImageView plantPic 
	 * @param soilConditions the String containing soil conditions split by a '-'
	 * @param sunConditions the String containing sun conditions split by a '-'
	 * @param moistureConditions the String containing moisture conditions split by a '-'
	 * @param price the passed in price
	 * @param lepCount the passed in lep count
	 */
	
	public Plant(String scientificName, String commonName, String url, String soilConditions,
			String sunConditions, String moistureConditions, double price, int lepCount, double spread, Lep lep1, String lep2ScientificName, String lep2Name) {
		this.scientificName = scientificName;
		this.commonName = commonName;
		this.soil = Arrays.asList(soilConditions.split(splitby));
		this.sun =  Arrays.asList(sunConditions.split(splitby));
		this.moisture =  Arrays.asList(moistureConditions.split(splitby));
		this.price = price;
		this.lepCount = lepCount;
		this.spread = spread;
		this.featuredLeps = new ArrayList<Lep>();
		featuredLeps.add(lep1);
		if(!lep2ScientificName.equals("null")){
		    Lep lep2 = new Lep(lep2ScientificName, lep2Name);
		    featuredLeps.add(lep2);
		}
	}
	
	
	
	/**
	 * Determines whether a plant is able to be placed in a Garden with the passed in conditions
	 * @param soilString Garden's soil
	 * @param sunString Garden's sun
	 * @param moistureString Gardens moisture
	 * @return boolean whether plant contains the passed in Garden conditions
	 */
	public boolean getConditions(String soilString, String sunString, String moistureString) {
		if(!this.soil.contains(soilString) || !this.sun.contains(sunString) || !this.moisture.contains(moistureString)) {
			return false;
		}
		return true;
	}
	
	/**
	 * OverRidden toString method, returns Common name and Scientific name of plant in string form
	 */
	@Override
	public String toString() {
		return "Common Name: " + commonName + "\nScientific Name: " + scientificName;
	}
	
}