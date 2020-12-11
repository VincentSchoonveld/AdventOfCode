package vincent.adventofcode.aoc2019.day16;

import java.util.ArrayList;
import java.util.List;

class Phases {

    private List<Integer> phase;

    Phases(List<Integer> phase) {
        this.phase = new ArrayList<>(phase);
    }

    void next() {
        for(int i=0; i<phase.size(); i++) {
            long sum = getSumForNextDigit(i);
            phase.set(i, getLastDigit(sum));
        }
    }

    void nextIfAllMultiplicationFactorsAreOne() {
        int firstDigit = getLastDigit(phase.stream().mapToLong(a -> a).sum());
        int lastDigitInPhaseBefore =  phase.get(0);
        phase.set(0, firstDigit);
        for(int i=1; i<phase.size(); i++) {
            int digitBefore = phase.get(i - 1);
            int digitNext = getLastDigit((digitBefore - lastDigitInPhaseBefore + 10));
            lastDigitInPhaseBefore = phase.get(i);
            phase.set(i, digitNext);
        }
    }

    String getFirst8Digits() {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i< 8; i++) {
            s.append(phase.get(i));
        }
        return s.toString();
    }

    private long getSumForNextDigit(int i) {
        long sum = 0;
        for(int j=0; j<phase.size(); j++) {
            sum += getMultiplicationFactor(i, j) * phase.get(j);
        }
        return sum;
    }

    private static int getLastDigit(long number) {
        if(number < 0) {
            number = -number;
        }
        return (int) (number%10);
    }

    private static long getMultiplicationFactor(int positionOutput, int positionInput) {
        int positionInFirstMinusOne = (positionInput + 1) / (positionOutput + 1);
        switch (positionInFirstMinusOne%4) {
            case 0:
            case 2:
                return 0;
            case 1: return 1;
            case 3: return -1;
            default: throw new IllegalStateException();
        }
    }
}
