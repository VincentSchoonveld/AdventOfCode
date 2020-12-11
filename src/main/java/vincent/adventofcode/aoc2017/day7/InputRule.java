package vincent.adventofcode.aoc2017.day7;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class InputRule {

    private String from;
    private List<String> to;
    private int weight;

    static InputRule forInput(String input) {
        String[] s = input.split(" ");
        String from = s[0];
        int weight = Integer.parseInt(s[1].substring(1, s[1].length()-1));
        List<String> to = new ArrayList<>();
        for(int i=3; i<s.length; i++) {
            to.add(removeComma(s[i]));
        }
        return new InputRule(from, to, weight);
    }

    private static String removeComma(String s) {
        if(s.charAt(s.length()-1) == ',') {
            return s.substring(0, s.length()-1);
        } else {
            return s;
        }
    }
}
