package vincent.adventofcode.aoc2019.day22;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@AllArgsConstructor
class Card {
    @Getter
    private long position;
    private final long deckSize;

    void handle(Motion motion) {
        switch (motion.getType()) {
            case DEAL_INTO_STACK: dealIntoNewStack(); break;
            case CUT: cut(motion.getNumber()); break;
            case INCREMENT_WITH: incrementWith(motion.getNumber()); break;
        }
    }

    void handleAsBefore(Motion motion) {
        switch (motion.getType()) {
            case DEAL_INTO_STACK: dealIntoNewStack(); break;
            case CUT: cut(-motion.getNumber()); break;
            case INCREMENT_WITH: incrementWith(ModularInverse.modInverse(motion.getNumber(), deckSize)); break;
        }
    }

    private void dealIntoNewStack() {
        position = deckSize-position-1;
    }

    private void cut(int n) {
        position = (position+deckSize-n)%deckSize;
    }

    private void incrementWith(long n) {
        long l = BigInteger.valueOf(position).multiply(BigInteger.valueOf(n)).mod(BigInteger.valueOf(deckSize)).longValue();
        position = l;
    }

}
