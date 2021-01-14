package com.arc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Arc1 extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame window;
	private Timer timer;
	
	public Arc1() { 
		window = new JFrame("ArcPie");
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		setPreferredSize(new Dimension(800,600));
		window.add(this);
		window.pack();
		timer = new Timer(500,new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				repaint();
			}
		});
		timer.start();
	}
	
	private void fillWheel(Graphics2D g,int colors) {
			int step = 360 / colors;
			int startX = 0;
			Random random = new Random();
			Stroke s = new BasicStroke(5);
			Color cols[] = new Color[colors];
			for(int i=0;i<cols.length;i++) {
				 cols[i] = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
			}
			for(int i=0;i<cols.length;i++) {
				g.setColor(cols[i]);
				g.setStroke(s);
				g.drawArc(getWidth()  / 2 - 80, getHeight() / 2 - 80, 200, 200,  startX , step);
				Color x = Color.blue;
				g.setColor(x);
				g.fillArc(getWidth()  / 2 - 80, getHeight() / 2 - 80, 200, 200,  startX , step);
				startX+=step;
			}
	}
	
	public void paint(Graphics g) { 
		Graphics2D g2  = (Graphics2D)g;
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		fillWheel(g2,5);
	}
	
	public static void main(String args[]) {
		 new Arc1();
	}
}
