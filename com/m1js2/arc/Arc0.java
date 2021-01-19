package com.m1js2.arc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Arc0 extends JPanel {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private JFrame window;

	public Arc0() {
		window = new JFrame("ArcPie");
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		setPreferredSize(new Dimension(800, 600));
		window.add(this);
		window.pack();
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.red);
		g.fillArc(getWidth() / 2 - 150, getHeight() / 2 - 150, getWidth() / 2, getHeight() / 2, 30, 30);
		repaint();
	}

	public static void main(String args[]) {
		new Arc0();
	}
}
