package org.tanglizi.algo;

import java.util.LinkedList;

public class InterestingBigNumberInJava {

    private LinkedList<Integer> answer = new LinkedList<Integer>();

    public LinkedList<Integer> solve() {
        find(new LinkedList<Integer>());
        return answer;
    }

    private void find(LinkedList<Integer> num) {
        if (greater(num, answer))
            answer = (LinkedList<Integer>) num.clone();

        int start = (num.size() == 0)?1:0;
        for (int i = start; i <= 9; i++) {
            num.add(i);
            if (canDivide(num, num.size()))
                find(num);
            num.removeLast();
        }
    }

    private boolean greater(LinkedList<Integer> xs, LinkedList<Integer> ys) {
        if (xs.size() != ys.size())
            return xs.size() > ys.size();

        for (int i=0; i<xs.size(); i++) {
            if (xs.get(i) != ys.get(i))
                return xs.get(i) > ys.get(i);
        }

        return false;
    }

    public boolean canDivide(LinkedList<Integer> xs, int n) {
        int remainder = 0;
        for (Integer x: xs)
            remainder = (remainder*10 + x)%n;
        return remainder == 0;
    }
}
