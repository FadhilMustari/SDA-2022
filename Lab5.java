import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
    int key, height;
    Node left, right;
    Stack<String> dataPlayer;
    int person;
    int parent;

    Node(int key) {
        this.key = key;
        this.height = 1;
        this.person = 1;
        this.dataPlayer = new Stack<>();
    }
}

class AVLTree {

    Node root;
    int temporaryPerson;

    void insert(int key, String name){
        root = insertNode(root, key, name);
    }
    

    void delete(Node node,int key){
        if (node.person > 1){
            node.person--;
        } else {
            deleteNode(root, key);
        }
    }

    Node search(int key){
        return searchNode(root, key);
    }

    int max(int a, int b){
        
        return (a > b) ? a : b;
    }

    Node rightRotate(Node b) {
        // TODO: implement right rotate
        Node a = b.left;
        Node c = a.right;
        a.right = b;
        b.left = c;
        b.height = max(getHeight(b.left), getHeight(b.right)) + 1;
        a.height = max(getHeight(a.left), getHeight(a.right)) + 1;

        return a;
    }

    Node leftRotate(Node a) {
        // TODO: implement left rotate
        Node b = a.right;
        Node c = b.left;
        b.left = a;
        a.right = c;
        a.height = max(getHeight(a.left), getHeight(a.right)) + 1;
        b.height = max(getHeight(b.left), getHeight(b.right)) + 1;

        return b;
    }

    Node insertNode(Node node, int key, String playerName) {
        // TODO: implement insert node
        if (node == null){
            return new Node(key);
        }
        if (key < node.key){
            node.left = insertNode(node.left, key, playerName);
        } else if (key > node.key){
            node.right = insertNode(node.right, key, playerName);
        } else {
            return node;
        }

        node.height = max(getHeight(node.left), getHeight(node.right))+1;
        int balancefactor = getBalance(node);
        if (balancefactor > 1){
            if(key < node.left.key){
                return rightRotate(node);
            } else if (key > node.left.key){
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (balancefactor < -1){
            if (key > node.right.key){
                return leftRotate(node);
            } else if (key < node.right.key){
                node.right = rightRotate(node);
                return leftRotate(node);
            }
        }
        return node;
    }

    Node minValueNode(Node node) 
    { 
        Node current = node; 
  
        /* loop down to find the leftmost leaf */
        while (current.left != null) 
        current = current.left; 
  
        return current; 
    } 

    Node deleteNode(Node root, int key) {
        // TODO: implement delete node
        if (root == null) 
            return root; 
        if (key < root.key) 
            root.left = deleteNode(root.left, key); 
        else if (key > root.key) 
            root.right = deleteNode(root.right, key); 
        else { 
            // node with only one child or no child 
            if ((root.left == null) || (root.right == null)) { 
                Node temp = null; 
                if (temp == root.left) 
                    temp = root.right; 
                else
                    temp = root.left; 
  
                // No child case 
                if (temp == null) 
                { 
                    temp = root; 
                    root = null; 
                } 
                else // One child case 
                    root = temp; // Copy the contents of 
                                // the non-empty child 
            } 
            else { 
                // node with two children: Get the inorder 
                // successor (smallest in the right subtree) 
                Node temp = minValueNode(root.right); 
  
                // Copy the inorder successor's data to this node 
                root.key = temp.key; 
  
                // Delete the inorder successor 
                root.right = deleteNode(root.right, temp.key); 
            } 
        } 
        if (root == null) 
            return root; 
        root.height = max(getHeight(root.left), getHeight(root.right)) + 1; 
        
        int balance = getBalance(root); 
        if (balance > 1 && getBalance(root.left) >= 0) 
            return rightRotate(root); 
        if (balance > 1 && getBalance(root.left) < 0) { 
            root.left = leftRotate(root.left); 
            return rightRotate(root); 
        } 
        if (balance < -1 && getBalance(root.right) <= 0) 
            return leftRotate(root); 
        if (balance < -1 && getBalance(root.right) > 0) { 
            root.right = rightRotate(root.right); 
            return leftRotate(root); 
        } 
        return root;
    }

    Node searchNode(Node node, int key){
        if (node != null){
            if (node.key == key){
                return node;
            } else {
                Node foundNode = searchNode(node.left, key);
                if (foundNode == null){
                    foundNode = searchNode(node.right, key);
                }
                return foundNode;
            }
        } else {
            return null;
        }
    }

    void findMax(Node node){
        if (node == null){
            return;
        }
        findMax(node.left);
        findMax(node.right);
        temporaryPerson += node.person;
    }

    Node lowerBound(Node node, int value) {
        // TODO: return node with the lowest key that is >= value
        return null;
    }

    Node upperBound(Node node, int value) {
        // TODO: return node with the greatest key that is <= value
        return null;
    }

    // Utility function to get height of node
    int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Utility function to get balance factor of node
    int getBalance(Node node) {
        if (node == null) {
            return 0;
        } else 
        return getHeight(node.left) - getHeight(node.right);
    }
}

public class Lab5 {

    private static InputReader in;
    static PrintWriter out;
    static AVLTree tree = new AVLTree();
    static ArrayList<Integer> dataPower;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        dataPower = new ArrayList<>();

        int numOfInitialPlayers = in.nextInt();
        for (int i = 0; i < numOfInitialPlayers; i++) {
            // TODO: process inputs
            String player = in.next();
            int power = in.nextInt();
            if (dataPower.contains(power)){
                Node theNode = tree.search(power);
                theNode.dataPlayer.push(player);
                theNode.person++;
            } else {
                tree.insert(power, player);
                Node temp = tree.search(power);
                temp.dataPlayer.push(player);
                dataPower.add(power);
            }        
        }

        int numOfQueries = in.nextInt();
        for (int i = 0; i < numOfQueries; i++) {
            String cmd = in.next();
            if (cmd.equals("MASUK")) {
                handleQueryMasuk();
            } else {
                handleQueryDuo();
            }
        }
        out.close();
    }

    static void handleQueryMasuk() {
        // TODO
        String player = in.next();
        int power = in.nextInt();
        Node theNode;
        if (dataPower.contains(power)){
            theNode = tree.search(power);
            theNode.dataPlayer.push(player);
            theNode.person++;
            
        } else {
            tree.insert(power, player);
            dataPower.add(power);
            theNode = tree.search(power);
            theNode.dataPlayer.push(player);
        }

        tree.findMax(theNode.left);
        int ans = tree.temporaryPerson;
        tree.temporaryPerson = 0;
        System.out.println(ans);
    }

    static void handleQueryDuo() {
        // TODOss
        Collections.sort(dataPower);
        int lower = in.nextInt();
        int upper = in.nextInt();
        Node low = tree.search(lower);
        Node up = tree.search(upper);
        
        int templower = 0;
        int tempupper = 0;
        Node templow;
        Node tempup;
        String playerlow;
        String playerup;
        if (lower == upper){
            Node temp = tree.search(lower);
            if (temp.person < 2){
                System.out.println(-1 + " " + -1);
            } else {
                playerlow = low.dataPlayer.pop();
                tree.delete(low, lower);
                playerup = up.dataPlayer.pop();
                tree.delete(up, upper);

                char charlow = playerlow.charAt(0);
                char charup = playerup.charAt(0);
                if (charlow < charup){
                    System.out.println(playerlow + " " + playerup);
                } else {
                    System.out.println(playerup + " " + playerlow);
                }
            }
        } else{
            if (low == null){
                for (Integer x : dataPower) {
                    if (x > lower){
                        templower = x;
                        break;
                    }
                }
                templow = tree.search(templower);
                playerlow = templow.dataPlayer.pop();
                tree.delete(templow, templower);
            }else {
                playerlow = low.dataPlayer.pop();
                tree.delete(low, lower);
            }
            if (up == null){
                Collections.sort(dataPower, Collections.reverseOrder());
                for (Integer x : dataPower) {
                    if (x < upper){
                        tempupper = x;
                        break;
                    }
                }
                tempup = tree.search(tempupper);
                playerup = tempup.dataPlayer.pop();
                tree.delete(tempup, tempupper);
            } else{
                playerup = up.dataPlayer.pop();
                tree.delete(up, upper);
            }

            char charlow = playerlow.charAt(0);
            char charup = playerup.charAt(0);
            if (charlow < charup){
                System.out.println(playerlow + " " + playerup);
            } else {
                System.out.println(playerup + " " + playerlow);
            }
        }   
    }

    // taken from https://codeforces.com/submissions/Petr
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