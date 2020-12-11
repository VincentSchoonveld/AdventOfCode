package vincent.adventofcode.aoc2019.day12;

import java.util.Arrays;

public class Main {
    public static void main(String... args) {

        System.out.println("FIRST PART");
        Moons moons = initialMoons();
        moons.run(1000);
        System.out.println(moons.totalEnergy()); //10944

        int periodX = MoonsRecurrence.getPeriodX(initialMoons());
        int periodY = MoonsRecurrence.getPeriodY(initialMoons());
        int periodZ = MoonsRecurrence.getPeriodZ(initialMoons());

        System.out.println("SECOND PART");
        System.out.println(lcm(lcm(periodX, periodY), periodZ)); //484244804958744
    }

    private static long lcm(long number1, long number2) {
        long higherNumber = Math.max(number1, number2);
        long lowerNumber = Math.min(number1, number2);
        long lcm = higherNumber;
        while (lcm % lowerNumber != 0) {
            lcm += higherNumber;
        }
        return lcm;
    }

    private static Moons initialMoons() {
        return new Moons(
                Arrays.asList(
                        new Moon(-3,10,-1),
                        new Moon(-12,-10,-5),
                        new Moon(-9,0,10),
                        new Moon(7,-5,-3)
                ));
    }
}
