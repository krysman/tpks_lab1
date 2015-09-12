package com.nariki.tpks.lab1.graph;

/**
 * Created by ������� on 10.09.2015.
 */
public class Main {
    public static void main(String [] args){
        MatrixConverter matrixConverter = new MatrixConverter();
        long [] mi = new long[7];
        mi[0] = Long.parseLong("11000000", 2);
        mi[1] = Long.parseLong("01100000", 2);
        mi[2] = Long.parseLong("00011000", 2);
        mi[3] = Long.parseLong("00000110", 2);
        mi[4] = Long.parseLong("10000001", 2);
        mi[5] = Long.parseLong("11000000", 2);
        mi[6] = Long.parseLong("00000110", 2);
        matrixConverter.convert(4, mi);
        matrixConverter.printAM();
        /*int dots = 3;
        int lines = 6;
        short [] mi = new short[lines];
        //�����
        short maskStart = Short.parseShort("010000", 2);
        short maskEnd = Short.parseShort("100000", 2);
        short maskLoop = Short.parseShort("110000", 2);
        //������
        mi[0] = Short.parseShort("110000", 2);
        mi[1] = Short.parseShort("011000", 2);
        mi[2] = Short.parseShort("001100", 2);
        mi[3] = Short.parseShort("001001", 2);
        mi[4] = Short.parseShort("010010", 2);
        mi[5] = Short.parseShort("100001", 2);
        //������� ���������
        short [] ms = new short[dots];
        //������
        int byteNum = 0;
        int pos = 0;
        while (byteNum != dots || pos != dots) {
            if (byteNum == pos){
                if (hasLoop(pos, mi, maskLoop)) {
                    setMSCell(byteNum, pos, ms, maskLoop);
                    System.out.println(Integer.toBinaryString(ms[byteNum]));
                }
            }else{
                if (isIncident(byteNum, pos, maskStart, maskEnd, mi)) {
                    setMSCell(byteNum, pos, ms, maskLoop);
                    System.out.println(Integer.toBinaryString(ms[byteNum]));
                }
            }
            //�������� ��������
            if (pos != dots){
                pos++;
            }else{
                byteNum++;
                pos = byteNum;
            }
        }
        System.out.println();*/
    }

    public static void setMSCell(int wordNum, int pos, short [] arr, short mask){
        mask = (short)( mask >> (pos * 2));
        arr[wordNum] = (short)(arr[wordNum] | mask);
    }

    public static boolean hasLoop(int pos, short [] arr, short maskLoop){
        maskLoop = (short)(maskLoop >> (pos * 2));
        for (int i = 0; i < arr.length; i++) {
            if (maskLoop == arr[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIncident(int pos1, int pos2, short maskStart, short maskEnd, short [] arr){
        short mask1 = (short)((maskStart >> (pos1 * 2)) | (maskEnd >> (pos2 * 2)));
        short mask2 = (short)((maskEnd >> (pos1 * 2)) | (maskStart >> (pos2 * 2)));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == mask1 || arr[i] == mask2) {
                return true;
            }
        }
        return false;
    }

}
