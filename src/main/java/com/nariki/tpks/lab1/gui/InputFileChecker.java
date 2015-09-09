package com.nariki.tpks.lab1.gui;

import java.io.File;

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

    public String  getErrorMessage() {
        return errorString;
    }

}
