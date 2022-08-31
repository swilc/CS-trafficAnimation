import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CS 121 Project 0: Traffic Animation
 *
 * Animates a [put your description here]
 *
 * @author BSU CS 121 Instructors
 * @author [put your name here]
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel
{
	// This is where you declare constants and variables that need to keep their
	// values between calls	to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events.
	 * Note: 100ms is 10 frames per second - you should not need
	 * a faster refresh rate than this
	 */
	private final int DELAY = 1; //milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = 0;

	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 2;

	private final Color BACKGROUND_COLOR = new Color(00, 106, 54);

	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		// Get the current width and height of the window.
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height

		// Fill the graphics page with the background color.
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);

		// Calculate the new xOffset position of the moving object.
		xOffset  = (xOffset + stepSize) % width;

		// Set up dimensions for the road
		int roadW = width;
		int roadH = height/3; 
		int roadX = 0;
		int roadY = height/3;

		// Draw crossroad
		g.setColor(new Color(30, 30, 30));
		g.fillRect(width/2-roadW/10, 0, roadW/5, roadH*3);
		
		// Draw road
		g.setColor(Color.black);
		g.fillRect(roadX, roadY, roadW, roadH);
		
		// Set up base dimensions for car
		int carW = height/6;
		int carH = height/10; 
		int carX = xOffset;
		int carY = height/2;

		// Set base color
		g.setColor(new Color(00, 71, 154));
		// Draw base oval (body of car)
		g.fillOval(carX, carY, carW, carH);
		// Draw smaller oval (hood of car)
		g.fillOval(carX+carW/8, carY+carH/4, carW, carH/2);
		// Draw lower part of car
		g.fillRect(carX+carW/8, carY+carH/2, carW, carH/2);
		
		// Set color for windows
		g.setColor(Color.black);
		// Draw one large oval as a window
		g.fillOval(carX+carH/6, carY+carH/15, carW-carW/6, carH-carH/6);
		
		// Go back to base color
		g.setColor(new Color(00, 71, 154));
		// Draw middle post to cover window (make into 2 windows)
		g.fillRect(carX+carW/2-carW/20, carY+carH/15, carW/10, carH/2);
		// Draw rectangle on lower part to cover the rest of the window
		g.fillRect(carX, carY+carH/2, carW, carH/2);
		
		// Set color for wheels
		g.setColor(new Color(49, 49, 49));
		// Left and right wheel respectively
		g.fillOval(carX, carY+height/15, carW/4, carH/2);
		g.fillOval(carX+carW-carW/4, carY+height/15, carW/4, carH/2);
		
		/**
		g.setColor(Color.orange);
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(Math.toRadians(69));
		g2d.fillRect(carX, carY, carW, carH);**/
		
		//Set color for the random guy crossing the street
		
		// TODO: Use width, height, and xOffset to draw your scalable objects
		// at their new positions on the screen




		// Put your code above this line. This makes the drawing smoother.
		Toolkit.getDefaultToolkit().sync();
	}


	//==============================================================
	// You don't need to modify anything beyond this point.
	//==============================================================


	/**
	 * Starting point for this program. Your code will not go in the
	 * main method for this program. It will go in the paintComponent
	 * method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins. This method also
	 * sets up a Timer that will call paint() with frequency specified by
	 * the DELAY constant.
	 */
	public TrafficAnimation()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY this method!
	 */
	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires.
	 * DO NOT MODIFY this class!
	 */
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}