package vincent.adventofcode.util.data;

import vincent.adventofcode.util.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InputDataUtil {
    private static final String INPUT = "input.txt";

    public static String getInputString(Day day) {
        List<String> inputStrings = getInputStrings(day);
        if(inputStrings.size() != 1) {
            throw new IllegalStateException("There is not a single string, but " + inputStrings.size() + " strings.");
        }
        return inputStrings.get(0);
    }

    public static List<String> getInputStrings(Day day) {
        return getInputStrings(day, INPUT);
    }

    public static List<StringBuilder> getInputStringBuilders(Day day) {
        return getInputStrings(day, INPUT).stream().map(StringBuilder::new).collect(Collectors.toList());
    }

    public static List<String> getInputStrings(Day day, String pathString) {
        Path path = Paths.get("src/main/resources", day.getPackageName(), pathString);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeDataException("File cannot be read", e);
        }
    }

    public static List<Integer> getInputIntegers(Day day) {
        List<String> inputStrings = getInputStrings(day);
        return inputStrings.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public static List<Long> getInputLongs(Day day) {
        List<String> inputStrings = getInputStrings(day);
        return inputStrings.stream().map(Long::parseLong).collect(Collectors.toList());
    }
}
