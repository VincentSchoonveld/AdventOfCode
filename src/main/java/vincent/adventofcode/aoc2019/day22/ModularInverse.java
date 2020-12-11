package vincent.adventofcode.aoc2019.day22;

import java.math.BigInteger;

class ModularInverse {

    static long modInverse(long a, long prime) {
        long power = power(a, prime - 2, prime);
        while(power < 0) {
            power = power + prime;
        }
        while(power >= prime) {
            power = power - prime;
        }
        return power;
    }

    private static long power(long x, long y, long prime) {
        if (y == 0)
            return 1;

        long p = power(x, y / 2, prime) % prime;
        p = (BigInteger.valueOf(p).multiply(BigInteger.valueOf(p))).mod(BigInteger.valueOf(prime)).longValue();

        if (y % 2 == 0)
            return p;
        else
            return (x * p) % prime;
    }
}
