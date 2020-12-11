package vincent.adventofcode.aoc2015.day8;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {

        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2015_DAY_8);
        System.out.println(inputStrings.get(17));
        System.out.println(parseString(inputStrings.get(17)));
        int sum = inputStrings.stream().mapToInt(String::length).sum();
        int sum2 = inputStrings.stream().map(Main::parseString).mapToInt(String::length).sum();
        System.out.println(sum+" - " + sum2 + " = " + (sum-sum2));
        int sum3 = inputStrings.stream().map(StringEscapeUtils::escapeJson).mapToInt(s -> s.length() + 2).sum();
        String x = "\"aaa\\\"aaa\"";
        System.out.println(x + " -> " + StringEscapeUtils.escapeJson(x) + " § " + x.length() + " -> " + StringEscapeUtils.escapeJson(x).length());
        System.out.println(StringEscapeUtils.escapeJson(x));
        System.out.println(sum3-sum);
    }

    static String parseString(String string) {
        if (StringUtils.isEmpty(string)) {
            return "";
        } else {
            int len = string.length();
            StringBuilder sb = new StringBuilder(len + 2);

            for(int i = 0; i < len; ++i) {
                char c = string.charAt(i);
                if(c == '\\') {
                    switch (string.charAt(i+1)) {
                        case 'b': sb.append('\b'); i++; break;
                        case 't': sb.append('\t'); i++; break;
                        case 'n': sb.append('\n'); i++; break;
                        case 'f': sb.append('\f'); i++; break;
                        case 'r': sb.append('\r'); i++; break;
                        case '\\': sb.append('\\'); i++; break;
                        case '\"': break;
                        case 'x':
                            String output = string.substring(i+2, (i + 3));
                            //convert hex to decimal
                            int decimal = Integer.parseInt(output, 16);
                            //convert the decimal to character
                            sb.append((char)decimal);
                            i+=3;
                            break;
                        default:
                            throw new IllegalArgumentException("Unexpected escape character: " + string.charAt(i + 1));
                    }
                } else {
                    sb.append(c);
                }
            }
            return sb.substring(1, sb.length()-1);
        }
    }
}
