package com.company.cipher;

import java.util.Scanner;

public class Vigener {
    private String key;
    private final String src;
    private StringBuilder fin;

    public Vigener() {
        key = "";
        Scanner in =new Scanner(System.in);
        System.out.println("Enter key:");
        key = in.nextLine();

        System.out.println("Enter source:");
        Scanner ins = new Scanner(System.in);
        src = ins.nextLine();

        makeKeyFinal();
    }


    public String encrypt() {
        fin = new StringBuilder();

        //Формирование зашифрованной строки
        for (int i = 0; i < src.length(); i++) {
            int x = (src.charAt(i) + key.charAt(i)) % 26;   //Смещение относительно ключа
            x += 'A';
            fin.append((char) x);
        }
        return fin.toString();
    }

    public String decrypt() {
        StringBuilder initial = new StringBuilder();
        //Формирование исходной строки
        for (int i = 0; i < fin.length(); i++){
            int x = (fin.charAt(i) - key.charAt(i) + 26) % 26;
            x += 'A';
            initial.append((char) x);
        }
        return initial.toString();
    }


    //Генерирование ключа
    private void makeKeyFinal() {
        int i = 0;
        int initialLength = key.length();
        while (src.length() > key.length()) {
            if (i == initialLength)
                i = 0;
            key += key.charAt(i++);
        }
    }

}
