import java.util.Collections;
import java.util.Collection;

public class Arrays {
    public static void main(String[] args) {
        String[] sa = {"a", "b", "c", "d", "e"};
        Integer [] ia;
        ia = new Integer [] {1,2,3,4,5};
        // ia[5] = 6; // java.lang.ArrayIndexOutOfBoundsException
        print(sa);
        print(ia);
        Collection<String> coll = Collections.list(sa);
        String[] sa2 = coll.toArray(new String[0]);
        print(sa2);
    }

    static void print(Object[] array) {
        System.out.println();
        for (Object o : array) {
            System.out.print(o + " ");
        }
        System.out.println();
    }
}