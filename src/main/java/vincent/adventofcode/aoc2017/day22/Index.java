package vincent.adventofcode.aoc2017.day22;

import lombok.Value;

@Value
class Index {
    private final int x;
    private final int y;

    Index next(Direction direction) {
        switch (direction) {
            case NORTH: return new Index(x,y-1);
            case SOUTH: return new Index(x, y+1);
            case EAST: return new Index(x+1, y);
            case WEST: return new Index(x-1, y);
            default: throw new IllegalArgumentException("The following direction is not implemented");
        }
    }
}
