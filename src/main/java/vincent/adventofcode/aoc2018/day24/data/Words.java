package vincent.adventofcode.aoc2018.day24.data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Words {
    private final List<String> wordList;
    private int index = 0;

    Words(final List<String> input) {
        this.wordList = inputToWords(input);
    }

    String word() {
        if(!hasNext()) {
            throw new IndexOutOfBoundsException("There isn't a next word.");
        }
        final String word = wordList.get(index);
        index++;
        return word;
    }

    boolean hasNext() {
        return wordList.size() >= index;
    }

    private List<String> inputToWords(List<String> input) {
        return input.stream()
                .flatMap(a -> Arrays.stream(a.split(" ")))
                .collect(Collectors.toList());
    }
}
