package vincent.adventofcode.aoc2020.day2;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.DAY_2);
        List<PasswordValues> passwordValues = inputStrings.stream().map(PasswordValues::forInput).collect(Collectors.toList());
        System.out.println("FIRST PART");
        //603
        System.out.println(passwordValues.stream().filter(PasswordValues::isValidFirstMethod).count());
        System.out.println("SECOND PART");
        //404
        System.out.println(passwordValues.stream().filter(PasswordValues::isValidSecondMethod).count());
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class PasswordValues {
        private final String password;
        private final char letter;
        private final int firstNumber;
        private final int secondNumber;

        static PasswordValues forInput(String s) {
            String[] split = s.split(" ");
            int firstNumber = Integer.parseInt(split[0].split("-")[0]);
            int secondNumber = Integer.parseInt(split[0].split("-")[1]);
            char letter = split[1].charAt(0);
            String password = split[2];
            return new PasswordValues(password, letter, firstNumber, secondNumber);
        }

        boolean isValidFirstMethod() {
            long letterCount = password.chars().filter(i -> i == letter).count();
            return letterCount >= firstNumber && letterCount <= secondNumber;
        }

        boolean isValidSecondMethod() {
            return password.charAt(firstNumber-1) == letter ^ password.charAt(secondNumber-1) == letter;
        }
    }
}
