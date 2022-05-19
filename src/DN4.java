class binheap {

    static int []table = new int[20];
    int size = 0;
    int comp = 0;

    void insert(int p) {
        if(size == 0) {
            comp++;
        }
        table[size] = p;
        size++;

        int i = size-1;


        comp++;
        while(i != 0 && table[parent(i)] > table[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }


    void deleteMin() {
        if(size != 0) {
            System.out.println("true: " + table[0]);
        }
        else {
            System.out.println("false");
        }
        size--;

        table[0] = table[size];
        table[size] = 0;
        heapfiy(0);

    }

    void printElements() {
        if(size == 0) {
            System.out.println("empty");
        }
        else {
            for (int i = 0; i < table.length; i++) {
                if(table[i] != 0) {
                    if(i != 0) {
                        System.out.print(", ");
                    }
                    System.out.print(table[i]);
                }
                else {
                    System.out.print("");
                    System.out.println();
                    return;
                }
            }
        }
    }

    void printMin() {
        if(size != 0) {
            System.out.println("MIN: " + table[0]);
        }
        else {
            System.out.println("MIN: none");
        }
    }

    void printComparisons() {
        System.out.println("COMPARISONS: " + comp);
    }

    int leftChild(int i) {
        return ((2 * i) + 1);
    }

    int rightChild(int i) {
        return ((2 * i) + 2);
    }

    void heapfiy(int i)  {
        int l = leftChild(i);
        int r = rightChild(i);

        int min = i;

        if(l != 0 && r!= 0) {
            comp++;

            if (l < size && table[l] < table[i]) {
                min = l;
            }
            if (r < size && table[r] < table[min]) {
                min = r;
            }

            if (i == min) {
                swap(i, min);
            } else {
                comp++;
                if (min <= table[i]) {
                    swap(i, min);
                }
            }
        }
        else {
            if (l < size && table[l] < table[i]) {
                min = l;
            }
            if (r < size && table[r] < table[min]) {
                min = r;
            }
            if (min < table[i]) {
                swap(i, min);
            }
        }
    }

     static void swap(int i, int j) {
        int temp= table[i];
        table[i] = table[j];
        table[j] = temp;
    }

    static int parent(int i) {
        int x = (i - 1) / 2;
        return x;
    }
}

public class DN4 {
    public static void main(String[] args) {
        binheap bh = new binheap();

        bh.printElements();
        bh.printMin();
        bh.printComparisons();
        bh.insert(30); bh.insert(20); bh.insert(10);
        bh.insert(7); bh.insert(25); bh.insert(5); bh.insert(15);
        bh.printElements();
        bh.printMin();
        bh.printComparisons();
        bh.deleteMin();
        bh.printElements();
        bh.printComparisons();
        bh.deleteMin();
        bh.printElements();
        bh.deleteMin();
        bh.printElements();
        bh.deleteMin(); bh.deleteMin(); bh.deleteMin();
        bh.printElements();
        bh.deleteMin();
        bh.printElements();
        bh.printComparisons();
    }
}
