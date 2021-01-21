package sameGnomeGame;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
class Controller {
	private static ArrayList<Ball> ballsToBeHighlighted = new ArrayList<Ball>();
	public  void highlight(Ball[][] allBalls,Ball atBall) {
		Point2D c =new Point2D.Float(-1,-1);
		Ball b = new Ball(Color.black,c);
		highlightHelper(allBalls,atBall,b);
	}
	public static void clearArr() {
		ballsToBeHighlighted.clear();
	}
	public static void clearHighlightted(Ball[][] allBalls) {
		for(int i = 0;i<allBalls.length;i++) {			
			for(int j = 0;j < allBalls[i].length;j++) {
				if(ballsToBeHighlighted.contains(allBalls[i][j]) && ballsToBeHighlighted.size() >= 2) {
					allBalls[i][j] = null;
				}
			}
		}
	}
	public  void collabse(Ball[][] allBalls2) {
		for(int i = 0;i<allBalls2.length;i++) {			
			for(int j = 0;j < allBalls2[i].length;j++) {
				if(allBalls2[i][j] == null && j != 0 && allBalls2[i][j-1] != null) {
					float x = (float) allBalls2[i][j-1].getCenter().getX();
					float y =(float) allBalls2[i][j-1].getCenter().getY();
					Point2D point = new Point2D.Float(x,y);
					allBalls2[i][j] = new Ball(allBalls2[i][j-1].getColor(),point);
					for(int t = 0;t<=100;t+=1) {
						point.setLocation(x, y+t);
						allBalls2[i][j] = new Ball(allBalls2[i][j-1].getColor(),point);
						SameGnomeFrame.grid.repaint();
					}
					allBalls2[i][j-1] = null;
					j=0;
				}
			}
		}
		int s =0;
		while(s !=10) {
			for(int i = 0;i<allBalls2.length-1;i++) {
				if(allBalls2[i][allBalls2[i].length-1] == null && i!=0) {
					for(int j = 0;j < allBalls2[i+1].length;j++) {
						if(allBalls2[i+1][j] != null) {
							Point2D point = new Point2D.Float((float)allBalls2[i+1][j].getCenter().getX()-100,
									(float)allBalls2[i+1][j].getCenter().getY());
							allBalls2[i][j] = new Ball(allBalls2[i+1][j].getColor(),point);
							allBalls2[i+1][j] = null;

						}
					}

				}	
			}	
			s++;
		}
	}

	
	public int highlightHelper(Ball[][] allBalls,Ball atBall,Ball prev) {
		atBall.setHighlightable(true);
		ballsToBeHighlighted.add(atBall);
		Point2D center = atBall.getCenter();
		int indexX = (int)(center.getX() / 100) - 1/2;
		int indexY = (int)(center.getY() / 100) - 1/2;
		if(atBall != null) {
			if(indexX != 14 
					&&allBalls[indexX+1][indexY] != null
					&& atBall.getColor().equals(allBalls[indexX+1][indexY].getColor()) 
					&& !(allBalls[indexX+1][indexY].equals(prev))
					&& ((! ballsToBeHighlighted.contains(allBalls[indexX+1][indexY])) || ballsToBeHighlighted.size() == 0)) 
			{
				ballsToBeHighlighted.add(allBalls[indexX+1][indexY]);
				allBalls[indexX+1][indexY].setHighlightable(true);
				highlightHelper(allBalls,allBalls[indexX+1][indexY],atBall);

			}
			if(indexX != 0  
					&& allBalls[indexX-1][indexY] != null
					&& atBall.getColor().equals(allBalls[indexX-1][indexY].getColor())
					&& !(allBalls[indexX-1][indexY].equals(prev))
					&& (! ballsToBeHighlighted.contains(allBalls[indexX-1][indexY]) || ballsToBeHighlighted.size() == 0))
			{
				ballsToBeHighlighted.add(allBalls[indexX-1][indexY]);
				allBalls[indexX-1][indexY].setHighlightable(true);
				highlightHelper(allBalls,allBalls[indexX-1][indexY],atBall);
			}
			if(indexY != 9  
					&& allBalls[indexX][indexY+1] != null
					&&  atBall.getColor().equals(allBalls[indexX][indexY+1].getColor())
					&& !(allBalls[indexX][indexY+1].equals(prev))
					&& (! ballsToBeHighlighted.contains(allBalls[indexX][indexY+1]) || ballsToBeHighlighted.size() == 0))
			{
				ballsToBeHighlighted.add(allBalls[indexX][indexY+1]);
				allBalls[indexX][indexY+1].setHighlightable(true);
				highlightHelper(allBalls,allBalls[indexX][indexY+1],atBall);
			}
			if(indexY != 0 
					&& allBalls[indexX][indexY-1] != null
					&& atBall.getColor().equals(allBalls[indexX][indexY-1].getColor())
					&& !(allBalls[indexX][indexY-1].equals(prev))
					&& (! ballsToBeHighlighted.contains(allBalls[indexX][indexY-1]) || ballsToBeHighlighted.size() == 0)) 
			{
				ballsToBeHighlighted.add(allBalls[indexX][indexY-1]);
				allBalls[indexX][indexY-1].setHighlightable(true);
				highlightHelper(allBalls,allBalls[indexX][indexY-1],atBall);
			}
		}
		return 0;
	}
	
	
}
