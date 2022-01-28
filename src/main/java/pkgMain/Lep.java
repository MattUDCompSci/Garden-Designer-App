package pkgMain;

import javafx.scene.control.Label;

/**
 * The Lep Object Class, containing the common name, scientific name, and picture of the Lep
 * @author mweis
 *
 */
public class Lep{
	
	/**
	 * The Common name of the lep
	 */
	public String name;
	/**
	 * The Scientific name of the lep
	 */
	public String scientificName;
	
	/**
	 * Class Construtor
	 * @param name the new leps common name
	 * @param scientificName the new leps scientific name
	 * @param url the new leps picture
	 */
	public Lep(String scientificName, String name) {
		this.name = name;
		this.scientificName = scientificName;
	}
}