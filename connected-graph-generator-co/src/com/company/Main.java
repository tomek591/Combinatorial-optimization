package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws IOException {

        int saturation = 50; // percent of saturation in graph
        int numberOfVertex = 8;
        float numberOfEdges = (numberOfVertex * (numberOfVertex - 1)) / (2 * (100 / (float) saturation));
        int[][] adjacencyMatrix = new int[numberOfVertex][numberOfVertex];

        fillMatrix(adjacencyMatrix);

        doConnection(adjacencyMatrix);

        doSaturation(adjacencyMatrix, numberOfVertex, numberOfEdges);

        displayMatrix(adjacencyMatrix);

        generateFileWithGraph(adjacencyMatrix, numberOfVertex);
    }

    public static void fillMatrix(int[][] adjacencyMatrix) {

        for (int[] matrix : adjacencyMatrix) {

            Arrays.fill(matrix, 0);

        }
    }

    public static void displayMatrix(int[][] adjacencyMatrix) {

        for (int[] matrix : adjacencyMatrix) {

            System.out.println(Arrays.toString(matrix));

        }
    }

    public static void doConnection(int[][] adjacencyMatrix) {

        for (int i = 0; i < adjacencyMatrix.length - 1; i++) {

            adjacencyMatrix[i][i + 1] = 1;
            adjacencyMatrix[i + 1][i] = 1;

        }
    }

    public static void doSaturation(int[][] adjacencyMatrix, int numberOfVertex, float numberOfEdges) {

        int edgesCounter = numberOfVertex - 1;
        int triesNumber = 0;

        while (numberOfEdges > edgesCounter) {

            triesNumber++;

            int vertex1 = ThreadLocalRandom.current().nextInt(0, numberOfVertex);
            int vertex2 = ThreadLocalRandom.current().nextInt(0, numberOfVertex);

            if (vertex1 == vertex2) continue; // if vertex tries to do edge to itself

            if (adjacencyMatrix[vertex1][vertex2] == 1) continue; // if edge already is

            adjacencyMatrix[vertex1][vertex2] = 1;
            adjacencyMatrix[vertex2][vertex1] = 1;

            edgesCounter++;

        }

        System.out.println("Number of edges in the graph: " + edgesCounter);
        System.out.println("Number of tries to do an edge: " + triesNumber);

    }

    public static void generateFileWithGraph(int[][] adjacencyMatrix, int numberOfVertex) throws IOException {

        FileWriter fileWriter = new FileWriter("graph.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(numberOfVertex);

        for (int i = 0; i < numberOfVertex - 1; i++) {

            for (int j = i + 1; j < numberOfVertex; j++) {

                if (adjacencyMatrix[i][j] == 1) {

                    printWriter.println((i + 1) + " " + (j + 1));  // indexing from one is required

                }
            }
        }

        printWriter.close();

    }
}
