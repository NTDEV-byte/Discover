package com.m1js2.bufferedImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class ClippedTextRect0 extends JPanel{
		
		
		
	
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


		private Font font = new Font("Verdana",Font.PLAIN,50);
		private String text = "Nassim";
		

		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			
			g2.setFont(font);
			FontRenderContext fm = g2.getFontRenderContext();
			
			Rectangle2D bT = font.getStringBounds(text, fm);
			
			int wT = (int)(bT.getWidth());
			int hT = (int)(bT.getHeight());
			
			int cntX = (getWidth() - wT) / 2;
			int cntY = (getHeight() - hT) / 2;
			
			
			g2.setColor(Color.yellow);
			
			g2.fillRect(cntX,cntY, wT, hT);
			
			g2.setColor(Color.black);
			
			int cY = cntY - (int)bT.getY();
			g2.drawString(text,cntX,cY);
			
			
			repaint();
		}
		
		

			public static void main(String[] args) {
				 com.utils.Utils.windowLauncher("Test", new ClippedTextRect0());
			}
}
