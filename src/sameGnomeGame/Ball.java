package sameGnomeGame;

import java.awt.Color;
import java.awt.geom.Point2D;

class Ball{
	/**
	 * the radius if the ball object
	 */
	private int radius = (int) ((SameGnomeFrame.DEFUALT_WIDTH) /( 2*15));
	
	private Point2D center;
	private Color c;
	private boolean highlightable;
	private boolean removed;
	public Ball(Color c, Point2D center) {
		this.c = c;
		this.center = center;
		highlightable  = false;
		removed = false;
	}
	public Point2D getCenter() {
		return this.center;
	}
	public boolean isRemoved() {
		return this.removed;
	}
	public void setRemoved(boolean b) {
		this.removed = b;
	}
	public int getRadius() {
		return radius;
	}
	public int getX2() {
		return (int)center.getX();
	}
	public int getY2() {
		return (int)center.getY();
	}
	public Color getColor() {
		return this.c;
	}
	public boolean getHighlightable() {
		return highlightable;
	}
	public void setCenter(Point2D center) {
		this.center = center;
	}
	public void setHighlightable(boolean h) {
		highlightable = h;
	}
	
	/**
	 * checks if the point in the ball or not
	 * @param point any point to be check
	 * @return true if point in ball false otherwise
	 */
	public boolean isInBall(Point2D point) {
		double xDiff =  (Math.pow(this.getX2()-point.getX(),2));
		double yDiff =  (Math.pow(this.getY2()-point.getY(),2));
		if(xDiff + yDiff > Math.pow(this.radius,2)) {
			return false;
		}else {
			return true;
		}
	}
	public String toString() {
		return "Ball with Center :"+ center;
	}
	public boolean equals(Ball b) {
		return this.center.equals(b.getCenter());
	}
}