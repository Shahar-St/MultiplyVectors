package org.example;

import java.util.Random;
import java.util.Vector;

public class MultiplyVectors {

    public static void main(String[] args) {

        int vectorSize = Integer.parseInt(args[0]);
        int numOfThreads = Integer.parseInt(args[1]);

        if (numOfThreads > vectorSize)
            numOfThreads = vectorSize;

        Integer[] vector1 = setVector(vectorSize), vector2 = setVector(vectorSize);
        ThreadCalculator[] threadCalculators = new ThreadCalculator[numOfThreads];
        Vector<Long> results = new Vector<>();

        int eachThreadJob = vectorSize / numOfThreads;
        int currIndex = 0;
        for (int i = 0; i < numOfThreads - 1; ++i, currIndex += eachThreadJob)
            threadCalculators[i] = new ThreadCalculator(vector1, vector2, results, currIndex, currIndex + eachThreadJob);

        threadCalculators[numOfThreads - 1] = new ThreadCalculator(vector1, vector2, results, currIndex, vectorSize);

        for (ThreadCalculator t : threadCalculators)
            t.start();

        for (ThreadCalculator t : threadCalculators)
        {
            try
            {
                t.join();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        Long sum = 0L;
        for (Long result : results)
            sum += result;

        System.out.println(sum);
    }

    public static Integer[] setVector(int size) {

        Integer[] vector = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; ++i)
            vector[i] = random.nextInt();

        return vector;
    }
}