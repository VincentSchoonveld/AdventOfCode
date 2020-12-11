package vincent.adventofcode.aoc2019.day11;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Color {
    BLACK(0, " "), WHITE(1, "X");

    @Override
    public String toString() {
        return stringValue;
    }

    @Getter
    private long number;
    private String stringValue;

    static Color forNumber(long number) {
        return Arrays.stream(Color.values())
                .filter(color -> color.number == number)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no color for number: " + number));
    }
}
