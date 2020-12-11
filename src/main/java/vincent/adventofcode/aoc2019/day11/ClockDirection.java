package vincent.adventofcode.aoc2019.day11;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ClockDirection {
    COUNTER_CLOCK_WISE(0L), CLOCK_WISE(1L);

    private long code;

    static ClockDirection forCode(long code) {
        return Arrays.stream(ClockDirection.values())
                .filter(clockDirection -> clockDirection.code == code)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no ClockDirection for code: "+code));
    }

    boolean isClockwise() {
        return this == CLOCK_WISE;
    }
}
