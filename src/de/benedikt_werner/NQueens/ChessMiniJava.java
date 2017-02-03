package de.benedikt_werner.NQueens;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessMiniJava {
	private static class Field extends JPanel {
		boolean black, queen;
		Point p;

		public Field(boolean black, boolean queen) {
			this.black = black;
			this.queen = queen;
			p = getLocation();
		}

		public void paint(Graphics g) {
			super.paint(g);
			if (black)
				g.setColor(Color.BLACK);
			else
				g.setColor(Color.WHITE);
			g.fillRect(p.getLocation().x, p.getLocation().y, getWidth() * 2, getHeight());
			g.setColor(Color.RED);

			GradientPaint gradient = new GradientPaint(0, 0, Color.YELLOW, getWidth(), 0, Color.RED);
			((Graphics2D) g).setPaint(gradient);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (queen) {
				g.fillOval((int) (getWidth() * .1), (int) (getHeight() * .7), (int) (getWidth() * .8),
						(int) (getHeight() * .2));
				int[] x = { (int) (getWidth() * .1), (int) (getWidth() * .9), (int) (getWidth() * .9),
						(int) (getWidth() * .7), (int) (getWidth() * .5), (int) (getWidth() * .3),
						(int) (getWidth() * .1) };
				int[] y = { (int) (getHeight() * .8), (int) (getHeight() * .8), (int) (getHeight() * .2),
						(int) (getHeight() * .6), (int) (getHeight() * .2), (int) (getHeight() * .6),
						(int) (getHeight() * .2) };
				g.fillPolygon(x, y, 7);
			}
		}
	}

	public static void paintField(int[][] field) {
		JFrame myFrame = new JFrame("Spielfeld");
		JPanel pan = new JPanel();
		java.util.Set<Integer> s1 = new java.util.HashSet<Integer>(), s2 = new java.util.HashSet<Integer>(),
				s3 = new java.util.HashSet<Integer>(), s4 = new java.util.HashSet<Integer>();
		pan.setLayout(new GridLayout(field.length, field.length));
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {
				pan.add(new Field((x + y) % 2 == 1, field[x][y] == 1));
				if (field[x][y] == 1) {
					s1.add(x);
					s2.add(y);
					s3.add(x + y);
					s4.add(field.length - 1 - y + x);
				}
			}
		}
		boolean sol = (s1.size() == field.length) && (field.length == s2.size()) && (field.length == s3.size())
				&& (field.length == s4.size());
		if (System.getProperty("SOL", "nowshow").equals("show")) {
			if (sol)
				pan.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN, 5));
			else
				pan.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 5));
		}
		myFrame.add(pan);
		myFrame.setSize(400, 400);
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setVisible(true);
	}
}
