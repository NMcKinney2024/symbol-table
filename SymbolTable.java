import java.util.ArrayList;
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
        }else if (cmp > 0) {
            n.size++;
            n.right = put(n.right, k, v);
        } else{
            n.value = v;
        }
        return n;
    }

    //Remove key k from the symbol table.    
    public void delete(Key k) {
        root = delete(root, k);
    }

    //Returns the root of the tree rooted at node with key k deleted.
    private Node delete(Node n, Key k) {
        if (n == null) {
            return null;
        }
        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = delete(n.left, k);
        } else if (cmp > 0) {
            n.right = delete(n.right, k);
        } else {
            if (n.right == null) {
                return n.left;
            }
            if (n.left == null) {
                return n.right;
            }
            Node t = n;
            n = min(t.right);
            n.right = delMin(t.right);
            n.left = t.left;
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

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

    //Delete the minimum.
    public void delMin() {
        root = delMin(root);
    }

    private Node delMin(Node n) {
        if (n == null) {
            return null;
        }
        if (n.left == null) {
            return n.right;
        }
        n.left = delMin(n.left);
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    //Delete the maximum.
    public void delMax() {
        root = delMax(root);
    }
    
    private Node delMax(Node n) {
        if (n == null) {
            return null;
        }
        if (n.right == null) {
            return n.left;
        }
        n.right = delMax(n.right);
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }
    
    //Return the number of items in the table.
    public int size() {
        return size(root);
    }

    //Get the size of the subtree rooted at node n.
    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    //Returns the key with k things less than it.
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node n, int k) {
        if (n == null) {
            return null;
        }
        int s = n.left.size;
        if (s == k) {
            return n;
        }
        if (s > k) {
            return select(n.left, k);
        }
        return select(n.right, k-s-1);
        
    }

    public Iterable<Key> keys() {
        return keys(this.min(), this.max());
    }

    public Iterable<Key> keys(Key min, Key max) {
        ArrayList<Key> a = new ArrayList<Key>();
        keys(root, a, min, max);
        return a;
    }

    private void keys(Node x, ArrayList<Key> a, Key min, Key max) {
        if (x == null) {
            return;
        }
        int cmpmin = min.compareTo(x.key);
        int cmpmax = max.compareTo(x.key);
        if (cmpmin < 0) {
            keys(x.left, a, min, max);
        }
        if (cmpmin <= 0 && cmpmax >= 0) {
            a.add(x.key);
        }
        if (cmpmax > 0) {
            keys(x.right, a, min, max);
        }
    }

    private class Node{
        Node left;
        Node right;
        Key key;
        Value value;
        int size;
    
        public Node(Key k, Value v) {
            this.key = k;
            this.value = v;
            left = null;
            right = null;
            size = 1;
        }
    }
}

