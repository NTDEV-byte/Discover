package com.maths;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import com.utils.Utils;

public class Fractal extends JPanel{
	
		
		public static final int WIDTH = 800;
		public static final int HEIGHT = 600;
		public static int x =  WIDTH / 2,y = HEIGHT - 150;
		public static float loop;
		
		
		public Fractal() {
			 setPreferredSize(new Dimension(WIDTH,HEIGHT));
		}
		
		
				public void paint(Graphics g) { 
					g.setColor(Color.black);
					g.fillRect(0, 0, WIDTH, HEIGHT);
					Graphics2D g2 = (Graphics2D)g;
					g2.setColor(Color.red);
					g2.translate(x,y);
					branch(g2,200);
					loop+=0.00002f;
					repaint();
				}
				
				
				
				private void branch(Graphics2D g,int size) {
						g.drawLine(0, 0, 0, -size);
						g.translate(0, -size);
						g.rotate(loop);
						if(size > 2) {
							g.rotate(loop );
							branch(g,(int)(size * 0.67));
							g.rotate(-loop);
							branch(g,(int)(size * 0.67));
						}
					//	g.drawLine(0, 0, 0, (int)(-size * 0.67));
				}
				
				private void push(Graphics2D g2,Point loc) { 
					
				}
				
				private void pop(Graphics2D g2,Point loc) { 
					
				}
				
				
				public static void main(String[] args) { 
					 Utils.windowLauncher("Fractals", new Fractal());
				}

}
