package com.maths;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import com.utils.Utils;

public class BombSlug extends JPanel{

		public static final int SPEED = 1;
		public static final int AMOUNT_PARTICALS = 200;
		private double angle;
		private double fallRate;
		private static Bomb bomb;
		private static Earth earth;
		private static Partical partical;
		private float xO = 150,yO = 400;
		private static List<Partical> particals = new ArrayList<Partical>();
		
		
			public BombSlug(double angle,double fallR) { 
				this.angle = Math.toRadians(angle);
				this.fallRate = fallR;
				setPreferredSize(new Dimension(800,600));
				bomb = new Bomb(xO,yO,this.angle,fallR);
				partical = new Partical(5,5,5);
				earth = new Earth();
			}
			
			
			public void paint(Graphics g) { 
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.black);
				g2.fillRect(0, 0, getWidth(), getHeight());
				
				
				bomb.render(g2);
				bomb.launch();
				partical.render(g2);
				partical.fly();
				earth.Earth(g2);
				
				if(earth.intersectsBomb(bomb)) { 
					generateParticals(this,8);
					bomb = new Bomb(xO,yO,-60,0.005);
				}
				
				updateAndShowParticals(g2);
				
			
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
			
	
			private void updateAndShowParticals(Graphics2D g2) { 
				for(Partical p : particals) { 
					p.fly();
				}
				for(Partical p : particals) {
					 p.render(g2);
				}
				for(int i=0;i<particals.size();i++) { 
					if(particals.get(i).dead) { 
							particals.remove(i);
					}
				}
			}
			
			private class Bomb{ 

				private java.awt.geom.Rectangle2D.Float bomb;
				private float x,y,angle,fallRate;
				private boolean exploded;
				
					public Bomb(float x,float y,double angle,double fallRate) { 
							this.x = x;
							this.y = y;
							this.angle = (float)Math.toRadians(angle);
							this.fallRate = (float)fallRate;
							bomb =  new Rectangle2D.Float(x,y,10,10);
					}
					
					
				public void render(Graphics2D g) { 
					g.setColor(Color.red);
					g.fill(bomb);
				}
				
				public void launch() { 
					float xDir =  (float)(Math.cos(angle) * SPEED);
					float yDir = (float)(Math.sin(angle) * SPEED);
					x+=xDir;
					y+=yDir;
					bomb.setRect(x, y, 10, 10);
					if(angle <= 0.9)
					angle+=fallRate;
					System.out.println(angle);
				}
				
			}
			
			
			
			private class Partical{ 
				private Color col = new Color(0xaaaaaa);
				private Rectangle2D.Float partical;
				private float x,y,xd,yd,life;
				private int clock;
				private boolean dead;
				
				public Partical(float x,float y,float life) { 
							this.x = x;
							this.y = y;
							this.xd = (float)new Random().nextGaussian();
							this.yd = (float)new Random().nextGaussian();
							this.life = new Random().nextInt(8);
							partical = new Rectangle2D.Float(x,y,5,5);
					}
					
					
				public void render(Graphics2D g) { 
					g.setColor(col);
					g.fill(partical);
				}
				
				public void fly() { 
					if(clock < 7500) clock++; else clock = 0;
					if(clock % 35 == 0) { 
						 life--; 
						 if(life <= 0) dead = true;
					}
					x+=xd;
					y+=yd;
					partical.setRect(x, y, 5, 5);
				}
			}
			
			private class Earth{ 
				
				private Rectangle bounds;
				
				
					public Earth() { 
						bounds = new Rectangle(0,540,800,20);
					}
					
					
				public void Earth(Graphics2D g) { 
					g.setColor(Color.GREEN);
					g.fill(bounds);
				}
				
				public boolean intersectsBomb(Bomb b) {
					if(b.bomb.intersects(bounds)) {
						  return true;
					}
					return false;
				}
				
				
				public boolean intersectsPartical(Partical p) {
					if(p.partical.intersects(bounds)) {
						  return true;
					}
					return false;
				}
			}
			
			
			public static void generateParticals(BombSlug b,int life) { 
				for(int i=0;i<AMOUNT_PARTICALS;i++) { 
					 particals.add(b.new Partical(bomb.x,bomb.y,life));
				}
			}
			
			public static void main(String[] args) { 
				Utils.windowLauncher("BombSlugDemo", new BombSlug(-60,0.005));
			}
			
			
			
		
	
			
}
