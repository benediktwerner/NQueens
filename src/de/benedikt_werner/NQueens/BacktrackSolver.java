package de.benedikt_werner.NQueens;

import java.awt.Point;
import java.util.LinkedList;

public class BacktrackSolver extends Solver {
	private int[][] mBoard;
	private LinkedList<Point> queens = new LinkedList<>();
	
	public static void printBoard(int[][] board) {
		for (int x = 0; x < board.length; x++) {
			String string = "";
			for (int y = 0; y < board.length; y++) {
				string += (board[x][y] == 1) ? "Q" : (board[x][y] == -1 ? "." : "_");
			}
			System.out.println(string);
		}
	}
	
	public int[][] solve(int n) {
		mBoard = new int[n][n];
		
		if (recurse(mBoard.length)) {
			return mBoard;
		}
		else {
			throw new IllegalStateException("No valid arrangement found!");
		}
	}
	
	private boolean recurse(int n) {
		if (n == 0) {
			return true;
		}
		for (int x = 0; x < mBoard.length; x++) {
			for (int y = 0; y < mBoard.length; y++) {
				if (isFreePosition(x, y)) {
					queens.addLast(new Point(x,y));
					if (recurse(n - 1)) {
						placeQueen(x, y);
						return true;
					}
					queens.removeLast();
				}
			}
		}
		return false;
	}
	
	private boolean isFreePosition(int x, int y) {
		for (Point p : queens) {
			if (x == p.x || y == p.y || (x - y) == (p.x - p.y) || (x + y) == (p.x + p.y)) {
				return false;
			}
		}
		return true;
	}
	
	private void placeQueen(int x, int y) {
		for (int i = 0; i < mBoard.length; i++) {
			mBoard[x][i] = -1;
			mBoard[i][y] = -1;
			
			int ix1 = x - y + i; 
			if (ix1 >= 0 && ix1 < mBoard.length) {
				mBoard[ix1][i] = -1;
			}
			
			int ix2 = x + y - i; 
			if (ix2 >= 0 && ix2 < mBoard.length) {
				mBoard[ix2][i] = -1;
			}
		}
		mBoard[x][y] = 1;
	}
}
