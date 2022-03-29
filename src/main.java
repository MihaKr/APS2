import javax.print.DocFlavor;
import java.util.*;

class ArrArray {
    public int stevec;
    public ArrayList <int[]> table;
    HashMap<Integer, Integer> occ = new HashMap<>();

    public ArrayList <Integer> temp = new ArrayList<>();
    public boolean test = false;


    public ArrArray() {
        table = new ArrayList<>();
        int [] test = new int[1];
        table.add(test);
        stevec = 0;
    }

    public void insert(int e) {
        boolean check = true;
        boolean cont = false;
        int contI = -1;
        int stevec_f = 0;

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
        temp.clear();

        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).length; j++) {
                if(table.get(i)[j] == -2) {
                    cont = true;
                }
            }
        }

        temp.add(e);

        for (int i = 0; i < table.size(); i++) {
            if(isEmpty(table.get(i))) {
                check = false;
                if(temp.size() == table.get(i).length) {
                    for (int k = 0; k < temp.size(); k++) {
                        table.get(i)[k] = temp.get(k);
                    }
                    temp.clear();

                    if(i > 0 && temp.size() > 0) {
                        for (int k = i-1; k > 0 ; k--) {
                            for (int l = 0; l < table.get(k).length; l++) {
                                temp.add(table.get(l)[k]);
                            }
                            int[] nov = new int[(int) Math.pow(2, i)];
                            table.set(i, nov);
                        }
                        for (int k = table.get(i).length; k < temp.size(); k++) {
                            table.get(i)[k] = temp.get(k);
                        }
                        temp.clear();
                    }
                    sorter(table.get(i));
                    fix(stevec_f);
                    return;
                }
            }
            else {
                stevec_f++;
                for (int j = 0; j < table.get(i).length; j++) {
                    if (table.get(i)[j] != 0) {
                        temp.add(table.get(i)[j]);
                    }
                    if(table.get(i)[j] == -2) {
                        table.get(i)[j] = e;
                        temp.clear();
                        sorter(table.get(i));
                        return;
                    }
                }
                if(!cont) {
                    int[] nov = new int[(int) Math.pow(2, i)];
                    table.set(i, nov);
                    Collections.sort(temp);
                }
            }
        }

        if(check) {
            int[] nov = new int[(int) Math.pow(2, table.size())];
            for (int i = table.size()-1; i >= 0; i--) {
                for (int j = 0; j < table.get(i).length; j++) {
                    if(table.get(i)[j] != 0) {
                        temp.add(table.get(i)[j]);
                    }
                }
            }
            for (int i = 0; i < temp.size(); i++) {
                nov[i] = temp.get(i);
            }
            temp.clear();

            sorter(nov);
            table.add(nov);
        }
    }

    public void fix (int stevec) {
        for (int i = 0; i < stevec; i++) {
            int[] nov = new int[(int) Math.pow(2, i)];
            table.set(i, nov);
            Collections.sort(temp);
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
                        int x = binarySearch(table.get(i), 0, table.get(i).length-1, e);
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
                        int x = binarySearch(table.get(i), 0, table.get(i).length-1, e);
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

        if(occ.containsKey(e)) {
            int y = occ.get(e);
            occ.put(e, y - 1);
        }
        else {
            return;
        }

        boolean found = false;
        for (int i = 0; i < table.size(); i++) {
            if(!isEmpty(table.get(i))) {
                int [] mid = table.get(i);
                int min = table.get(i)[0];
                int max = table.get(i)[table.get(i).length-1];
                if(e == max) {
                    if (occ.get(e) == 0) {
                        mid[mid.length - 1] = -2;
                    }
                    found = true;
                }

                if(e == min) {
                    if(occ.get(e) == 0) {
                        mid[0] = -2;
                    }
                    found = true;
                }

                else if(min < e && e < max) {
                    int x = binarySearch(table.get(i), 0, table.get(i).length-1, e);
                    if(x != -1) {
                        if(occ.get(e) == 0) {
                            mid[x] = -2;
                        }
                        found = true;
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
        boolean empty = true;
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).length; j++) {
                if(table.get(i)[j] != -2 && table.get(i)[j] != 0) {
                    empty = false;
                }
            }
        }
        if(table.size() == 1) {
            System.out.println("empty");
            return;
        }
        if(empty) {
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
        tbl.delete(11);
        tbl.insert(22);
        tbl.insert(33);
        tbl.insert(12);
        tbl.insert(9);
        tbl.delete(7);
        tbl.insert(7);
        tbl.insert(18);
        tbl.insert(19);
        tbl.insert(99);
        tbl.insert(98);
        tbl.printOut();
        tbl.find(11);
        tbl.find(9);
        tbl.find(42);
        tbl.delete(4);
        tbl.insert(98);
        tbl.insert(4);
        tbl.delete(4);
        tbl.delete(98);
        tbl.delete(98);
        tbl.delete(7);
        tbl.delete(18);
        tbl.printOut();
    }
}
