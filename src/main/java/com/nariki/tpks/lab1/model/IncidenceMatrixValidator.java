package com.nariki.tpks.lab1.model;

import java.util.LinkedList;
import java.util.List;

public class IncidenceMatrixValidator {

    /*
    *
    * строки в матрице могут содержать только: -1, 0, 1, 2.
    * причем в строке кроме множества 0 может быть либо -1 и 1, либо 2
    * строк(вершин) должно быть не больше n = 16
    * столбцев(дуг) должно быть не больше k = (n*(n-1))/2
    *
     */

    private static final int MAX_VERTEX_NUMBER = 16;
    private static final int MAX_EDGE_NUMBER = (MAX_VERTEX_NUMBER * (MAX_VERTEX_NUMBER - 1)) / 2;

    private int [][] incidenceMatrix;
    private String [] matrixToCheck;
    private String errorString;

    public IncidenceMatrixValidator(String [] matrixToCheck) {

        this.matrixToCheck = matrixToCheck;

    }

    public boolean isIncidenceMatrixValid() {
        if(isRowNumberOk()) {
            if(getIntegerFormOfIncidenceMatrix()) {
                if(isColumnNumberOk()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getErrorString() {
        return errorString;
    }

    private boolean isRowNumberOk() {
        if( MAX_VERTEX_NUMBER < matrixToCheck.length) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return true if matrix is OK, otherwise false. integer form of matrix placed in class field incidenceMatrix
     */
    private boolean getIntegerFormOfIncidenceMatrix() {

        // получаем матрицу инцидентности в виде двумерного массива
        for(int i = 0; i < matrixToCheck.length; i++) {
            String lineWithoutSpaces = matrixToCheck[i].replaceAll("\\s+",""); // уберем все пробелы
            List<Integer> currentRow = new LinkedList<>();

            for(int j = 0; j < lineWithoutSpaces.length(); j++) {
                if((lineWithoutSpaces.charAt(j) == '0')) {
                    currentRow.add(0);
                    continue;
                } else if(lineWithoutSpaces.charAt(j) == '1') {
                    currentRow.add(1);
                    continue;
                } else if(lineWithoutSpaces.charAt(j) == '-') {
                    currentRow.add(-1);
                    i++; // т.к. -1 занимает 2(два) символа
                    continue;
                } else if(lineWithoutSpaces.charAt(j) == '2') {
                    currentRow.add(2);
                    continue;
                } else {
                    // ошибка, любые другие символы недопустимы
                    errorString = "Ошибка! Символ: " + lineWithoutSpaces.charAt(j) + " недопустим в строке: " + matrixToCheck[i];
                    return false;
                }
            }


        }

        return true;
    }

    private boolean isColumnNumberOk() {

        return false;
    }

}
