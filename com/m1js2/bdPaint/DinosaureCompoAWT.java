package com.m1js2.bdPaint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

public class DinosaureCompoAWT{

	/**
	 * Press Win To ESCAPE ;)
	 */
	private static final long serialVersionUID = 1L;
	
		
		public static void main(String[] args) {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gd = ge.getDefaultScreenDevice();
			
			GraphicsConfiguration gc = gd.getDefaultConfiguration();
			
			Frame win =  new Frame(gc);
			
			win.setUndecorated(true);
			win.setIgnoreRepaint(true);

			gd.setFullScreenWindow(win);
			
			win.createBufferStrategy(3);
			
			BufferStrategy bs = win.getBufferStrategy();
			
			Graphics g = bs.getDrawGraphics();
			Rectangle rec = win.getBounds();
			
				g.setColor(Color.red);
				g.fillRect(rec.x, rec.y, rec.width, rec.height);
				bs.show();
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

}
