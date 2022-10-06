import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class TP1 {
    private static InputReader in;
    private static PrintWriter out;

    public static ArrayList<Integer> harga_menu;
    public static ArrayList<String> jenis_menu;
    public static HashMap<Integer,koki> jenis_koki;
    public static List<Integer> koki_Airfood;
    public static List<Integer> koki_Seafood;
    public static List<Integer> koki_Groundfood;
    public static HashMap<Integer,pelanggan> data_pelanggan;
    public static Queue<pesanan> antrian_masak; 
    public static List<Object> answer;
    public static List<Integer> dataBlackList;

    public static class koki{
        String jenis;
        int pelayanan;

        koki(String Jenis){
            pelayanan = 0;
            jenis = Jenis;
        }

        String getjenis(){
            return jenis;
        }
    }

    public static class pelanggan{
        boolean blacklist = false;
        char status_kesehatan;
        int uang;
        int tagihan;

        pelanggan(char STATUS_KESEHATAN, int UANG){
            status_kesehatan = STATUS_KESEHATAN;
            uang = UANG;
        }
    }

    public static class pesanan{
        int id_pelanggan;
        int id_koki;
        int harga_pesanan;

        pesanan(int ID_PELANGGAN, int ID_KOKI, int HARGA_PESANAN){
            id_pelanggan = ID_PELANGGAN;
            id_koki = ID_KOKI;
            harga_pesanan = HARGA_PESANAN;
        }
    }

    public static String cekstatus(int negatif,int positif){
        String ans = "";
        if (positif > negatif){
            ans += "+";
        } else if (positif <= negatif){
            ans += "-";
        }
        return ans;
    }


    public static void main(String[] args) throws IOException  {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        harga_menu = new ArrayList<Integer>();
        jenis_menu = new ArrayList<String>();
        jenis_koki = new HashMap<Integer,koki>();
        data_pelanggan = new HashMap<Integer,pelanggan>();
        koki_Airfood = new ArrayList<Integer>();
        koki_Seafood = new ArrayList<Integer>();
        koki_Groundfood = new ArrayList<Integer>();
        antrian_masak = new ArrayDeque<pesanan>();
        dataBlackList = new ArrayList<>();
        answer = new ArrayList<>();

        int jumlah_menu = in.nextInt();
        for (int i = 0; i < jumlah_menu; i++){
            int harga = in.nextInt();
            String jenis = in.next();
            harga_menu.add(harga);
            jenis_menu.add(jenis);
        }

        int jumlah_koki = in.nextInt();
        for (int i = 0; i < jumlah_koki; i++){
            String jenis = in.next();
            koki newkoki = new koki(jenis);
            jenis_koki.put(i, newkoki);
            switch (jenis) {
                case "A":
                    koki_Airfood.add(i);
                    break;
                case "S":
                    koki_Seafood.add(i);
                    break;
                case "G":
                    koki_Groundfood.add(i);
                default:
                    break;
            }
        }

        int jumlah_pelanggan = in.nextInt();
        int jumlah_kursi = in.nextInt();
        int jumlah_hari = in.nextInt();

        while (jumlah_hari > 0){
            int pelanggan_hari_ini = in.nextInt();
            int isirestoran = 0;
            int status_negatif = 0;
            int status_positif = 0;
            for (int x = 0; x < pelanggan_hari_ini; x++){
                int id = in.nextInt();
                char kesehatan = in.next().charAt(0);
                int duit = in.nextInt();
                if (dataBlackList.contains(id)){
                    if (kesehatan == '?'){
                        int count = in.nextInt();
                    }
                    answer.add("3");
                } else {
                    pelanggan newpelanggan = new pelanggan(kesehatan, duit);
                    data_pelanggan.put(id, newpelanggan);
                    if (isirestoran == jumlah_kursi){
                        answer.add("2");
                    } else{
                        if (kesehatan == '-'){
                            answer.add("1");
                            status_negatif++;
                            isirestoran += 1;
                        } else if (kesehatan == '+'){
                            answer.add("0");
                            status_positif++;
                        } else if (kesehatan == '?'){
                            int scanning = in.nextInt();
                            String hasil_scanning = cekstatus(status_negatif, status_positif);
                            if (hasil_scanning.equals("-")){
                                status_negatif++;
                                isirestoran += 1;
                                answer.add("1");
                            } else {
                                status_positif++;
                                answer.add("0");
                            }
                        }
                    }
                }
            }

            int jumlah_query = in.nextInt();
            for (int x = 0; x < jumlah_query; x++){
                String query = in.next();
                switch (query) {
                    case "P":
                        int id_pelanggan = in.nextInt();
                        int indeks_makanan = in.nextInt();
                        int harga_pesanan = harga_menu.get(indeks_makanan-1);
                        String jenis_makanan = jenis_menu.get(indeks_makanan-1);
                        int indeks = 0;
                        if (jenis_makanan.equals("A")){
                            indeks = koki_Airfood.get(0);
                            int jmlh_pelayanan = jenis_koki.get(koki_Airfood.get(0)).pelayanan;
                            koki koki_pelayan = jenis_koki.get(koki_Airfood.get(0));
                            for (int i = 1; i < koki_Airfood.size(); i++){
                                if (jenis_koki.get(koki_Airfood.get(i)).pelayanan == jmlh_pelayanan){
                                    if (indeks > koki_Airfood.get(i)){
                                        koki_pelayan = jenis_koki.get(koki_Airfood.get(i));
                                        jmlh_pelayanan = jenis_koki.get(koki_Airfood.get(i)).pelayanan;
                                        indeks = koki_Airfood.get(i);
                                    }
                                } else if (jenis_koki.get(koki_Airfood.get(i)).pelayanan < jmlh_pelayanan){
                                        koki_pelayan = jenis_koki.get(koki_Airfood.get(i));
                                        jmlh_pelayanan = jenis_koki.get(koki_Airfood.get(i)).pelayanan;
                                        indeks = koki_Airfood.get(i);
                                }
                            }
                        } else if (jenis_makanan.equals("S")){
                            indeks = koki_Seafood.get(0);
                            int jmlh_pelayanan = jenis_koki.get(koki_Seafood.get(0)).pelayanan;
                            koki koki_pelayan = jenis_koki.get(koki_Seafood.get(0));
                            for (int i = 1; i < koki_Seafood.size(); i++){
                                if (jenis_koki.get(koki_Seafood.get(i)).pelayanan == jmlh_pelayanan){
                                    if (indeks > koki_Seafood.get(i)){
                                        koki_pelayan = jenis_koki.get(koki_Seafood.get(i));
                                        jmlh_pelayanan = jenis_koki.get(koki_Seafood.get(i)).pelayanan;
                                        indeks = koki_Seafood.get(i);
                                    }
                                } else if (jenis_koki.get(koki_Seafood.get(i)).pelayanan < jmlh_pelayanan){
                                        koki_pelayan = jenis_koki.get(koki_Seafood.get(i));
                                        jmlh_pelayanan = jenis_koki.get(koki_Seafood.get(i)).pelayanan;
                                        indeks = koki_Seafood.get(i);
                                }
                            }
                        } else {
                            indeks = koki_Groundfood.get(0);
                            int jmlh_pelayanan = jenis_koki.get(koki_Groundfood.get(0)).pelayanan;
                            koki koki_pelayan = jenis_koki.get(koki_Groundfood.get(0));
                            for (int i = 1; i < koki_Groundfood.size(); i++){
                                if (jenis_koki.get(koki_Groundfood.get(i)).pelayanan == jmlh_pelayanan){
                                    if (indeks > koki_Groundfood.get(i)){
                                        koki_pelayan = jenis_koki.get(koki_Groundfood.get(i));
                                        jmlh_pelayanan = jenis_koki.get(koki_Groundfood.get(i)).pelayanan;
                                        indeks = koki_Groundfood.get(i);
                                    }
                                } else if (jenis_koki.get(koki_Groundfood.get(i)).pelayanan < jmlh_pelayanan){
                                        koki_pelayan = jenis_koki.get(koki_Groundfood.get(i));
                                        jmlh_pelayanan = jenis_koki.get(koki_Groundfood.get(i)).pelayanan;
                                        indeks = koki_Groundfood.get(i);
                                }
                            }
                        }
                        pesanan newpesanan = new pesanan(id_pelanggan, indeks, harga_pesanan);
                        answer.add(indeks+1);
                        antrian_masak.offer(newpesanan);
                        break;
                    case "L":
                        pesanan pesanan_dimasak = antrian_masak.peek();
                        jenis_koki.get(pesanan_dimasak.id_koki).pelayanan++;
                        data_pelanggan.get(pesanan_dimasak.id_pelanggan).tagihan += pesanan_dimasak.harga_pesanan;
                        answer.add(pesanan_dimasak.id_pelanggan);
                        antrian_masak.poll();
                        break;
                    case "B":
                        int id = in.nextInt();
                        pelanggan siPelanggan = data_pelanggan.get(id);
                        if (siPelanggan.tagihan > siPelanggan.uang){
                            dataBlackList.add(id);
                            answer.add("0");
                        } else {
                            answer.add("1");
                        }
                        break;
                    case "C":
                        int q = in.nextInt();

                        break;
                    case "D":
                        int cost_A = in.nextInt();
                        int cost_G = in.nextInt();
                        int cost_S = in.nextInt();

                        break;
                    default:
                        break;
                }
            }
            for (Object x : answer) {
                System.out.print(x + " ");
            }
            jumlah_hari--;
            answer.clear();
            data_pelanggan.clear();
        }
        
        // for (int x = 1; x <= jumlah_koki; x++){
        //     System.out.println("INDEKS KE-" + x);
        //     System.out.println("Jenis koki = " + jenis_koki.get(x).jenis);
        // }

        // for (int x = 0; x<jumlah_menu;x++){
        //     System.out.println("INDEKS KE-" + x);
        //     System.out.println("Harga = " + harga_menu.get(x) + "\nJenis = " + jenis_menu.get(x));
        // }

        // for (String jenis : jenis_menu) {
        //     System.out.println(jenis);
        // }
        out.close();
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