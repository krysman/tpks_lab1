package com.nariki.tpks.lab1.model;

/**
 * ����� AdjacencyMatrix ������������ ������ ������������� �������
 * ��������� � ��.
 */
public class AdjacencyMatrix {
    private int max = 16;
    private int [] matrix;

    public AdjacencyMatrix(int amountOfVertices){
        if (amountOfVertices <= max) {
            matrix = new int [amountOfVertices];
        }
    }


    public int[] getMatrix() {
        return matrix;
    }
}
