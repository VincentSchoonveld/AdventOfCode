package vincent.adventofcode.aoc2015.day24;

import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
public class CombinationUtil {

    static List<List<Integer>> combinationsWithSizeAndSum(List<Integer> arr, int size, int sum) {
        List<List<Integer>> ls = new ArrayList<>();
        int[] data = new int[size];
        combinationUtil(arr, arr.size(), size, 0, data, 0, ls, sum, 3);
        return ls;
    }

    private static void combinationUtil(List<Integer> arr, int n, int r, int index, int[] data, int i, List<List<Integer>> ls, int sum, int numberOfPartition) {
        if (index == r && sum(data) == sum && (numberOfPartition == 1 || hasPartition(arr, data, sum, numberOfPartition))) {
            List<Integer> ls1 = new ArrayList<>();
            for (int j = 0; j < r; j++) {
                ls1.add(data[j]);
            }
            ls.add(ls1);
        } else if(index < r && i < n) {
            data[index] = arr.get(i);
            combinationUtil(arr, n, r, index + 1, data, i + 1, ls, sum, numberOfPartition);
            combinationUtil(arr, n, r, index, data, i + 1, ls, sum, numberOfPartition);
        }
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for(int i : arr)
            sum += i;
        return sum;
    }

    /*

    if (i >= n)
            return;

        // current is included, put next at next
        // location
        data[index] = arr[i];
        combinationUtil(arr, n, r, index + 1,
                data, i + 1);

        // current is excluded, replace it with
        // next (Note that i+1 is passed, but
        // index is not changed)
        combinationUtil(arr, n, r, index, data, i + 1);
     */

    private static boolean hasPartition(final List<Integer> arr, final int[] data, final int sum, final int numberOfPartition) {
        List<Integer> ls = new ArrayList<>(arr);
        for(int i : data) {
            ls.remove(new Integer(i));
        }
        for(int i=1; i<ls.size()/2; i++) {
            List<List<Integer>> ls1 = new ArrayList<>();
            int[] data1 = new int[i];
            combinationUtil(ls, ls.size(), i, 0, data1, 0, ls1, sum, numberOfPartition-1);
            if(!ls1.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
