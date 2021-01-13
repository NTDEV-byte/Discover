package com.m1js2.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GrowingCircle1 extends JPanel{
	
			/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			private JFrame window;
			private Ellipse2D cercle;
			private float sin;
			private int timer;
			private float width = 100, height = 100;
			
				public GrowingCircle1() {
					 window = new JFrame("Growing");
					 window.setVisible(true);
					 window.setResizable(false);
					 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					 window.setLocationRelativeTo(null);
					 this.setPreferredSize(new Dimension(800,600));
					 window.add(this);
					 window.pack();
					 cercle = new Ellipse2D.Float((getWidth() - 100 )/ 2,(getHeight() - 100) / 2, 100, 100);
				}
			
			
			
			
			public void paint(Graphics g) {
				timer++;
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.black);
				g2.fillRect(0, 0, getWidth(), getHeight());
				g2.setColor(Color.red);
				g2.fill(cercle);
				cercle.setFrame(150, 150, width * sin,height * sin);
				if(timer % 50 == 0) {
					 sin =(float)(Math.sin(timer) * 2 * Math.PI) + 15;
				}
				repaint();
			}
			
			
			public static void main(String[] args) {
				 new GrowingCircle1();
			}

}
