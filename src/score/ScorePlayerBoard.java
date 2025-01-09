package score;

import fr.uge.tile.Tile;
import fr.uge.tile.TileCoord;
import fr.uge.tile.TileLandscape;
import java.util.*;

public class ScorePlayerBoard {

  public static Map<TileLandscape, Integer> findLargestLandscape(Map<TileCoord, Tile> board) {
    Set<TileCoord> visited = new HashSet<>();
    Map<TileLandscape, Integer> landscapeSize = new HashMap<>();

    for (var coord : board.keySet()) {
      if (!visited.contains(coord)) {
        // size of the connected landscape
        var size = exploreConnectedLandscape(board, coord, visited);
        var landscape = board.get(coord).landscape();
        landscapeSize.put(landscape, landscapeSize.getOrDefault(landscape, 0) + size);
      }
    }
    // find the largest landscape
    return landscapeSize;
  }

  private static int exploreConnectedLandscape(
      Map<TileCoord, Tile> board, TileCoord coord, Set<TileCoord> visited) {
    Stack<TileCoord> stack = initializeStack(coord, visited);
    TileLandscape targetLandscape = board.get(coord).landscape();
    return calculateConnectedSize(board, stack, visited, targetLandscape);
  }

  private static Stack<TileCoord> initializeStack(TileCoord coord, Set<TileCoord> visited) {
    Stack<TileCoord> stack = new Stack<>();
    stack.push(coord);
    visited.add(coord);
    return stack;
  }

  private static int calculateConnectedSize(
      Map<TileCoord, Tile> board,
      Stack<TileCoord> stack,
      Set<TileCoord> visited,
      TileLandscape targetLandscape) {
    int size = 0;
    while (!stack.isEmpty()) {
      TileCoord current = stack.pop();
      size++;
      exploreNeighbors(board, stack, visited, current, targetLandscape);
    }
    return size;
  }

  private static void exploreNeighbors(
      Map<TileCoord, Tile> board,
      Stack<TileCoord> stack,
      Set<TileCoord> visited,
      TileCoord current,
      TileLandscape targetLandscape) {
    for (TileCoord neighbor : board.get(current).neighborPosition(current)) {
      if (!visited.contains(neighbor) && board.get(neighbor).landscape() == targetLandscape) {
        stack.push(neighbor);
        visited.add(neighbor);
      }
    }
  }
}
