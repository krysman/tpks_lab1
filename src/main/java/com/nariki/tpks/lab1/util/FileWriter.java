package com.nariki.tpks.lab1.util;

import java.io.*;

/*
 Для инкапсуляции логики записи в файл
 */
public class FileWriter {


    public void writeArrayOfBitVectorsInFile(char[] arr, int numberOfVertex, String fullFileName) {

        PrintWriter writer = null;
        int groupSize = 1;
        try {
            writer = new PrintWriter(fullFileName, "UTF-8");

            for(int j = 0; j < arr.length; j++) {
                StringBuilder result = new StringBuilder();
                for(int i = numberOfVertex - 1; i >= 0 ; i--) {
                    int mask = 1 << i;
                    result.append((arr[j] & mask) != 0 ? "1" : "0");

                    if (i % groupSize == 0)
                        result.append(" ");
                }
                result.replace(result.length() - 1, result.length(), "");
                writer.println(result.toString());
            }
            writer.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
