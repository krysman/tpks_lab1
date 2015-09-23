package com.nariki.tpks.lab1.action;

/**
 * Класс MatrixConverter - набор статических методов для обработки МИ.
 * А именно: преобразование МИ в МС.
 */
public class MatrixConverter {
    /*
    * maskStart - маска начальной вершины для матрицы инцидентности, маска одной дуги
    * между вершинами для матрицы смежности.
    * maskEbd - маска конечной вершины для матрицы инцидентности, маска  двух кратных
    * дуг для матрицы смежности.
    * maskLoop - маска петли для матрицы инцидентности
    */
    public static long[] convert(int vertices, long [] incidenceMatrix){
        long maskStart = Long.parseLong("01", 2);
        long maskEnd = Long.parseLong("10", 2);
        long maskLoop = Long.parseLong("11", 2);
        //корректировка масок зависит от числа вершин графа
        int amountOfVertices = vertices - 1;
        maskStart <<= (amountOfVertices * 2);
        maskEnd <<= (amountOfVertices * 2);
        maskLoop <<= (amountOfVertices * 2);
        //инициализация матрицы смежности
        long[] adjacencyMatrix = new long [vertices];
        //переменные для обхода матрицы
        int pos1 = 0; //pos1 - номер слова в массиве
        int pos2 = 0; //pos2 - номер пары бит (нумерация слева направо)
        while(pos1 != vertices){
            if(pos1 == pos2){
                //ищем и считаем петли
                int res = getAmountOfLoops(pos2, incidenceMatrix, maskLoop);
                switch (res){
                    case 1: setAMCell(pos1, pos2, maskStart, adjacencyMatrix);break;
                    case 2: setAMCell(pos1, pos2, maskEnd, adjacencyMatrix);break;
                    case 3: setAMCell(pos1, pos2, maskLoop, adjacencyMatrix);break;
                }
            }else{
                //ищем параллельные дуги исходящие из pos1 и входящие в pos2
                int res = isIncident(pos1, pos2, incidenceMatrix, maskStart, maskEnd);
                switch (res){
                    case 1: setAMCell(pos1, pos2, maskStart, adjacencyMatrix);break;
                    case 2: setAMCell(pos1, pos2, maskEnd, adjacencyMatrix);break;
                    case 3: setAMCell(pos1, pos2, maskLoop, adjacencyMatrix);break;
                }
            }
            //увеливаем переменные для обхода матрицы
            if(pos2 == (vertices -1)){
                pos2 = 0;
                pos1++;
            }else {
                pos2++;
            }
        }
        return adjacencyMatrix;
    }

    public static int[] convert(int vertices, int [] incidenceMatrix){
        int maskStart = Integer.parseInt("01", 2);
        int maskEnd = Integer.parseInt("10", 2);
        int maskLoop = Integer.parseInt("11", 2);
        //корректировка масок зависит от числа вершин графа
        int amountOfVertices = vertices - 1;
        maskStart <<= (amountOfVertices * 2);
        maskEnd <<= (amountOfVertices * 2);
        maskLoop <<= (amountOfVertices * 2);
        //инициализация матрицы смежности
        int[] adjacencyMatrix = new int [vertices];
        //переменные для обхода матрицы
        int pos1 = 0; //pos1 - номер слова в массиве
        int pos2 = 0; //pos2 - номер пары бит (нумерация слева направо)
        while(pos1 != vertices){
            if(pos1 == pos2){
                //ищем и считаем петли
                int res = getAmountOfLoops(pos2, incidenceMatrix, maskLoop);
                switch (res){
                    case 1: setAMCell(pos1, pos2, maskStart, adjacencyMatrix);break;
                    case 2: setAMCell(pos1, pos2, maskEnd, adjacencyMatrix);break;
                    case 3: setAMCell(pos1, pos2, maskLoop, adjacencyMatrix);break;
                }
            }else{
                //ищем параллельные дуги исходящие из pos1 и входящие в pos2
                int res = isIncident(pos1, pos2, incidenceMatrix, maskStart, maskEnd);
                switch (res){
                    case 1: setAMCell(pos1, pos2, maskStart, adjacencyMatrix);break;
                    case 2: setAMCell(pos1, pos2, maskEnd, adjacencyMatrix);break;
                    case 3: setAMCell(pos1, pos2, maskLoop, adjacencyMatrix);break;
                }
            }
            //увеливаем переменные для обхода матрицы
            if(pos2 == (vertices -1)){
                pos2 = 0;
                pos1++;
            }else {
                pos2++;
            }
        }
        return adjacencyMatrix;
    }

    public static short[] convert(int vertices, short [] incidenceMatrix){
        short maskStart = Short.parseShort("01", 2);
        short maskEnd = Short.parseShort("10", 2);
        short maskLoop = Short.parseShort("11", 2);
        //корректировка масок зависит от числа вершин графа
        int amountOfVertices = vertices - 1;
        maskStart <<= (amountOfVertices * 2);
        maskEnd <<= (amountOfVertices * 2);
        maskLoop <<= (amountOfVertices * 2);
        //инициализация матрицы смежности
        short[] adjacencyMatrix = new short [vertices];
        //переменные для обхода матрицы
        int pos1 = 0; //pos1 - номер слова в массиве
        int pos2 = 0; //pos2 - номер пары бит (нумерация слева направо)
        while(pos1 != vertices){
            if(pos1 == pos2){
                //ищем и считаем петли
                int res = getAmountOfLoops(pos2, incidenceMatrix, maskLoop);
                switch (res){
                    case 1: setAMCell(pos1, pos2, maskStart, adjacencyMatrix);break;
                    case 2: setAMCell(pos1, pos2, maskEnd, adjacencyMatrix);break;
                    case 3: setAMCell(pos1, pos2, maskLoop, adjacencyMatrix);break;
                }
            }else{
                //ищем параллельные дуги исходящие из pos1 и входящие в pos2
                int res = isIncident(pos1, pos2, incidenceMatrix, maskStart, maskEnd);
                switch (res){
                    case 1: setAMCell(pos1, pos2, maskStart, adjacencyMatrix);break;
                    case 2: setAMCell(pos1, pos2, maskEnd, adjacencyMatrix);break;
                    case 3: setAMCell(pos1, pos2, maskLoop, adjacencyMatrix);break;
                }
            }
            //увеливаем переменные для обхода матрицы
            if(pos2 == (vertices - 1)){
                pos2 = 0;
                pos1++;
            }else {
                pos2++;
            }
        }
        return adjacencyMatrix;
    }

    public static byte[] convert(int vertices, byte [] incidenceMatrix){
        byte maskStart = Byte.parseByte("01", 2);
        byte maskEnd = Byte.parseByte("10", 2);
        byte maskLoop = Byte.parseByte("11", 2);
        //корректировка масок зависит от числа вершин графа
        int amountOfVertices = vertices - 1;
        maskStart <<= (amountOfVertices * 2);
        maskEnd <<= (amountOfVertices * 2);
        maskLoop <<= (amountOfVertices * 2);
        //инициализация матрицы смежности
        byte[] adjacencyMatrix = new byte [vertices];
        //переменные для обхода матрицы
        int pos1 = 0; //pos1 - номер слова в массиве
        int pos2 = 0; //pos2 - номер пары бит (нумерация слева направо)
        while(pos1 != vertices){
            if(pos1 == pos2){
                //ищем и считаем петли
                int res = getAmountOfLoops(pos2, incidenceMatrix, maskLoop);
                switch (res){
                    case 1: setAMCell(pos1, pos2, maskStart, adjacencyMatrix);break;
                    case 2: setAMCell(pos1, pos2, maskEnd, adjacencyMatrix);break;
                    case 3: setAMCell(pos1, pos2, maskLoop, adjacencyMatrix);break;
                }
            }else{
                //ищем параллельные дуги исходящие из pos1 и входящие в pos2
                int res = isIncident(pos1, pos2, incidenceMatrix, maskStart, maskEnd);
                switch (res){
                    case 1: setAMCell(pos1, pos2, maskStart, adjacencyMatrix);break;
                    case 2: setAMCell(pos1, pos2, maskEnd, adjacencyMatrix);break;
                    case 3: setAMCell(pos1, pos2, maskLoop, adjacencyMatrix);break;
                }
            }
            //увеливаем переменные для обхода матрицы
            if(pos2 == (vertices - 1)){
                pos2 = 0;
                pos1++;
            }else {
                pos2++;
            }
        }
        return adjacencyMatrix;
    }

    private static int getAmountOfLoops(int pos2, long [] incidenceMatrix, long maskLoop){
        long mask = maskLoop >> (pos2 * 2);
        int loopCounter = 0;
        for (long anIncidenceMatrix : incidenceMatrix) {
            if (anIncidenceMatrix == mask) {
                loopCounter++;
            }
        }
        return loopCounter;
    }

    private static int getAmountOfLoops(int pos2, int [] incidenceMatrix, int maskLoop){
        long mask = maskLoop >> (pos2 * 2);
        int loopCounter = 0;
        for (int anIncidenceMatrix : incidenceMatrix) {
            if (anIncidenceMatrix == mask) {
                loopCounter++;
            }
        }
        return loopCounter;
    }

    private static int getAmountOfLoops(int pos2, short [] incidenceMatrix, short maskLoop){
        long mask = maskLoop >> (pos2 * 2);
        int loopCounter = 0;
        for (short anIncidenceMatrix : incidenceMatrix) {
            if (anIncidenceMatrix == mask) {
                loopCounter++;
            }
        }
        return loopCounter;
    }

    private static int getAmountOfLoops(int pos2, byte [] incidenceMatrix, byte maskLoop){
        byte mask = (byte) (maskLoop >> (pos2 * 2));
        int loopCounter = 0;
        for (byte anIncidenceMatrix : incidenceMatrix) {
            if (anIncidenceMatrix == mask) {
                loopCounter++;
            }
        }
        return loopCounter;
    }

    private static int isIncident(int pos1, int pos2, long [] incidenceMatrix, long maskStart, long maskEnd){
        long mask = ((maskStart >> (pos1 * 2)) | (maskEnd >> (pos2 * 2)));
        int lineCounter = 0;
        for (long anIncidenceMatrix : incidenceMatrix) {
            if (anIncidenceMatrix == mask) {
                lineCounter++;
            }
        }
        return lineCounter;
    }

    private static int isIncident(int pos1, int pos2, int [] incidenceMatrix, int maskStart, int maskEnd){
        int mask = ((maskStart >> (pos1 * 2)) | (maskEnd >> (pos2 * 2)));
        int lineCounter = 0;
        for (int anIncidenceMatrix : incidenceMatrix) {
            if (anIncidenceMatrix == mask) {
                lineCounter++;
            }
        }
        return lineCounter;
    }

    private static int isIncident(int pos1, int pos2, short [] incidenceMatrix, short maskStart, short maskEnd){
        short mask = (short) ((maskStart >> (pos1 * 2)) | (maskEnd >> (pos2 * 2)));
        int lineCounter = 0;
        for (short anIncidenceMatrix : incidenceMatrix) {
            if (anIncidenceMatrix == mask) {
                lineCounter++;
            }
        }
        return lineCounter;
    }

    private static int isIncident(int pos1, int pos2, byte [] incidenceMatrix, byte maskStart, byte maskEnd){
        byte mask = (byte) ((maskStart >> (pos1 * 2)) | (maskEnd >> (pos2 * 2)));
        int lineCounter = 0;
        for (byte anIncidenceMatrix : incidenceMatrix) {
            if (anIncidenceMatrix == mask) {
                lineCounter++;
            }
        }
        return lineCounter;
    }

    private static void setAMCell(int pos1, int pos2, long value, long [] adjacencyMatrix){
        value = value >> (pos2 * 2);
        adjacencyMatrix[pos1] = adjacencyMatrix[pos1] | value;
    }

    private static void setAMCell(int pos1, int pos2, int value, int [] adjacencyMatrix){
        value = value >> (pos2 * 2);
        adjacencyMatrix[pos1] = adjacencyMatrix[pos1] | value;
    }

    private static void setAMCell(int pos1, int pos2, short value, short [] adjacencyMatrix){
        value = (short) (value >> (pos2 * 2));
        adjacencyMatrix[pos1] = (short) (adjacencyMatrix[pos1] | value);
    }

    private static void setAMCell(int pos1, int pos2, byte value, byte [] adjacencyMatrix){
        value = (byte) (value >> (pos2 * 2));
        adjacencyMatrix[pos1] = (byte) (adjacencyMatrix[pos1] | value);
    }
}
