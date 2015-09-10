package com.nariki.tpks.lab1.gui;

import com.nariki.tpks.lab1.model.IncidenceMatrixValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InputFileChecker {

    private File fileToCheck;
    private String errorString;

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
                String [] matrix = readAllLinesFromFile();
                IncidenceMatrixValidator incidenceMatrixValidator = new IncidenceMatrixValidator(matrix);

                // TODO: проверка файла на содержание валидной матрицы инцидентности


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

    private String[] readAllLinesFromFile() {
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

        return (String[]) allStringsInFile.toArray();
    }

    public String  getErrorMessage() {
        return errorString;
    }

}
