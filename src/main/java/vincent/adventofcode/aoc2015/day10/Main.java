package vincent.adventofcode.aoc2015.day10;

public class Main {
    public static void main(String... args) {
        String input = "1321131112";
        for(int i=0; i<50; i++) {
            input = lookAndSay(input);
        }
        System.out.println(input.length());
    }

    private static String lookAndSay(String string) {
        StringBuilder response = new StringBuilder();
        int n = 0;
        for(int i=0; i<string.length()-1; i++) {
            n++;
            if(string.charAt(i) != string.charAt(i+1)) {
                response.append(n).append(string.charAt(i));
                n=0;
            }
        }
        n++;
        response.append(n).append(string.charAt(string.length()-1));
        return response.toString();
    }
}
