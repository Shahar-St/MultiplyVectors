package org.example;

import java.math.BigInteger;
import java.util.Vector;

public class ThreadCalculator extends Thread {

    private Integer[] vector1;
    private Integer[] vector2;
    private Vector<BigInteger> resultVector;
    private int startIndex;
    private int endIndex;

    public ThreadCalculator(Integer[] vector1, Integer[] vector2, Vector<BigInteger> resultVector, int startIndex, int endIndex) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.resultVector = resultVector;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
    @Override
    public void run() {
        BigInteger sum = BigInteger.valueOf(0);
        for (int i = startIndex; i < endIndex; ++i)
            sum = sum.add(BigInteger.valueOf(vector1[i]).multiply(BigInteger.valueOf(vector2[i])));

        resultVector.add(sum); // Vectors are thread-safe - no need for synchronisation
    }
}
