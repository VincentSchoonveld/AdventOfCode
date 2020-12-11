package vincent.adventofcode.aoc2019.day18;

enum Quadrant {
    UPPER_LEFT, UPPER_RIGHT, DOWN_LEFT, DOWN_RIGHT;

    boolean isCrossOpposite(Quadrant quadrant) {
        switch (quadrant) {
            case UPPER_LEFT: return this == DOWN_RIGHT;
            case UPPER_RIGHT:return this == DOWN_LEFT;
            case DOWN_LEFT: return this == UPPER_RIGHT;
            case DOWN_RIGHT: return this == UPPER_LEFT;
            default: throw new IllegalStateException();
        }
    }
}
