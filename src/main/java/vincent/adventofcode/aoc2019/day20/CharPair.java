package vincent.adventofcode.aoc2019.day20;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
class CharPair {
    private char c1;
    private char c2;

    boolean isStart() {
        return c1 == 'A' && c2 == 'A';
    }

    boolean isFinish() {
        return c1 == 'Z' && c2 == 'Z';
    }
}
