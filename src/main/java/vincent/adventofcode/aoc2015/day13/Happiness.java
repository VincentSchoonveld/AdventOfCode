package vincent.adventofcode.aoc2015.day13;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Happinesses {
    private final int[][] happinessArray;

    static Happinesses forInput(List<String> input) {
        int size = size(input);
        int[][] happinessArray = new int[size][size];
        for(String line : input) {
            String[] split = line.split(" ");
            int person = Integer.min(size-1, split[0].charAt(0)-'A');
            int sittingNextTo = Integer.min(size - 1, split[10].replace(".", "").charAt(0)-'A');
            int happinessValue = (split[2].equals("lose") ? -1 : 1) * Integer.parseInt(split[3]);
            happinessArray[person][sittingNextTo] = happinessValue;
        }
        return new Happinesses(happinessArray);
    }

    private static int size(List<String> input) {
        int size = 0;
        for(String s : input) {
            char c = s.charAt(0);
            if(c == 'M') {
                c = 'H';
            }
            size = Integer.max(size, c -'A'+1);
        }
        return size;
    }

    int happiness(List<Integer> ls) {
        int happiness = 0;
        for(int i=0; i<ls.size()-1; i++) {
            happiness = happiness + happinessArray[ls.get(i)][ls.get(i+1)];
            happiness = happiness + happinessArray[ls.get(i+1)][ls.get(i)];
        }
//        happiness = happiness + happinessArray[ls.get(0)][ls.get(ls.size()-1)];
//        happiness = happiness + happinessArray[ls.get(ls.size()-1)][ls.get(0)];
        return happiness;
    }
}
