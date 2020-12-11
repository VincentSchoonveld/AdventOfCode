package vincent.adventofcode.aoc2019.day7;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum OpCode {
    ADD(1), MULTIPLY(2), INPUT(3), OUTPUT(4), JUMP_IF_TRUE(5), JUMP_IF_FALSE(6), LESS_THAN(7), EQUALS(8);

    private int code;

    public static OpCode forRawCode(int rawCode) {
        String rawCodeString = String.valueOf(rawCode);
        int code = rawCodeString.charAt(rawCodeString.length() - 1) - '0';
        return opCodeForCode(code);
    }

    private static OpCode opCodeForCode(int code) {
        return Arrays.stream(OpCode.values())
                .filter(a -> a.code == code)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no OpCode for code: " + code));
    }
}
