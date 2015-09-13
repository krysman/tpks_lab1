package com.nariki.tpks.lab1.util;

import com.nariki.tpks.lab1.model.IncidenceMatrixValidator;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class InputFileChecker {

    private File fileToCheck;
    private String errorString;

    private int [][] incidenceMatrix;

    public InputFileChecker(File fileToCheck) {
        this.fileToCheck = fileToCheck;
    }

    public boolean isFileCorrect() {

        /*
         *файл корректен если он имеет расширение .txt, он не пустой, и его содержание соответствует матрице инцидентности
         */

        boolean fileEndsWithTxt = fileToCheck.getPath().endsWith(".txt");
        if(fileEndsWithTxt) {

            boolean fileIsNotEmpty = 0 < fileToCheck.length();
            if(fileIsNotEmpty) {

                // считать построчно содержание файла
                String [] matrix = getFileContentByLines();
                IncidenceMatrixValidator incidenceMatrixValidator = new IncidenceMatrixValidator(matrix);

                // проверка файла на содержание валидной матрицы инцидентности
                if(incidenceMatrixValidator.isIncidenceMatrixValid()) {
                    incidenceMatrix = incidenceMatrixValidator.getIncidenceMatrix();

                } else {
                    errorString = incidenceMatrixValidator.getErrorString();
                    return false;
                }

                return true;
            } else { // если файл пустой то он не корректный
                errorString = "Файл пустой!";
                return false;
            }
        } else { // если файл не *.txt то он не корректный
            errorString = "Расширение файла болжно быть .txt";
            return false;
        }
    }

    public int[][] getIncidenceMatrix() {
        return incidenceMatrix;
    }

    public String[] getFileContentByLines() {
        List<String> allStringsInFile = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileToCheck))) {
            String line;
            while( (line = br.readLine()) != null) {
                allStringsInFile.add(line);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        String [] result = new String[allStringsInFile.size()];
        for(int i = 0; i < allStringsInFile.size(); i++) {
            result[i] = allStringsInFile.get(i);
        }
        return result;
    }

    public String  getErrorMessage() {
        return errorString;
    }

}
