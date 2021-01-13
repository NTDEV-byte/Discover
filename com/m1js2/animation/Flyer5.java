package com.m1js2.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Flyer5 extends JPanel{

	private JFrame window;
	private Shape shape;
	private float coords[];
	private float speeds[];
	private Timer timer;
	private Paint drag = new GradientPaint(50,50,Color.red,250,500,Color.blue);
	
	
	
				public Flyer5() {
					window = new JFrame("Hypnose");
					window.setVisible(true);
					window.setResizable(false);
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.setLocationRelativeTo(null);
					setPreferredSize(new Dimension(800,600));
					window.add(this);
					window.pack();
					coords = new float[2 + 2 * 4];
					speeds = new float[2 + 2 * 4];
					fill(300,coords);
					fill(5,speeds);
					timer = new Timer(20,new ActionListener() {
							public void actionPerformed(ActionEvent e) { 
									update();
							}
					});
					timer.start();
				}
				
		
				
			private void fill(int max,float tab[]) {
				for(int i=0;i<tab.length;i++) {
					tab[i] = (int)(max * Math.random());
				}
			}
			
			private void update() { 
				Dimension d = getSize();
				for(int i=0;i<coords.length;i++) {
					int limit = (i % 2 == 0) ? d.width : d.height;
					coords[i]+=speeds[i];
					if(coords[i] > limit) { 
						speeds[i] = -speeds[i];
					}
					else
						if(coords[i] <= 0) { 
							coords[i] = 0;
							speeds[i] = -speeds[i];
						}
				}
			}
			
			private Shape createShape() {
				 GeneralPath path = new GeneralPath();
				 path.moveTo(coords[0], coords[1]);
				 for(int i=2;i<coords.length;i+=4) {
					   path.quadTo(coords[i], coords[i + 1],coords[i + 2], coords[i + 3]);
				 }
				 path.closePath();
				 return path;
			}
			
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.black);
				g2.fillRect(0, 0, getWidth(), getHeight());
				g2.setPaint(drag);
				shape = createShape();
				g2.fill(shape);
				repaint();
			}
			
				public static void main(String[] args) {
					  new Flyer5();
				}
}