package vincent.adventofcode.aoc2019.day21;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class AsciiProgram {
    private List<Long> intcodeProgramCore;

    String execute(String input) {
        IntcodeProgram intcodeProgram = new IntcodeProgram(intcodeProgramCore);
        List<Long> inputLongs = inputStringToLongs(input);
        List<Long> output = intcodeProgram.execute(inputLongs);
        return outputToString(output);
    }

    private List<Long> inputStringToLongs(String input) {
        return Arrays.stream(input.split(""))
                .map(a -> (long) a.charAt(0))
                .collect(Collectors.toList());
    }

    private static String outputToString(List<Long> outputList) {
        return outputList.stream()
                .map(AsciiProgram::longToString)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static String longToString(long aLong) {
        return isAscii(aLong) ? String.valueOf((char) aLong) : String.valueOf(aLong);
    }

    private static boolean isAscii(long l) {
        return l>=0 && l<128;
    }
}
