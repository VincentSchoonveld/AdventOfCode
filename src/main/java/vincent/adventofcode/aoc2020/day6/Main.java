package vincent.adventofcode.aoc2020.day6;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static final List<String> INPUT_STRINGS = InputDataUtil.getInputStrings(Day.DAY_6);

    public static void main(String[] args) {
        System.out.println("FIRST PART");
        System.out.println(count());
        System.out.println("SECOND PART");
    }

    static int count() {
        Set<Character> set =  new HashSet<>();
        int count = 0;
        boolean hasBeenAdd = false;
        for(int i=0; i<INPUT_STRINGS.size(); i++) {
            final String s = INPUT_STRINGS.get(i);
            if(s.isEmpty()) {
                count = count + set.size();
                hasBeenAdd = false;
                set = new HashSet<>();
            } else {
                if(!hasBeenAdd) {
                    for(int j = 0; j< s.length(); j++) {
                        set.add(s.charAt(j));
                    }
                    hasBeenAdd = true;
                } else {
                    Set<Character> set1 = new HashSet<>();
                    for(int j = 0; j< s.length(); j++) {
                        set1.add(s.charAt(j));
                    }
                    set = set.stream().filter(set1::contains).collect(Collectors.toSet());
                }

            }
        }
        return count+set.size();
    }

}
