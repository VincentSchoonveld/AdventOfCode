package vincent.adventofcode.aoc2015.day12;

import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

public class Main {
    public static void main(String... args) throws ParseException {
        String input = InputDataUtil.getInputString(Day.AOC_2015_DAY_12);
        Object somethingJson =  new JSONParser().parse(input);

        System.out.println(sum(somethingJson));
    }

    @SneakyThrows
    private static long sum(Object object) {
        if(object instanceof String) {
            return 0;
        } else if(object instanceof Long) {
            return (Long) object;
        } else if(object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            return jsonArray.stream().mapToLong(Main::sum).sum();
        } else if(object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            if(jsonObject.containsValue("red")) {
                return 0;
            } else {
                return jsonObject.values().stream().mapToLong(Main::sum).sum();
            }
        } else {
            throw new ClassNotFoundException("Class is not known: " + object.getClass());
        }
    }
}
