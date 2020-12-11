package vincent.adventofcode.aoc2015.day20;

public class Main {
    public static void main(String[] args) {
        int i=1;
        while(divSum(i)*11L < 29000000L) {
            i++;
        }

        // <2636363
        System.out.println(i);
        System.out.println(divSum(i));
    }

    static long divSum(int num)
    {
        // Final result of summation of divisors
        long result = 0;

        // find all divisors which divides 'num'
        for (int i = 2; i <= Math.sqrt(num); i++)
        {
            // if 'i' is divisor of 'num'
            if (num % i == 0) {
                if(num/i <= 50) {
                    result += i;
                }
                if(num/(num/i) <= 50 && i != num/i) {
                    result += num/i;
                }
//
//                // if both divisors are same then
//                // add it only once else add both
//                if (i == (num / i))
//                    result += i;
//                else
//                    result += (i + num / i);
            }
        }

        // Add 1 to the result as 1 is also
        // a divisor
        if(num<=50) {
            return result+1+num;
        } else {
            return result+num;
        }
    }
}
