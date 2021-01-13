package com.m1js2.animation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BufferedImageComposition6  extends JPanel{
	
		private JFrame window;
		private byte data[];
		private BufferedImage image = null;
		
		
			
				public BufferedImageComposition6() {
					window = new JFrame("Window");
					window.setVisible(true);
					window.setResizable(false);
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.setLocationRelativeTo(null);
					this.setPreferredSize(new Dimension(800,600));
					window.add(this);
					window.pack();
					createImageX(800,600);
				}
				
				
				private void createImageX(int w,int h) {
					  data = new byte[w * h];
					  DataBufferByte buff = new DataBufferByte(data,data.length);
					  WritableRaster raster = Raster.createPackedRaster(buff, w, h, 1, null);
					  ColorModel cm = new IndexColorModel(1,2,new byte[] {0,(byte)255},new byte[] {0,(byte)255},new byte[] {0,(byte)255});
					  image = new BufferedImage(cm,raster,false,null);
				}
				
				
				public void paint(Graphics g) {
					g.drawImage(image, 0,0, getWidth(), getHeight(), null);
					new Random().nextBytes(data);
					repaint();
				}
				
				
				public static void main(String[] args) {
					 	new BufferedImageComposition6();
				}

}
