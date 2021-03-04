package com.maths;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.utils.Utils;

public class DefenseTower extends JPanel{

			
			public static final int WIDTH = 800;
			public static final int HEIGHT = 600;
			
			private static List<Hydra> hydras;
			private static List<Fire> fires;
			private static Tower tower;
		
				
				private DefenseTower() { 
					this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
					hydras = new ArrayList<Hydra>();
					fires = new ArrayList<Fire>();
					tower = new Tower(new Rectangle(400, 550, 15, 15));
					generateHydras(20);
				}
				
				private void update() { 
					for(Hydra h : hydras) { 
						h.update();
					}
					for(Fire f : fires) { 
						f.update();
					}
				}

				private void render(Graphics g) { 
					for(Hydra h : hydras) { 
						h.render(g);
					}
					Graphics2D g2 = (Graphics2D)g;
					for(Fire f : fires) { 
						f.render(g2);
					}
				}
				
				private void generateHydras(int amount) { 
					int x,y,w,h;
					for(int i=0;i<amount;i++) { 
						x = (int)(Math.random() * WIDTH);
						y = (int)(Math.random() * HEIGHT);
						w = (int)(5 + Math.random() * 30);
						h =	(int)(5 + Math.random() * 30);
						hydras.add(new Hydra(new Rectangle(x,y,w,h),Color.green));
					}
				}
				
				
				public void paint(Graphics g) { 
					g.setColor(Color.black);
					g.fillRect(0, 0, WIDTH, HEIGHT);
					tower.update();
					tower.render(g);
					update();
					render(g);
					
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					repaint();
				}

				private static class Hydra{ 
					private Rectangle bounds;
					private Color color;
					private int timer,dx,dy;
					private int resistance = 3;
					
						public Hydra(Rectangle bounds,Color c) { 
							this.bounds = bounds;
							this.color = c;
						}
						
						public void update() { 
							if(timer < 7500) timer++; else timer = 0;
							if(timer % 30 == 0) { 
								dx = (((int)(Math.random() * 4)) - 1);
								dy = (((int)(Math.random() * 4)) - 1);
							}
							collisionWorld();
							bounds.x+=dx;
							bounds.y+=dy;
						}
						
						private void collisionWorld() { 
							if(bounds.intersectsLine(0, 0, WIDTH, 0)) { 
								 dy*=-1;
							}
							else if(bounds.intersectsLine(WIDTH, 0, WIDTH, HEIGHT)) { 
								 dx*=-1;
							}
							else if(bounds.intersectsLine(0, HEIGHT, WIDTH, HEIGHT)) { 
								 dy*=-1;
							}
							else if(bounds.intersectsLine(0, 0, 0, HEIGHT)) { 
								 dx*=-1;
							}
						}
						
						public void render(Graphics g) { 
							g.setColor(color);
							g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
						}
				}
				
				
				
				private static class Tower{ 
					
					public static int RAYON_DETECTION = 300;
					
					private Rectangle bounds;
					private double angle;
					private double dx,dy;
					private Color color;
					private int indexTarget = -1;
						
						public Tower(Rectangle bounds) { 
							this.bounds = bounds;
							this.color = Color.white;
						}
						
						public void update() { 
							bounds.x+=dx;
							bounds.y+=dy;
							destroyHydra();
						}
						
						public void render(Graphics g) { 
							g.setColor(color);
							g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
						}
						
						
						private void destroyHydra() { 
							if(intrusion()) { 
								this.color = Color.red;
								shoot();
							}else {
								this.color = Color.white;
								indexTarget = -1;
							}
						}
						
						private double angleOfFire(Hydra h) { 
							double dx = h.bounds.x - bounds.x;
							double dy = h.bounds.y - bounds.y;
							return Math.atan2(dy, dx);
						}
						
						
						private void shoot() { 
							if(indexTarget!=-1) { 
								fires.add(new Fire(bounds.x + 2,bounds.y + 3,angleOfFire(hydras.get(indexTarget))));
							}
						}
						private boolean intrusion() { 
							for(int i=0;i<hydras.size();i++) { 
								System.out.println(i+"  "+(getDistance(hydras.get(i))));
								 if(getDistance(hydras.get(i)) <= RAYON_DETECTION ) { 
									   indexTarget = i;
									  return true;
								 }
							}
							return false;
						}
						
						
						private double getDistance(Hydra h) { 
							double dx = bounds.x - h.bounds.x;
							double dy = bounds.y - h.bounds.y;
							return Math.sqrt(dx * dx + dy * dy);
						}
						
				}
				
				private static class Fire{ 
					public static int SPEED = 2;
					private java.awt.geom.Rectangle2D.Float bounds;
				
					private double angle;
					private double dx,dy;
						
						public Fire(int x,int y,double angle) { 
							this.bounds = new java.awt.geom.Rectangle2D.Float(x,y,5,5);
							this.angle = angle;
						}
						
						public void update() { 
							dx = Math.cos(angle);
							dy = Math.sin(angle);
							
							bounds.x+=dx;
							bounds.y+=dy;
							
						}
						
						public void render(Graphics2D g) { 
							g.setColor(Color.blue);
							g.fill(bounds);	
							}
				}

				

				public static void main(String[] args) {
					 Utils.windowLauncher("DefenseTower",new DefenseTower());
				}



}
