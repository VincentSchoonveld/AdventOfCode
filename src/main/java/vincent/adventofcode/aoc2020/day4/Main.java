package vincent.adventofcode.aoc2020.day4;

import lombok.val;
import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final List<String> INPUT_STRINGS = InputDataUtil.getInputStrings(Day.DAY_4);

    public static void main(String[] args) {
        System.out.println("FIRST PART");
        System.out.println(isValid(INPUT_STRINGS));
        System.out.println("SECOND PART");
    }

    private static int isValid(List<String> s) {
        int count = 0;
        Map<String, String> map = new HashMap<>();
        for(int i=0; i<s.size(); i++) {
            if(s.get(i).isEmpty()) {
                if((map.size() == 8 || (map.size() == 7 && !map.containsKey("cid"))) && isValid(map)) {
                    count++;
                }
                map = new HashMap<>();
            } else {
                final val s1 = s.get(i).split(" ");
                for(String s2 : s1) {
                    final val split = s2.split(":");
                    map.put(split[0], split[1]);
                }
            }
        }
        if((map.size() == 8 || (map.size() == 7 && !map.containsKey("cid"))) && isValid(map)) {
            count++;
        }
        return count;

    }

    private static boolean isValid(Map<String, String> map) {
        /*
        byr (Birth Year) - four digits; at least 1920 and at most 2002.
iyr (Issue Year) - four digits; at least 2010 and at most 2020.
eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
hgt (Height) - a number followed by either cm or in:
If cm, the number must be at least 150 and at most 193.
If in, the number must be at least 59 and at most 76.
hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
pid (Passport ID) - a nine-digit number, including leading zeroes.
         */
        try {
            final int byr = Integer.parseInt(map.get("byr"));
            if(byr < 1920 || byr > 2002) {
                return false;
            }
            final int iyr = Integer.parseInt(map.get("iyr"));
            if(iyr <2010 || iyr > 2020) {
                return false;
            }
            final int eyr = Integer.parseInt(map.get("eyr"));
            if(eyr <2020 || eyr > 2030) {
                return false;
            }
            final String hgt = map.get("hgt");
            if(hgt.endsWith("cm")) {
                final int i = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
                if(i<150 || i>193) {
                    return false;
                }
            } else if(hgt.endsWith("in")) {
                final int i = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
                if (i < 59 || i > 76) {
                    return false;
                }
            } else {
                return false;
            }
            /*
            hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
pid (Passport ID) - a nine-digit number, including leading zeroes.
             */
            final String hcl = map.get("hcl");
            if(hcl.length() != 7 || hcl.charAt(0) != '#') {
                return false;
            }
            for(int i=1; i<7; i++) {
                if(!Character.isDigit(hcl.charAt(i)) && (hcl.charAt(i) < 'a' || hcl.charAt(i) > 'f')) {
                    return false;
                }
            }
            final String ecl = map.get("ecl");
            if(!Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(ecl)) {
                return false;
            }
            if(!map.containsKey("pid")) {
                return true;
            }
            final String pid = map.get("pid");
            return pid.length() == 9 && pid.chars().allMatch(i -> Character.isDigit(i));

        } catch (Exception e) {
            return false;
        }
    }
}
