/**
 * The JUnit testing of the Model (business logic) class
 */
package SortVisualizer4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModelTest {

	@Test
	void testIsSortedTrue() {
		Model theModel = new Model();
		
		while(!theModel.isSorted(theModel.getArray())) {
			theModel.insertionSort(theModel.getArray());
		}
		
		assertTrue(theModel.isSorted(theModel.getArray()));	
	}
	
	@Test
	void testIsSortedFalse() {
		Model theModel = new Model();
		assertFalse(theModel.isSorted(theModel.getArray()));
	}
	
	
	
	

}
