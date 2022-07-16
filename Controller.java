/**
 * @author https://github.com/Brian-S-Poon
 * The Controller class of the Model-view-controller design pattern.
 */
package SortVisualizer4;

import SortVisualizer4.Model.sortState; // Import the public enum from Model class

public class Controller {
	// Create objects for the Controller class to perform operations
	private Model theModel;
	private View theView;
	
	// Specific delays for each algorithm so they perform at reasonable speeds
	private static final long BUBBLE_SORT_DELAY = 1;
	private static final long INSERTION_SORT_DELAY = 25;
	private static final long SELECTION_SORT_DELAY = 30;
	private static final long GNOME_SORT_DELAY = 1;
	private static final long COCKTAIL_SORT_DELAY = 40;
	
	/**
	 * The Controller constructor will control both business logic and user interface.
	 * @param theModel - the business logic 
	 * @param theView - the user interface
	 * @throws InterruptedException 
	 */
	public Controller(Model theModel, View theView) throws InterruptedException {
		this.theModel = theModel;
		this.theView = theView;
	
		
		while(!theModel.isSorted(theModel.getArray())) {
			
			theView.getDisplayPanel().setIsSorted(false);

			// Pass the current unsorted state of array to the DisplayPanel class
			theView.getDisplayPanel().setModel(theModel);
		
			// Perform 1 operation of comparison swap and also update the menu panel 
			if(theModel.getState()==sortState.BUBBLE_SORT.ordinal()) {
				theModel.bubbleSort(theModel.getArray());
				theView.setAlgorithmNameLabel("Bubble Sort");
				theView.setAlgorithmDelayLabel(BUBBLE_SORT_DELAY);
				theView.setArrayAccessCountLabel(theModel.getBubbleSortArrayAccessCount());
				Thread.sleep(BUBBLE_SORT_DELAY);
			}
			else if(theModel.getState()==sortState.INSERTION_SORT.ordinal()) {
				theModel.insertionSort(theModel.getArray());
				theView.setAlgorithmNameLabel("Insertion Sort");
				theView.setAlgorithmDelayLabel(INSERTION_SORT_DELAY);
				theView.setArrayAccessCountLabel(theModel.getInsertionSortArrayAccessCount());
				Thread.sleep(INSERTION_SORT_DELAY);
			}
			else if(theModel.getState()==sortState.SELECTION_SORT.ordinal()) {
				theModel.selectionSort(theModel.getArray());
				theView.setAlgorithmNameLabel("Selection Sort");
				theView.setArrayAccessCountLabel(theModel.getSelectionSortArrayAccessCount());
				Thread.sleep(SELECTION_SORT_DELAY);
			}
			else if(theModel.getState()==sortState.GNOME_SORT.ordinal()) {
				theModel.gnomeSort(theModel.getArray(), theModel.getArray().length);
				theView.setAlgorithmNameLabel("Gnome Sort");
				theView.setArrayAccessCountLabel(theModel.getGnomeSortArrayAccessCount());
				Thread.sleep(GNOME_SORT_DELAY);
			}
			else if(theModel.getState()==sortState.COCKTAIL_SORT.ordinal()) {
				theModel.cocktailSort(theModel.getArray());
				theView.setAlgorithmNameLabel("Cocktail Sort");
				theView.setArrayAccessCountLabel(theModel.getCocktailSortArrayAccessCount());
				Thread.sleep(COCKTAIL_SORT_DELAY);
			}

			theView.getDisplayPanel().repaint(); // Repaint the Display Panel after 1 swap of sorting algorithm to visualize.
		}
	
		
		if(theModel.isSorted(theModel.getArray())) {
			
			// Set isSorted to true to paint sorted array with green rectangles
			theView.getDisplayPanel().setIsSorted(true);
			
			// Repaint after setting IsSorted to true to draw green rectangles
			theView.getDisplayPanel().repaint();
			
			Thread.sleep(2000);
	
			// Randomize array to prepare for next sorting algorithm visualizer
			theModel.randomizeArray(theModel.getArray());
			
			if(theModel.getState()==sortState.COCKTAIL_SORT.ordinal()) { // If all sorting algorithm demonstrated
				System.exit(0); 										// Close the program
			}
			else {
				theModel.setState(theModel.getState()+1);                // Increment to next sorting algorithm
			}
			
			// Reset array count for all algorithms
			theModel.setBubbleSortArrayAccessCount(0);;
			theModel.setInsertionSortArrayAccessCount(0);;
			theModel.setSelectionSortArrayAccessCount(0);;
			theModel.setGnomeSortArrayAccessCount(0);;
			theModel.setCocktailSortArrayAccessCount(0);;
		}
	}

}
