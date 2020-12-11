package vincent.adventofcode.aoc2019.day23;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

class InputOutputHandler {
    private List<Queue<Long>> queueList = new ArrayList<>();
    private volatile Output output = null;
    private volatile Long lastYNatSend = null;
    private AtomicInteger counter = new AtomicInteger(0);
    private final boolean hasResetMechanism;

    InputOutputHandler(int size, boolean hasResetMechanism) {
        initiateQueues(size);
        this.hasResetMechanism = hasResetMechanism;
    }

    synchronized void add(Output output) {
        if(output.isResetValue()) {
            if(!hasResetMechanism) {
                throw new IllegalArgumentException(output.toString());
            }
            this.output = output.asOutputForNetworkInterfaceController0();
        } else {
            Queue<Long> queue = queueList.get(output.getFirst().intValue());
            queue.offer(output.getSecond());
            queue.offer(output.getThird());
        }
    }

    synchronized Long get(int i) {
        Long poll = queueList.get(i).poll();
        if(poll == null) {
            if(queueList.stream().allMatch(Collection::isEmpty)) {
                counter.incrementAndGet();
            } else {
                counter.set(0);
            }
            if(counter.get()>10000 && hasResetMechanism) {
                if(lastYNatSend != null
                        && lastYNatSend.equals(output.getThird())) {
                    System.out.println(lastYNatSend);
                }
                lastYNatSend = output.getThird();
                add(output);
                counter.set(0);
            }
            return -1L;
        }
        counter.set(0);
        return poll;
    }

    private void initiateQueues(int size) {
        for(long i=0; i<size; i++) {
            Queue<Long> queue = new LinkedList<>();
            queue.add(i);
            queueList.add(queue);
        }
    }
}
