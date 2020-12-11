package vincent.adventofcode.aoc2019.day5.opcode;

import vincent.adventofcode.aoc2019.day5.ProgramState;

import java.util.List;

public class OpCodeOutput implements OpCodeApplier {
    @Override
    public ProgramState apply(ProgramState programState, int input) {
        List<Integer> state = programState.getState();
        int index = programState.getIndex();
        String s = OpCodeApplier.addZeros(String.valueOf(state.get(index)));
        if(s.charAt(2) == '0') {
            System.out.println(state.get(state.get(index+1)));
        } else {
            System.out.println(state.get(index+1));
        }
        return new ProgramState(state, index + 2);
    }
}
