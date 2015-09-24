package com.nariki.tpks.lab1.model;


public class IncidenceMatrix {

    private int numberOfVertex;
    private int[][] incidenceMatrix;

    private long[] matrixAsBitVectorLong;
    private int[] matrixAsBitVectorInt;
    private short[] matrixAsBitVectorShort;
    private byte[] matrixAsBitVectorByte;

    private String bitVectorType;

    public IncidenceMatrix(int [][] incidenceMatrix) {
        numberOfVertex = incidenceMatrix.length;
        this.incidenceMatrix = incidenceMatrix;

        // print incidence matrix
       /* System.out.println("Print incidence matrix");
        for(int i = 0; i < incidenceMatrix.length; i++) {
            for(int j = 0; j < incidenceMatrix[0].length; j++) {
                System.out.print(incidenceMatrix[i][j] + " ");
            }
            System.out.println();
        }*/


        createMatrixAsBitVector();
    }


    private void createMatrixAsBitVector() {

        if(numberOfVertex < 4) {
            createBitVectorOfBytes();
        } else if(numberOfVertex < 8) {
            createBitVectorOfShorts();
        } else if(numberOfVertex < 16) {
            createBitVectorOfInts();
        } else {
            createBitVectorOfLongs();
        }

        /*
        * при кодировании:
        *   |число|  |битовое представление|
        *     |0|            |00|
        *     |1|            |01|
        *     |-1|           |10|
        *     |2|            |11|
        * ходим по столбцам
        * начинаем с первого столбца
        * нацинаем с последнего элемента(самой нижней строки) - ему будут сопоставлены два самых правых бита и поднимаемся вверх
        * один столбец - один элемент массива типа long
        * 0     1
        * -1    0
        * 0    -1
        * 1    [0]↑
        *
        * Пример для второго столбца: (куча нулей)01001000
        * Пример для первого столбца: (куча нулей)00100001
         */
    }

    private void createBitVectorOfLongs() {
        bitVectorType = "LONG";
        matrixAsBitVectorLong = new long[incidenceMatrix[0].length];

        // j*2  -  указывает на позицию в битовом векторе
        for(int i = 0; i < matrixAsBitVectorLong.length; i++) {
            for(int j = incidenceMatrix.length - 1, position = 0; j >= 0; j--, position++) {
                // маски
                long maskForPositiveOne = 1;
                long maskForNegativeOne = 2;
                long maskForTwo = 3;

                if(incidenceMatrix[j][i] == 1) {
                    // сдвигаем маску на j*2 позиции влево и применяем операцию ИЛИ к i-му вектору
                    maskForPositiveOne = maskForPositiveOne << (position*2);
                    matrixAsBitVectorLong[i] = matrixAsBitVectorLong[i] | maskForPositiveOne;
                } else if(incidenceMatrix[j][i] == -1) {
                    maskForNegativeOne = maskForNegativeOne << (position*2);
                    matrixAsBitVectorLong[i] = matrixAsBitVectorLong[i] | maskForNegativeOne;
                } else if(incidenceMatrix[j][i] == 2) {
                    maskForTwo = maskForTwo << (position*2);
                    matrixAsBitVectorLong[i] = matrixAsBitVectorLong[i] | maskForTwo;
                }
            }
        }
    }

    private void createBitVectorOfInts() {
        bitVectorType = "INT";
        matrixAsBitVectorInt = new int[incidenceMatrix[0].length];

        // j*2  -  указывает на позицию в битовом векторе
        for(int i = 0; i < matrixAsBitVectorInt.length; i++) {
            for(int j = incidenceMatrix.length - 1, position = 0; j >= 0; j--, position++) {
                // маски
                int maskForPositiveOne = 1;
                int maskForNegativeOne = 2;
                int maskForTwo = 3;

                if(incidenceMatrix[j][i] == 1) {
                    // сдвигаем маску на j*2 позиции влево и применяем операцию ИЛИ к i-му вектору
                    maskForPositiveOne = maskForPositiveOne << (position*2);
                    matrixAsBitVectorInt[i] = matrixAsBitVectorInt[i] | maskForPositiveOne;
                } else if(incidenceMatrix[j][i] == -1) {
                    maskForNegativeOne = maskForNegativeOne << (position*2);
                    matrixAsBitVectorInt[i] = matrixAsBitVectorInt[i] | maskForNegativeOne;
                } else if(incidenceMatrix[j][i] == 2) {
                    maskForTwo = maskForTwo << (position*2);
                    matrixAsBitVectorInt[i] = matrixAsBitVectorInt[i] | maskForTwo;
                }
            }
        }
    }

    private void createBitVectorOfShorts() {
        bitVectorType = "SHORT";
        matrixAsBitVectorShort = new short[incidenceMatrix[0].length];

        // j*2  -  указывает на позицию в битовом векторе
        for(int i = 0; i < matrixAsBitVectorShort.length; i++) {
            short position = (short) 0;
            for(int j = incidenceMatrix.length - 1; j >= 0; j--, position++) {

                // маски
                short maskForPositiveOne = 1;
                short maskForNegativeOne = 2;
                short maskForTwo = 3;

                if(incidenceMatrix[j][i] == 1) {
                    // сдвигаем маску на j*2 позиции влево и применяем операцию ИЛИ к i-му вектору
                    maskForPositiveOne = (short) (maskForPositiveOne << (position * 2));
                    matrixAsBitVectorShort[i] = (short) (matrixAsBitVectorShort[i] | maskForPositiveOne);
                } else if(incidenceMatrix[j][i] == -1) {
                    maskForNegativeOne = (short) (maskForNegativeOne << (position*2));
                    matrixAsBitVectorShort[i] = (short) (matrixAsBitVectorShort[i] | maskForNegativeOne);
                } else if(incidenceMatrix[j][i] == 2) {
                    maskForTwo = (short) (maskForTwo << (position*2));
                    matrixAsBitVectorShort[i] = (short) (matrixAsBitVectorShort[i] | maskForTwo);
                }
            }
        }
    }

    private void createBitVectorOfBytes() {
        bitVectorType = "BYTE";
        matrixAsBitVectorByte = new byte[incidenceMatrix[0].length];

        // j*2  -  указывает на позицию в битовом векторе
        for(int i = 0; i < matrixAsBitVectorShort.length; i++) {
            byte position = (byte) 0;
            for(int j = incidenceMatrix.length - 1; j >= 0; j--, position++) {

                // маски
                byte maskForPositiveOne = 1;
                byte maskForNegativeOne = 2;
                byte maskForTwo = 3;

                if(incidenceMatrix[j][i] == 1) {
                    // сдвигаем маску на j*2 позиции влево и применяем операцию ИЛИ к i-му вектору
                    maskForPositiveOne = (byte) (maskForPositiveOne << (position * 2));
                    matrixAsBitVectorByte[i] = (byte) (matrixAsBitVectorByte[i] | maskForPositiveOne);
                } else if(incidenceMatrix[j][i] == -1) {
                    maskForNegativeOne = (byte) (maskForNegativeOne << (position*2));
                    matrixAsBitVectorByte[i] = (byte) (matrixAsBitVectorByte[i] | maskForNegativeOne);
                } else if(incidenceMatrix[j][i] == 2) {
                    maskForTwo = (byte) (maskForTwo << (position*2));
                    matrixAsBitVectorByte[i] = (byte) (matrixAsBitVectorByte[i] | maskForTwo);
                }
            }
        }
    }


    public String getBitVectorType() {
        return bitVectorType;
    }

    public long[] getMatrixAsBitVectorLong() {
        return matrixAsBitVectorLong;
    }

    public int[] getMatrixAsBitVectorInt() {
        return matrixAsBitVectorInt;
    }

    public short[] getMatrixAsBitVectorShort() {
        return matrixAsBitVectorShort;
    }

    public byte[] getMatrixAsBitVectorByte() {
        return matrixAsBitVectorByte;
    }

    public int getNumberOfVertex() {
        return numberOfVertex;
    }
}
