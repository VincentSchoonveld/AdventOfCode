package vincent.adventofcode.aoc2019.day22;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<Motion> motionList = InputDataUtil.getInputStrings(Day.AOC_2019_DAY_22).stream().map(Motion::forString).collect(Collectors.toList());

        Card card2019 = new Card(2019, 10_007);
        motionList.forEach(card2019::handle);
        System.out.println(card2019.getPosition());//<7692 >4529
//        iets(motionList);

        for(long l=0; l<119315717514047L; l++) {
            Card card = new Card(l, 119315717514047L);
            motionList.forEach(card2019::handle);
            //System.out.println("l="+l+", result="+card2019.getPosition());
        }
    }


    private static void iets(List<Motion> motionList) {
        Set<Long> longSet = new HashSet<>();//41586975146478

        Card cardAtPosition2020 = new Card(2020, 119315717514047L);
        for(long i=0; i<101741582076661L; i++) {
//            if(i%1_000L == 0) {
//                System.out.println(i);
//            }
            for(int k=motionList.size()-1; k>=0; k--) {
                cardAtPosition2020.handle(motionList.get(k));
                //System.out.println(cardAtPosition2020.getPosition());
            }
            //System.out.println(i + "   " + cardAtPosition2020.getPosition());
            if(!longSet.add(cardAtPosition2020.getPosition())) {
                System.out.println(i + "   " + cardAtPosition2020.getPosition());
                //break;
            }
        }
        System.out.println(cardAtPosition2020.getPosition());
    }
}
