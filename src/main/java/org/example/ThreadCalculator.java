package org.example;

import java.math.BigInteger;

import static org.example.MultiplyVectors.*;

public class ThreadCalculator extends Thread {

    private int startIndex;
    private int endIndex;
    boolean isActive = false;

    public ThreadCalculator() {
    }
    public ThreadCalculator(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        isActive = true;
    }
    @Override
    public void run() {
        if (isActive)   // in case the num of threads exceeds the vector size, the remaining threads won't be active
        {
            BigInteger sum = BigInteger.valueOf(0);
            for (int i = startIndex; i < endIndex; ++i)
                sum = sum.add(BigInteger.valueOf(vector1[i]).multiply(BigInteger.valueOf(vector2[i])));

            results.add(sum); // Vectors are thread-safe - no need for synchronisation
        }
    }
}
