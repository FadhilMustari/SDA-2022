// import java.io.IOException;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.io.PrintWriter;
// import java.util.*;

// class Saham {
//     public int id;
//     public int harga;

//     public Saham(int id, int harga) {
//         this.id = id;
//         this.harga = harga;
//     }
// }

// class Heap {
//     ArrayList<Saham> dataSaham;

//     public Heap(ArrayList<Saham> newData) {
//         this.dataSaham = newData;
//         sort(dataSaham);
//     }

//     public void sort(ArrayList<Saham> arr)
//     {
//         int N = arr.size();
 
//         for (int i = N / 2 - 1; i >= 0; i--)
//             heapify(arr, N, i);
 
//         for (int i = N - 1; i > 0; i--) {
//             Saham temp = arr.get(0);
//             arr.set(0, arr.get(i));
//             arr.set(i, temp);
 
//             heapify(arr, i, 0);
//         }
//     }
 
//     void heapify(ArrayList<Saham> arr, int N, int i)
//     {
//         int largest = i; 
//         int l = 2 * i + 1; 
//         int r = 2 * i + 2; 
 
//         if (l < N && arr.get(l).harga > arr.get(largest).harga)
//             largest = l;
 
//         if (r < N && arr.get(r).harga > arr.get(largest).harga)
//             largest = r;
 
//         if (largest != i) {
//             Saham swap = arr.get(i);
//             arr.set(i, arr.get(largest));
//             arr.set(largest, swap);
 
//             heapify(arr, N, largest);
//         }
//     }

//     public void add(Saham newSaham){
//         dataSaham.add(newSaham);
//         sort(dataSaham);
//         // percolateUp(dataSaham.size()-1);
//     }

//     public void ganjilgenap(){
//         if (dataSaham.size()%2 == 0){
//             getGenap();
//         } else {
//             getGanjil();
//         }
//     }

//     private void getGenap(){
//         int count = ((dataSaham.size()/2) + 1);
//         int counter = dataSaham.get(count).id;
//         System.out.println(counter);
//     }

//     private void getGanjil(){
//         int count = ((dataSaham.size() + 2 - 1)/ 2)-1;
//         int counter = dataSaham.get(count).id;
//         System.out.println(counter);
//     }

//     public void reset(int noSeri, int newHarga){
//         for (int i = 0; i < dataSaham.size(); i++) {
//             if (dataSaham.get(i).id == noSeri){
//                 dataSaham.get(i).harga = newHarga;
//                 break;
//             }
//         }
//     }

//     public void helpSort(){
//         sort(dataSaham);
//     }
// }

// public class Lab6Plus {

//     private static InputReader in;
//     private static PrintWriter out;
//     public static ArrayList<Saham> data;

//     public static void main(String[] args) {
//         InputStream inputStream = System.in;
//         in = new InputReader(inputStream);
//         OutputStream outputStream = System.out;
//         out = new PrintWriter(outputStream);

//         data = new ArrayList<Saham>();

//         int N = in.nextInt();

//         // TODO
//         for (int i = 1; i <= N; i++) {
//             int harga = in.nextInt();
//             Saham newsaham = new Saham(i, harga);
//             data.add(newsaham);
//         }

//         Heap initiate = new Heap(data);

//         int Q = in.nextInt();

//         // TODO
//         for (int i = 0; i < Q; i++) {
//             String q = in.next();

//             if (q.equals("TAMBAH")) {
//                 int harga = in.nextInt();
//                 Saham newSaham = new Saham(data.size()+1, harga);
//                 initiate.add(newSaham);
//                 initiate.ganjilgenap();

//             } else if (q.equals("UBAH")) {
//                 int nomorSeri = in.nextInt();
//                 int harga = in.nextInt();
//                 initiate.reset(nomorSeri, harga);
//                 initiate.helpSort();
//                 initiate.ganjilgenap();
//             }
//         }
//         out.flush();
//     }

//     static class InputReader {
//         public BufferedReader reader;
//         public StringTokenizer tokenizer;

//         public InputReader(InputStream stream) {
//             reader = new BufferedReader(new InputStreamReader(stream), 32768);
//             tokenizer = null;
//         }

//         public String next() {
//             while (tokenizer == null || !tokenizer.hasMoreTokens()) {
//                 try {
//                     tokenizer = new StringTokenizer(reader.readLine());
//                 } catch (IOException e) {
//                     throw new RuntimeException(e);
//                 }
//             }
//             return tokenizer.nextToken();
//         }

//         public int nextInt() {
//             return Integer.parseInt(next());
//         }

//         public long nextLong() {
//             return Long.parseLong(next());
//         }
//     }
// }