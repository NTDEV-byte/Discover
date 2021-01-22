package com.m1js2.bufferedImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.utils.Utils;

public class ClipHypno extends JPanel{

	
	
			/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			public static final int WIDTH = 800;
			public static final int HEIGHT = 600;
			public static final double RATE = 30.0;
			public static final String PATH = "C:/Users/nassi/Desktop/All/images/opti.jpg";
			
			
			private Shape shape;
			private int coords[];
			private int speeds[];
			private BufferedImage image;
			
			
			
			
			public ClipHypno() { 
				this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
				loadIMG();
				coords = new int[2 + 2 * 4];
				speeds = new int[2 + 2 * 4];
				fillTab(coords,300);
				fillTab(speeds,5);
			}
			
			public void fillTab(int tab[],int max) { 
				for(int i=0;i<tab.length;i++) {
					   tab[i] = (int)(max * Math.random());
				}
			}
			
			public void loadIMG() { 
				try {
					image = ImageIO.read(new FileInputStream(PATH));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			public GeneralPath createShape() { 
				
				GeneralPath path = new GeneralPath();
				
				path.moveTo(coords[0], coords[1]);
				
				for(int i=2;i<coords.length;i+=4) {
					  path.quadTo(coords[i], coords[i + 1], coords[i + 2], coords[i + 3]);
				}
				
				path.closePath();
				
				return path;
			}
			
			public void update() {
				 Dimension size = ClipHypno.this.getSize();
				 int limit;
				 if(size.width == 0 || size.height == 0) { 
					  System.err.println("null window");
					  System.exit(1);}
				 
				 
				 for(int i=0;i<coords.length;i++) { 
					 limit = (i % 2 == 0) ? size.width : size.height;
					 
					 if(coords[i] <= 0) {
						  coords[i] = 0;
						  speeds[i] = -speeds[i];
					 }
					 else
						 if(coords[i] >= limit) {
							  coords[i] = limit;
							  speeds[i] = -speeds[i];
						 }
					 
					 coords[i]+=speeds[i];
				 }
			}
			public void paint(Graphics g) {
				 Graphics2D g2 = (Graphics2D)g;
				 g2.setColor(Color.black);
				 g2.fillRect(0, 0, getWidth(), getHeight());
				 shape = createShape();
				 g2.setClip(shape);
				 g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
				if((System.currentTimeMillis() % RATE / RATE) == 0) update();
				 repaint();
			}
	
			public static void main(String[] args) {
				Utils.windowLauncher("ClipDemo", new ClipHypno());
			}
}
