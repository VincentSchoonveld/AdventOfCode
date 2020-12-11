package vincent.adventofcode.aoc2018.day17;

import lombok.AllArgsConstructor;

@AllArgsConstructor
enum MapObject {
    PIPE("#"), SOURCE("+"), WATER("|"), STILL_WATER("~");

    private String string;

    @Override
    public String toString() {
        return string;
    }
}
