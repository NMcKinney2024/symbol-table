public class SymbolTable<Key extends Comparable<Key>, Value> {
    Node root;

    //Rank ~
    //Floor
    //Ceiling

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

    //Delete

    //Return the value at key k.
    public Value get(Key k) {
        return get(root, k).value;
    }

    private Node get(Node n, Key k) {
        if (n == null) {
            return null;
        }
        int cmp = k.compareTo(n.key);
        if (cmp > 0) {
            return get(n.right, k);
        }
        if (cmp < 0) {
            return get(n.left, k);
        }
        return n;
    }

    //Return if the table contains key k.
    public boolean contains(Key k) {
        return !(get(k) == null);
    }

    //Find the smallest key.
    public Key min() {
        return min(root).key;
    }
    
    private Node min(Node n) {
        if (n.left == null) {
            return n;
        }
        return min(n.left);
    }

    //Find the largest key.
    public Key max() {
        return max(root).key;
    }

    private Node max(Node n) {
        if (n.right == null) {
            return n;
        }
        return max(n.right);
    }

    public delMin() {

    }

    public delMax() {

    }
    
    //Return the number of items in the subtree rooted at key k.
    public int size(Key k) {
        return get(root, k).size;
    }

    //Returns the key with k things less than it.
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node n, int k) {
        if (n == null) {
            return null;
        }
        int s = size(n.left.key);
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

