import java.util.*;

class ArrArray {
    public int stevec;
    public ArrayList <int[]> table;
    HashMap<Integer, Integer> occ = new HashMap<Integer, Integer>();

    public ArrayList <Integer> temp = new ArrayList<>();
    public boolean test = false;


    public ArrArray() {
        table = new ArrayList<>();
        int [] test = new int[1];
        table.add(test);
        stevec = 0;
    }

    public void insert(int e) {
        if(occ.containsKey(e)) {
            int x = occ.get(e);
            occ.put(e, x+1);
        }
        else{
            occ.put(e, 1);
        }

        if(find_two(e)) {
            return;
        }
        temp.add(e);

        for (int i = 0; i < table.size(); i++) {
            int[] vmesni = table.get(i);
            if (isEmpty(vmesni)) {
                if(temp.size() == table.get(i).length) {
                    for (int j = 0; j < temp.size(); j++) {
                        vmesni[j] = temp.get(j);
                    }
                    sorter(vmesni);
                    table.set(i, vmesni);
                    temp.clear();
                }
            }
        }
        if(temp.size() > 0) {
            boolean check = true;

            for (int i = 0; i < table.size(); i++) {
                if(temp.size() > 0) {
                    if(isEmpty(table.get(i))) {
                        int[] vmes = new int[(int) Math.pow(2, i)];
                        for (int j = 0; j < i; j++) {
                            for (int k = 0; k < table.get(j).length; k++) {
                                temp.add(table.get(j)[k]);
                            }
                            int[] clr = new int[(int) Math.pow(2, j)];
                            table.set(j, clr);
                        }
                        for (int g = 0; g < temp.size(); g++) {
                            vmes[g] = temp.get(g);
                        }
                        table.set(i, vmes);
                        temp.clear();
                        check = false;
                    }
                }
            }
            if(check) {
                int[] nov = new int[(int) Math.pow(2, table.size())];
                for (int i = 0; i < table.size(); i++) {
                    for (int j = 0; j < table.get(i).length; j++) {
                        temp.add(table.get(i)[j]);
                    }
                    int[] vmes = new int[(int) Math.pow(2, i)];
                    table.set(i, vmes);
                }
                for (int i = 0; i < temp.size(); i++) {
                    nov[i] = temp.get(i);
                }
                sorter(nov);
                temp.clear();
                table.add(nov);
            }
        }
    }

    public void find(int e) {
        boolean found = false;
        for (int i = 0; i < table.size(); i++) {
            if(!isEmpty(table.get(i))) {
                int min = table.get(i)[0];
                int max = table.get(i)[table.get(i).length-1];
                if(e == max || e == min) {
                    found = true;
                }
                else if(min < e && e < max) {
                    for (int j = 0; j < table.get(i).length; j++) {
                        int x = binarySearch(table.get(i), 0, table.get(i).length, e);
                        if(x != -1) {
                            found = true;
                        }
                    }
                }
            }
        }
        System.out.println(found);
    }

    public boolean find_two(int e) {
        for (int i = 0; i < table.size(); i++) {
            if(!isEmpty(table.get(i))) {
                int min = table.get(i)[0];
                int max = table.get(i)[table.get(i).length-1];
                if(e == max || e == min) {
                    return true;

                }
                else if(min < e && e < max) {
                    for (int j = 0; j < table.get(i).length; j++) {
                        int x = binarySearch(table.get(i), 0, table.get(i).length, e);
                        if(x != -1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public void delete(int e) {

        int y = occ.get(e);
        occ.put(e, y-1);

        boolean found = false;
        for (int i = 0; i < table.size(); i++) {
            if(!isEmpty(table.get(i))) {
                int [] mid = table.get(i);
                int min = table.get(i)[0];
                int max = table.get(i)[table.get(i).length-1];
                if(e == max) {
                    if (occ.get(e) == 0) {
                        mid[mid.length - 1] = -2;
                        found = true;
                    }
                }

                if(e == min) {
                    if(occ.get(e) == 0) {
                        mid[0] = -2;
                        found = true;
                    }

                }

                else if(min < e && e < max) {
                    int x = binarySearch(table.get(i), 0, table.get(i).length, e);
                    if(x != -1) {
                        if(occ.get(e) == 0) {
                            mid[x] = -2;
                            found = true;
                        }
                    }
                }
                table.set(i,mid);
            }
        }

        System.out.println(found);
    }

    /*public void delete(int e) {

        int vmes = occ[e];
        occ[e] = vmes-1;

        boolean found = false;
        for (int i = 0; i < table.size(); i++) {
            if(!isEmpty(table.get(i))) {
                int [] mid = table.get(i);
                int min = table.get(i)[0];
                int max = table.get(i)[table.get(i).length-1];
                if(e == max) {
                    if (occ[e] == 0) {
                        mid[mid.length - 1] = 0;
                    }
                    found = true;
                }

                if(e == min) {
                    if(occ[e] == 0) {
                        mid[0] = 0;
                    }
                    found = true;
                }

                else if(min < e && e < max) {
                    int x = binarySearch(table.get(i), 0, table.get(i).length, e);
                    if(x != -1) {
                        if(occ[e] == 0) {
                            mid[x] = 0;
                        }
                        found = true;
                    }
                }
                table.set(i,mid);
            }
        }

        ArrayList <Integer> temp_2 = new ArrayList<>();
        for (int i = 0; i < occ.length; i++) {
            if(occ[i] != 0) {
                occ[i] -= 1;
            }
        };

        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).length; j++) {
                if(table.get(i)[j] != 0) {
                    temp_2.add(table.get(i)[j]);
                }
            }
            int[] nov = new int[(int) Math.pow(2, i)];
            table.set(i, nov);
        }

        for (int i = 0; i < temp_2.size(); i++) {
            this.insert(temp_2.get(i));
        }

        for (int i = 0; i < table.size(); i++) {
            sorter(table.get(i));
        }

        System.out.println(found);
    } */

    public void printOut() {
        if(table.size() == 1) {
            System.out.println("empty");
            return;
        }
        for (int i = 0; i < table.size(); i++) {
            if(isEmpty(table.get(i))) {
                System.out.println("A_"+ i + ": ...");
            }
            else {
                StringBuilder out = new StringBuilder();
                out.append("A_"+ i + ": ");
                for (int j = 0; j < table.get(i).length; j++) {
                    if(table.get(i)[j] == -2) {
                        out.append("x, ");
                    }
                    else {
                        out.append(table.get(i)[j] + "/" + occ.get(table.get(i)[j]) + ", ");
                    }
                }
                out.setLength(out.length() - 1);
                out.setLength(out.length() - 1);
                System.out.println(out);
            }
        }
    }

    public boolean isEmpty(int[] arr) {
        boolean value = true;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != 0) {
                value = false;
            }
        }
        return value;
    }

    public void sorter (int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    public int binarySearch(int arr[], int l, int r, int x)
    {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            if (arr[mid] == x)
                return mid;

            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);

            return binarySearch(arr, mid + 1, r, x);
        }
        return -1;
    }
}

public class main {
    public static void main(String[] args) {

        ArrArray tbl = new ArrArray();

        tbl.insert(7);
        tbl.insert(5);
        tbl.insert(9);
        tbl.insert(3);
        tbl.insert(15);
        tbl.insert(11);
        tbl.insert(17);
        tbl.delete(15);
        tbl.insert(11);
        tbl.delete(5);
        tbl.printOut();
        tbl.find(7);
        tbl.find(5);
        tbl.find(42);
    }
}
