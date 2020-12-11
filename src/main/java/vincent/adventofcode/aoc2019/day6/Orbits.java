package vincent.adventofcode.aoc2019.day6;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Orbits {
    private Map<String, String> map;

    static Orbits forInput(List<Orbit> orbitList) {
        return new Orbits(
                orbitList.stream()
                        .collect(Collectors.toMap(
                                Orbit::getInOrbit,
                                Orbit::getAround
                        ))
        );
    }

    long getCheckSum() {
        return map.keySet().stream()
                .mapToLong(this::getCheckSum)
                .sum();
    }

    private long getCheckSum(String key) {
        return !map.containsKey(key) ? 0 : 1 + getCheckSum(map.get(key));
    }

    int distanceBetweenYOUAndSAN() {
        List<String> path1 = path("YOU");
        List<String> path2 = path("SAN");
        return path1.stream()
                .filter(path2::contains)
                .mapToInt(orbitName -> path1.indexOf(orbitName) + path2.indexOf(orbitName))
                .min()
                .orElseThrow(() -> new IllegalStateException("There is no route between 'YOU' and 'SAN'"));
    }

    private List<String> path(String orbitName) {
        List<String> pathList = new ArrayList<>();
        while(map.containsKey(orbitName)) {
            String next = map.get(orbitName);
            pathList.add(next);
            orbitName = next;
        }
        return pathList;
    }
}
