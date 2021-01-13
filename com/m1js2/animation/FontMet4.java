package com.m1js2.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FontMet4 extends JPanel{

	private JFrame window;
    private Rectangle rect;
    private int size = 10;
	private String name = "Nassim";
	private Timer timer;
	
	
				public FontMet4() {
					window = new JFrame("Transformations");
					window.setVisible(true);
					window.setResizable(false);
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.setLocationRelativeTo(null);
					setPreferredSize(new Dimension(800,600));
					window.add(this);
					window.pack();
					rect = new Rectangle(500,200,100,100);
					timer = new Timer(50,new ActionListener() {
						 public void actionPerformed(ActionEvent e) {
							  size++;
						 }
					});
					timer.start();
				}
		
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.black);
				g2.fillRect(0, 0, getWidth(), getHeight());
				g2.setColor(Color.red);
				FontMetrics fm = g2.getFontMetrics();
				int ws = fm.stringWidth(name);
				g2.setFont(new Font("Verdana",Font.BOLD,size));
				g2.drawString(name,(getWidth() - ws) / 2 , getHeight() / 2);
				repaint();
			}
		
				public static void main(String[] args) {
					  new FontMet4();
				}
}
