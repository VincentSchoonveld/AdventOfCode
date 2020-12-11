package vincent.adventofcode.aoc2019.day15;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Direction {
    UP(1), DOWN(2), LEFT(3), RIGHT(4);

    @Getter
    private long code;
}
