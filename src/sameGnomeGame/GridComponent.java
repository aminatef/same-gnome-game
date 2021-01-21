package sameGnomeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import sameGnomeGame.GridComponent.MouseHandler;
import sameGnomeGame.GridComponent.MouseMotionHandler;

class GridComponent extends JPanel{
	/**
	 * array of balls to be drown
	 */
	private Ball [][] balls;
	private Controller control;
	/**
	 * initialize mouse listener and mouse motion listener
	 * @param Balls balls to be Drown
	 */
	public GridComponent(Ball[][] Balls) {
		balls = Balls;
		control = new Controller();
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
	}
	
	public Dimension getPreferredSize() {
		if (isPreferredSizeSet()) {
			return super.getPreferredSize();
		}
		return new Dimension(SameGnomeFrame.DEFUALT_WIDTH, SameGnomeFrame.DEFUALT_HEIGHT);
	}
	/**
	 * checks balls [][] to find the ball that contains Point2D point
	 * @param point point to be checked
	 * @return the ball that the point is inside
	 */
	public Ball getBall(Point2D point) {
		for(Ball[] ballArr :balls) {
			for(Ball ball:ballArr) {
				if(ball != null) {
					if(ball.isInBall(point)) {
						return ball;
					}
				}
			}
		}
		return null;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
		for(int i = 0;i<balls.length;i++) {			
			for(int j = 0;j < balls[i].length;j++) {
				if(balls[i][j] == null) {
					continue;
				}
				Point2D center = balls[i][j].getCenter();
				int radius = balls[i][j].getRadius();
				if(balls[i][j].getHighlightable()) {
					
					Ellipse2D.Double cir = new Ellipse2D.Double(center.getX()-radius-5,
										 center.getY()-radius-5,
							             (radius+5)*2,(radius+5)*2);
					g2D.fill(cir);
					g2D.setPaint(balls[i][j].getColor());
					g2D.draw(cir);
				}
				Ellipse2D.Double cir=new Ellipse2D.Double(center.getX()-radius,center.getY()-radius,radius*2,radius*2);
				g2D.setPaint(balls[i][j].getColor());
				g2D.fill(cir);
				g2D.draw(cir);
				balls[i][j].setHighlightable(false);
			}
			
		}
	}
	class MouseHandler extends MouseAdapter{
		public void mouseClicked(MouseEvent event){
			Ball b = getBall(event.getPoint());
			if(b != null) {
				control.highlight(balls,b);
				Controller.clearHighlightted(balls);
				System.out.println("************************");
				control.collabse(balls);
				repaint();
				Controller.clearArr();
			}
		}
	}
	class MouseMotionHandler implements MouseMotionListener{
		public void mouseMoved(MouseEvent event) {
			Ball b = getBall(event.getPoint());
			if(b != null) {
				control.highlight(balls,b);
				repaint();
				Controller.clearArr();
			}
	}
	public void mouseDragged(MouseEvent event) {}
	}	
}