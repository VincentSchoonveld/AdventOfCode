package vincent.adventofcode.aoc2019.day19;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
class TractorBeam {

    private Map<Index, Long> map;

    static TractorBeam forIntcodeProgramAndSizes(IntcodeProgram intcodeProgram, int yMax) {
        Map<Index, Long> map = new HashMap<>();
        for(long y=0; y<6; y++) {
            for(long x=0; x<10; x++) {
                Index input = new Index(x, y);
                long execute = intcodeProgram.execute(input);
                if(execute == 1L) {
                    map.put(input, 1L);
                }
            }
        }

        long currentMin = minX(map, 5);
        long currentMax = maxX(map, 5);

        for(long y=6; y<yMax; y++) {
            currentMin = currentMin(intcodeProgram, y, currentMin);
            currentMax = currentLast(intcodeProgram, y, currentMax);
            map.put(new Index(currentMin,y), 1L);
            map.put(new Index(currentMax,y), 1L);
        }
        return new TractorBeam(map);
    }

    long numberOfPointsAffected(long size) {
        long amount = 0;
        for(int y=0; y<size; y++) {
            long xMin = minX(map, y);
            long xMax = maxX(map, y);
            if(xMin < size) {
                if(xMax < size) {
                    amount += xMax - xMin + 1;
                } else {
                    amount += size - xMin;
                }
            }
        }
        return amount;
    }

    Optional<Index> containsSquare(long size) {
        return map.keySet().stream().flatMap(index -> map.keySet().stream().filter(index1 -> index1.getX()-index.getX()>=size-1
                && index.getY()-index1.getY() >=size-1).map(index1 -> new Index(index.getX(), index1.getY())))
                .min(Comparator.comparing(Index::size));
    }

    private static long currentMin(IntcodeProgram intcodeProgram, long y, long xMinLast) {
        long xMin = xMinLast;
        while (intcodeProgram.execute(new Index(xMin, y)) == 0L) {
            xMin++;
        }
        return xMin;
    }

    private static long currentLast(IntcodeProgram intcodeProgram, long y, long xMaxLast) {
        long xMax = xMaxLast;
        boolean seenOnes = false;
        boolean b = intcodeProgram.execute(new Index(xMax, y)) == 1L;
        while (b || !seenOnes) {
            seenOnes = b;
            xMax++;
            b = intcodeProgram.execute(new Index(xMax, y)) == 1L;
        }
        return xMax-1;
    }

    private static long minX(Map<Index, Long> map, final long y) {
        return map.keySet()
                .stream()
                .filter(aLong -> aLong.getY() == y)
                .mapToLong(Index::getX)
                .min()
                .orElse(Long.MAX_VALUE);
    }

    private static long maxX(Map<Index, Long> map, final long y) {
        return map.keySet()
                .stream()
                .filter(aLong -> aLong.getY() == y)
                .mapToLong(Index::getX)
                .max()
                .orElse(Long.MAX_VALUE);
    }

}
