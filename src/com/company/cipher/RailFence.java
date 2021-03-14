package com.company.cipher;

import java.util.Scanner;

public class RailFence {
    private final int key;
    private final String src;
    private int widthMatrix;
    private char [][] matrix;
    private StringBuilder fin;


    public RailFence() {
        System.out.println("Enter key:");
        Scanner in = new Scanner(System.in);
        key = in.nextInt();

        System.out.println("Enter source:");
        Scanner ins = new Scanner(System.in);
        src = ins.nextLine();
    }



    public String encrypt () {
        fin = new StringBuilder();
        matrix = initMatrix();
        int row=0;
        boolean direction = false;

        //Заполнение матрицы
        for (int i = 0;i < src.length(); i++) {
            if (row == 0 || row == key - 1){
                direction = !direction;
            }
                matrix[row][i] = src.charAt(i);

            row += direction ? 1 : -1;
        }

        for (int i = 0; i < key; i++)
            for (int j = 0; j < widthMatrix; j++){
                if (matrix[i][j] != 0) {
                    fin.append(matrix[i][j]);
                }
            }
        return fin.toString();
    }


    public String decrypt() {
        StringBuilder initial = new StringBuilder();
        char[][] matrix2 = initMatrix();
        int row=0;
        boolean direction = false;

        //Установление ячеек, в которые будет записываться информация
        for (int i = 0;i < fin.length(); i++) {
            if (row == 0 || row == key - 1){
                direction = !direction;
            }
            matrix2[row][i] = '*';
            row += direction ? 1 : -1;
        }

        int a = 0;

        //Занесение в матрицу в правильном порядке
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < widthMatrix; j++) {
                if (matrix2[i][j] == '*')
                matrix2[i][j] = fin.charAt(a++);
            }
        }

        for (int i = 0; i < widthMatrix; i++) {
            for (int j = 0; j < key; j++) {
                if (matrix[j][i] != 0)
                initial.append(matrix[j][i]);
            }
        }
        return initial.toString();
    }


    private char[][] initMatrix() {
        widthMatrix = src.length();
        return new char[key][widthMatrix];
    }
}
