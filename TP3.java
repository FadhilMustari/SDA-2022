import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Priorityqueue {

  static int[] H = new int[50];
  static int size = -1;

  static int parent(int i) {
    return (i - 1) / 2;
  }

  static int leftChild(int i) {
    return ((2 * i) + 1);
  }

  static int rightChild(int i) {
    return ((2 * i) + 2);
  }

  static void shiftUp(int i) {
    while (i > 0 && H[parent(i)] < H[i]) {
      swap(parent(i), i);
      i = parent(i);
    }
  }

  static void shiftDown(int i) {
    int maxIndex = i;
    int l = leftChild(i);

    if (l <= size && H[l] > H[maxIndex]) {
      maxIndex = l;
    }

    int r = rightChild(i);

    if (r <= size && H[r] > H[maxIndex]) {
      maxIndex = r;
    }

    if (i != maxIndex) {
      swap(i, maxIndex);
      shiftDown(maxIndex);
    }
  }

  static void insert(int p) {
    size = size + 1;
    H[size] = p;

    shiftUp(size);
  }

  static int extractMax() {
    int result = H[0];

    H[0] = H[size];
    size = size - 1;

    shiftDown(0);
    return result;
  }

  static void changePriority(int i, int p) {
    int oldp = H[i];
    H[i] = p;

    if (p > oldp) {
      shiftUp(i);
    } else {
      shiftDown(i);
    }
  }

  static int getMax() {
    return H[0];
  }

  static void remove(int i) {
    H[i] = getMax() + 1;

    shiftUp(i);

    extractMax();
  }

  static void swap(int i, int j) {
    int temp = H[i];
    H[i] = H[j];
    H[j] = temp;
  }
}

class Edge {

  public int From;
  public int To;
  public int L;
  public int S;

  public Edge(int from, int to, int length, int size) {
    From = from;
    To = to;
    L = length;
    S = size;
  }
}

class Vertex {

  public int id;
  public List<Edge> adj;
  public int dist;
  public int prev;

  public Vertex(int id) {
    this.id = id;
    adj = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public void addEdge(Edge edge) {
    adj.add(edge);
  }

  public List getAdj() {
    return adj;
  }
}

class Graph {

  public Vertex[] vertices;
  public int v;

  public Graph(int v) {
    this.vertices = new Vertex[v];
    this.v = v;

    for (int i = 0; i < v; i++) {
      vertices[i] = new Vertex(i);
    }
  }

  public void addEdge(int A, int B, int L, int S) {
    Edge edge = new Edge(A, B, L, S);
    vertices[A].addEdge(edge);

    edge = new Edge(B, A, L, S);
    vertices[B].addEdge(edge);
  }

  public List<Edge> getAdj(int v) {
    return vertices[v].getAdj();
  }

  public void primMST(Graph graph) {}
}

public class TP3 {

  private static InputReader in;
  private static PrintWriter out;

  static Priorityqueue pq;
  static Graph graph;

  public static void main(String[] args) {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    pq = new Priorityqueue();

    int N = in.nextInt();
    int M = in.nextInt();

    graph = new Graph(M);

    for (int x = 0; x < M; x++) {
      int A = in.nextInt();
      int B = in.nextInt();
      int L = in.nextInt();
      int S = in.nextInt();
      graph.addEdge(A, B, L, S);
    }

    int P = in.nextInt();

    for (int y = 0; y < P; y++) {
      int R = in.nextInt();
    }

    int Q = in.nextInt();

    for (int z = 0; z < Q; z++) {
      String q = in.next();

      if (q.equals("KABUR")) {
        int F = in.nextInt();
        int E = in.nextInt();
      } else if (q.equals("SIMULASI")) {} else if (q.equals("SUPER")) {
        int v1 = in.nextInt();
        int v2 = in.nextInt();
        int v3 = in.nextInt();
      }
    }
  }

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

    public char nextChar() {
      return next().equals("R") ? 'R' : 'B';
    }
  }
}
