package vincent.adventofcode.aoc2019.day5.opcode;

import vincent.adventofcode.aoc2019.day5.IntegerPair;
import vincent.adventofcode.aoc2019.day5.ProgramState;

import java.util.List;

public class OpCodeAdd implements OpCodeApplier {

    @Override
    public ProgramState apply(ProgramState programState, final int input) {

        List<Integer> state = programState.getState();
        int index = programState.getIndex();

        IntegerPair nextPair = programState.getNextPair();
        int sum = nextPair.getFirst() + nextPair.getSecond();
        state.set(state.get(index+3), sum);

        return new ProgramState(state, index + 4);
    }
}
