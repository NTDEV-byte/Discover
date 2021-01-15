package com.areaCalculation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AreaCalculation extends JPanel implements KeyListener{
			
			/**
	 * 
	 */
	
	
	public static Color INTERSECTION = Color.green;
	public static Color ADD = Color.yellow;
	public static Color SUBSTRACT = Color.magenta;
	public static Color XOR = Color.cyan;
	
	
	private static final long serialVersionUID = 1L;
			private JFrame window;
			private JPanel main,panButt;
			private Area a1,a2;
			private Rectangle r1,r2;
			private boolean up,right,down,left;
			private boolean intersection,add,substract,xor;
			
				public AreaCalculation() {
					 setView();
					 window = new JFrame("Window");
					 window.setVisible(true);
					 window.setResizable(false);
					 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					 window.setLocationRelativeTo(null);
					 window.addKeyListener(this);
					 window.add(main);
					 window.pack();
					 
					 r1 = new Rectangle(150,150,100,60);
					 r2 = new Rectangle(150,180,80,20);
					 
					 a1 = new Area(r1);
					 a2 = new Area(r2);
				}
				
				
				private void setView() { 
					panButt = new JPanel();
					panButt.setBackground(Color.DARK_GRAY);
					main = new JPanel();
					main.setPreferredSize(new Dimension(800,600));
					main.setLayout(new BorderLayout());
					String[] labels = {"intesection","add","substract","xor"};
					JCheckBox butts[] = new JCheckBox[4];
					
					ItemListener listener = new ItemListener() {

						@Override
						public void itemStateChanged(ItemEvent e) {
							    for(int i=0;i<butts.length;i++) { 
							    	if(e.getSource() == butts[i]) { 
							    		 if(e.getStateChange() == ItemEvent.SELECTED) {
							    			   if(i == 0) {
							    				   intersection = true;
							    			   }
							    			   else if(i == 1) {
							    				   add = true;
							    			   }
							    			   else if(i == 2) {
							    				   substract = true;
							    			   }
							    			   else if(i == 3) {
							    				   xor = true;
							    			   }
							    		 
							    		 }else {
							    			   if(i == 0) {
							    				   intersection = false;
							    			   }
							    			   else if(i == 1) {
							    				   add = false;
							    			   }
							    			   else if(i == 2) {
							    				   substract = false;
							    			   }
							    			   else if(i == 3) {
							    				   xor = false;
							    			   }
							    		 }
							    	}
							    }
						}
						
					};
					
					
					ButtonGroup bg = new ButtonGroup();
					for(int i=0;i<butts.length;i++) {
						 butts[i] = new JCheckBox(labels[i]);
						 butts[i].addItemListener(listener);
						 butts[i].setBackground(Color.LIGHT_GRAY);
						 bg.add(butts[i]);
						 panButt.add(butts[i]);
					}
					
					main.add(BorderLayout.NORTH,panButt);
					main.add(BorderLayout.CENTER,this);
				}
				
				public void paint(Graphics g) { 
					Graphics2D g2 = (Graphics2D)g;
					g.setColor(Color.black);
					g.fillRect(0, 0, getWidth(), getHeight());
					update();
					g.setColor(Color.blue);
					g.fillRect(r1.x, r1.y, r1.width, r1.height);
					g.setColor(Color.red);
					g.fillRect(r2.x, r2.y, r2.width, r2.height);
					if(intersection)intersection(g2);
					if(add)add(g2);
					if(substract)substract(g2);
					if(xor)xor(g2);
					window.requestFocus();
					repaint();
				}
				
				
				public void update() {
					 if(up) {
						 r2.y-=1;
					 }
					 if(right) {
						 r2.x+=1;
					 }
					 if(down){
						 r2.y+=1;
					 }
					 if(left) { 
						 r2.x-=1;
					 }
				}
				
				
				private void intersection(Graphics2D g) { 
					a1 = new Area(r1);
					a2 = new Area(r2);
					a1.intersect(a2);
					if(!a1.isEmpty()) { 
						g.setColor(INTERSECTION);
						g.fill(a1);
					}
				}
				
				
				private void add(Graphics2D g) { 
					a1 = new Area(r1);
					a2 = new Area(r2);
					a1.intersect(a2);
					if(!a1.isEmpty()) { 
						a1 = new Area(r1);
						a2 = new Area(r2);
						a1.add(a2);
						g.setColor(ADD);
						g.fill(a1);
					}
				}
				
				private void substract(Graphics2D g) { 
					a1 = new Area(r1);
					a2 = new Area(r2);
					a1.intersect(a2);
					if(!a1.isEmpty()) { 
						a1 = new Area(r1);
						a2 = new Area(r2);
						a1.subtract(a2);
						g.setColor(SUBSTRACT);
						g.fill(a1);
					}
				}
				
				private void xor(Graphics2D g) { 
					a1 = new Area(r1);
					a2 = new Area(r2);
					a1.exclusiveOr(a2);
					if(!a1.isEmpty()) { 
						g.setColor(XOR);
						g.fill(a1);
					}
				}
				
				public static void main(String[] args) { 
					new AreaCalculation();	
				}

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					switch(e.getKeyCode()) { 
					
					case KeyEvent.VK_UP:
						
						up = true;
						
						break;
						
					case KeyEvent.VK_RIGHT:
						
						right = true;
						break;
						
					case KeyEvent.VK_DOWN:
	
						down = true;
						
						break;
	
					case KeyEvent.VK_LEFT:
						
						
						left = true;
	
						break;
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					 up = right = down = left =  false;
				}
	
		
			

}
