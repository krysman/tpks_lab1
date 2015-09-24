package com.nariki.tpks.lab1.util;

import java.io.*;

/*
 Для инкапсуляции логики записи в файл
 */
public class FileWriter {

    public void writeArrayOfBitVectorsInFile(byte[] arr, String fileName) {
        PrintWriter writer = null;
        int groupSize = 2;
        try {
            writer = new PrintWriter(fileName, "UTF-8");

            for(int j = 0; j < arr.length; j++) {
                StringBuilder result = new StringBuilder();
                for(int i = 7; i >= 0 ; i--) {
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
    public void writeArrayOfBitVectorsInFile(short[] arr, String fileName) {
        PrintWriter writer = null;
        int groupSize = 2;
        try {
            writer = new PrintWriter(fileName, "UTF-8");

            for(int j = 0; j < arr.length; j++) {
                StringBuilder result = new StringBuilder();
                for(int i = 15; i >= 0 ; i--) {
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

    public void writeArrayOfBitVectorsInFile(int[] arr, String fileName) {
        PrintWriter writer = null;
        int groupSize = 2;
        try {
            writer = new PrintWriter(fileName, "UTF-8");

            for(int j = 0; j < arr.length; j++) {
                StringBuilder result = new StringBuilder();
                for(int i = 31; i >= 0 ; i--) {
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
    public void writeArrayOfBitVectorsInFile(long[] arr, String fileName) {
        PrintWriter writer = null;
        int groupSize = 2;
        try {
            writer = new PrintWriter(fileName, "UTF-8");

            for(int j = 0; j < arr.length; j++) {
                StringBuilder result = new StringBuilder();
                for(int i = 631; i >= 0 ; i--) {
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
