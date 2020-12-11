package vincent.adventofcode.aoc2020.day1;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<Integer> inputIntegers = InputDataUtil.getInputIntegers(Day.DAY_1);
        System.out.println("FIRST PART");
        
        for(int i=0; i<inputIntegers.size(); i++) {
            for(int j=i+1; j<inputIntegers.size(); j++) {
                if(inputIntegers.get(i)+inputIntegers.get(j) == 2020) {
                    //1013211
                    System.out.println(inputIntegers.get(i)*inputIntegers.get(j));
                }
            }
        }
        System.out.println("SECOND PART");
        for(int i=0; i<inputIntegers.size(); i++) {
            for(int j=i+1; j<inputIntegers.size(); j++) {
                for(int k=j+1; k<inputIntegers.size(); k++) {
                    if(inputIntegers.get(i)+inputIntegers.get(j)+inputIntegers.get(k) == 2020) {
                        //13891280
                        System.out.println(inputIntegers.get(i)*inputIntegers.get(j)*inputIntegers.get(k));
                    }
                }
            }
        }
    }
}
