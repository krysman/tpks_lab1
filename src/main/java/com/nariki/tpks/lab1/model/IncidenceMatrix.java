package com.nariki.tpks.lab1.model;


public class IncidenceMatrix {

    private int numberOfVertex;
    private long[] matrixAsBitVector;
    private int[][] incidenceMatrix;

    public IncidenceMatrix(int [][] incidenceMatrix) {
        numberOfVertex = incidenceMatrix.length;
        matrixAsBitVector = new long[incidenceMatrix[0].length];

        this.incidenceMatrix = incidenceMatrix;

        /*
        // print incidence Matrix
        for(int i = 0; i < incidenceMatrix.length; i++) {
            for(int j = 0; j < incidenceMatrix[0].length; j++) {
                System.out.print(incidenceMatrix[i][j] + " ");
            }
            System.out.println();
        }*/

        createMatrixAsBitVector(incidenceMatrix);
    }


    private void createMatrixAsBitVector(int [][] incidenceMatrix) {

        /*
        * при кодировании:
        *   |число|  |битовое представление|
        *     |0|            |00|
        *     |1|            |01|
        *     |-1|           |10|
        *     |2|            |11|
        * ходим по столбцам
        * нацинаем с последнего элемента(самой нижней строки) - ему будут сопоставлены два самых правых бита и поднимаемся вверх
        * один столбец - один элемент массива типа long
        * 0     1
        * -1    0
        * 0    -1
        * [1]↑  0
        *
        * Пример для первого столбца: (куча нулей)00100001
        * Пример для второго столбца: (куча нулей)01001000
         */


        // j*2  -  указывает на позицию в битовом векторе
        for(int i = 0; i < matrixAsBitVector.length; i++) {
            for(int j = numberOfVertex - 1; j >= 0; j--) {
                // маски
                long maskForPositiveOne = 1;
                long maskForNegativeOne = 2;
                long maskForTwo = 3;

                if(incidenceMatrix[j][i] == 1) {
                    // сдвигаем маску на j*2 позиции влево и применяем операцию ИЛИ к i-му вектору
                    maskForPositiveOne = maskForPositiveOne << (j*2);
                    matrixAsBitVector[i] = matrixAsBitVector[i] | maskForPositiveOne;
                } else if(incidenceMatrix[j][i] == -1) {
                    maskForNegativeOne = maskForNegativeOne << (j*2);
                    matrixAsBitVector[i] = matrixAsBitVector[i] | maskForNegativeOne;
                } else if(incidenceMatrix[j][i] == 2) {
                    maskForTwo = maskForTwo << (j*2);
                    matrixAsBitVector[i] = matrixAsBitVector[i] | maskForTwo;
                }
            }
        }

    }

    public long[] getMatrixAsBitVector() {
        return matrixAsBitVector;
    }
}
