package vincent.adventofcode.aoc2015.day21;

import lombok.experimental.UtilityClass;
import vincent.adventofcode.util.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class CombinationUtil {
    static List<List<Item>> subsetSets(List<Item> ls, int minSize, int maxSizeInclusive) {
        List<List<Item>> lists = new ArrayList<>();
        for(int i=minSize; i<=maxSizeInclusive; i++) {
            lists.addAll(combinationUtil(ls, i));
        }
        return lists;
    }

    private static List<List<Item>> combinationUtil(List<Item> arr, int number) {
        if(number == 0) {
            return Collections.singletonList(Collections.emptyList());
        } else if(number == 1) {
            List<List<Item>> ls = new ArrayList<>();
            arr.forEach(item -> ls.add(Collections.singletonList(item)));
            return ls;
        } else if(number == 2) {
            List<List<Item>> ls = new ArrayList<>();
            for(int i=0; i<arr.size(); i++) {
                for(int j=i+1; j<arr.size(); j++) {
                    ls.add(Arrays.asList(arr.get(i), arr.get(j)));
                }
            }
            return ls;
        } else {
            throw new NotImplementedException();
        }
    }
}
