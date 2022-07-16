/**
 * @author https://github.com/Brian-S-Poon
 * The lower JPanel of the user interface JFrame. Used to display and update the algorithm visualizer.
 */
package SortVisualizer4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


import javax.swing.JPanel;

import SortVisualizer4.Model.sortState;

public class DisplayPanel extends JPanel{
	private Model theModel; // Need a Model object so Display Panel can access array elements and other methods.
	private final int PROGRAM_HEIGHT = 600;
	private int BAR_WIDTH = 5;
	private boolean isSorted = false; // To turn all rectangles green after completely sorted
	
	
	/**
	 * Sets the boolean of isSorted to turn all rectangles green after completely sorted
	 * @param bool - true or false of isSorted
	 */
	public void setIsSorted(boolean bool) {
		this.isSorted = bool; 
		
	}
	
	/**
	 * Paints the Display Panel with array rectangles
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;

		if(isSorted) {
			g2D.setColor(new Color(0,102,0));// Set green sorted color for all ascending sorted elements
			
			for(int i=0; i<theModel.getArray().length; i++) {
				Rectangle2D rect = getArrayRectangle(i);
				g2D.fill(rect);
			}
		}
		else {
			for(int i=0; i<theModel.getArray().length; i++) {
				// Sorting algorithm specific highlight visualizer
				if(theModel.getCurrentComparisonIndex() == i &&
					theModel.getState()!=sortState.COCKTAIL_SORT.ordinal()) {
					g2D.setColor(Color.red);
				}
				else if(theModel.getCurrentComparisonIndex()+1 == i &&
						theModel.getState()!=sortState.COCKTAIL_SORT.ordinal() &&
						theModel.getState()!=sortState.SELECTION_SORT.ordinal()) {
					g2D.setColor(new Color(255,46,46));
				}
				else if(theModel.getCurrentComparisonIndex()+2 == i &&
						theModel.getState()!=sortState.COCKTAIL_SORT.ordinal() &&
						theModel.getState()!=sortState.SELECTION_SORT.ordinal()) {
					g2D.setColor(new Color(255,92,92));
				}
				else if(theModel.getCurrentComparisonIndex()+3 == i &&
						theModel.getState()!=sortState.COCKTAIL_SORT.ordinal() &&
						theModel.getState()!=sortState.SELECTION_SORT.ordinal()) {
					g2D.setColor(new Color(255,138,138));
				}
				else {
					g2D.setColor(Color.white);
				}
		
				Rectangle2D rect = getArrayRectangle(i);
				g2D.fill(rect);
			}
		}	
	}
	
	/**
	 * Sends the current state of this array(unsorted/sorted) to the View; used in the
	 * Controller class to update the paint component in Display Panel.
	 * @param model - the current state of Model
	 */
	public void setModel(Model model) {
		this.theModel = model;
	}
	
	/**
	 * Returns the Rectangle2D array to draw on the Display Panel.
	 * @notice Many constants used to properly format arrays into program dimensions! Ignore them! Just for GUI formatting!
	 * @param arrayIndex - index of array 
	 * @return Rectangle2D array
	 */
	public Rectangle2D getArrayRectangle(int arrayIndex) {
		int height = theModel.getArrayIndexValue(arrayIndex) * 2;
		double xBegin = arrayIndex + (BAR_WIDTH - 1.25) * arrayIndex; // where array start from on left side
		int yBegin = PROGRAM_HEIGHT - height - 135;    // vertically try to get array to perfectly start on bottom of app
		
		Rectangle2D rect = new Rectangle2D.Double(xBegin, yBegin, 3.75, height);
		return rect;
	}

}
