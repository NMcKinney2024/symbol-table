public class SymbolTable<Key extends Comparable<Key>, Value> {
    Node root;

    //Add a new node (key and value) to the Symbol Table.
    public void put(Key k, Value v) {
        root = put(root, k, v);
    }

    private Node put(Node n, Key k, Value v) {
        if(n == null) {
            return new Node(k, v);
        }

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.size++;
            n.left = put(n.left, k, v);
        }
        if (cmp > 0) {
            n.size++;
            n.right = put(n.right, k, v);
        }
        n.value = v;
        return n;
    }

    //Min = all the way to the left
    //Max = all the way to the right

    public int size() {
        return size(root);
    }

    private int size(Node n) {

    }

    //Returns the key with k things less than it.
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node n, int k) {
        if (n == null) {
            return null;
        }
        int s = size(n.left);
        if (s == k) {
            return n;
        }
        if (s > k) {
            return select(n.left, k);
        }
        return select(n.right, k-s-1);
        
    }

    class Node{
        Node left = null;
        Node right = null;
        Key key;
        Value value;
        int size = 1;
    
        public Node(Key k, Value v) {
            this.key = k;
            this.value = v;
        }
    }
}

