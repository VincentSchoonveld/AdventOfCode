package vincent.adventofcode.aoc2019.day15;

public enum Type {
    UNKNOWN, WALL, EMPTY, OXYGEN_SYSTEM;

    static Type forCode(long code) {
        if (code == 0) {
            return WALL;
        } else if (code == 1) {
            return EMPTY;
        } else if (code == 2) {
            return OXYGEN_SYSTEM;
        }
        throw new IllegalArgumentException("There is no type for code: " + code);
    }
}
