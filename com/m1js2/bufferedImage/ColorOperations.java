package com.m1js2.bufferedImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ColorOperations extends JPanel{

		/*
		 * ColorConvertOp
		 * RescaleOp
		 * LookupOp
		 * ConvolveOp
		 * AffineTransformOp
		 */
	
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public static final int WIDTH_IMAGE = 130;
		public static final int HEIGHT_IMAGE = 200;
		public static final int WIDTH = 4 * WIDTH_IMAGE;
		public static final int HEIGHT = 4 * HEIGHT_IMAGE;
		public static BufferedImage image,temp;
		public static ByteLookupTable blt = new ByteLookupTable(0,new byte[] {0,(byte)256});
		
		public static BufferedImageOp operations[] = {
				 new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null),
			      // 3) Image negative. Multiply each color value by -1.0 and add 255
			      new RescaleOp(-1.0f, 255f, null),
			      // 4) Brighten using a linear formula that increases all color
			      // values
			      new RescaleOp(1.25f, 0, null),
			      
			      new ConvolveOp(new Kernel(3, 3, new float[] {
			    		  .1111f, .1111f,.1111f,
			    		  .1111f, .1111f, .1111f, 
			    		  .1111f, .1111f, .1111f, })),
			      // 8) Sharpen by using a different matrix
			      new ConvolveOp(new Kernel(3, 3, new float[] { 
			    	   0.0f, -0.75f, 0.0f,
			          -0.75f, 4.0f, -0.75f,
			           0.0f, -0.75f, 0.0f })),
			      // 9) Edge detect using yet another matrix
			      new ConvolveOp(new Kernel(3, 3, new float[] { 
			    	    0.0f, -0.75f, 0.0f,
			           -0.75f, 3.0f, -0.75f,
			           0.0f, -0.75f, 0.0f })),
		
				
		}; 

		public ColorOperations() {
			setPreferredSize(new Dimension(800,200));
				loadImage();
			}
		
		private void loadImage() {
			try {
				image = ImageIO.read(new FileInputStream("C:/Users/nassi/Desktop/All/images/bambi.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
			public void paint(Graphics g) { 
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.black);
				g2.fillRect(0,0,getWidth(),getHeight());
				for(int i=0;i<operations.length;i++) {
					g2.drawImage(operations[i].filter(image, null), i * WIDTH_IMAGE, 0, WIDTH_IMAGE, HEIGHT_IMAGE, this);
				}
				repaint();
			}


			public static void main(String[] args) { 
				com.utils.Utils.windowLauncher("color operations", new ColorOperations());
			}

}
