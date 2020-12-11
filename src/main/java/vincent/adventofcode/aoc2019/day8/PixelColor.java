package vincent.adventofcode.aoc2019.day8;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
enum PixelColor {
    BLACK(0), WHITE(1), TRANSPARENT(2);

    private int number;

    static PixelColor forNumber(int number) {
        return Arrays.stream(PixelColor.values())
                .filter(a -> a.number == number)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("This is not a known number: " + number));
    }

    @Override
    public String toString() {
        return this == WHITE ? "X" : " ";
    }
}
