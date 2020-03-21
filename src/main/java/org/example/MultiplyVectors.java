package org.example;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class MultiplyVectors {

    public static void main(String[] args) {

        int vectorSize = Integer.parseInt(args[0]);
        int numOfThreads = Integer.parseInt(args[1]);
        //int vectorSize = 2;
        //int numOfThreads = 800;

        if (numOfThreads > vectorSize)
            numOfThreads = vectorSize;

        Integer[] vector1 = setVector(vectorSize), vector2 = setVector(vectorSize);
        ThreadCalculator[] threadCalculators = new ThreadCalculator[numOfThreads];
        Vector<BigInteger> results = new Vector<>();

        int eachThreadJob = vectorSize / numOfThreads;
        int currIndex = 0;
        for (int i = 0; i < numOfThreads - 1; ++i, currIndex += eachThreadJob)
            threadCalculators[i] = new ThreadCalculator(vector1, vector2, results, currIndex, currIndex + eachThreadJob);

        threadCalculators[numOfThreads - 1] = new ThreadCalculator(vector1, vector2, results, currIndex, vectorSize);

        for (ThreadCalculator t : threadCalculators)
            t.start();

        for (ThreadCalculator t : threadCalculators)
            try
            {
                t.join();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        BigInteger sum = BigInteger.valueOf(0);
        for (BigInteger result : results)
            sum = sum.add(result);

        System.out.println("vector 1 is: " + Arrays.toString(vector1));
        System.out.println("vector 2 is: " + Arrays.toString(vector2));
        System.out.println("Their product is: " + sum);
    }

    public static Integer[] setVector(int size) {

        Integer[] vector = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; ++i)
            vector[i] = random.nextInt();

        return vector;
    }
}