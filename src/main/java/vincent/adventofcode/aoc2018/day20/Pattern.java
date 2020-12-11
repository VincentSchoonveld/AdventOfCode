package vincent.adventofcode.aoc2018.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Pattern {

    private List<StringBuilder> strings = new ArrayList<>();

    List<String> getStrings() {
        return strings.stream().map(StringBuilder::toString).collect(Collectors.toList());
    }

//    Pattern(String string) {
//
//        StringIterator stringIterator = new StringIterator(string);
//        stringIterator.next();
//        while(!stringIterator.isEnd()) {
//            handleNext(stringIterator);
//        }
//    }
//
//    private void handleNext(StringIterator stringIterator) {
//        if(stringIterator.isDirection()) {
//            add(stringIterator.next());
//        } else if(stringIterator.isOpeningBracket()) {
//
//        }
//    }
//
//    private void add(char c) {
//        strings.forEach(stringBuilder -> stringBuilder.append(c));
//    }
}
