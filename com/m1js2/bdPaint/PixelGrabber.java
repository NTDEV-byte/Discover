package com.m1js2.bdPaint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PixelGrabber extends JPanel{
	
	
		/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
		public static final int WIDTH_IMG = 500;
		public static final int HEIGHT_IMG = 500;
		public static final int DELAY = 10; // Delai en MS avant de choisir de nouvelles couleurs pour chaque pixel
		
		private JFrame window;
		private BufferedImage image = new BufferedImage(WIDTH_IMG,HEIGHT_IMG,BufferedImage.TYPE_INT_RGB);
		private int pix[] = ((DataBufferInt)(image.getRaster().getDataBuffer())).getData();
		private Timer colPicker;
		private Random random = new Random();
		private Font FONT = new Font("Verdana",Font.PLAIN,30);
		
		
					public PixelGrabber() { 
						window = new JFrame("PixelGrabber");
						window.setVisible(true);
						window.setResizable(false);
						setPreferredSize(new Dimension(800,600));
						window.add(this);
						window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						window.setLocationRelativeTo(null);
						window.pack();
						colPicker = new Timer(DELAY,new ActionListener() {
								public void actionPerformed(ActionEvent e) { 
									  	pickRandomColorPerPixel();
								}
						});
						colPicker.start();
					}
			
				public void paint(Graphics g) { 
					g.setColor(Color.DARK_GRAY);
					g.fillRect(0, 0,getWidth(), getHeight());
					g.setColor(Color.black);
					g.setFont(FONT);
					g.drawString("No Signal !",100,30);
					g.drawImage(image,(getWidth() - WIDTH_IMG) / 2 , (getHeight() - HEIGHT_IMG) / 2,WIDTH_IMG,HEIGHT_IMG,null);
					repaint();
				}
				
				private void pickRandomColorPerPixel() { 
					  for(int i=0;i<WIDTH_IMG * HEIGHT_IMG;i++) { 
						    pix[i] = random.nextInt(0xffffff);
					  }
				}
				
				public static void main(String[] args) { 
					 new PixelGrabber();
				}
		

}
