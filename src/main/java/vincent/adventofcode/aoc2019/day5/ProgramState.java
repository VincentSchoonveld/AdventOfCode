package vincent.adventofcode.aoc2019.day5;

import lombok.Value;
import vincent.adventofcode.aoc2019.day5.opcode.OpCode;

import java.util.List;

@Value
public
class ProgramState {
    private List<Integer> state;
    private int index;

    boolean isFinished() {
        return index >= state.size()
                || state.get(index) == 99;
    }

    OpCode getOpCode() {
        return OpCode.forCode(opCode(state.get(index)));
    }

    public IntegerPair getNextPair() {
        String s = addZeros(String.valueOf(state.get(index)));
        int first = s.charAt(2) == '0' ? state.get(state.get(index + 1)) : state.get(index+1);
        int second = s.charAt(1) == '0' ? state.get(state.get(index + 2)) : state.get(index+2);
        return new IntegerPair(first, second);
    }

    private static int opCode(int code) {
        String s = String.valueOf(code);
        if(s.length() == 1) {
            return code;
        } else {
            return s.charAt(s.length()-1)-'0';
        }
    }

    private static String addZeros(String string) {
        if(string.length() == 5) {
            return string;
        } else {
            return addZeros("0"+string);
        }
    }
}
