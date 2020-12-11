package vincent.adventofcode.aoc2017.day20;

import lombok.Value;

@Value
class Index {
    private final int x;
    private final int y;
    private final int z;

    int distance() {
        return Math.abs(x)+Math.abs(y)+Math.abs(z);
    }

    Index plus(Index other) {
        return new Index(x+other.x, y+other.y, z+other.z);
    }
}
