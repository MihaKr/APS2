class binheap {

    static int []table = new int[50];
    int size = -1;



    void insert(int key) {
        size++;
        table[size] = key;

        shiftDown(size);

    }
    void deleteMin() {

    }

    void printElements() {
        int min = 100000;
        int minI = 0;

        for (int i = 0; i < table.length; i++) {
            if(table[i] > min) {
                min = table[i];
                minI = i;
            }
        }


        if(size == -1) {
            System.out.println("empty");
        }
        else {
            preRec(minI);
        }
    }

    void preRec(int root) {
        System.out.println(table[root]);
        if(table[left(root)] != 0) {
            preRec(left(root));
        }
        if(table[right(root)] != 0) {
            preRec(right(root));
        }
    }


    void printMin() {

    }
    void printComparisons() {

    }

    static void shiftUp(int i) {
        if(i > 0) {
            while (table[parent(i)] < table[i]) {
                swap(parent(i),i);
                i = parent(i);
            }
        }
    }

    void shiftDown(int i) {
        int maxIndex = i;

        int l = left(i);

        if(l <= size) {
            while (table[maxIndex] < table[l]) {
                maxIndex = l;
            }
        }

        int r = right(i);

        if(r <= size) {
            while (table[maxIndex] < table[r]) {
                maxIndex = r;
            }
        }

        if (i != maxIndex) {
            swap(i, maxIndex);
            shiftDown(maxIndex);
        }
    }

     static void swap(int i, int j) {
        int temp= table[i];
        table[i] = table[j];
        table[j] = temp;
    }

    static int parent(int i) {
        return (i - 1) / 2;
    }


    static int left(int i) {
        return ((2 * i) + 1);
    }


    static int right(int i) {
        return ((2 * i) + 2);
    }

}

public class DN4 {
    public static void main(String[] args) {
        binheap bh = new binheap();

        bh.insert(10);
        bh.insert(20);
        bh.insert(30);
        bh.insert(5);
        bh.insert(15);
        bh.insert(7);
        bh.insert(25);
        bh.printElements();

    }
}
