package vincent.adventofcode.aoc2019.day20;

import lombok.Value;

@Value
class ThreeIndex {
    private int x;
    private int y;
    private int level;

    Index toIndex() {
        return new Index(x,y);
    }
}
