package com.nariki.tpks.lab1.util;

import java.io.*;

/*
 Для инкапсуляции логики записи в файл
 */
public class FileWriter {

    public void writeArrayOfBitVectorsInFile(byte[] arr, String fileName) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName, "UTF-8");

            for(int i = 0; i < arr.length; i++) {
                writer.println(Integer.toBinaryString(arr[i]));
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
        try {
            writer = new PrintWriter(fileName, "UTF-8");

            for(int i = 0; i < arr.length; i++) {
                writer.println(Integer.toBinaryString(arr[i]));
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
        try {
            writer = new PrintWriter(fileName, "UTF-8");

            for(int i = 0; i < arr.length; i++) {
                writer.println(Integer.toBinaryString(arr[i]));
            }
            writer.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        /*Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[0].length; j++) {
                    writer.write(arr[i][j] + " ");
                }
                writer.write('\n');
            }

        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            *//*ignore*//*
            }
        }*/
    }
    public void writeArrayOfBitVectorsInFile(long[] arr, String fileName) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName, "UTF-8");

            for(int i = 0; i < arr.length; i++) {
                writer.println(Long.toBinaryString(arr[i]));
            }
            writer.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
