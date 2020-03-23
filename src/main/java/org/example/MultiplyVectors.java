package org.example;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class MultiplyVectors {

    static Integer[] vector1, vector2;
    static Vector<BigInteger> results;

    public static void main(String[] args) {

        int vectorSize = Integer.parseInt(args[0]);
        int numOfThreads = Integer.parseInt(args[1]);
        //int vectorSize = 5;
        //int numOfThreads = 2;

        vector1 = setVector(vectorSize);
        vector2 = setVector(vectorSize);
        results = new Vector<>();
        ThreadCalculator[] threadCalculators = new ThreadCalculator[numOfThreads];


        int eachThreadJob = Math.max(1, vectorSize / numOfThreads);
        for (int i = 0, currIndex = 0; i < numOfThreads; ++i, currIndex += eachThreadJob)
        {
            if (i < vectorSize)
                if (i < numOfThreads - 1)
                    threadCalculators[i] = new ThreadCalculator(currIndex, currIndex + eachThreadJob);
                else    // i is the last thread
                    threadCalculators[numOfThreads - 1] = new ThreadCalculator(currIndex, vectorSize);
            else
                threadCalculators[i] = new ThreadCalculator();
        }


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