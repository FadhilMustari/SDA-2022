import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Lab7 {

  private static InputReader in;
  private static PrintWriter out;

  static class pair {

    int first, second;

    pair(int f, int s) {
      this.first = f;
      this.second = s;
    }
  }

  // Stores minimum-cost of path from source
  static int minSum = Integer.MAX_VALUE;

  // Function to Perform BFS on graph g
  // starting from vertex v
  static void getMinPathSum(
    Map<Integer, ArrayList<pair>> graph,
    boolean[] visited,
    int source,
    int dest,
    int currSum
  ) {
    // If destination is reached
    if (source == dest) {
      // Set flag to true
      boolean flag = true;

      // Visit all the intermediate nodes
      // for(int i : necessary){
      //     // If any intermediate node
      //     // is not visited
      //     if (!visited[i]){
      //         flag = false;
      //         break;
      //     }
      // }

      // If all intermediate
      // nodes are visited
      if (flag) {
        // Update the minSum
        minSum = Math.min(minSum, currSum);
      }
      return;
    } else {
      // Mark the current node
      // visited
      visited[source] = true;

      // Traverse adjacent nodes
      for (pair node : graph.get(source)) {
        if (!visited[node.first]) {
          // Mark the neighbour visited
          visited[node.first] = true;

          // Find minimum cost path
          // considering the neighbour
          // as the source
          getMinPathSum(
            graph,
            visited,
            node.first,
            dest,
            currSum + node.second
          );
          // Mark the neighbour unvisited
          visited[node.first] = false;
        }
      }
      // Mark the source unvisited
      visited[source] = false;
    }
  }

  public static void main(String[] args) {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    Map<Integer, ArrayList<pair>> graph = new HashMap<>();

    int N = in.nextInt(), M = in.nextInt();

    int[] diserang = new int[M];
    boolean[] visited = new boolean[N];

    for (int i = 0; i <= N; i++) {
      // TODO: Inisialisasi setiap benteng
      graph.put(i, new ArrayList<pair>());
    }

    for (int i = 0; i < M; i++) {
      int F = in.nextInt();
      // TODO: Tandai benteng F sebagai benteng diserang
      diserang[i] = F - 1;
    }

    int E = in.nextInt();
    for (int i = 0; i < E; i++) {
      int A = in.nextInt(), B = in.nextInt(), W = in.nextInt();
      // TODO: Inisialisasi jalan berarah dari benteng A ke B dengan W musuh
      graph.get(A - 1).add(new pair(B - 1, W));
    }
    int noway = 0;
    int Q = in.nextInt();
    while (Q-- > 0) {
      int S = in.nextInt(), K = in.nextInt();
      // TODO: Implementasi query
      for (int i : diserang) {
        getMinPathSum(graph, visited, S - 1, i, 0);
        if (minSum == Integer.MAX_VALUE) {
          noway = -1;
        } else {
          if (minSum < K) {
            noway = -1;
          } else {
            System.out.println(minSum);
            noway = 1;
            break;
          }
        }
        minSum = Integer.MAX_VALUE;
      }
      if (noway == -1) {
        System.out.println("NO");
      } else if (noway == 1) {
        System.out.println("YES");
      }
    }

    out.close();
  }

  // taken from https://codeforces.com/submissions/Petr
  // together with PrintWriter, these input-output (IO) is much faster than the
  // usual Scanner(System.in) and System.out
  // please use these classes to avoid your fast algorithm gets Time Limit
  // Exceeded caused by slow input-output (IO)
  static class InputReader {

    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream), 32768);
      tokenizer = null;
    }

    public String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }
  }
}
