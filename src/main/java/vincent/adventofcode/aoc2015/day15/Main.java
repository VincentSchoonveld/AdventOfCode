package vincent.adventofcode.aoc2015.day15;

public class Main {
    public static void main(String[] args) {
        int max = 0;
        int number = 0;
        for(int sugar = 1; sugar < 100; sugar++) {
            for(int sprinkles = 1; sprinkles<sugar; sprinkles++) {
                for(int candy = 1; candy<100; candy++) {
                    for(int chocolate = 1; chocolate<100; chocolate++) {
                        int sum = sugar + sprinkles + candy + chocolate;
                        int calories = 2*sugar+9*sprinkles+candy+8*chocolate;
                        if(sum == 100 && calories == 500) {

                            int capacity = Math.max(3 * sugar - 3 * sprinkles - candy, 0);
                            int durability = 3 * sprinkles;
                            int flavor = Math.max(4 * candy - 2 * chocolate, 0);
                            int texture = Math.max(-3 * sugar + 2 * chocolate, 0);
                            if(capacity * durability * flavor * texture > max) {
                                max = capacity *durability* flavor * texture;
                                number = sum;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(max);
        System.out.println(number);
    }
}
