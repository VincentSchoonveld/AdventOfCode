package vincent.adventofcode.aoc2019.day18;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.DataReader;
import vincent.adventofcode.util.data.DataWriter;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        TunnelMap tunnelMap = getTunnelMap();
        DistanceMap distanceMap = getDistanceMap(tunnelMap);
        CalculateMinPath calculateMinPath = new CalculateMinPath(tunnelMap, distanceMap);
        System.out.println("FIRST PART");
        System.out.println(calculateMinPath.getMinPart1());
        System.out.println("SECOND PART");
        System.out.println(calculateMinPath.getMinPart2());
    }

    private static TunnelMap getTunnelMap() {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_18);
        return TunnelMap.forInput(inputStrings);
    }

    private static DistanceMap getDistanceMap(TunnelMap tunnelMap) {
        try {
            return (DistanceMap) DataReader.readOnce(Day.AOC_2019_DAY_18, "distanceMap.txt");
        } catch (Exception e) {
            System.out.println("An exception occurred while reading from distanceMap.txt " + e.getMessage());
            DistanceMap distanceMap = DistanceMap.forTunnelMap(tunnelMap);
            DataWriter.writeOnce(Day.AOC_2019_DAY_18, distanceMap, "distanceMap.txt");
            return distanceMap;
        }
    }
}
