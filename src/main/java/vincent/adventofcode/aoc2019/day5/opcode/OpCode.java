package vincent.adventofcode.aoc2019.day5.opcode;

import lombok.AllArgsConstructor;
import vincent.adventofcode.aoc2019.day5.ProgramState;

import java.util.Arrays;

@AllArgsConstructor
public enum OpCode {
    ADD(1, new OpCodeAdd()),
    MULTIPLY(2, new OpCodeMultiply()),
    INPUT(3, new OpCodeInput()),
    OUTPUT(4, new OpCodeOutput()),
    JUMP_IF_TRUE(5, new OpCodeJumpIfTrue()),
    JUMP_IF_FALSE(6, new OpCodeJumpIfFalse()),
    LESS_THAN(7, new OpCodeLessThan()),
    EQUALS(8, new OpCodeEquals());

    private int code;
    private OpCodeApplier opCodeApplier;

    public static OpCode forCode(int code) {
        return Arrays.stream(OpCode.values())
                .filter(a -> a.code == code)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no OpCode for code: " + code));
    }

    public ProgramState apply(ProgramState programState, int input) {
        return opCodeApplier.apply(programState, input);
    }
}
