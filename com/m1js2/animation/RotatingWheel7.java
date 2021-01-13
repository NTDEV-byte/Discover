package com.m1js2.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RotatingWheel7 extends JPanel{

		
			/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			private JFrame window;
			private Color colors[] = {Color.red,Color.blue,Color.green,Color.yellow,Color.cyan,Color.magenta,Color.magenta,Color.gray,Color.lightGray,Color.PINK};
			
					public RotatingWheel7() {
						window = new JFrame("Window");
						window.setVisible(true);
						window.setResizable(false);
						window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						window.setLocationRelativeTo(null);
						this.setPreferredSize(new Dimension(800,600));
						window.add(this);
						window.pack();
					}
					
					private void drawWheel(Graphics g) { 
						int step = 360 / colors.length;
						int start = 0;
						for(int i=0;i<colors.length;i++) {
							 g.setColor(colors[i]);
							 g.fillArc((getWidth() - 200) / 2, (getHeight() - 200) / 2, 200, 200, start, step);
							 start+=step;
						}
					}
					
					public void paint(Graphics g) {
						g.setColor(Color.black);
						g.fillRect(0, 0,getWidth(), getHeight());
						drawWheel(g);
						rotate();
						repaint();
						try {
							Thread.sleep(40);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					private void rotate()
					{
						 Color c = colors[0];
						 System.arraycopy(colors, 1, colors, 0, colors.length - 1);
						 colors[colors.length - 1] = c;
					}
					
					
					public static void main(String[] args) {
						 	new RotatingWheel7();
					}
					

	}
