import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Lab1 {
    private static InputReader in;
    private static PrintWriter out;

    static int getTotalDeletedLetters(int N, char[] x) {
        // TODO: implement method getTotalDeletedLetter(int, char[]) to get the answer
        String keyword = "SOFITA";
        String ans = ""; //untuk simpan kata sofita yang terbentuk
        ArrayList<Integer> indeksterpakai = new ArrayList<>(); //untuk simpen indeks huruf yang pernah dipakai
        int totalnotdeleted = 0; //total huruf yang terpakai
        int indeksmunculS = 0; //indeks s yang terpakai terakhir muncul
        int ss = 0; //untuk iterasi keyword sofita
        for(int aa = 0; aa < N && ss < 6;aa++){
            if (keyword.charAt(ss) == x[aa] && !indeksterpakai.contains(aa)){ //jika char array indeks ke-ss sesuai dengan string keyword indeks ke-ss
                ans += keyword.charAt(ss); //ans ditambahkan huruf yang sesuai dengan iterasi ke-ss string sofita
                indeksterpakai.add(aa); //memasukkan indeks huruf yang telah terpakai ke array list
                ss++;
            }
                // if (x[aa] == 'S'){ //jika char array element s masuk ke if condition
                //     indeksmunculS = aa;
                // }
            if (ans.equals(keyword)){ //jika ans sudah berisi sofita artinya kata sofita berhasil terbentuk
                totalnotdeleted += 6;// menambahkan 6 ke total huruf yang terpakai
                ans = ""; // ans direset ulang
                ss = 0;// iterasi untuk keyword direset ulang
                aa = 0; // set aa menjadi indeks terakhir s digunakan
            }
        }
        
        return N - totalnotdeleted;


        // while (aa < N && ss < 6){
        //     if (x[aa] == keyword.charAt(ss) && !indeksterpakai.contains(aa)){
        //         if (x[aa] == 'S'){
        //             indeksmunculS = aa;
        //         }
        //         indeksterpakai.add(aa);
        //         ans += keyword.charAt(ss);
        //         ss++;
        //         if (ans.equals(keyword)){
        //             totalnotdeleted += 6;
        //             ss = 0;
        //             ans = "";
        //             aa = indeksmunculS-1;
        //         }
        //     }
        //     aa++;   
        // }
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        // Read value of N
        int N = in.nextInt();

        // Read value of x
        char[] x = new char[N];
        for (int i = 0; i < N; ++i) {
            x[i] = in.next().charAt(0);
        }

        int ans = getTotalDeletedLetters(N, x);
        out.println(ans);

        // don't forget to close/flush the output
        out.close();
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
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