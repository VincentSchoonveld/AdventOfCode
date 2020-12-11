package vincent.adventofcode.aoc2017.day3;

public enum Direction {
    NORTH, SOUTH, WEST, EAST;

    Direction next() {
        switch (this) {
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case WEST: return SOUTH;
            case EAST: return NORTH;
            default: throw new IllegalStateException();
        }
    }
}
