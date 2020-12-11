package vincent.adventofcode.aoc2019.day13;

import java.util.ArrayList;
import java.util.List;

class Output {
    private static final int OUTPUT_SIZE = 3;

    private final List<Long> longList = new ArrayList<>();

    void add(long value) {
        longList.add(value);
    }

    boolean isFilled() {
        return longList.size() >= OUTPUT_SIZE;
    }

    Long getFirst() {
        return longList.get(0);
    }

    Long getThird() {
        return longList.get(2);
    }
}
