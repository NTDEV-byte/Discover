package com.m1js2.antiAliasing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AntiAliasing extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		private JFrame window;
		private Rectangle bounds,fen;
		private int dx,dy;
		private int timer;
		private Random random = new Random();
		
				public AntiAliasing() {
					fen = new Rectangle(0,0,800,600);
					window = new JFrame("Window");
					window.setVisible(true);
					window.setResizable(false);
					setPreferredSize(new Dimension(800,600));
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.setLocationRelativeTo(null);
					window.add(this);
					window.pack();
					bounds = new Rectangle(150,150,100,40);
				}
				
				public void move() { 
					timer++;
					if(timer % 1000 == 0) {
						 dx = random.nextInt(3) - 1;
						 dy = random.nextInt(3) - 1;
						}
					 bounds.x+=dx;
					 bounds.y+=dy;
					 if(bounds.intersectsLine(0, 0, 0, getHeight()) 
							 || bounds.intersectsLine(0, 0, getWidth(), 0) ||
							 	  		bounds.intersectsLine(getWidth(),0,getWidth(), getHeight()) || 
							 	  				bounds.intersectsLine(0, getHeight(), getWidth(), getHeight())) {
						 		dx*=-1;
						 		dy*=-1;
					 }
				}
			
				public void paint(Graphics g) { 
					Graphics2D g2 = (Graphics2D)g;
					g2.setColor(Color.blue);
					g2.fillRect(fen.x, fen.y, fen.width,fen.height);
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(Color.red);
					g2.fillRect(bounds.x, bounds.y,bounds.width, bounds.height);
					move();
					repaint();
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				public static void main(String[] args) { 
						new AntiAliasing();
				}
}
