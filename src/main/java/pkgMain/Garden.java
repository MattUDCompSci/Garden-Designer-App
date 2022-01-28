package pkgMain;

import java.util.ArrayList;

/**
 * The Garden Class is responsible for serializing Garden Parameters after the final Garden is submitted
 * @author Matthew Weis
 *
 */
public class Garden implements java.io.Serializable{
	
	/**
	 * The Gardens Width
	 */
	public double gardenWidth;
	/**
	 * The Gardens Height
	 */
	public double gardenHeight;
	/**
	 * The number of leps supported by the Garden
	 */
	public int lepCount;
	/**
	 * ArrayList containing special objects containing information about each plant ImageView
	 */
	public ArrayList<SavedImageView> gardenPlantPics;
	
	/**
	 * Class Constructor
	 * @param gardenWidth The width of the Garden in screen pixels
	 * @param gardenHeight The length of the Garden in Screen Pixels
	 */
	public Garden(double gardenWidth, double gardenHeight, int lepCount) {
		this.gardenWidth = gardenWidth;
		this.gardenHeight = gardenHeight;
		this.lepCount = lepCount;
		gardenPlantPics = new ArrayList<SavedImageView>();
	}
	
	/**
	 * Returns the Garden Width in pixels
	 * @return
	 */
	public double getGardenWidth() {
		return this.gardenWidth;
	}
	
	/**
	 * Returns the Garden Height in pixels
	 * @return
	 */
	public double getGardenHeight() {
		return this.gardenHeight;
	}
	
	/**
	 * Returns the ArrayList of SavedImageView objects
	 * @return
	 */
	public ArrayList<SavedImageView> getGardenPlantPics() {
		return this.gardenPlantPics;
	}
	
	/**
	 * Sets the Garden width to the passed in value
	 * @param gardenWidth the width of the Garden passed in
	 */
	public void setGardenWidth(double gardenWidth) {
		this.gardenWidth = gardenWidth;
	}
	
	/**
	 * Sets the Garden height to the passed in value
	 * @param gardenHeight the height of the Garden passed in
	 */
	public void setGardenHeight(double gardenHeight) {
		this.gardenHeight = gardenHeight;
	}
	
	/**
	 * Sets the Garden gardenPlantPics to the passed in value
	 * @param gardenPlantPics the passed in ArrayList of SavedImageViews containing Plant ImageView data
	 */
	public void setGardenPlantPics(ArrayList<SavedImageView> gardenPlantPics) {
		this.gardenPlantPics = gardenPlantPics;
	}


	/**
	 * toString() method
	 */
	public String toString()
    {
        return "[Garden: gardenWidth=" + gardenWidth + 
            " gardenHeight=" + gardenHeight +
            " gardenPlantPics=" + gardenPlantPics +
            "]";
    } 
}
