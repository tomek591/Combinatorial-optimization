package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	    int[][] adjacencyMatrix = readFromFileToMatrix();

        displayMatrix(adjacencyMatrix);

        greedyColoring(adjacencyMatrix);

        displayMatrix(adjacencyMatrix);
    }

    public static int[][] readFromFileToMatrix()
            throws IOException {
        String file = "C:\\Users\\tomas\\IdeaProjects\\combinatorial-optimization\\connected-graph-generator-co\\graph.txt";
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter("\r\n");

        int numberOfVertex = extractInt(scanner.next().trim(), 0);
        int[][] adjacencyMatrix = new int[numberOfVertex][numberOfVertex];

        while(scanner.hasNext()) {
            String edge = scanner.next().trim();
            int firstVertex = extractInt(edge, 0) - 1; // indexing from one in read file
            int secondVertex = extractInt(edge, 1) - 1;

            adjacencyMatrix[firstVertex][secondVertex] = 1;
            adjacencyMatrix[secondVertex][firstVertex] = 1;
        }
        scanner.close();
        return adjacencyMatrix;
    }

    public static int extractInt(String edge, int partOfString) {
        String[] helpArray = edge.split(" ");
        return Integer.parseInt(String.valueOf(helpArray[partOfString]));

    }

    public static void displayMatrix(int[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix[0].length; i++) {
            System.out.println(Arrays.toString(adjacencyMatrix[i]));
        }
        System.out.println("\n");
    }

    public static void greedyColoring(int[][] adjacencyMatrix) {
        int[][] graph = adjacencyMatrix;
        int length = adjacencyMatrix[0].length;

        for(int i = 0; i < length; i++) {
            int color = 2;
            for(int j = 0; j < i; j++) {
                if (graph[i][j] == color) {
                    j = -1;
                    color++;
                }
            }
            fillColumn(graph, color, i);
        }
        colorsOrder(graph);
    }

    public static void fillColumn(int[][] graph, int color, int column) {
        for(int i = 0; i < graph[0].length; i++) {
            if(graph[i][column] == 1) graph[i][column] = color;
        }
    }

    public static void colorsOrder(int[][] graph) {
        int length = graph[0].length;
        int[] order = new int[length];

        for(int i = 0; i < length - 1; i++) {
            for(int j = 0; j < length; j++) {
                if(graph[j][i] != 0) {
                    order[i] = graph[j][i] - 1;
                    break;
                }
            }
        }
        order[length - 1] = graph[length - 2][length - 1] - 1;

        System.out.println("Colors of Vertexes: " + Arrays.toString(order));
    }
}
