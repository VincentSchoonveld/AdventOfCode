package vincent.adventofcode.aoc2018.day7;

import lombok.AllArgsConstructor;

import java.util.Comparator;

@AllArgsConstructor
public class CharacterComparator implements Comparator<Character> {

    private Rules rules;

    @Override
    public int compare(Character o1, Character o2) {
        if(rules.getAllParents(o1).contains(o2)) {
            return 1;
        } else if(rules.getAllParents(o2).contains(o1)) {
            return -1;
        } else {
            return Character.compare(o1, o2);
        }
    }
}
