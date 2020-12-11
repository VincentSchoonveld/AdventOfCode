package vincent.adventofcode.aoc2019.day5.opcode;

import vincent.adventofcode.aoc2019.day5.IntegerPair;
import vincent.adventofcode.aoc2019.day5.ProgramState;

import java.util.List;

public class OpCodeMultiply implements OpCodeApplier {
    @Override
    public ProgramState apply(ProgramState programState, int input) {
        List<Integer> state = programState.getState();
        int index = programState.getIndex();
        IntegerPair nextPair = programState.getNextPair();
        int product = nextPair.getFirst() * nextPair.getSecond();
        state.set(state.get(index+3), product);
        return new ProgramState(state, index + 4);
    }
}
