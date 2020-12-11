package vincent.adventofcode.aoc2017.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class Jumps {
    private final List<Integer> offSets;
    private final int size;
    private int index;
    private int steps;

    Jumps(List<Integer> offSets) {
        this.offSets = new ArrayList<>(offSets);
        this.size = offSets.size();
        this.index = 0;
        this.steps = 0;
    }

    int escapeMazeFirst() {
        return escape(offSet -> offSet+1);
    }

    int escapeMazeSecond() {
        return escape(offSet -> offSet + (offSet>=3 ? -1 : 1));
    }

    private int escape(Function<Integer, Integer> offSetFunction) {
        while(!isEscapeMaze()) {
            next(offSetFunction);
        }
        return steps;
    }

    private boolean isEscapeMaze() {
        return index < 0 || index >= size;
    }

    private void next(Function<Integer, Integer> offSetFunction) {
        int offSet = offSets.get(index);
        offSets.set(index, offSetFunction.apply(offSet));
        index += offSet;
        steps++;
    }
}
