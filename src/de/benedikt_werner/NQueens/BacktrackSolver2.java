package de.benedikt_werner.NQueens;

public class BacktrackSolver2 extends Solver {
	private int[][] solution;
	
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
		if (recurse(new int[n][n], n, 0)) {
			return solution;
		}
		else {
			throw new IllegalStateException("No valid arrangement found!");
		}
	}
	
	private boolean recurse(int[][] board, int n, int minX) {
		if (n == 0) {
			solution = board;
			return true;
		}
		for (int x = minX; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {
				if (board[x][y] == 0) {
					if (recurse(placeQueen(board,x, y), n-1, x+1)) return true;
				}
			}
		}
		return false;
	}
	
	private static int[][] placeQueen(int[][] board, int x, int y) {
		int[][] result = new int[board.length][board.length];
		
		for (int a = 0; a < board.length; a++) {
			for (int b = 0; b < board.length; b++) {
				if (board[a][b] == 0) {
					if (a == x || b == y || (a - b) == (x - y) || (a + b) == (x + y)) {
						result[a][b] = -1;
					}
					else {
						result[a][b] = 0;
					}
				}
				else {
					result[a][b] = board[a][b];
				}
			}
		}
		result[x][y] = 1;
		return result;
	}
}
