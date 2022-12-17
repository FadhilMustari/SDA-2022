import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

// TODO: Lengkapi Class Lantai
class Lantai{
    int item;
    Lantai previous;
    Lantai next;

    public Lantai(){

    }

    public Lantai(int item){
        this.item = item;
    }

    Lantai head,tail;

    public void add(int item){
        Lantai newnode = new Lantai(item);
        if (head == null){
            head = tail = newnode;
            head.previous = null;
            tail.next = null;
        } else {
            tail.next = newnode;
            newnode.previous = tail;
            tail = newnode;
            tail.next = null;
        }
    }

    public void addFront(){
        Lantai newnode = new Lantai(1);
        newnode.next = head;
    }

    public void insert(Lantai prevlantai, int data){
        Lantai newlantai = new Lantai(data);
        newlantai.next = prevlantai.next;
        prevlantai.next = newlantai;
        newlantai.previous = prevlantai;
    }

    public Iterator<Lantai> Iterator() {
        return null;
    }
}

// TODO: Lengkapi Class Gedung
class Gedung{
    String nama;
    Lantai lantai;
    Gedung next;

    public Gedung(){

    }

    public Gedung(String namaGedung, int jumlahLantai) {
        this.nama = namaGedung;
        buatLantai(jumlahLantai);
    }

    public Gedung head,tail = null;

    public void buatLantai(int jumlahLantai){
        this.lantai = new Lantai();
        for (int x = 1; x < jumlahLantai+1; x++){
            lantai.add(x);
        }
        
    }

    public void gedungAddFront(){
        lantai.addFront();
    }

    public void gedunginsert(Lantai prevlantai, int data){
        lantai.insert(prevlantai,data);
    }

    public void add(String namaGedung, int jumlahLantai){
        Gedung newGedung = new Gedung(namaGedung, jumlahLantai);
        if (head == null){
            head = newGedung;
            tail = newGedung;
            newGedung.next = head;
        } else {
            tail.next = newGedung;
            tail = newGedung;
            tail.next = head;
        }
    }

    public Iterator<Gedung> iterator() {
        return null;
    }
}

// TODO: Lengkapi Class Karakter
class Karakter {
    String posisiGedung;
    int posisiLantai;
    String posisi;

    public Karakter(String gedung,int lantai, String posisi) {
        this.posisiGedung = gedung;
        this.posisiLantai = lantai;
        this.posisi = posisi;
    }

}

public class Lab4 {

    private static InputReader in;
    static PrintWriter out;

    public static Gedung listGedung;
    public static Karakter denji;
    public static Karakter iblis;
    public static int pertemuan;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);
        
        listGedung = new Gedung();

        int jumlahGedung = in.nextInt();
        for (int i = 0; i < jumlahGedung; i++) {
            String namaGedung = in.next();
            int jumlahLantai = in.nextInt();

            // TODO: Inisiasi gedung pada kondisi awal
            listGedung.add(namaGedung, jumlahLantai);
        }

        String gedungDenji = in.next();
        int lantaiDenji = in.nextInt();
        // TODO: Tetapkan kondisi awal Denji
        assignDenji(gedungDenji, lantaiDenji);

        String gedungIblis = in.next();
        int lantaiIblis = in.nextInt();
        // TODO: Tetapkan kondisi awal Iblis
        assignIblis(gedungIblis, lantaiIblis);

        int Q = in.nextInt();

        for (int i = 0; i < Q; i++) {

            String command = in.next();

            if (command.equals("GERAK")) {
                gerak();
                System.out.println(denji.posisiGedung + " " + denji.posisiLantai + " " + iblis.posisiGedung + " " + iblis.posisiLantai + " " + pertemuan);
            } else if (command.equals("HANCUR")) {
                hancur();
            } else if (command.equals("TAMBAH")) {
                tambah();
            } else if (command.equals("PINDAH")) {
                pindah();
            }
        }

        out.close();
    }

    static void assignDenji(String gedung, int lantai){
        denji = new Karakter(gedung, lantai, "UP");
    }

    static void assignIblis(String gedung, int lantai){
        iblis = new Karakter(gedung, lantai, "DOWN");
    }

    // TODO: Implemen perintah GERAK
    static void gerak() {
        
        //Gerak denji
        Gedung temp = listGedung.head;
        while (!temp.nama.equals(denji.posisiGedung)){
            temp = listGedung.head.next;
        }
        if ((denji.posisiLantai ==  temp.lantai.tail.item) && denji.posisi.equals("UP")){
            denji.posisiGedung = temp.next.nama;
            denji.posisiLantai = temp.next.lantai.tail.item;
            denji.posisi = "DOWN";
        } else if ((denji.posisiLantai == temp.lantai.head.item) && denji.posisi.equals("DOWN")){
            denji.posisiGedung = temp.next.nama;
            denji.posisiLantai = temp.next.lantai.head.item;
            denji.posisi = "UP";
        } else {
            Lantai counter = temp.lantai.head;
            while (!(counter.item == denji.posisiLantai)){
                counter = counter.next;
            }
            if (denji.posisi.equals("UP")){
                denji.posisiLantai = counter.next.item;
            } else {
                denji.posisiLantai = counter.previous.item;
            }
        }

        if (denji.posisiGedung.equals(iblis.posisiGedung)){
            if (denji.posisiLantai == iblis.posisiLantai){
                pertemuan += 1;
            }
        }
        //Gerak iblis
        Gedung x = listGedung.head;
        while (!x.nama.equals(iblis.posisiGedung)){
            x = listGedung.head.next;
        }
        if ((iblis.posisiLantai ==  x.lantai.tail.item) && iblis.posisi.equals("UP")){
            iblis.posisiGedung = x.next.nama;
            iblis.posisiLantai = x.next.lantai.tail.previous.item;
            iblis.posisi = "DOWN";
        } else if ((iblis.posisiLantai == x.lantai.head.item) && iblis.posisi.equals("DOWN")){
            iblis.posisiGedung = x.next.nama;
            iblis.posisiLantai = x.next.lantai.head.next.item;
            iblis.posisi = "UP";
        } else {
            Lantai counter = x.lantai.head;
            while (!(counter.item == iblis.posisiLantai)){
                counter = counter.next;
            }
            if (iblis.posisi.equals("UP")){
                if ((x.lantai.tail.item - iblis.posisiLantai) == 1){
                    iblis.posisiGedung = x.next.nama;
                    iblis.posisiLantai = x.next.lantai.tail.item;
                    iblis.posisi = "DOWN";
                } else {
                    iblis.posisiLantai = counter.previous.previous.item;
                }
            } else if (iblis.posisi.equals("DOWN")){
                if ((x.lantai.head.item - iblis.posisiLantai) == -1){
                    iblis.posisiGedung = x.next.nama;
                    iblis.posisiLantai = x.next.lantai.head.item;
                    iblis.posisi = "UP";
                } else {
                    iblis.posisiLantai = counter.next.next.item;
                }
            }
        }

        if (iblis.posisiGedung.equals(denji.posisiGedung)){
            if (iblis.posisiLantai == denji.posisiLantai){
                pertemuan += 1;
            }
        }

        // System.out.println("Gedung temp = " + temp.nama);
        // System.out.println("Denji di gedung = " + denji.posisiGedung);
        // System.out.println("Denji di lantai = " + denji.posisiLantai);

    }

    // TODO: Implemen perintah HANCUR
    static void hancur() {
        if (denji.posisiLantai == 1){
            System.out.println(denji.posisiGedung + " " + -1);
        } else if(denji.posisiGedung.equals(iblis.posisiGedung) && (denji.posisiLantai-1) == iblis.posisiLantai){
            System.out.println(denji.posisiGedung + " " + -1); 
        } else {
            Gedung temp = listGedung.head;
            while (!(temp.nama.equals(denji.posisiGedung))){
                temp = temp.next;
            }
            int target = denji.posisiLantai-1;
            Lantai templantai = temp.lantai.head;
            if (templantai.item == target){
                templantai = templantai.next;
                System.out.println(temp.nama + " " + target);
            } else {
                while (!(templantai.item == denji.posisiLantai-1)){
                    templantai = templantai.next;
                }
                templantai.next.previous = templantai.previous;
                templantai.previous.next = templantai.next;
                System.out.println(temp.nama + " " + target);
            }
            Lantai counter = temp.lantai.head;
            // while (!(counter.item == temp.lantai.tail.item)){
            //     counter.item = count;
            //     counter = counter.next;
            //     count++;
            // }

            for (int x = counter.item; x < temp.lantai.tail.item-1;x++){
                counter.next.item = x+1;
            }
            denji.posisiLantai -= 1;

            
        }
    }

    // TODO: Implemen perintah TAMBAH
    static void tambah() {
        Gedung temp = listGedung.head;
        while (!(temp.nama.equals(iblis.posisiGedung))){
            temp = temp.next;
        }
        int target = iblis.posisiLantai-1;
        if (target == 1){
            temp.gedungAddFront();
        }
        Lantai templantai = temp.lantai.head;
        while (!(templantai.item == target)){
            templantai = templantai.next;
        }
        temp.gedunginsert(templantai, target);
        Lantai counter = temp.lantai.head;
        for (int x = counter.item; x < temp.lantai.tail.item;x++){
            counter.next.item = x;
        }


    }

    // TODO: Implemen perintah PINDAH
    static void pindah() {

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
    }
}