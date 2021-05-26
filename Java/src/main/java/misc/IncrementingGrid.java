package misc;

import java.util.Scanner;

public class IncrementingGrid {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Please Enter an input : ");
    final int p = input.nextInt();
    // square grid with side length 2p + 1
    final int[][] grid = initGrid(2 * p + 1);
    fillGrid(grid, p);
    printGrid(grid);
  }

  private static int[][] initGrid(int size) {
    // 2-d int array
    int[][] grid = new int[size][];
    for (int i = 0; i < size; i++) {
      grid[i] = new int[size];
    }
    return grid;
  }

  private static void fillGrid(int[][] grid, int p) {
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        grid[row][col] = calculateValue(row, col, p);
      }
    }
  }

  private static int calculateValue(int row, int col, int centre) {
    // the algorithm for calculating the value of the cell
    int dx = Math.abs(row - centre);
    int dy = Math.abs(col - centre);
    return 1 + Math.max(dx, dy);
  }

  private static void printGrid(int[][] grid) {
    for (final int[] row : grid) {
      for (int cell : row) {
        System.out.print(String.format(" %2d ", cell));
      }
      System.out.println();
    }
  }
}
