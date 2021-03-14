package com.company.cipher;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class RotatingLattice {
    private final String src;
    private final int n;
    private char [][] Matrix;
    private int[][] lattice;
    private int [][] boolMatrixStart;
    private int [][]numbMatrix;
    private StringBuilder fin;

    public RotatingLattice() {
        System.out.println("Enter source:");
        Scanner ins = new Scanner(System.in);
        src = ins.nextLine();

        System.out.println("Enter n:");
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        if (n % 2 != 0)
            System.out.println("Incorrect input!");
        else {
                latticeInput(n);
                Matrix = new char [n][n];
                initMatrix();
                lattice = new int[n][n];
                if (! isRightMatrix())
                    System.out.println("Incorrect input!");
        }
    }


    public String encrypt() {
        int index = 0;
        fin = new StringBuilder();
        lattice = Arrays.stream(boolMatrixStart).map(int[]::clone).toArray(int[][]::new);

        //Занесение в матрицу исодной строки (с поворотом решетки)
        while (index < src.length()){
            for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                if (lattice[i][j] == 1 && index<src.length())
                    Matrix[i][j] = src.charAt(index++);
            }
            rotateMatrix(n, lattice);
        }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                fin.append(Matrix[i][j]);
            }
        return fin.toString();
    }


    public String decrypt() {
        lattice = Arrays.stream(boolMatrixStart).map(int[]::clone).toArray(int[][]::new);;
        StringBuilder initial = new StringBuilder();
        int index = 0;

        //Занесение в матрицу зашифрованной строки
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                Matrix[i][j] = fin.charAt(index++);
            }
        index = 0;

        //Формирование первоначальной строки
        for (int countTurns = 0; countTurns < 4; countTurns++){
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++){
                    if (lattice[i][j] == 1) {
                        initial.append(Matrix[i][j]);
                        index++;
                    }
                }
            rotateMatrix(n, lattice);
        }
        return initial.toString();
    }

    //Формирование матрицы цифр и булевой
    private void latticeInput(int n) {
         numbMatrix = new int[n][n];
         boolMatrixStart = new int[n][n];
        Scanner scanner = new Scanner(System.in);

        //Инициализация матриц
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                boolMatrixStart[i][j] = 0;
                numbMatrix[i][j] = 0 ;
            }

        int count = 1;
        //Заполнение матрицы цифр
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                numbMatrix[i][j] = count;
                numbMatrix[n - 1 - j][i] = count;
                numbMatrix[n - 1 - i][n - 1 - j] = count;
                numbMatrix[j][n - 1 - i] = count;
                count++;
            }
        }
        count--;

        //Вывод цифровой матрицы
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(numbMatrix[i][j] + " ");
            }
            System.out.println();
        }

        //"Вырезание отверстий" в логической матрице
        for (int i = 1; i <= count; i++) {
                System.out.println("Введите координаты отверстия "+ i);
                System.out.print("x: ");
                int x = scanner.nextInt();
                System.out.print("y: ");
                int y = scanner.nextInt() ;
                boolMatrixStart[x][y] = 1;
        }
    }

    //проверка матрицы boolMatrixStart на правильность
    private boolean isRightMatrix() {
        HashSet <Integer> numbers = new HashSet<Integer>();
        for (int i = 0; i < n; i++)
            for (int j = 0;j < n;j++){
                if (boolMatrixStart[i][j] == 1)
                if (! numbers.add(numbMatrix[i][j]))
                    return false;
            }
        return true;
    }

    //Поворот решетки
    static void rotateMatrix(int N, int mat[][]) {
        for (int x = 0; x < N / 2; x++) {
            for (int y = x; y < N - x - 1; y++) {
                int temp = mat[y][N - 1 - x];
                mat[y][N - 1 - x] = mat[x][y];
                mat[x][y] = mat[N - 1 - y][x];
                mat[N - 1 - y][x] = mat[N - 1 - x][N - 1 - y];
                mat[N - 1 - x][N - 1 - y] = temp;
            }
        }
    }

    //Заполнение матрицы рандомными символами
    private void initMatrix() {
        for (int i = 0; i < n ; i++)
            for(int j = 0 ; j < n ; j++)
                Matrix[i][j] = (char) ((int)(Math.random() * 26) + 'A') ;
    }

}
