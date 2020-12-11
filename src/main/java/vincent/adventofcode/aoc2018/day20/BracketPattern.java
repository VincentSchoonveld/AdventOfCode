package vincent.adventofcode.aoc2018.day20;

import java.util.ArrayList;
import java.util.List;

class BracketPattern {

    private int index;
    private int commaIndex = 0;

    private List<Integer> indices = new ArrayList<>();

    BracketPattern(int index) {
        this.index = index;
    }

    List<Integer> getIndices() {
        do { next(); } while(!isEnd());
        return indices;
    }

    private void next() {
        handleCommas();
        index++;
    }

    private boolean isEnd() {
        return commaIndex == 0;
    }

    private void handleCommas() {
        char c = Day20.INPUT_STRING.charAt(index);
        if(c == '(') {
            commaIndex++;
        } else if(c == ')') {
            commaIndex--;
        }
        if((c == '|' || c == '(') && commaIndex == 1) {
            indices.add(index+1);
        }
    }
}
