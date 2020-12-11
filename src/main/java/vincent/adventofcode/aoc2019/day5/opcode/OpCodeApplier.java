package vincent.adventofcode.aoc2019.day5.opcode;

import vincent.adventofcode.aoc2019.day5.ProgramState;

interface OpCodeApplier {
    ProgramState apply(ProgramState programState, final int input);

    static String addZeros(String string) {
        if(string.length() == 5) {
            return string;
        } else {
            return addZeros("0"+string);
        }
    }
}
