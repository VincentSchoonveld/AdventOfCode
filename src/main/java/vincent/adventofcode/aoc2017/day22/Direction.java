package vincent.adventofcode.aoc2017.day22;

enum Direction {
    NORTH, SOUTH, EAST, WEST;

    Direction next(InfectionState infectionState) {
        switch (infectionState) {
            case CLEAN: return left();
            case WEAKENED: return this;
            case INFECTED: return right();
            case FLAGGED: return reverse();
            default: throw new IllegalArgumentException("The following is not implemented: "+infectionState);
        }
    }

    private Direction right() {
        switch (this) {
            case NORTH: return EAST;
            case SOUTH: return WEST;
            case EAST: return SOUTH;
            case WEST: return NORTH;
            default: throw new IllegalStateException("This cannot happen");
        }
    }

    private Direction left() {
        switch (this) {
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case EAST: return NORTH;
            case WEST: return SOUTH;
            default: throw new IllegalStateException("This cannot happen");
        }
    }

    private Direction reverse() {
        switch (this) {
            case NORTH: return SOUTH;
            case SOUTH: return NORTH;
            case EAST: return WEST;
            case WEST: return EAST;
            default: throw new IllegalStateException("This cannot happen");
        }
    }
}
