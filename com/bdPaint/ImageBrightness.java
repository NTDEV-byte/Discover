package com.bdPaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageBrightness extends JPanel implements KeyListener{

		/**
	 * 
	 */
	
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;

	
	
	public static Font font = new Font("Verdana",Font.PLAIN,15);
	public static final String PATH = "C:\\Users\\nassi\\Desktop\\All\\images\\optimus.jpg";
	private static final long serialVersionUID = 1L;

	
		public int WIDTH;
		public int HEIGHT;
	 	private boolean restart;
		private JFrame window;
		private JPanel mainPanel,buttPanel;
		private BufferedImage original,backup;
		private int oPixels[];
		private int r,g,b;
		
		
		
			public ImageBrightness() {
				window = new JFrame("ImageBrightness Changer");
				window.setVisible(true);
				window.setResizable(false);
				window.setLocationRelativeTo(null);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.addKeyListener(this);
				setView();
				loadImage();
				
				System.out.println(original.getType());
			}
			
			
			private void loadImage() { 
				try {
					original = ImageIO.read(new FileInputStream(PATH));
					backup = ImageIO.read(new FileInputStream(PATH));
					
					int w = backup.getWidth();
					int h = backup.getHeight();
					
					WIDTH = w;
					HEIGHT = h;
					
					
					oPixels = new int[w * h];
					backup.getRGB(0, 0, w, h, oPixels, 0, w);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			private void setView() { 
				mainPanel = new JPanel();
				mainPanel.setLayout(new BorderLayout());
				mainPanel.setPreferredSize(new Dimension(800,600));
				buttPanel = new JPanel();
				buttPanel.setBackground(Color.DARK_GRAY);
				String labels[] = {"Red","Green","Blue"};
				JSlider sliders[] = new JSlider[3];
				
				
				ChangeListener listener = new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {
						  for(int i=0;i<sliders.length;i++) {
							      if(e.getSource() == sliders[i]) { 
							    	  if(i == 0) {
							    		  r = sliders[i].getValue();
							    		  changeBrightness(RED);
							    	  }
							    	  if(i == 1) {
							    		  g = sliders[i].getValue();
							    		  changeBrightness(GREEN);
							    	  }
							    	  if(i == 2) {
							    		  b = sliders[i].getValue();
							    		  changeBrightness(BLUE);
							    	  }
							      }
						  }
					} 
				};
				
				for(int i=0;i<sliders.length;i++) {
					 sliders[i] = new JSlider();
					 sliders[i].setMinimum(0);
					 sliders[i].setMaximum(255);
					 sliders[i].getPaintLabels();
					 sliders[i].setBackground(Color.gray);
					 sliders[i].addChangeListener(listener);
					 JLabel label = new JLabel(labels[i]);
					 label.setForeground(Color.white);
					 buttPanel.add(label);
					 buttPanel.add(sliders[i]);
				}
				
				mainPanel.add(BorderLayout.NORTH,buttPanel);
				mainPanel.add(this);
				
				window.add(mainPanel);
				window.pack();
			}
			
			public void paint(Graphics g) { 
					g.setColor(Color.black);
					g.fillRect(0, 0, getWidth(), getHeight());
					g.setColor(Color.red);
					g.setFont(font);
					g.drawString("Press R to set Default ",getWidth() / 2  - 80, 50);
					if(restart) {
						original.setRGB(0, 0, WIDTH, HEIGHT, oPixels, 0, WIDTH);
					}
					g.drawImage(original,(getWidth() - 300) / 2 , (getHeight() - 300 ) / 2, 300 , 300 , null);
					window.requestFocus();
					repaint();
			}
			
			
			private void changeBrightness(int channel) { 
				int w = original.getWidth();
				int h = original.getHeight();
				
				int pixels[] = new int[w * h];
				
				original.getRGB(0, 0, w, h, pixels, 0, w);
				
				
				for(int y=0;y<h;y++) {
					 for(int x=0;x<w;x++) { 
						 int pixel = pixels[x + y * w];
						 
						 int r = (pixel & 0xff0000) >> 16;
						 int g = (pixel & 0xff00) >> 8;
						 int b = (pixel & 0xff);
						 
						if(channel == RED)   r = this.r;
						if(channel == GREEN) g = this.g;
						if(channel == BLUE)  b = this.b;
						 
						 pixels[x + y * w] = (r << 16) | (g << 8) | b;
						 
					 }
				}
				
				original.setRGB(0, 0, w, h, pixels, 0, w);

				
			}
			
			
			public static void main(String[] args) { 
				new ImageBrightness();
			}


			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void keyPressed(KeyEvent e) {
			  if( e.getKeyCode()  == KeyEvent.VK_R) { 
				   restart = true;
			  }
			}


			@Override
			public void keyReleased(KeyEvent e) {

					restart = false;
			}
}
