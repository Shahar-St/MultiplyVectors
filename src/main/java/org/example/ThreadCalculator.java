package org.example;

import java.util.Vector;

public class ThreadCalculator extends Thread {

    private Integer[] vector1;
    private Integer[] vector2;
    private Vector<Long> resultVector;
    private int startIndex;
    private int endIndex;


    public ThreadCalculator(Integer[] vector1, Integer[] vector2, Vector<Long> resultVector, int startIndex, int endIndex) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.resultVector = resultVector;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
    @Override
    public void run() {
        long sum = 0;
        for (int i = startIndex; i < endIndex; ++i)
            sum += vector1[i] * vector2[i];

        System.out.println("Adding " + sum);
        resultVector.add(sum); // Vectors are thread-safe - no need for synchronisation
    }
}
