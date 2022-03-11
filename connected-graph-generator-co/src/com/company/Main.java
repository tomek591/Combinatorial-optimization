package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int saturation = 50; // percent of saturation in graph
        int numberOfVertex = 8;
        float numberOfEdges = (numberOfVertex * (numberOfVertex - 1)) / (2 * (100 / (float) saturation));
        int[][] adjacencyMatrix = new int[numberOfVertex][numberOfVertex];

        fillMatrix(adjacencyMatrix);

    }

    public static void fillMatrix(int[][] adjacencyMatrix) {

        for (int[] matrix : adjacencyMatrix) {

            Arrays.fill(matrix, 0);

        }
    }
}
