import java.io.*;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Lab2 {
    // TODO : Silahkan menambahkan struktur data yang diperlukan
    private static InputReader in;
    private static PrintWriter out;

    static ArrayDeque<Stack> dequeToples = new ArrayDeque<>();

    static int geserKanan() {
        // TODO : Implementasi fitur geser kanan conveyor 
        Stack mainStack = dequeToples.getLast();
        dequeToples.removeLast();
        dequeToples.push(mainStack);
        return (int) mainStack.peek();
    }

    static int beliRasa(int rasa) {
        // TODO : Implementasi fitur beli rasa, manfaatkan fitur geser kanan
        int counter = 0;
        // System.out.println(dequeToples);
        // System.out.println("###################");
        for (Stack dq : dequeToples) {
            if ((int) dq.peek() == rasa){
                break;
            }
            counter += 1;
        }
        // System.out.println(counter);
    
        int count = dequeToples.size() - counter;
        // if (dequeToples.size() == counter)
        for (int i = 0; i<count;i++){
            geserKanan();
        }
        // System.out.println(dequeToples);
        // System.out.println("###################");
        dequeToples.getFirst().pop();

        if (counter == dequeToples.size()){
            return -1;
        } else {
            return counter;
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);
        
        int N = in.nextInt();
        int X = in.nextInt();
        int C = in.nextInt();
        
        for (int i = 0; i < N; ++i) {
            Stack<Integer> stackRasaKue = new Stack<>();
            // TODO: Inisiasi toples ke-i
            dequeToples.offerFirst(stackRasaKue);
            
            for (int j = 0; j < X; j++) {
                
                int rasaKeJ = in.nextInt();
                
                // TODO: Inisiasi kue ke-j ke dalam toples ke-i
                stackRasaKue.push(rasaKeJ);
            }
        }

        for (int i = 0; i < C; i++) {
            String perintah = in.next();
            if (perintah.equals("GESER_KANAN")) {
                out.println(geserKanan());
            } else if (perintah.equals("BELI_RASA")) {
                int namaRasa = in.nextInt();
                out.println(beliRasa(namaRasa));
            }
        }
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