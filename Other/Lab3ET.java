package Other;
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Lab3ET {
    private static InputReader in;
    private static PrintWriter out;

    public static char[] A;
    public static int N;
    public static int[][] dpdata = new int[N+1][N];

    public static int getMaxRedVotes(int start, int end) {
        // TODO : Implementasikan solusi rekursif untuk mendapatkan skor vote maksimal
        if (start == end){
            return dpdata[start][end];
        }
        int max = 0;
        for (int i = start+1; i < end+1; i++){
            int votes = 0;
            int R = 0;
            int B = 0;
            for (int j = start; j < i; j++){
                if (A[j] == 'R'){
                    R++;
                } else {
                    B++;
                }
            }
            if (R > B){
                votes = R+B;
            } else {
                votes = 0;
            }
        
            dpdata[i][end] = getMaxRedVotes(i, end);
            int count = votes + dpdata[i][end];
            if (count > max){
                max = count;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        // Inisialisasi Array Input
        N = in.nextInt();
        A = new char[N];

        // Membaca File Input
        for (int i = 0; i < N; i++) {
            A[i] = in.nextChar();
        }

        // Run Solusi
        int solution = getMaxRedVotes(0, N - 1);
        out.print(solution);

        // Tutup OutputStream
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

        public char nextChar() {
            return next().equals("R") ? 'R' : 'B';
        }
    }
}