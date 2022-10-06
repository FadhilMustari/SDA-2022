package Other;
public class other{
    static void sort(int a[]) throws Exception{
        for (int i = a.length-1; i>=0; i--){
            boolean swapped = false;
            for (int j = 0; j<i; j++) {
                if (a[j] > a[j+1]) {
                int T = a[j];
                a[j] = a[j+1];
                a[j+1] = T;
                swapped = true;
                }
            }
            if (!swapped)
                return;
        }
    }
    public static void main(String[] args) {
        int[] a = {2,1,40,3,43,0,-1,58,3,42,4,65};
        for (int x : a) {
            System.out.print(x + " ");
        }
        System.out.println("/n");
        try {
            sort(a);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int x : a) {
            System.out.print(x + " ");
        }
    }
}