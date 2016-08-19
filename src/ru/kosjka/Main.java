package ru.kosjka;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

    public static void main(String[] args) throws IOException {
        RandomAccessFile in = null;
        try {
            in = new RandomAccessFile("goods", "r");
            for (int i=0;i<in.length()/140;i++) {
                byte[] byteNgoods = new byte[4];
                in.read(byteNgoods, 0, 4);
                int ngoods = java.nio.ByteBuffer.wrap(byteNgoods).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
                byte xbyte = in.readByte();
                byte[] tempId = new byte[xbyte];
                byte[] temp = new byte[131];
                in.read(tempId, 0, xbyte);
                in.read(temp, 0, 131-xbyte);
                String fullgoods = new String(tempId);
                byte[] bytePrice = new byte[4];
                in.read(bytePrice, 0, 4);
                int price = java.nio.ByteBuffer.wrap(bytePrice).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
                System.out.printf("Код: %d, Длина строки: %d, Товар: %s, Цена в копейках: %d копеек.\n", ngoods, xbyte, fullgoods, price);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
