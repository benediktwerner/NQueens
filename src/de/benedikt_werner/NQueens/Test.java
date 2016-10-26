package de.benedikt_werner.NQueens;

public class Test {

	public static void main(String[] args) {
		//benchmark(new BacktrackSolver(), 10);
		benchmark(new BacktrackSolver2(), 16);
	}
	
	public static void benchmark(Solver s, int n) {
		long start = System.nanoTime();		
		int[][] solution = s.solve(n);
		long end = System.nanoTime();

		System.out.println("Time: " + (end-start) + "ns");
		System.out.println("Time: " + ((end-start)/1000000000.0) + "s");
		
		paintField(solution);
	}
	
	public static void paintField(int[][] field) {
		int[] b = new int[field.length];
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {
				if (field[x][y] == 1) {
					b[x] =  y;
					break;
				}
			}
		}
		ChessMiniJava.paintField(b);
	}
}
