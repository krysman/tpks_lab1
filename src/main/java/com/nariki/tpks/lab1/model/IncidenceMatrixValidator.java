package com.nariki.tpks.lab1.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IncidenceMatrixValidator {

    /*
    *
    * строки в матрице могут содержать только: -1, 0, 1, 2.
    * причем в строке кроме множества нулей может быть либо -1 и 1, либо 2
    * строк(вершин) должно быть не больше n = 16
    * столбцев(дуг) должно быть не больше k = (n*(n-1))/2
    *
     */

    private static final int MAX_VERTEX_NUMBER = 16;
    private static final int MAX_EDGE_NUMBER = (MAX_VERTEX_NUMBER * (MAX_VERTEX_NUMBER - 1)) / 2;

    private int [][] incidenceMatrix;
    List<List<Integer>> matrixAsList;

    private String [] matrixToCheck;
    private String errorString;

    public IncidenceMatrixValidator(String [] matrixToCheck) {

        this.matrixToCheck = matrixToCheck;
    }

    public boolean isIncidenceMatrixValid() {
        if(isColumnNumberOk()) {
            if(createAndCheckIncidenceMatrix()) {
                if(isRowNumberOk()) {
                    if(isNumberOfEdgesInOneVertexLessOrEqualsThree()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    public String getErrorString() {
        return errorString;
    }

    public List<List<Integer>> getMatrixAsList() {
        return matrixAsList;
    }

    public int[][] getIncidenceMatrix() {
        return incidenceMatrix;
    }

    private boolean isColumnNumberOk() {
        if( MAX_VERTEX_NUMBER < matrixToCheck.length) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return true if matrix is OK, otherwise false. integer form of matrix placed in class field incidenceMatrix
     */
    private boolean createAndCheckIncidenceMatrix() {

        matrixAsList = new ArrayList<>();

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
                    j++; // т.к. -1 занимает 2(два) символа
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
            matrixAsList.add(currentRow);
        }

        // получаем матрицу инцидентности в виде двумерного массива
        int numberOfColumns = matrixAsList.size();
        int numberOfRows = matrixAsList.get(0).size();
        incidenceMatrix = new int[numberOfColumns][numberOfRows];


        for(int i = 0; i < matrixAsList.size(); i++) {
            List<Integer> rowList = matrixAsList.get(i);
            for(int j = 0; j < rowList.size(); j++) {
                Integer x = rowList.get(j);
                incidenceMatrix[i][j] = x;
            }

        }
        return true;
    }

    /*
     * длинна всех строк должна быть одинакова
     */
    private boolean isRowNumberOk() {
        int rowLength = matrixAsList.get(0).size();
        for(List<Integer> list: matrixAsList) {
            if(list.size() != list.size()) {
                errorString = "Длинна строк должна быть одинакова! " + list.toString();
                return false;
            }
        }

        return true;
    }

    /*
     * в одну ввершину должно вести не более 3-х дуг. в матрице инцидентности это выражается присутствием 3-х и менее одинаковыс строк
     * данное ограничение связано с тем, что в матрице смежности(которая будет получена из данной матрици инцидентности)
     * для каждой вершины есть всего 2 бита, которыми можно закодировать цифры от нуля до трех
     */
    private boolean isNumberOfEdgesInOneVertexLessOrEqualsThree() {

        for(int i = 0; i < matrixToCheck.length; i++) {
            String lineWithoutSpaces = matrixToCheck[i].replaceAll("\\s+",""); // уберем все пробелы

            int counterOfEqualsStrings = 0;
            for(int j = i + 1; j < matrixToCheck.length; j++) {
                String currentLine = matrixToCheck[j].replaceAll("\\s+","");
                if(lineWithoutSpaces.equals(currentLine)) {
                    counterOfEqualsStrings++;
                }
            }

            if(2 < counterOfEqualsStrings) {
                errorString = "В одну вершину должно вести (из одной вершины исходить) не более 3-х дуг!";
                return false;
            }

        }
        return true;
    }

}
