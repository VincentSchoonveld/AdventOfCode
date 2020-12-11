package vincent.adventofcode.aoc2019.day17;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class StringDecomposition {
    private String mainString;

    private String stringA;
    private String stringB;
    private String stringC;

    static StringDecomposition forString(String s) {
        for(int i=5; i<= 20; i++) {
            for(int j=5; j<=20; j++) {
                for(int k=5; k<=20; k++) {
                    String stringA = s.substring(0, i);
                    String stringB = s.substring(s.length()-j);
                    String stringC1 = s.substring(i+1, i+k+1);
                    if(isDecomposition(s, stringA, stringB, stringC1)) {
                        String mainString = replaceAllSubstrings(s, stringA, stringB, stringC1);
                        return new StringDecomposition(mainString, stringA, stringB, stringC1);
                    }
                    String stringC2 = s.substring(s.length()-j-k-1, s.length()-j-1);
                    if(isDecomposition(s, stringA, stringB, stringC2)) {
                        String mainString = replaceAllSubstrings(s, stringA, stringB, stringC2);
                        return new StringDecomposition(mainString, stringA, stringB, stringC2);
                    }
                }
            }
        }
        throw new IllegalArgumentException("There is no decomposition for string: "+s);
    }

    String toInputString() {
        return mainString + "\n"
                + stringA + "\n"
                + stringB + "\n"
                + stringC + "\n"
                + "n\n";
    }

    private static boolean isDecomposition(String s, String stringA, String stringB, String stringC) {
        return startsWithLetterAndEndsWithDigit(stringA)
                && startsWithLetterAndEndsWithDigit(stringB)
                && startsWithLetterAndEndsWithDigit(stringC)
                && replaceAllSubstrings(s, stringA, stringB, stringC)
                    .matches("^[ABC,]*");
    }

    private static boolean startsWithLetterAndEndsWithDigit(String s) {
        return Character.isLetter(s.charAt(0))
                && Character.isDigit(s.charAt(s.length()-1));
    }

    private static String replaceAllSubstrings(String s, String stringA, String stringB, String stringC) {
        return s.replaceAll(stringA, "A")
                .replaceAll(stringB, "B")
                .replaceAll(stringC, "C");
    }
}
