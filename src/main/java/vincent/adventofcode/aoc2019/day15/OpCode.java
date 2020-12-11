package vincent.adventofcode.aoc2019.day15;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
enum OpCode {
    ADD(1), MULTIPLY(2), INPUT(3), OUTPUT(4), JUMP_IF_TRUE(5), JUMP_IF_FALSE(6), LESS_THAN(7), EQUALS(8), ADJUST_RELATIVE_BASE(9);

    private int code;

    public static OpCode forRawCode(long rawCode) {
        int code = lastDigit(rawCode);
        return Arrays.stream(OpCode.values())
                .filter(a -> a.code == code)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no OpCode for code: " + code));
    }

    private static int lastDigit(long rawCode) {
        String rawCodeString = String.valueOf(rawCode);
        return rawCodeString.charAt(rawCodeString.length() - 1) - '0';
    }
}
