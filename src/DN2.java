class Node {
    entry key;
    Node left, right;

    public Node(entry item) {
        key = item;
        left = right = null;
    }
}

class entry  {
    int key;
    int occ;
    boolean del = false;

    public entry(int key, int occ) {
        this.key = key;
        this.occ = occ;
    }
}

class BST {
    Node root;
    int compares = 0;
    boolean sr = true;

    BST() {
        root = null;
    }

    void insert(int key) {
        if(find_t(key)) {
        }
        else {
            entry e = new entry(key, 1);
            root = recursion(root, e);
        }
    }

    Node recursion(Node root, entry key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        this.compares++;

        if (key.key < root.key.key) {
            root.left = recursion(root.left, key);
        }
        else if (key.key > root.key.key) {
            root.right = recursion(root.right, key);
        }

        return root;

    }

    boolean find_t(int key) {
        return findRec_t(this.root, key);
    }

    boolean findRec_t (Node x, int key) {
        if(x == null) {
            return false;
        }

        if(x.key.key == key) {
            x.key.occ +=1;
            return true;
        }

        if(key < x.key.key) {
            return findRec_t(x.left, key);
        }

        else {
            return findRec_t(x.right, key);
        }
    }



    boolean find(int key) {
        return findRec(this.root, key);
    }

    boolean findRec (Node x, int key) {
        if(x == null) {
            return false;
        }
        this.compares++;

        if(x.key.key == key) {
            return true;
        }

        if(key < x.key.key) {
            return findRec(x.left, key);
        }

        else {
            return findRec(x.right, key);
        }
    }

    void delete(int key) {
        root = deleteR(root, key);
    }

    Node deleteR(Node root, int key) {
        if (root == null) {
            return root;
        }
        if(!root.key.del) {
            this.compares++;
        }

        if (key < root.key.key) {
            root.left = deleteR(root.left, key);
        }
        else if (key > root.key.key) {
            root.right = deleteR(root.right, key);
        }
        else {
            if(!root.key.del) {
                root.key.occ--;
            }
            if(root.key.del || root.key.occ == 0) {
                if (root.left == null && root.right == null) {
                    return null;
                }

                if (root.left == null) {
                    return root.right;
                }
                else if (root.right == null) {
                    return root.left;
                }
                else{
                    if (sr) {
                        maxValue(root.left).del = true;
                        root.key = maxValue(root.left);
                        sr = false;
                        root.left = deleteR(root.left, root.key.key);
                        root.key.del = false;

                    } else {
                        minValue(root.right).del = true;
                        root.key = minValue(root.right);
                        sr = true;
                        root.right = deleteR(root.right, root.key.key);
                        root.key.del = false;
                    }
                }
            }
        }

        return root;
    }

    entry minValue(Node root) {
        entry min = root.key;
        while (root.left != null) {
            min = root.left.key;
            root = root.left;
        }
        return min;
    }

    entry maxValue(Node root) {
        entry max = root.key;
        while (root.right != null) {
            max = root.right.key;
            root = root.right;
        }
        return max;
    }

    void printInorder() {
        if(this.root != null) {
            inRec(this.root);
        }
    }

    void printPreorder() {
        if(this.root != null) {
            preRec(this.root);
        }
    }

    void printPostorder() {
        if(this.root != null) {
            postRec(this.root);
        }
    }

    void inRec(Node root) {
        if (root != null) {
            inRec(root.left);
            System.out.println(root.key.key);
            inRec(root.right);
        }
    }

    void preRec(Node root) {
        System.out.println(root.key.key);
        if(root.left != null) {
            preRec(root.left);
        }
        if(root.right != null) {
            preRec(root.right);
        }
    }

    void postRec(Node root) {
        if (root != null) {
            postRec(root.left);
            postRec(root.right);
            System.out.println(root.key.key);
        }
    }

    void printNodeComparisons() {
        System.out.println(this.compares);
    }
}
public class DN2 {
    public static void main(String[] args) {
        BST b = new BST();
        b.insert(29);
        b.insert(15);
        b.insert(43);
        b.insert(5);
        b.insert(23);
        b.insert(35);
        b.insert(51);
        b.insert(3);
        b.insert(7);
        b.insert(19);
        b.insert(27);
        b.insert(31);
        b.insert(37);
        b.insert(47);
        b.insert(59);
        b.delete(15);
        b.delete(43);
        b.printPreorder();
    }
}
