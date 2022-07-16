/**
 * @author https://github.com/Brian-S-Poon
 * The user interface of the Model-view-controller design pattern.
 */
package SortVisualizer4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame{
	// We need a separate JPanel class called Display Panel with paint component
	// method to update the GUI
	private DisplayPanel displayPanel = new DisplayPanel();
	private JLabel algorithmNameLabel = new JLabel();
	private JLabel arrayAccessCountLabel = new JLabel();
	private JLabel algorithmDelayLabel = new JLabel();
	
	private final int PROGRAM_WIDTH = 1000;
	private final int PROGRAM_HEIGHT = 600;
	
	private final int MENU_HEIGHT = 100;
	private final int DISPLAY_HEIGHT = 500;
	
	public View() {
		// This Menu JPanel is the top section for labels and buttons.
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(Color.black);
		menuPanel.setPreferredSize(new Dimension(PROGRAM_WIDTH, MENU_HEIGHT));
		menuPanel.setLayout(new GridLayout(1,3));
		
		// This DisplayPanel JPanel is the bottom section for the sorting visualizer.
		displayPanel.setBackground(Color.black);
		displayPanel.setPreferredSize(new Dimension(PROGRAM_WIDTH, DISPLAY_HEIGHT));
		
		// Set font size/color and orientation settings for algorithm name label
		algorithmNameLabel.setHorizontalAlignment(JLabel.CENTER);
		algorithmNameLabel.setVerticalAlignment(JLabel.CENTER);
		algorithmNameLabel.setForeground(Color.white);
		algorithmNameLabel.setFont(new Font("Corsola", Font.BOLD, 30));
		
		// Set font size/color and orientation settings for array access count label
		arrayAccessCountLabel.setHorizontalAlignment(JLabel.CENTER);
		arrayAccessCountLabel.setVerticalAlignment(JLabel.CENTER);
		arrayAccessCountLabel.setForeground(Color.white);
		arrayAccessCountLabel.setFont(new Font("Corsola", Font.BOLD, 30));
		
		// Set font size/color and orientation settings for algorithm delay label
		algorithmDelayLabel.setHorizontalAlignment(JLabel.CENTER);
		algorithmDelayLabel.setVerticalAlignment(JLabel.CENTER);
		algorithmDelayLabel.setForeground(Color.white);
		algorithmDelayLabel.setFont(new Font("Corsola", Font.BOLD, 30));

		
		// Set desktop program frame settings
		ImageIcon logo = new ImageIcon("logo.png");
		this.setLayout(new BorderLayout());
		this.setTitle("Sorting Algorithm Visualizer"); // set title of program
		this.setIconImage(logo.getImage()); // set desktop icon
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(PROGRAM_WIDTH, PROGRAM_HEIGHT));
		this.setResizable(false); // don't allow user to resize to keep array width consistent
		this.getContentPane().setBackground(Color.green);
		
		// Add the description JLabels to the Menu JPanel
		menuPanel.add(algorithmNameLabel);
		menuPanel.add(arrayAccessCountLabel);
		menuPanel.add(algorithmDelayLabel);
		
		// Add JPanels to JFrame AFTER setting JFrame Layout Manager
		this.add(menuPanel, BorderLayout.NORTH);
		this.add(displayPanel, BorderLayout.CENTER);
		
		this.pack();
		this.setLocationRelativeTo(null); // open window in center of screen
		this.setVisible(true); // frame.validate() is also internally called in this function, so don't need to call validate()
	}
	
	
	/**
	 * This method is used in the Controller class to call repaint().
	 * @return - the current display panel object
	 */
	public DisplayPanel getDisplayPanel() {
		return this.displayPanel;
	}
	
	/**
	 * Sets the name of the algorithm name label
	 * @param algorithmName - the name of the algorithm
	 */
	public void setAlgorithmNameLabel(String algorithmName) {
		algorithmNameLabel.setText(algorithmName);
	}
	
	/**
	 * Sets the count of the array access count label
	 * @param arrayAccessCount - the count of array accesses
	 */
	public void setArrayAccessCountLabel(int arrayAccessCount) {
		arrayAccessCountLabel.setText(String.valueOf(arrayAccessCount) + " array accesses");
	}
	
	/**
	 * Sets the delay of the algorithm delay label
	 * @param algorithmDelay - the delay between each algorithm swap operation
	 */
	public void setAlgorithmDelayLabel(long algorithmDelay) {
		algorithmDelayLabel.setText(String.valueOf(algorithmDelay) + ".0 ms delay");
	}

}
