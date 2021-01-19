package com.m1js2.bdPaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.utils.Utils;

public class DoubleBuffering extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
		public static final int WIDTH_IMAGE = 400;
		public static final int HEIGHT_IMAGE = 400;
		public static final int DELAY = 500;
	
		private BufferedImage image = new BufferedImage(WIDTH_IMAGE,HEIGHT_IMAGE,BufferedImage.TYPE_INT_RGB);	
		private Timer updateBackBuffer;
			
		
		public DoubleBuffering() { 
			drawToBackBuffer();
			updateBackBuffer = new Timer(DELAY, new ActionListener() { 
				 public void actionPerformed(ActionEvent e) { 
					 	drawToBackBuffer();
				 }
			});
			updateBackBuffer.start();
		}
		/**
		 * ceci permet de dessiner sur l'image 
		 * une fois dessiné on affiche l'image
		 * ce qui nous permet en quelque sorte de faire du double buffering
		 */
		private void drawToBackBuffer() { 
			Color colors[] = {Color.red,Color.gray,Color.green,Color.magenta,Color.yellow,Color.orange};
			Random ran = new Random();
			int w = 100 , h = 100;
			Graphics g = image.getGraphics();
		    g.setColor(colors[ran.nextInt(colors.length)]);
		    g.fillRect((WIDTH_IMAGE - w) / 2, (HEIGHT_IMAGE - h) / 2, w ,h);
		}
		
		public void paint(Graphics g) { 
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0,0, getWidth(), getHeight());
			g.drawImage(image, (getWidth() - WIDTH_IMAGE) / 2, (getHeight() - HEIGHT_IMAGE) / 2, WIDTH_IMAGE, HEIGHT_IMAGE,null);
			repaint();
		}
	
	
	
	
	public static void main(String[] args) {
			 Utils.windowLauncher("DoubleBuffer !",new DoubleBuffering());
		
	}

}
