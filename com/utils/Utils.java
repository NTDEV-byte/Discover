package com.utils;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Utils {
		
		private Utils() {
		}
		
		public static void windowLauncher(String title,JPanel panel) { 
			 	JFrame window = new JFrame(title);
			 	window.setLocationRelativeTo(null);
			 	window.setVisible(true);
			 	window.setResizable(false);
			 	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 	window.add(panel);
			 	window.pack();
		}
		
		public static void windowLauncher(String title,int width,int height,JPanel panel) { 
		 	JFrame window = new JFrame(title);
		 	window.setPreferredSize(new Dimension(width,height));
		 	window.setLocationRelativeTo(null);
		 	window.setVisible(true);
		 	window.setResizable(false);
		 	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 	window.add(panel);
		 	window.pack();
	}
		
}
