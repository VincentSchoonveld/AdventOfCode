package vincent.adventofcode.aoc2019.day24;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
enum InfectionState {
    IS_INFECTED('#'), NOT_INFECTED('.'), UNKNOWN('?');

    @Getter
    private char c;

    static InfectionState forInput(char c) {
        return Arrays.stream(InfectionState.values())
                .filter(infectionState -> infectionState.c == c)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no InfectionState for char: " + c));
    }

    InfectionState nextInfectionState(long infectedNeighbors) {
        switch (this) {
            case IS_INFECTED:
                return infectedNeighbors == 1 ? InfectionState.IS_INFECTED : InfectionState.NOT_INFECTED;
            case NOT_INFECTED:
                return (infectedNeighbors == 1 || infectedNeighbors == 2) ? InfectionState.IS_INFECTED : InfectionState.NOT_INFECTED;
            default:
                return UNKNOWN;
        }
    }
}
