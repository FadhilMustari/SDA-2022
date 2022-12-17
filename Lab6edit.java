// import java.io.IOException;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.io.PrintWriter;
// import java.util.*;

// // TODO: Lengkapi class ini
// class Saham {
//     public int id;
//     public int harga;

//     public Saham(int id, int harga) {
//         this.id = id;
//         this.harga = harga;
//     }
// }

// // TODO: Lengkapi class ini
// class minHeap{
//     ArrayList<Saham> dataSaham;

//     public minHeap() {
//         this.dataSaham = new ArrayList<Saham>();
//     }

//     public int size(){
//         if (dataSaham.isEmpty()){
//             return 0;
//         } else{
//             return dataSaham.size();
//         }
//     }

//     private int parentOf(int i){
//         return (i-1) / 2;
//     }

//     private int leftChildOf(int i){
//         return 2 * i + 1;
//     }

//     private int rightChildOf(int i){
//         return 2 * (i + 1);
//     }

//     public int peek(){
//         if (dataSaham.isEmpty()){
//             return 0;
//         } else{
//             Saham obj = dataSaham.get(0);
//             return obj.harga;
//         }
//     }

//     public void insert(Saham newSaham){
//         dataSaham.add(newSaham);
//         percolateUp(dataSaham.size()-1);
//     }

//     public Saham poll(){
//         Saham result = dataSaham.get(0);
//         dataSaham.set(0, dataSaham.get(size()-1));
//         dataSaham.remove(size()-1);
//         percolateDown(0);
//         return result;
//     }

// private void percolateUp(int upThis){
//     int parent = parentOf(upThis);
//     Saham valueSaham = dataSaham.get(upThis);
//     int value = valueSaham.harga;
//     while(upThis > 0 && value < dataSaham.get(parent).harga){
//         dataSaham.set(upThis, dataSaham.get(parent));
//         upThis = parent;
//         parent = parentOf(upThis);
//     }
//     dataSaham.set(upThis, valueSaham);
// }

//     private void percolateDown(int downThis){
//         int heapSize = dataSaham.size();
//         Saham valueSaham = dataSaham.get(downThis);
//         int value = valueSaham.harga;
//         while(downThis < heapSize){
//             int childPos = leftChildOf(downThis);
//             if (childPos < heapSize){
//                 if ((rightChildOf(downThis) < heapSize) && (dataSaham.get(childPos+1).harga < dataSaham.get(childPos).harga)){
//                     childPos++;
//                 }
//                 if (dataSaham.get(childPos).harga <=  value){
//                     if (dataSaham.get(childPos).id == value){
//                         if (dataSaham.get(childPos).id < valueSaham.id){
//                             dataSaham.set(downThis, dataSaham.get(childPos));
//                         }
//                     }
//                     dataSaham.set(downThis, dataSaham.get(childPos));
//                     downThis = childPos;
//                 } else {
//                     dataSaham.set(downThis, valueSaham);
//                     return;
//                 }
//             } else {
//                 dataSaham.set(downThis, valueSaham);
//                 return;
//             }
//         }
//     }
// }

// class maxHeap {
//     ArrayList<Saham> dataSaham;

//     public maxHeap() {
//         this.dataSaham = new ArrayList<Saham>();
//     }

//     public int size(){
//         if (dataSaham.isEmpty()){
//             return 0;
//         } else{
//             return dataSaham.size();
//         }
//     }

//     private int parentOf(int i){
//         return (i-1) / 2;
//     }

//     private int leftChildOf(int i){
//         return 2 * i + 1;
//     }

//     private int rightChildOf(int i){
//         return 2 * (i + 1);
//     }

//     public int peek(){
//         if (dataSaham.isEmpty()){
//             return 0;
//         } else{
//             Saham obj = dataSaham.get(0);
//             return obj.harga;
//         }
//     }

//     public void insert(Saham newSaham){
//         dataSaham.add(newSaham);
//         percolateUp(dataSaham.size()-1);
//     }

//     public Saham poll(){
//         Saham result = dataSaham.get(0);
//         dataSaham.set(0, dataSaham.get(size()-1));
//         dataSaham.remove(size()-1);
//         percolateDown(0);
//         return result;
//     }

//     private void percolateUp(int upThis){
//         int parent = parentOf(upThis);
//         Saham valueSaham = dataSaham.get(upThis);
//         int value = valueSaham.harga;
//         while(upThis > 0 && value > dataSaham.get(parent).harga){
//             dataSaham.set(upThis, dataSaham.get(parent));
//             upThis = parent;
//             parent = parentOf(upThis);
//         }
//         dataSaham.set(upThis, valueSaham);
//     }

//     private void percolateDown(int downThis){
//         int heapSize = dataSaham.size();
//         Saham valueSaham = dataSaham.get(downThis);
//         int value = valueSaham.harga;
//         while(downThis < heapSize){
//             int childPos = leftChildOf(downThis);
//             if (childPos < heapSize){
//                 if ((rightChildOf(downThis) < heapSize) && (dataSaham.get(childPos+1).harga > dataSaham.get(childPos).harga)){
//                     childPos++;
//                 }
//                 if (dataSaham.get(childPos).harga > value){
//                     // if (dataSaham.get(childPos).harga == value){

//                     // }
//                     dataSaham.set(downThis, dataSaham.get(childPos));
//                     downThis = childPos;
//                 } else {
//                     dataSaham.set(downThis, valueSaham);
//                     return;
//                 }
//             } else {
//                 dataSaham.set(downThis, valueSaham);
//                 return;
//             }
//         }
//     }
// }

// public class Lab6edit {

//     private static InputReader in;
//     private static PrintWriter out;
//     public static ArrayList<Saham> data;

//     public static void addSaham(Saham saham, minHeap kanan, maxHeap kiri){
//         if (kiri.size() == 0 || saham.harga < kiri.peek()){
//             kiri.insert(saham);
//         } else {
//             kanan.insert(saham);
//         }
//     }

//     public static void rebalace(minHeap kanan, maxHeap kiri){
//         if (kiri.size() > kanan.size()){
//             if (kiri.size() - kanan.size() >= 2){
//                 kanan.insert(kiri.poll());
//             }
//         } else if (kanan.size() > kiri.size()){
//             if (kanan.size() - kiri.size() >= 2){
//                 kiri.insert(kanan.poll());
//             }
//         }
//     }

//     public static int getMedian(minHeap kanan, maxHeap kiri){
//         if (kiri.size() == kanan.size()){
//             return kanan.peek();
//         } else if (kiri.size() > kanan.size()){
//             return kiri.peek();
//         } else {
//             return kanan.peek();
//         }
//     }

//     public static int getMedians(ArrayList<Saham> array){
//         minHeap kanan = new minHeap();
//         maxHeap kiri = new maxHeap();
//         int[] medians = new int[array.size()];
//         for(int i = 0; i < array.size(); i++){
//             Saham saham = array.get(i);
//             addSaham(saham,kanan,kiri);
//             rebalace(kanan,kiri);
//             medians[i] = getMedian(kanan,kiri);
//         }
//         if (kanan.dataSaham.get(0).harga == medians[(medians.length)-1]){
//             return kanan.dataSaham.get(0).id;
//         } else {
//             return kiri.dataSaham.get(0).id;
//         }
//     }

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
//             Saham newSaham = new Saham(i, harga);
//             data.add(newSaham);
//         }

//         int ans = getMedians(data);

//         int Q = in.nextInt();

//         // TODO
//         for (int i = 0; i < Q; i++) {
//             String q = in.next();

//             if (q.equals("TAMBAH")) {
//                 int harga = in.nextInt();
//                 Saham newSaham = new Saham(data.size()+1, harga);
//                 data.add(newSaham);
//                 int med = getMedians(data);
//                 System.out.println(med);

//             } else if (q.equals("UBAH")) {
//                 int nomorSeri = in.nextInt();
//                 int harga = in.nextInt();
//                 data.get(nomorSeri-1).harga = harga;
//                 int med = getMedians(data);
//                 System.out.println(med);
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
