package vincent.adventofcode.aoc2019.day19;

import lombok.Value;

@Value
public class Index {
    private long x;
    private long y;

    long size() {
        return x+y;
    }

    long output() {
        return x*10_000+y;
    }
}
