public class SymbolTable<Key, Value> {
    Node root;

    Void put(Key k, Value v) {
        root = put(root, k, v);
    }

    Node put(Node x, Key k, Value v) {
        if (x == null) {
            return new Node<Key, Value>(k, v);
        }
        int cmp = k.compareTo(x.key);
        if (cmp > 0) {
            x.right = put(x.right, k, v);
        }
        if (cmp < 0) {
            x.left = put(x.left, k, v);
        }
        return x;
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
}

class Node<Key, Value> {
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