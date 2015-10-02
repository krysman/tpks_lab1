package com.nariki.tpks.lab1.model;

/**
 * Класс AdjacencyMatrix инкапсулиует логику представления матрицы
 * смежности в ОП.
 */
public class AdjacencyMatrix {
    private int max = 16;
    private char [] matrix;

    public AdjacencyMatrix(int amountOfVertices){
        if (amountOfVertices <= max) {
            matrix = new char [amountOfVertices];
        }
    }


    public char[] getMatrix() {
        return matrix;
    }
}
