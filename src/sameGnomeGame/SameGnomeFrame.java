package sameGnomeGame;
/**
 * Same-Gnome game :
 * The game board consists of a 15 by 10 array of squares. Each square can
 * contain a ball of one of three colors. The board is initialized by placing
 * a ball in each square, choosing its color at random. When the player moves
 * the mouse cursor over a ball, that ball and all connected balls of
 * the same color are highlighted. ( Two balls are connected if they share a
 * side, or share a side with a another ball connected to either. In other
 * words, if two balls are connected, there is a path from one to the other
 * that remains on the same color). Isolated balls of any color are not
 * activated by the mouse nor deleted.
 * 
 * If the player clicks the mouse, the connected set of balls in removed from
 * the board and the board is compacted. The board is compacted by first
 * dropping balls within columns to fill in empty spaces (as if under the
 * influence of gravity). The board is then compacted by sliding complete
 * columns to the left to remove and empty columns. Each time the player
 * clicks the mouse, their score it incremented by the {\it square} of the
 * number of balls deleted by that click.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.Random;
import java.util.ArrayList;
public class SameGnomeFrame extends JFrame{
	/**
	 * display screen width
	 */
	public static final int DEFUALT_WIDTH = 1500;
	/**
	 * display screen height
	 */
	public static final int DEFUALT_HEIGHT = 1000;
	/**
	 * panel to draw on
	 */
	public static GridComponent grid;
	/**
	 * ball to be drown on gridComponent
	 */
	private Ball[][] gridBall= new Ball[15][10];
	
	
	/**
	 * sets the size of the Frame
	 * intialize Ball[][] 
	 * creates a new GridComponent
	 * adds grid to Frame
	 */
	public SameGnomeFrame() {
		setSize(DEFUALT_WIDTH,DEFUALT_WIDTH);
		intializeGridBall();
		grid = new GridComponent(gridBall);
		add(grid);
		
	}
	/**
	 * divide Defualt width and height to 15*10 squares and 
	 * initialize each Ball in ball[][] with the center of each square
	 */
	public void intializeGridBall() {
		int incWidth = DEFUALT_WIDTH /15;	
		
		for(int i = 0;i<gridBall.length;i++) {			
			for(int j = 0;j < gridBall[i].length;j++) {
				Point2D point= new Point2D.Float();
				point.setLocation((incWidth*i)+(incWidth/2),(incWidth*j)+(incWidth/2));
				Color c = getRandomColor();
				gridBall[i][j] = new Ball(c,point);
			}
		}
	}
	/**
	 * gets randomw color between 3 colors
	 * @return random color
	 */
	public Color getRandomColor() {
		Random rand = new Random();
		int i = rand.nextInt(3);
		if(i==0){
			return Color.blue.darker();
		}else if(i==1) {
			return Color.red.darker();
		}else
			return Color.GREEN.darker();
	}
	/**
	 * initialize Frame 
	 * sets title 
	 * sets Exit on close
	 * @param args
	 */
	public static void main(String [] args) {
		SameGnomeFrame frame = new SameGnomeFrame();
		frame.setTitle("Same Gnome");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}


























