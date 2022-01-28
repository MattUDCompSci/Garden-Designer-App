package pkgMain;

/**
 * This class contains information Regarding the X, Y, and Image Location data of each Garden plant
 * @author mweis
 *
 */
public class SavedImageView implements java.io.Serializable{

	/**
	 * The x location of the ImageView in the Garden pane
	 */
	public double xloc;
	/**
	 * The y location of the ImageView in the Garden pane
	 */
	public double yloc;
	/**
	 * The Width of the ImageView in pixels
	 */
	public double width;
	/**
	 * The Height of the ImageView in pixels
	 */
	public double height;
	/**
	 * The name of the png file in which the plant picture is stored under src/main/resources/img/plants
	 */
	public String imageLoc;
	
	/**
	 * Class Constructor
	 * @param xloc passed in xloc value
	 * @param yloc passed in yloc value
	 * @param width passed in width value
	 * @param height passed in height value
	 * @param imageLoc passed in imageLoc value
	 */
	public SavedImageView(double xloc, double yloc, double width, double height, String imageLoc) {
		this.xloc = xloc;
		this.yloc = yloc;
		this.width = width;
		this.height = height;
		this.imageLoc = imageLoc;
	}
}
