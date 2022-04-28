class HTB {
    int p;
    int m;
    int c1;
    int c2;
    int[] arr;
    int col;

    public HTB(int p, int m, int c1, int c2) {
        this.p = p;
        this.m = m;
        this.c1 = c1;
        this.c2 = c2;
        arr = new int[m];
        col = 0;
    }

    int hash(int k) {
        return (k * p) % m;
    }

    int newHash(int k, int i) {
        return (int) ((hash(k) + c1 * i + c2 * Math.pow(i,2)) % m);
    }

    void insert(int key) {
        if(find(key)) {
            return;
        }
        int h = hash(key);
        if(arr[h] == 0) {
            arr[h] = key;
            return;
        }
        else {
            col++;
            for (int i = 1; i < arr.length; i++) {
                int h2 = newHash(key, i);
                if(h2 < arr.length) {
                    if (arr[h2] == 0) {
                        arr[h2] = key;
                        return;
                    }
                    else {
                        col++;
                    }
                }
            }
        }
        resize();
        insert(key);
    }

    boolean find(int key) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == key) {
                return true;
            }
        }
        return false;
    }

    void delete(int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                arr[i] = 0;
            }
        }
    }

    void printKeys() {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] !=0) {
                System.out.printf("%d: %d\n", i, arr[i]);
            }
        }
    }
    void printCollisions() {
        System.out.println(col);
    }

    void resize() {
        m = 2 * m + 1;
        int [] a2 = arr;
        arr = new int[m];

        for (int i = 0; i < a2.length ; i++) {
            if(a2[i] != 0) {
                insert(a2[i]);
            }
        }
    }
}

public class HTBb {
    public static void main(String[] args) {
        HTB ht = new HTB(7,3,5,7);
        ht.insert(19);
        ht.insert(11);
        ht.insert(23);
        ht.insert(19);
        ht.insert(29);
        ht.insert(17);
        ht.insert(2);
        ht.insert(33);
        ht.insert(99);
        ht.insert(129);
        ht.delete(11);
        ht.insert(11);
        ht.delete(17);
        ht.insert(17);
        ht.delete(2);
        ht.insert(2);
        ht.printKeys();
        ht.printCollisions();
    }
}


