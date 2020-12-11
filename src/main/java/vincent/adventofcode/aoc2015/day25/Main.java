package vincent.adventofcode.aoc2015.day25;

public class Main {
    public static void main(String[] args) {
//        int number = number(3029, 2947);
//        int number = number(3029, 2947);
        System.out.println(toCode(number(3029,2947)));
    }

    private static int number(int column, int row) {
        int diagonal = row+(column-1);
        int before = (diagonal-1)*diagonal/2;
        return before+column;
    }

    private static long toCode(int number) {
        long code = 20151125;
        long multiply = 252533;
        long divide = 33554393;
        for(int i=1; i<number; i++) {
            code = (code*multiply)%divide;
        }
        return code;
    }
}
