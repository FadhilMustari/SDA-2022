import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

class Mesin {

  int id;
  int total_skor;
  ArrayList<Integer> skor;
  Mesin next;
  Mesin prev;

  public Mesin(int id) {
    this.id = id;
  }

  public Mesin(int id, ArrayList<Integer> skor, int totalskor) {
    this.id = id;
    this.skor = skor;
    this.total_skor = totalskor;
    mergeSort(skor, 0, skor.size() - 1);
  }

  public void addSkor(int newskor) {
    skor.add(newskor);
    total_skor += newskor;
    mergeSort(skor, 0, skor.size() - 1);
  }

  public void removeAllSkor() {
    skor.clear();
  }

  public int cekRentang(int L, int H) { // L nilai bawah, H nilai atas
    int nilai_L = -1;
    int nilai_H = -1;
    for (int i = skor.size() - 1; i >= 0; i--) { // iterasi dari kanan alias terkecil
      if (skor.get(i) > H) {
        break;
      } else if (skor.get(i) >= L) {
        nilai_L = i;
        break;
      }
    }
    for (int i = 0; i < skor.size(); i++) { // iterasi dari kiri alias terbesar
      if (skor.get(i) < L) {
        break;
      } else if (skor.get(i) <= H) {
        nilai_H = i;
        break;
      }
    }
    if (nilai_L < 0 || nilai_H < 0) {
      return 0;
    } else {
      return (nilai_L - nilai_H) + 1;
    }
  }

  public void merge(
    ArrayList<Integer> array,
    int lower,
    int middle,
    int upper
  ) {
    int i, j, k;
    ArrayList<Integer> left = new ArrayList<>();
    ArrayList<Integer> right = new ArrayList<>();
    for (i = 0; i < middle - lower + 1; i++) left.add(array.get(lower + i));
    for (j = 0; j < upper - middle; j++) right.add(array.get(middle + 1 + j));
    i = 0;
    j = 0;
    k = lower;
    for (k = lower; i < middle - lower + 1 && j < upper - middle; k++) {
      if (left.get(i) >= right.get(j)) array.set(
        k,
        left.get(i++)
      ); else array.set(k, right.get(j++));
    }
    while (i < middle - lower + 1) array.set(k++, left.get(i++));
    while (j < upper - middle) array.set(k++, right.get(j++));
  }

  public void mergeSort(ArrayList<Integer> array, int lower, int upper) {
    if (lower >= upper) return;
    mergeSort(array, lower, (lower + upper) / 2);
    mergeSort(array, ((lower + upper) / 2) + 1, upper);
    merge(array, lower, (lower + upper) / 2, upper);
  }

  public int deleteSkor(int X) {
    int total = 0;
    for (int i = 0; i < X; i++) {
      total += skor.get(0);
      total_skor -= skor.get(0);
      skor.remove(0);
    }
    return total;
  }
}

class CDLL {

  Mesin head;
  Mesin tail;
  int sizeCDLL;
  Mesin H;
  Mesin T;

  public CDLL() {
    head = null;
    tail = null;
    head = tail;
  }

  public void insert(Mesin newMesin) {
    //Kalau CDLL masih kosong
    if (head == null) {
      newMesin.next = newMesin.prev = newMesin;
      head = newMesin;
      tail = head;
    } else {
      //Insert dibelakang
      newMesin.prev = tail;
      tail.next = newMesin;
      head.prev = newMesin;
      newMesin.next = head;
      tail = newMesin;
    }
    sizeCDLL++;
  }

  public void removeMesin(Mesin value) {
    if (value == head) {
      head = head.next;
      tail.next = head;
      head.prev = tail;
    } else if (value == tail) {
      tail = tail.prev;
      tail.next = head;
      head.prev = tail;
    } else {
      Mesin tempPrev = value.prev;
      Mesin tempNext = value.next;
      tempPrev.next = tempNext;
      tempNext.prev = tempPrev;
    }
  }

  Mesin merge(Mesin first, Mesin second) {
    //LL kosong
    if (first == null) {
      return second;
    }

    // LL sec kosong
    if (second == null) {
      return first;
    }

    if (first.skor.size() >= second.skor.size()) {
      if (first.id < second.id) {
        first.next = merge(first.next, second);
        first.next.prev = first;
        return first;
      } else {
        second.next = merge(first, second.next);
        second.next.prev = second;
        second.prev = null;
        return second;
      }
    } else {
      if (first.prev == T) {
        second.next = merge(first, second.next);
        second.prev = T;
        T.next = second;
        H = second;
        second.next.prev = second;
        return second;
      } else {
        second.next = merge(first, second.next);
        second.next.prev = second;
        second.prev = null;
        return second;
      }
    }
  }

  Mesin split(Mesin head) {
    Mesin fast = head, slow = head;
    while (
      (fast.next != null && fast.next != H) &&
      (fast.next.next != null && fast.next.next != H)
    ) {
      fast = fast.next.next;
      slow = slow.next;
    }
    Mesin temp = slow.next;
    slow.next = null;
    return temp;
  }

  Mesin mergeSort(Mesin node) {
    if (node == null || (node.next == null || node.next == T)) {
      return node;
    }
    Mesin second = split(node);

    node = mergeSort(node);
    second = mergeSort(second);

    return merge(node, second);
  }

  public int traverseUrutasn(Mesin siHead, int id) {
    int ans = 0;
    if (siHead.id == id) {
      ans = 1;
    } else {
      while (siHead.next != head) {
        if (siHead.id == id) {
          ans += 1;
          break;
        }
        siHead = siHead.next;
        ans++;
      }
    }
    return ans;
  }
}

public class TP2 {

  private static InputReader in;
  private static PrintWriter out;
  public static CDLL node;

  public static void main(String[] args) {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    Mesin CurrentPos;

    node = new CDLL();

    int N = in.nextInt();

    //Minta input skor
    for (int i = 1; i <= N; i++) {
      int M = in.nextInt();
      ArrayList<Integer> temp = new ArrayList<>();
      int total_skor = 0;
      for (int y = 0; y < M; y++) {
        int Z = in.nextInt();
        total_skor += Z;
        temp.add(Z);
      }
      Mesin newmesin = new Mesin(i, temp, total_skor);
      node.insert(newmesin);
    }
    CurrentPos = node.head;
    int Q = in.nextInt();

    for (int i = 0; i < Q; i++) {
      String q = in.next();

      if (q.equals("MAIN")) {
        int newSkor = in.nextInt();
        CurrentPos.addSkor(newSkor);
        System.out.println(CurrentPos.skor.indexOf(newSkor) + 1);
      } else if (q.equals("GERAK")) {
        String arah = in.next();
        if (arah.equals("KIRI")) {
          CurrentPos = CurrentPos.prev;
          System.out.println(CurrentPos.id);
        } else if (arah.equals("KANAN")) {
          CurrentPos = CurrentPos.next;
          System.out.println(CurrentPos.id);
        }
      } else if (q.equals("HAPUS")) {
        int X = in.nextInt();
        if (CurrentPos.skor.size() <= X) { //Harus delete node
          if (CurrentPos.skor.size() == 0) {
            System.out.println(0);
          } else if (CurrentPos == node.tail) { // kalo node paling kanan
            if (node.sizeCDLL == 1) { // kalo CDLL cmn 1
              System.out.println(CurrentPos.total_skor);
              CurrentPos.removeAllSkor();
            } else { // kalo node paling kanan
              System.out.println(CurrentPos.total_skor);
              CurrentPos.removeAllSkor();
              CurrentPos = CurrentPos.next;
            }
          } else {
            Mesin temp = CurrentPos;
            int idTemp = CurrentPos.id;
            CurrentPos = CurrentPos.next;
            System.out.println(temp.deleteSkor(temp.skor.size()));
            node.removeMesin(temp);
            node.insert(new Mesin(idTemp));
          }
        } else {
          int ans = CurrentPos.deleteSkor(X);
          System.out.println(ans);
        }
      } else if (q.equals("LIHAT")) {
        int L = in.nextInt();
        int H = in.nextInt();
        System.out.println(CurrentPos.cekRentang(L, H));
      } else if (q.equals("EVALUASI")) {
        node.H = node.head;
        node.T = node.tail;
        node.head = node.mergeSort(node.head);
        System.out.println(node.traverseUrutasn(node.head, CurrentPos.id));
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

    public long nextLong() {
      return Long.parseLong(next());
    }
  }
}
