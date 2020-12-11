package vincent.adventofcode.aoc2019.day6;

import lombok.Value;

@Value
class Orbit {
    private String inOrbit;
    private String around;

    Orbit(String input) {
        String[] split = input.split("\\)");
        this.inOrbit = split[1];
        this.around = split[0];
    }
}
