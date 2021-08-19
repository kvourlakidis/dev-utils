package misc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Spies {
  public static void main(String[] args) {
    final int gridSize = 100;

    final long t1 = System.currentTimeMillis();
    final Grid sequentialGrid = new Grid(gridSize);
    sequentialGrid.fillSequentially();
    final long t2 = System.currentTimeMillis();
    System.out.println("Sequential grid:");
    System.out.println("Number of spies: " + sequentialGrid.occupiedPositions.size());
    System.out.println("Time taken: " + (t2 - t1));

    final long t3 = System.currentTimeMillis();
    final Grid randomGrid = new Grid(gridSize);
    randomGrid.fillRandomly();
    final long t4 = System.currentTimeMillis();
    System.out.println("Random grid:");
    System.out.println("Number of spies: " + randomGrid.occupiedPositions.size());
    System.out.println("Time taken: " + (t4 - t3));

    final long t5 = System.currentTimeMillis();
    final Grid betterGrid = new Grid(gridSize);
    betterGrid.addSpyRandomly();
    while (betterGrid.addSpyWithMinimumCost());
    final long t6 = System.currentTimeMillis();
    System.out.println("Better grid:");
    System.out.println("Number of spies: " + betterGrid.occupiedPositions.size());
    System.out.println("Time taken: " + (t2 - t1));
    System.arra
  }

  static class Grid {
    static final Random RANDOM = new Random();

    final int size;
    final List<Position> occupiedPositions;
    final boolean[][] exclusionsMask;

    Grid(int size) {
      this.size = size;
      occupiedPositions = new ArrayList<>();
      exclusionsMask = new boolean[size][];
      for (int i = 0; i < size; i++) {
        exclusionsMask[i] = new boolean[size];
      }
    }

    int fillSequentially() {
      while (addSpySequentially())
        ;
      return occupiedPositions.size();
    }

    boolean addSpySequentially() {
      for (int row = 0; row < size; row++) {
        for (int column = 0; column < size; column++) {
          final Position position = new Position(row, column);
          if (isAvailable(position)) {
            return addIfAvailable(position);
          }
        }
      }
      return false;
    }

    int fillRandomly() {
      while (addSpyRandomly())
        ;
      return occupiedPositions.size();
    }

    boolean addSpyRandomly() {
      final List<Position> available = new ArrayList<>();
      for (int row = 0; row < size; row++) {
        for (int col = 0; col < size; col++) {
          final Position position = new Position(row, col);
          if (isAvailable(position)) {
            available.add(position);
          }
        }
      }
      if (!available.isEmpty()) {
        final Position randomAvailable = available.get(RANDOM.nextInt(available.size()));
        return addIfAvailable(randomAvailable);
      }
      return false;
    }

    boolean addSpyWithMinimumCost() {
      int minCost = Integer.MAX_VALUE;
      Position minCostPosition = null;
      Set<Position> minCostExclusions = null;
      for (int row = 0; row < size; row++) {
        for (int col = 0; col < size; col++) {
          if (!exclusionsMask[row][col]) {
            final Position position = new Position(row, col);
            final Set<Position> exclusions = calculateExclusions(position);
            final int cost = exclusions.size();
            if (cost < minCost) {
              minCost = cost;
              minCostPosition = position;
              minCostExclusions = exclusions;
            }
          }
        }
      }
      if (minCostPosition != null) {
        applyExclusions(minCostExclusions);
        return occupiedPositions.add(minCostPosition);
      }
      return false;
    }

    boolean isAvailable(Position position) {
      if (isInsideGrid(position)) {
        return !exclusionsMask[position.row][position.col];
      }
      return false;
    }

    private boolean isInsideGrid(Position position) {
      return (position.col >= 0 && position.row >= 0 && position.col < size && position.row < size);
    }

    private boolean addIfAvailable(Position newPosition) {
      if (isAvailable(newPosition)) {
        final Set<Position> exclusions = calculateExclusions(newPosition);
        applyExclusions(exclusions);
        occupiedPositions.add(newPosition);
        return true;
      }
      return false;
    }

    private Set<Position> calculateExclusions(Position newPosition) {
      final Stream<Position> horizontals = excludeHorizontal(newPosition);
      final Stream<Position> verticals = excludeVertical(newPosition);
      final Stream<Position> diagonals = excludeDiagonals(newPosition);
      final Stream<Position> straightLines = excludeStraightLines(newPosition);
      return Stream.of(horizontals, verticals, diagonals, straightLines)
          .flatMap(stream -> stream)
          .filter(this::isAvailable)
          .collect(Collectors.toSet());
    }

    private Stream<Position> excludeHorizontal(Position newSpy) {
      final int row = newSpy.row;
      return IntStream.range(0, size).mapToObj(col -> new Position(row, col));
    }

    private Stream<Position> excludeVertical(Position newSpy) {
      final int col = newSpy.col;
      return IntStream.range(0, size).mapToObj(row -> new Position(row, col));
    }

    private Stream<Position> excludeDiagonals(Position newSpy) {
      final Stream<Position> leftDiag =
          IntStream.range(0, size)
              .mapToObj(row -> new Position(row, newSpy.col - (newSpy.row - row)));
      final Stream<Position> rightDiag =
          IntStream.range(0, size)
              .mapToObj(row -> new Position(row, newSpy.col + (newSpy.row - row)));
      return Stream.concat(leftDiag, rightDiag);
    }

    private Stream<Position> excludeStraightLines(Position newPosition) {
      return occupiedPositions.stream()
          .flatMap(
              position -> {
                final int colDiff = position.col - newPosition.col;
                final int rowDiff = position.row - newPosition.row;
                final int gcd = gcd(colDiff, rowDiff);
                final int dCol = colDiff / gcd;
                final int dRow = rowDiff / gcd;
                final int maxFactor = size / Math.max(Math.abs(dCol), Math.abs(dRow));
                final Stream<Position> a =
                    IntStream.range(1, maxFactor)
                        .mapToObj(
                            factor ->
                                new Position(
                                    newPosition.row + (factor * dRow),
                                    newPosition.col + (factor * dCol)));
                final Stream<Position> b =
                    IntStream.range(1, maxFactor)
                        .mapToObj(
                            factor ->
                                new Position(
                                    (newPosition.row - factor * dRow),
                                    newPosition.col - (factor * dCol)));
                return Stream.concat(a, b);
              });
    }

    private void applyExclusions(Collection<Position> exclusions) {
      exclusions.forEach(exclusion -> exclusionsMask[exclusion.row][exclusion.col] = true);
    }

    /** Given 2 integers, return the greatest common denominator. */
    static int gcd(int a, int b) {
      if (b == 0) {
        return a;
      }
      return gcd(b, a % b);
    }

    static final char AVAILABLE = '*';
    static final char UNAVAIlABLE = '-';
    static final char SPY = 'S';

    @Override
    public String toString() {
      char[][] printGrid = new char[size][];
      for (int i = 0; i < size; i++) {
        printGrid[i] = new char[size];
        for (int j = 0; j < size; j++) {
          if (exclusionsMask[i][j]) {
            printGrid[i][j] = UNAVAIlABLE;
          } else {
            printGrid[i][j] = AVAILABLE;
          }
        }
      }
      for (Position position : occupiedPositions) {
        printGrid[position.row][position.col] = SPY;
      }
      return Stream.of(printGrid)
          .map(row -> join(' ', row))
          .collect(Collectors.joining(System.lineSeparator()));
    }
  }

  static String join(char delimiter, char[] elements) {
    char[] array = new char[elements.length * 2 - 1];
    for (int idx = 0; idx < elements.length - 1; idx++) {
      final int newIdx = 2 * idx;
      array[newIdx] = elements[idx];
      array[newIdx + 1] = delimiter;
    }
    array[array.length - 1] = elements[elements.length - 1];
    return String.valueOf(array);
  }

  static class Position {
    final int row;
    final int col;

    Position(int row, int col) {
      this.col = col;
      this.row = row;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Position position = (Position) o;
      return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }
}
