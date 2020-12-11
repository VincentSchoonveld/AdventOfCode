package vincent.adventofcode.aoc2019.day17;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Type {
    SCAFFELD(35), OPEN_SPACE(46);

    private long code;

    char asChar() {
        return (char) code;
    }
}
