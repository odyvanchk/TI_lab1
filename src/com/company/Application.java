package com.company;

import com.company.cipher.Column;
import com.company.cipher.RailFence;
import com.company.cipher.RotatingLattice;
import com.company.cipher.Vigener;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        System.out.println("Choose cipher:");
        System.out.println("1. Column");
        System.out.println("2. Rotating Lattice");
        System.out.println("3. Rail Fence");
        System.out.println("4. Vigener");

        ;

        boolean notExit = true;
        while (notExit) {

            Scanner in = new Scanner(System.in);
            System.out.println("Enter number:");
            int number = in.nextInt();

            switch (number) {
                case 1 -> {
                    Column column = new Column();
                    System.out.println(column.encrypt());
                    System.out.println(column.decrypt());
                    break;
                }
                case 2 -> {
                    RotatingLattice rotatingLattice = new RotatingLattice();
                    System.out.println(rotatingLattice.encrypt());
                    System.out.println(rotatingLattice.decrypt());
                    break;
                }
                case 3 -> {
                    RailFence railFence = new RailFence();
                    System.out.println(railFence.encrypt());
                    System.out.println(railFence.decrypt());
                    break;
                }
                case 4 -> {
                    Vigener vigener = new Vigener();
                    System.out.println(vigener.encrypt());
                    System.out.println(vigener.decrypt());
                    break;
                }
                case 5 -> {
                    notExit = false;
                    break;
                }
            }
        }
    }

}