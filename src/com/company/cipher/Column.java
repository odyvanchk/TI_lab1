package com.company.cipher;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Column {
    private String key;
    private StringBuilder fin;
    private final String src;
    private int widthMatrix;
    private int heightMatrix;
    private char [][] matrix;
    private final Vector<record> parsKey;



    public Column() {
        key = "";
        fin = new StringBuilder();
        Scanner in =new Scanner(System.in);
        System.out.println("Enter key:");
        key = in.nextLine();

        System.out.println("Enter source:");
        Scanner ins = new Scanner(System.in);
        src = ins.nextLine();

        matrix = initMatrix();
        parsKey = keyToInt();
    }


    //Зашифровка
    public String encrypt() {
        int count=0;

        //Заполнение матрицы исходной строкой
        for (int i = 0; i < heightMatrix; i++){
            for (int j = 0; j < widthMatrix; j++){
                if (count < src.length()) {
                    matrix[i][j] = src.charAt(count++);
                }
                else
                    break;
            }
        }

        //Формирование зашифрованной строки
        for (int i = 0; i < key.length(); i++){
            for (int j = 0; j < heightMatrix; j++){
                if ( matrix[j][parsKey.elementAt(i).first] != 0){
                        fin.append(matrix[j][parsKey.elementAt(i).first]);
                }
            }
        }
        return fin.toString();
    }

    public String decrypt() {
        StringBuilder initial = new StringBuilder();
        int a = 0;
        char [][] matrix2 = initMatrix();

        //Заполнение матрицы
        for (int i = 0; i< key.length(); i++) {
            for (int j = 0; j < heightMatrix - 1; j++) {
                matrix2[j][parsKey.elementAt(i).first] = fin.charAt(a++);
            }
            if (fin.length() % key.length() == 0 || fin.length() % key.length() > parsKey.elementAt(i).first)
                matrix2[heightMatrix - 1][parsKey.elementAt(i).first] = fin.charAt(a++);
        }

        //Формирование исходной строки
        for (int i = 0; i < heightMatrix; i++)
            for (int j = 0; j < widthMatrix; j++) {
                if (matrix2[i][j] != 0)
                    initial.append(matrix2[i][j]);
            }
        return initial.toString();
    }

    //Инициализация матрицы
    private char[][] initMatrix() {
        widthMatrix = key.length();
        heightMatrix = (src.length()/ widthMatrix +(src.length()% widthMatrix == 0 ? 0: 1));
        return new char[heightMatrix][widthMatrix];
    }


    //Преобразование ключа в Vector<record>
    private Vector<record> keyToInt () {
        Vector<record> keyVec = new Vector<record>(key.length());
        for (int i = 0; i < key.length(); i++) {
           keyVec.add(new record(key.charAt(i), i, 0));
        }
        Collections.sort(keyVec);
        for (int i = 0; i < key.length(); i++){
           keyVec.elementAt(i).afterSort = i;
        }
        return keyVec;
    }

    //Класс для хранения информации о каждом символе ключа
    private class record implements Comparable<record> {
        //Символ
       private final Character aChar;
       //Первоначальное положение символа внутри ключа
       private final Integer first;
       //Положение символа после сортировки
       private Integer afterSort;

        private record(char ch, int f,int a) {
           aChar = ch;
           first = f;
           afterSort = a;
        }


        //Переопределенный метод для сортировки Vector<record>
        @Override
        public int compareTo(record o) {
            return this.aChar.compareTo(o.aChar);
        }
    }
}
