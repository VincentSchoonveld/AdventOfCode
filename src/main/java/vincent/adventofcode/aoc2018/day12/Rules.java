package vincent.adventofcode.aoc2018.day12;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Rules {

    private Map<State, Boolean> map;

    static Rules forInput(List<String> input) {
        Map<State, Boolean> map = new HashMap<>();
        for(String s:input) {
            if(s.contains(" => ")) {
                String[] s1 = s.split(" => ");
                map.put(new State(s1[0]), s1[1].charAt(0)=='#');
            }
        }
        return new Rules(map);
    }

    boolean next(boolean leftleft, boolean left, boolean self, boolean right, boolean rightright) {
        return map.getOrDefault(new State(leftleft, left, self, right, rightright), false);
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    private static class State {
        private boolean leftleft;
        private boolean left;
        private boolean self;
        private boolean right;
        private boolean rightright;

        State(String input) {
            leftleft = input.charAt(0) == '#';
            left = input.charAt(1) == '#';
            self = input.charAt(2) == '#';
            right = input.charAt(3) == '#';
            rightright = input.charAt(4) == '#';
        }
    }
}
