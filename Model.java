/**
 * @author https://github.com/Brian-S-Poon
 * The business logic of the Model-view-controller design pattern.
 */
package SortVisualizer4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model{
	// Array is specifically of size 208 so the rectangle widths would fit perfectly
	// into the 1000x600 Program width. 
	private int arr[] = new int[208]; 
	
	// Used to color the current array rectangle red in the DisplayPanel class
	private int currentComparisonIndex = 0; 

	// Variable to keep track of current index for breaking after 1 swap. Not part of algorithm. Special modification to track and display sorting algorithm.
	private int selectionSortIndex = 0; 
	
	// The different sorting algorithms to visualize
	public enum sortState {
		BUBBLE_SORT, INSERTION_SORT, SELECTION_SORT, GNOME_SORT, COCKTAIL_SORT
	}
	
	// Variable to keep track of current sort state from sortState public enum. Used in Controller class.
	private int state = 0;

	// Count to keep track of array accesses, used to display on Menu Panel in Controller class
	private int bubbleSortArrayAccessCount = 0;
	private int insertionSortArrayAccessCount = 0;
	private int selectionSortArrayAccessCount = 0;
	private int gnomeSortArrayAccessCount = 0;
	private int cocktailSortArrayAccessCount = 0;
	

	/**
	 * Initialize a sorted array from 1 to n. Instantiate an ArrayList and copy elements
	 * from sorted array to ArrayList. Shuffle the sorted ArrayList using Collections.
	 * Copy the randomized ArrayList to original array. Now contains unsorted 1 to n.
	 */
	public Model() {
		for(int i=0; i<arr.length; i++) {       // Initialize sorted array from 1 to n
			arr[i] = i+1;
		}
	
		List<Integer> list = new ArrayList<>(); // Create temporary list
		
		for(int i=0; i<arr.length; i++) {       // Copy sorted array to list
			list.add(arr[i]);
		}
		
		Collections.shuffle(list);              // Scramble list
		
		for(int i=0; i<list.size(); i++) {      // Copy unsorted list back to original array
			arr[i] = list.get(i);
		}
		// Now we have a random array of size n with unique elements	
	}
	
	
	/**
	 * Return the state of the sortState. 
	 * @return the state of the sortState
	 */
	public int getState() {
		return this.state;
	}
	
	/**
	 * Sets the state of the sortState.
	 * @param state - the state of the sortState
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	/**
	 * Prints the inputed array into the console.
	 * @param arr - input array
	 */
	public void printArray(int arr[]) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * Randomizes the input array.
	 * @param arr - input array
	 */
	public void randomizeArray(int arr[]) {
		for(int i=0; i<arr.length; i++) {       // Initialize sorted array from 1 to n
			arr[i] = i+1;
		}
	
		List<Integer> list = new ArrayList<>(); // Create temporary list
		
		for(int i=0; i<arr.length; i++) {       // Copy sorted array to list
			list.add(arr[i]);
		}
		
		Collections.shuffle(list);              // Scramble list
		
		for(int i=0; i<list.size(); i++) {      // Copy unsorted list back to original array
			arr[i] = list.get(i);
		}
	}

	/**
	 * Checks whether or not this array is sorted in ascending order.
	 * @param arr - input array 
	 * @return true if the array is sorted in ascending order; false otherwise
	 */
	public boolean isSorted(int arr[]) {
		for(int i=0; i<arr.length-1; i++) {
			if(arr[i] > arr[i+1]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns this array so the Controller class can access its elements.
	 * @return this.arr
	 */
	public int[] getArray() {
		return this.arr;
	}
	
	/**
	 * Returns the array index value so the Display Panel class can paint and update
	 * its component.
	 * @param index - the position in this array
	 * @return the element in this array
	 */
	public int getArrayIndexValue(int index) {
		return this.arr[index];
	}

	/**
	 * Sets the passed parameter index as the current comparison index.
	 * @param currentComparisonIndex - current comparison index
	 */
	public void setCurrentComparisonIndex(int currentComparisonIndex) {
		this.currentComparisonIndex = currentComparisonIndex;
	}
	
	/**
	 * Returns the current comparison index.
	 * @return the current comparison index
	 */
	public int getCurrentComparisonIndex() {
		return this.currentComparisonIndex;
	}
	
	
	
	/**
	 * Visualizer modified bubble sort algorithm to break operation after 1 successful
	 * comparison sort, used to display array swaps on visualizer Display Panel class
	 * @param arr - input array
	 */
	public void bubbleSort(int arr[]) {
		// Boolean variable to break out of outer for-loop after 1 successful swap operation. Used to display 1 successful iteration in Display Panel
		boolean swapped = false; 
		
		int n = arr.length; 

		for(int i=0; i<n-1; i++) {
			for(int j=0; j<n-i-1; j++) {
				if(arr[j] > arr[j+1]) {
					bubbleSortArrayAccessCount++;
					// Set the current comparison index for visualizer Display Panel class.
					// Used to show current successful swap comparison.
					currentComparisonIndex = j; 
				
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					swapped = true; // This is used to break out of outer for-loop after 1 swap
					break; // Break after 1 swap to visualizer algorithm on Display Panel
				}
			}
			if(swapped) { // Break out of outer for-loop after a successful swap to visualize array on Display Panel
				break;
			}	
		}
	}
	

	/**
	 * Visualizer modified insertion sort to display after 1 swap comparison to 
	 * Display Panel class
	 * @param arr - input array
	 */
	public void insertionSort(int arr[]) {
		int n = arr.length;
		for(int i=1; i<n; ++i) {
			int value = arr[i]; 
			int j = i - 1;    	
			while(j >= 0 && arr[j] > value) {
				insertionSortArrayAccessCount++;
				currentComparisonIndex = j; // To keep track of current array swap, to visualize on Display Panel class
				arr[j + 1] = arr[j]; 
				j = j - 1; 			  
				break; // Used to break after 1 swap comparison to visualize for Display Panel class
			}
			arr[j + 1] = value;		  
		}
	}
	
	
	/**
	 * Visualizer modified selection sort to display on visualizer after 1 successful 
	 * swap iteration
	 * @param arr - input array
	 */
	public void selectionSort(int arr[]) {
		int n = arr.length;
        for (int i = selectionSortIndex; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
            	selectionSortArrayAccessCount++;
            	
                if (arr[j] < arr[min_idx]) {
                	min_idx = j;
                }       
        	}
            
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
            
            
            currentComparisonIndex = min_idx;  // Variable to display current array swap comparison on Display Panel
            selectionSortIndex++; // Variable to keep track of current index for breaking after 1 swap. Not part of algorithm. Special modification to track and display sorting algorithm.
            break; // Break after 1 swap, used to repaint Display Panel after 1 successful swap
        }
	}
	
	
	/**
	 * Visualizer modified gnome sort to display 1 successful swap comparison to Display
	 * Panel class
	 * @param arr - the input array
	 * @param n - length of the array
	 */
	public void gnomeSort(int arr[], int n) {
		int index = 0;
        while (index < n) {
            if (index == 0)
                index++;
            if (arr[index] >= arr[index - 1])
                index++;
            else {
            	gnomeSortArrayAccessCount++;
            	currentComparisonIndex = index; // Used to  visualize current array swap on Display Panel
                int temp = 0;
                temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index--;
                break; // Break after 1 swap comparison, used to visualize on Display Panel
            }
        }
        return;
	}
	
	
	/**
	 * Visualizer modified cocktail sort to skip(break/continue) and display 1 successful 
	 * swap iteration to visualizer Display Panel class
	 * @param a - input array
	 */
	public void cocktailSort(int a[])
    {
        boolean swapped = true;
        int start = 0;
        int end = a.length;
        while (swapped == true)
        {
            swapped = false;
            for (int i = start; i < end - 1; ++i)
            {
            	 
                if (a[i] > a[i + 1]) {
                    if(swapped) { // Do not swap if already swapped once. Continue loop to display 1 successful swap iteration to Display Panel class.
                    	continue;
                    }
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                    cocktailSortArrayAccessCount++;
                }
            }
            if (swapped == false)
                break;
            swapped = false;
            end = end - 1;
            for (int i = end - 1; i >= start; i--)
            {
            	
                if (a[i] > a[i + 1])
                {
                    if(swapped) {  // Do not swap if already swapped once. Continue loop to display 1 successful swap iteration to Display Panel class.
                    	continue;
                    }
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                    cocktailSortArrayAccessCount++;
                }
            }
            start = start + 1;       
        }
    }
	

	
	/**
	 * Returns the count of bubble sort array accesses.
	 * @return the count of bubble sort array accesses
	 */
	public int getBubbleSortArrayAccessCount() {
		return this.bubbleSortArrayAccessCount;
	}
	
	/**
	 * Returns the count of insertion sort array accesses.
	 * @return the count of insertion sort array accesses
	 */
	public int getInsertionSortArrayAccessCount() {
		return this.insertionSortArrayAccessCount;
	}
	
	/**
	 * Returns the count of selection sort array accesses.
	 * @return the count of selection sort array accesses
	 */
	public int getSelectionSortArrayAccessCount() {
		return this.selectionSortArrayAccessCount;
	}
	
	/**
	 * Returns the count of gnome sort array accesses.
	 * @return the count of gnome sort array accesses
	 */
	public int getGnomeSortArrayAccessCount() {
		return this.gnomeSortArrayAccessCount;
	}
	
	/**
	 * Returns the count of cocktail sort array accesses.
	 * @return the count of cocktail sort array accesses
	 */
	public int getCocktailSortArrayAccessCount() {
		return this.cocktailSortArrayAccessCount;
	}
	
	
	/**
	 * Sets the count of bubble sort array accesses.
	 * @param bubbleSortArrayAccessCount - the count of bubble sort array accesses
	 */
	public void setBubbleSortArrayAccessCount(int bubbleSortArrayAccessCount) {
		this.bubbleSortArrayAccessCount = bubbleSortArrayAccessCount;
	}

	/**
	 * Sets the count of insertion sort array accesses.
	 * @param insertionSortArrayAccessCount - the count of insertion sort array accesses
	 */
	public void setInsertionSortArrayAccessCount(int insertionSortArrayAccessCount) {
		this.insertionSortArrayAccessCount = insertionSortArrayAccessCount;
	}

	/**
	 * Sets the count of selection sort array accesses.
	 * @param selectionSortArrayAccessCount - the count of selection sort array accesses
	 */
	public void setSelectionSortArrayAccessCount(int selectionSortArrayAccessCount) {
		this.selectionSortArrayAccessCount = selectionSortArrayAccessCount;
	}

	/**
	 * Sets the count of gnome sort array accesses.
	 * @param gnomeSortArrayAccessCount - the count of gnome sort array accesses
	 */
	public void setGnomeSortArrayAccessCount(int gnomeSortArrayAccessCount) {
		this.gnomeSortArrayAccessCount = gnomeSortArrayAccessCount;
	}

	/**
	 * Sets the count of cocktail sort array accesses.
	 * @param cocktailSortArrayAccessCount - the count of cocktail sort array accesses
	 */
	public void setCocktailSortArrayAccessCount(int cocktailSortArrayAccessCount) {
		this.cocktailSortArrayAccessCount = cocktailSortArrayAccessCount;
	}
	
}
