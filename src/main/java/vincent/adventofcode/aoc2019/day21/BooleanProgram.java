package vincent.adventofcode.aoc2019.day21;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class BooleanProgram {
    private final AsciiProgram asciiProgram;
    private String booleanString = "";

    BooleanProgram not(String first, String second) {
        applyOperator("NOT", first, second);
        return this;
    }

    BooleanProgram or(String first, String second) {
        applyOperator("OR", first, second);
        return this;
    }

    BooleanProgram and(String first, String second) {
        applyOperator("AND", first, second);
        return this;
    }

    String walk() {
        booleanString = booleanString + "WALK\n";
        return asciiProgram.execute(booleanString);
    }

    String run() {
        booleanString = booleanString + "RUN\n";
        return asciiProgram.execute(booleanString);
    }

    private void applyOperator(String operator, String firstElement, String secondElement) {
        booleanString = String.format("%s%s %s %s%n", booleanString, operator, firstElement, secondElement);
    }

}
