package vincent.adventofcode.aoc2019.day23;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
class Output {
    private List<Long> longList = new ArrayList<>();

    void add(long value) {
        longList.add(value);
    }

    boolean isFilled() {
        return longList.size() >= 3;
    }

    Long getFirst() {
        return longList.get(0);
    }

    Long getSecond() {
        return longList.get(1);
    }

    Long getThird() {
        return longList.get(2);
    }

    boolean isResetValue() {
        return getFirst().equals(255L);
    }

    Output asOutputForNetworkInterfaceController0() {
        longList.set(0, 0L);
        return this;
    }
}
