package vincent.adventofcode.aoc2019.day17;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Direction {
    UP('^'), DOWN('v'), LEFT('<'), RIGHT('>');

    private char c;

    static Direction forChar(char c) {
        return Arrays.stream(Direction.values())
                .filter(a ->a.c == c)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no direction for char " + c));
    }

    char asChar() {
        return c;
    }

    public Direction left() {
        switch (this) {
            case UP: return LEFT;
            case DOWN: return RIGHT;
            case LEFT: return DOWN;
            case RIGHT: return UP;
        }
        throw new IllegalStateException();
    }

    public Direction right() {
        switch (this) {
            case UP: return RIGHT;
            case DOWN: return LEFT;
            case LEFT: return UP;
            case RIGHT: return DOWN;
        }
        throw new IllegalStateException();
    }
}
