package vincent.adventofcode.aoc2019.day5.opcode;

import vincent.adventofcode.aoc2019.day5.IntegerPair;
import vincent.adventofcode.aoc2019.day5.ProgramState;

import java.util.List;

public class OpCodeJumpIfFalse implements OpCodeApplier {
    @Override
    public ProgramState apply(ProgramState programState, int input) {
        List<Integer> state = programState.getState();
        int index = programState.getIndex();
        IntegerPair nextPair = programState.getNextPair();
        if(nextPair.getFirst() == 0) {
            return new ProgramState(state, nextPair.getSecond());
        } else {
            return new ProgramState(state, index + 3);
        }
    }
}
