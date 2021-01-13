package com.m1js2.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Transformation2 extends JPanel{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JFrame window;
	    private Rectangle rect;
			
					public Transformation2() {
						window = new JFrame("Transformations");
						window.setVisible(true);
						window.setResizable(false);
						window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						window.setLocationRelativeTo(null);
						setPreferredSize(new Dimension(800,600));
						window.add(this);
						window.pack();
						rect = new Rectangle(500,200,100,100);
					}
			
				double sin,angle = 0.02;
				public void paint(Graphics g) {
					Graphics2D g2 = (Graphics2D)g;
					g2.setColor(Color.black);
					g2.fillRect(0, 0, getWidth(), getHeight());
					g2.setColor(Color.red);
					g2.scale(sin, sin);
					angle+=0.0001;
					sin = Math.abs(Math.sin(angle));
					g2.fillRect(rect.x, rect.y, rect.width, rect.height);
					repaint();
				}
			
					public static void main(String[] args) {
						  new Transformation2();
					}
}
