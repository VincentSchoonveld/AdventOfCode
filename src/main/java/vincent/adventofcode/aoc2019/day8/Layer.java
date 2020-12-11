package vincent.adventofcode.aoc2019.day8;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Layer {
    private List<PixelColor> characterList;

    static Layer forInput(String input) {
        return new Layer(input.chars()
                .map(numberChar -> numberChar - '0')
                .mapToObj(PixelColor::forNumber)
                .collect(Collectors.toList()));
    }

    long getNumberOfPixelColor(PixelColor pixelColor) {
        return characterList.stream()
                .filter(character -> character == pixelColor)
                .count();
    }

    PixelColor getPixelColor(int number) {
        return characterList.get(number);
    }
}
