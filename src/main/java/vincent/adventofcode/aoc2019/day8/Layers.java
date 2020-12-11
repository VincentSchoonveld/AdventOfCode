package vincent.adventofcode.aoc2019.day8;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Layers {
    private static final int WIDTH = 25;
    private static final int HEIGHT = 6;

    private List<Layer> layerList;

    static Layers forInput(String input) {
        int size = WIDTH*HEIGHT;
        return new Layers(IntStream.range(0, input.length()/size)
                .mapToObj(a -> input.substring(a*size, (a+1)*size))
                .map(Layer::forInput)
                .collect(Collectors.toList()));
    }

    long findNumberOfOnesTimeTwoWhereZerosAreLeast() {
        return layerList.stream()
                .min(Comparator.comparing(a -> a.getNumberOfPixelColor(PixelColor.BLACK)))
                .map(a -> a.getNumberOfPixelColor(PixelColor.WHITE)*a.getNumberOfPixelColor(PixelColor.TRANSPARENT))
                .orElseThrow(() -> new IllegalStateException("No layers available"));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int j=0; j<HEIGHT; j++) {
            for(int i=0; i<WIDTH; i++) {
                stringBuilder.append(forNumber(j*WIDTH+i).toString());
            }
            stringBuilder.append("\n\r");
        }

        return stringBuilder.toString();
    }

    private PixelColor forNumber(int number) {
        return layerList.stream()
                .map(layer -> layer.getPixelColor(number))
                .filter(pixelColor -> pixelColor != PixelColor.TRANSPARENT)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No non-transparent pixels available"));
    }
}
