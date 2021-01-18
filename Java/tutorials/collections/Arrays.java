package tutorials.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.List;

public class Arrays {
    public static void main(String[] args) {
        String[] stringArray = {"a", "b", "c", "d", "e"};
        int [] intArray;
        intArray = new int [] {1,2,3,4,5};
        System.out.println(java.util.Arrays.toString(stringArray));
        System.out.println(java.util.Arrays.toString(intArray));
        final List<String> arrayToList = java.util.Arrays.asList(stringArray);
        arrayToList.add("f");
        String[] backToArray = arrayToList.toArray(new String[0]);

    }
}