package vincent.adventofcode.aoc2019.day3;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Direction {
    UP('U'), DOWN('D'), RIGHT('R'), LEFT('L');

    private char aChar;

    static Direction forChar(char aChar) {
        return Arrays.stream(Direction.values())
                .filter(a -> a.aChar == aChar)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("The following char is not known: " + aChar));
    }
}
