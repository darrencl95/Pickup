package matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by zachschlesinger on 5/8/17.
 */
public class intersect {
    private static Collection out;

    // TODO: write this algorithm to account for finding overlap between specific sizes

    public static void main(String[] args) {
        ArrayList vals = new ArrayList();
        int[] arr1 = {1,2,3,4};
        int[] arr2 = {1,2,3,4};
        int[] arr3 = {1,2,3,4};
        int[] arr4 = {1,2,3,4};
        int[] arr5 = {1,2,3,4};
        vals.add(arr1);
        vals.add(arr2);
        vals.add(arr3);
        vals.add(arr4);
        vals.add(arr5);
        int i = vals.size();
        out = arrToList((int[]) vals.get(i - 1));
        helper(vals, i - 1);
        System.out.println(out.toString());
    }

    public static void helper(ArrayList vals, int num) {
        if (num == 1) {
            out = intersectMethod(arrToList((int[]) vals.get(num)), arrToList((int[]) vals.get(num-1)));
        } else {
            while (num >= 2) {
                out = intersectMethod(arrToList((int[]) vals.get(num - 1)), out);
                out = intersectMethod(arrToList((int[]) vals.get(num - 2)), out);
                num--;
            }
        }
    }

    public static <T> Collection<T> intersectMethod(Collection<? extends T> a, Collection<? extends T> b) {
        Collection<T> result = new ArrayList<T>();
        for (T t : a) {
            if (b.remove(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static List arrToList(int[] arr) {
        List list = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
