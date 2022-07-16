/**
 * @author https://github.com/Brian-S-Poon
 * The Main class uses the Model-view-controller design pattern by creating separate instances
 * of the business logic and user interface and then passes their objects to a Controller
 * class.
 */
package SortVisualizer4;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Model model = new Model();
		View view = new View();
		
		while(true) {
			Controller controller = new Controller(model, view);
		}
	}

}
