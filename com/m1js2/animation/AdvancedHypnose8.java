package com.m1js2.animation;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.GeneralPath;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AdvancedHypnose8 extends JPanel{
	    	
		/**
	 * storke outline 
	 * axis and clip
	 */
	
	
	
	private static final long serialVersionUID = 1L;
	
		public static final int ANTI_ALIASING = 0;
		public static final int TRANSFORMATION = 1;
		public static final int GRADIENT = 2;
		public static final int OUTLINE = 3;
		public static final int DOTTED = 4;
		public static final int AXIS = 5;
	
		private JFrame window;
		private Random random = new Random();
		private Color col1,col2;
		private Paint paint = new GradientPaint(50,50,Color.red,300,400,Color.blue);
		private double points[];
		private double speeds[];
		private Timer timer,x;
		private JPanel panButt,main;
		private boolean ali,tra,gra,out,dot,axi;
		private BasicStroke stroke = new BasicStroke(2.0f);
		
		
		
				public AdvancedHypnose8() {
					window = new JFrame("Bouncing Shape");
					window.setVisible(true);
					window.setResizable(false);
					window.setFont(new Font("Verdana",Font.BOLD,15));
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.setLocationRelativeTo(null);
					main = new JPanel();
					main.setPreferredSize(new Dimension(800,600));
					main.setLayout(new BorderLayout());
					window.add(main);
					window.pack();
					points = new double[2 * 4 + 2];
					speeds = new double[2 * 4 + 2];
					
					
					
					fillPoints(300,points);
					fillPoints(5,speeds);
					
					setPanButt();
					
					
					timer = new Timer(30,new ActionListener() { 
						public void actionPerformed(ActionEvent e) {
							   update();
						}
					});
					timer.start();
					x = new Timer(10000,new ActionListener() { 
						public void actionPerformed(ActionEvent e) {
							  changeColor();
						}
					});
					x.start();
					
				}
				
				
				
				private void switchState(int choice,boolean value) { 
					switch(choice) { 
					case ANTI_ALIASING:
						 ali = value;
						break;
					case TRANSFORMATION:
					     tra = value;
						break;
					case GRADIENT:
						gra = value;
						break;
					case OUTLINE:
						out = value;
						break;
					case DOTTED:
						dot = value;
						break;
					case AXIS:
						axi = value;
						break;
					}
				}
				
				
				private void setPanButt() { 
					panButt = new JPanel();
					panButt.setBackground(Color.DARK_GRAY);
					String names[] =  {"Anti-aliasing","Transformations","Gradient","Outline","Dotted","Axis","Clip"};
					JCheckBox boxs[] = new JCheckBox[7]; 
					ItemListener listener = new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							 for(int i=0;i<boxs.length;i++) { 
								  if(e.getSource() == boxs[i]) { 
									   switch(i) { 
									   case ANTI_ALIASING:
											if(e.getStateChange() == ItemEvent.SELECTED) {
												 switchState(i,true);
											}else {
												switchState(i,false);
											}
											break;
										case TRANSFORMATION:
											if(e.getStateChange() == ItemEvent.SELECTED) {
												 switchState(i,true);
											}else {
												switchState(i,false);
											}
											break;
										case GRADIENT:
											if(e.getStateChange() == ItemEvent.SELECTED) {
												 switchState(i,true);
											}else {
												switchState(i,false);
											}
											break;
										case OUTLINE:
											if(e.getStateChange() == ItemEvent.SELECTED) {
												 switchState(i,true);
											}else {
												switchState(i,false);
											}
											break;
										case DOTTED:
											if(e.getStateChange() == ItemEvent.SELECTED) {
												 switchState(i,true);
											}else {
												switchState(i,false);
											}
											break;
										case AXIS:
											if(e.getStateChange() == ItemEvent.SELECTED) {
												 switchState(i,true);
											}else {
												switchState(i,false);
											}
											break;
										}
									   }
								  }
							 }
					};
					for(int i=0;i<boxs.length;i++) {
						 boxs[i] = new JCheckBox(names[i]);
						 boxs[i].addItemListener(listener);
						 panButt.add(boxs[i]);
					}
					main.add(BorderLayout.NORTH,panButt);
					main.add(BorderLayout.CENTER,this);
				}
				
				private void actions(Graphics2D g) { 
					if(ali) {
						g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					}
					else {
						g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
					}
					if(tra) {
						g.rotate((System.currentTimeMillis() % 10000 / 10000.0 * Math.PI * 2),getWidth() / 2 , getHeight() / 2);
						
					}
				
					if(out) {
						stroke = new BasicStroke(2f);
					}
					if(dot) {
						   stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
							        BasicStroke.JOIN_ROUND, 150, new float[] {8, 15}, 300);
					}
					if(axi) {
						drawAxis(g);
					}
					if(gra) {
						g.setPaint(paint);
						
					}else {
						g.setColor(Color.red);
					}
				}
				
				private void fillPoints(int max,double points[]) {
					 for(int i=0;i<points.length;i++) { 
						  points[i] = (int)(max * Math.random());
					 }
				}

				private GeneralPath createShape() {
					 GeneralPath path = new GeneralPath();
					 path.moveTo(points[0], points[1]);
					 for(int i=2;i<points.length;i+=4) { 
						 path.quadTo(points[i],points[i + 1],points[i + 2],points[i + 3]);
					 }
					 path.closePath();
					 return path;
				}
				
				private void update() { 
					Dimension size = getSize();
					if(size.width <= 0 || size.height <= 0) return;
				
						for(int i=0;i<points.length;i++) { 
							 int limit = (i % 2 == 0) ? size.width : size.height;
							  
							  points[i]+=speeds[i];
							  
							  if(points[i] < 0) { 
								   speeds[i] = -speeds[i];
							  }
							  
							  else 
								  if(points[i] >= limit) { 
									  speeds[i] = -speeds[i];
								  }
						}
				}
				
				public void paint(Graphics g) {
					Graphics2D g2 = (Graphics2D)g;
					g2.setColor(Color.black);
					g2.fillRect(0,0,getWidth(),getHeight());
					Shape s = createShape();
					if(out) {
							g2.setStroke(stroke);
							g2.setColor(Color.white);
							g2.draw(s);
					    }
					actions(g2);
					g2.fill(s);
				
				   
					repaint();
				}
				
				private void drawAxis(Graphics g) {
					int w = 15 , h = 15;
					 g.setColor(Color.white);
					 g.drawLine(w *5 , h * 5, w * 6, h *6);
				}
				
				private void changeColor() {
					int r = random.nextInt(256);
					   int g = random.nextInt(256);
					   int b = random.nextInt(256);

					   col1 = new Color(r,g,b);
					   
					    r = random.nextInt(256);
					    g = random.nextInt(256);
					    b = random.nextInt(256);

					   
					   col2 = new Color(r,g,b);
					   
					   paint = new GradientPaint(50,50,col1,300,400,col2);
				}
				
				
				public static void main(String[] args) {
						new AdvancedHypnose8();
				}
		
}	
