package com.nariki.tpks.lab1.action;

/**
 * Created by Рамазан on 12.09.2015.
 * Данный класс инкапсулирует логику преобразования графа.
 * В конкретном случае - преобразования матрицы инцидентсности графа в матрицу смежности.
 */
public class MatrixConverter {
    private long maskStart = Long.parseLong("01", 2);   // начало дуги
    private long maskEnd = Long.parseLong("10", 2);     // конец дуги
    private long maskLoop = Long.parseLong("11", 2);    // петля
    private int [][] adjacencyMatrix;

    private void correctMasks(int amountOfVertices){
        amountOfVertices -= 1;
        maskStart <<= (amountOfVertices * 2);
        maskEnd <<= (amountOfVertices * 2);
        maskLoop <<= (amountOfVertices * 2);
    }

    public void convert(int vertices, long [] incidenceMatrix){
        //корректировка масок зависит от числа вершин графа
        correctMasks(vertices);
        //инициализация матрицы смежности
        adjacencyMatrix = new int [vertices][vertices];
        //переменные для обхода матрицы
        int pos1 = 0; //pos1 - номер слова в массиве
        int pos2 = 0; //pos2 - номер пары бит (нумерация слева направо)
        while(pos1 != vertices){
            if(pos1 == pos2){
                //ищем и считаем петли
                adjacencyMatrix[pos1][pos2] = getAmountOfLoops(pos2, incidenceMatrix);
            }else{
                //ищем параллельные дуги исходящие из pos1 и входящие в pos2
                adjacencyMatrix[pos1][pos2] = isIncident(pos1, pos2, incidenceMatrix);
            }
            //увеливаем переменные для обхода матрицы
            if(pos2 == (vertices -1)){
                pos2 = 0;
                pos1++;
            }else {
                pos2++;
            }
        }
    }

    private int getAmountOfLoops(int pos2, long [] incidenceMatrix){
        long mask = maskLoop >> (pos2 * 2);
        int loopCounter = 0;
        for (int i = 0; i < incidenceMatrix.length; i++) {
            if (incidenceMatrix[i] == mask) {
                loopCounter++;
            }
        }
        return loopCounter;
    }

    private int isIncident(int pos1, int pos2, long [] incidenceMatrix){
        long mask = ((maskStart >> (pos1 * 2)) | (maskEnd >> (pos2 * 2)));
        int lineCounter = 0;
        for (int i = 0; i < incidenceMatrix.length; i++){
            if (incidenceMatrix[i] == mask) {
                lineCounter++;
            }
        }
        return lineCounter;
    }

    public void printAM(){
        if (adjacencyMatrix != null) {
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix.length; j++) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}
