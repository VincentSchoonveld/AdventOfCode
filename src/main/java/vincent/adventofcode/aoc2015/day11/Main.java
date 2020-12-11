package vincent.adventofcode.aoc2015.day11;

public class Main {
    public static void main(String... args) {
        String input = "cqjxjnds";
        String x = nextPassword(input);
        System.out.println(x);
        System.out.println(nextPassword(x));
    }

    private static String nextPassword(String string) {
        string = nextPossiblePassword(string);
        while(!isValidPassword(string)) {
            string = nextPossiblePassword(string);
        }
        return string;
    }

    private static String nextPossiblePassword(String string) {
        if(string.isEmpty()) {
            return "";
        } else if(string.charAt(string.length()-1) == 'z') {
            return nextPossiblePassword(string.substring(0, string.length()-1)) + "a";
        } else {
            return string.substring(0, string.length()-1)+ (char) (string.charAt(string.length()-1) + 1);
        }
    }

    private static boolean isValidPassword(String string) {
        return isInCreasingThree(string)
                && isNotContainingIOL(string)
                && containsTwoNonOverlappingPair(string);
    }

    private static boolean isInCreasingThree(String string) {
        for(int i=0; i<string.length()-2; i++) {
            if(isNext(string.charAt(i), string.charAt(i+1))
                    && isNext(string.charAt(i+1), string.charAt(i+2))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNext(char c1, char c2) {
        return ((int) c1)+1 == ((int) c2);
    }

    private static boolean isNotContainingIOL(String string) {
        return !string.matches("[iol]]");
    }

    private static boolean containsTwoNonOverlappingPair(String string) {
        int numberOfNonOverlappingPairs = 0;
        for(int i=0; i<string.length()-1; i++) {
            if(string.charAt(i) == string.charAt(i+1)) {
                numberOfNonOverlappingPairs++;
                i++;
            }
        }
        return numberOfNonOverlappingPairs >= 2;
    }
}
