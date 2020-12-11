package vincent.adventofcode.aoc2019.day11;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Direction next(ClockDirection clockDirection) {
        boolean clockwise = clockDirection.isClockwise();
        switch (this) {
            case UP: return clockwise ? RIGHT : LEFT;
            case LEFT: return clockwise ? UP : DOWN;
            case RIGHT: return clockwise ? DOWN : UP;
            case DOWN: return clockwise ? LEFT : RIGHT;
            default: throw new IllegalArgumentException("The following direction is not known: "+this);
        }
    }
}
