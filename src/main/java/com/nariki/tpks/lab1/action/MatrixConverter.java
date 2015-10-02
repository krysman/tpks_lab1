package com.nariki.tpks.lab1.action;

import com.nariki.tpks.lab1.model.AdjacencyMatrix;

/**
 * Класс MatrixConverter - набор статических методов для обработки МИ.
 * А именно: преобразование МИ в МС.
 */
public class MatrixConverter {
    /*
    * maskStart - маска начальной вершины для матрицы инцидентности, маска одной дуги
    * между вершинами для матрицы смежности.
    * maskEbd - маска конечной вершины для матрицы инцидентности, маска  двух кратных
    * дуг для матрицы смежности.
    * maskLoop - маска петли для матрицы инцидентности
    */
    public static char[] convert(int vertices, long [] incidenceMatrix){
        long maskStart = Long.parseLong("01", 2);
        long maskEnd = Long.parseLong("10", 2);
        long maskLoop = Long.parseLong("11", 2);
        int aMask = 1;
        //корректировка масок зависит от числа вершин графа
        int amountOfVertices = vertices - 1;
        maskStart <<= (amountOfVertices * 2);
        maskEnd <<= (amountOfVertices * 2);
        maskLoop <<= (amountOfVertices * 2);
        aMask <<= amountOfVertices;
        //инициализация матрицы смежности
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(vertices);
        char [] result = adjacencyMatrix.getMatrix();
        //переменные для обхода матрицы
        int pos1 = 0; //pos1 - номер слова в массиве
        int pos2 = 0; //pos2 - номер пары бит (нумерация слева направо)
        while(pos1 != vertices){
            if(pos1 == pos2){
                //ищем и считаем петли
                if(hasLoop(pos2, incidenceMatrix, maskLoop)){
                    setAMCell(pos1, pos2, aMask, result);
                }
            }else{
                //ищем параллельные дуги исходящие из pos1 и входящие в pos2
                if(isIncident(pos1, pos2, incidenceMatrix, maskStart, maskEnd)){
                    setAMCell(pos1, pos2, aMask, result);
                }
            }
            //увеливаем переменные для обхода матрицы
            if(pos2 == (vertices -1)){
                pos2 = 0;
                pos1++;
            }else {
                pos2++;
            }
        }
        return result;
    }

    private static boolean hasLoop(int pos2, long[] incidenceMatrix, long maskLoop){
        long mask = maskLoop >> (pos2 * 2);
        for (long anIncidenceMatrix : incidenceMatrix) {
            if (anIncidenceMatrix == mask) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIncident(int pos1, int pos2, long [] incidenceMatrix, long maskStart, long maskEnd){
        long mask = ((maskStart >> (pos1 * 2)) | (maskEnd >> (pos2 * 2)));
        for (long anIncidenceMatrix : incidenceMatrix) {
            if (anIncidenceMatrix == mask) {
                return true;
            }
        }
        return false;
    }

    private static void setAMCell(int pos1, int pos2, int value, char [] adjacencyMatrix){
        value = value >> pos2;
        adjacencyMatrix[pos1] = (char) (adjacencyMatrix[pos1] | value);
    }
}
