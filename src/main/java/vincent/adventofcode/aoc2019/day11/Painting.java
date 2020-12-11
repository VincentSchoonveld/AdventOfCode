package vincent.adventofcode.aoc2019.day11;

import java.util.HashMap;
import java.util.Map;

class Painting {
    private static final Color DEFAULT_COLOR = Color.BLACK;

    private final Map<Index, Color> colorMap = new HashMap<>();

    Color get(Index index) {
        return colorMap.getOrDefault(index, DEFAULT_COLOR);
    }

    void put(Index index, Color color) {
        colorMap.put(index, color);
    }

    int size() {
        return colorMap.size();
    }

    @Override
    public String toString() {
        int minX = colorMap.keySet().stream().mapToInt(Index::getX).min().orElse(0);
        int minY = colorMap.keySet().stream().mapToInt(Index::getY).min().orElse(0);
        int maxX = colorMap.keySet().stream().mapToInt(Index::getX).max().orElse(0);
        int maxY = colorMap.keySet().stream().mapToInt(Index::getY).max().orElse(0);
        StringBuilder stringBuilder = new StringBuilder();
        for(int j=maxY; j>=minY; j--) {
            for(int i=minX; i<=maxX; i++) {
                Color color = colorMap.getOrDefault(new Index(i, j), Color.BLACK);
                stringBuilder.append(color);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
