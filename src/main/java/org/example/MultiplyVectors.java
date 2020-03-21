package org.example;

import java.util.Random;

public class MultiplyVectors {

    public static void main(String[] args) {

        int n = 5, N = 5;
        Integer[] vector1 = setVector(n), vector2 = setVector(n);

        

    }

    public static Integer[] setVector(int size) {

        Integer[] vector = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; ++i)
            vector[i] = random.nextInt();

        return vector;
    }
}