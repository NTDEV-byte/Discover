package com.concept.d2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grid extends JPanel implements MouseMotionListener,MouseListener{
	
		
		/**
		 *
		 * 
		 * 
		 */
	private static final long serialVersionUID = 1L;
		@SuppressWarnings("unused")
		private int grid[];
		private int lignes,colonnes;
		private int sw,sh,wc,hc;
		private JFrame window;	
		private Color back = Color.black,front = Color.red;
		private GridController menu;
		
		
			public Grid(int screenWidth,int screenHeight,int wc,int hc) { 
				 	colonnes = screenWidth / wc;
					lignes = screenHeight / hc;
					this.sw  = screenWidth;
					this.sh = screenHeight;
					this.wc = wc;
					this.hc = hc;
				    grid = new int[lignes * colonnes];
				    menu = new GridController(screenWidth,screenHeight);
				    window = new JFrame("Grid");
				    window.setVisible(true);
				    window.setResizable(false);
				    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    window.setLocationRelativeTo(null);
				    setPreferredSize(new Dimension(screenWidth,screenHeight));
				    window.add(menu);
				    window.pack();
			}

			public void displayGrid(Graphics g) { 
				g.setColor(front);
				for(int y=0;y<lignes;y++) { 
					 for(int x=0;x<colonnes;x++) { 
						g.drawLine(x * wc, 0 , x * wc , sh); 
					 }
					 g.drawLine(0, y * hc, sw, y * hc);
				}
			}
			
			public void paint(Graphics g) { 
				g.setColor(back);
				g.fillRect(0, 0, getWidth(), getHeight());
				displayGrid(g);
				repaint();
			}
			
			public static void main(String[] args) {
				new Grid(800,600,5,5);
			}
			
			private class GridController extends JPanel{
					
				 	/**
				 	 * 
				 	 */
				private static final long serialVersionUID = 1L;
					private JButton zI,zO;
				 	private JPanel panButt;
				 	
				 	public GridController(int ws,int hs) { 
				 		this.setPreferredSize(new Dimension(ws,hs));
				 		setView();
				 	}
				 	
				 	
				 	private void setView() { 
				 		panButt = new JPanel();
				 		zI = new JButton("Zoom+");
				 		zO = new JButton("Zoom-");
				 		
				 		ActionListener listener = new ActionListener() { 
				 			 public void actionPerformed(ActionEvent e) { 
				 				     if(e.getSource() == zI) { 
				 				    	 wc+=1;hc+=1;
				 				     }
				 				     if(e.getSource() == zO) { 
				 				    	 wc-=1;hc-=1;
				 				    	 System.out.println(wc);
				 				     }
				 			 }
				 		};
				 		
				 		zI.addActionListener(listener);
				 		zO.addActionListener(listener);
				 		
				 		panButt.add(zI); panButt.add(zO);
				 		
				 		
				 		panButt.setBackground(Color.DARK_GRAY);
				 		this.setBackground(Color.DARK_GRAY);
				 		this.add(BorderLayout.NORTH,panButt);
				 		this.add(BorderLayout.CENTER,Grid.this);
				 	}
				 	
			}



			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}


}
