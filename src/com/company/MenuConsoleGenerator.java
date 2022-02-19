package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class MenuConsoleGenerator {
    public static String menuConsoleGenerator(String[] menuTemp){
        String sMenu ="";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < menuTemp.length; i++) {
            String s = menuTemp[i];
            System.out.println(s);
        }

        System.out.print("Pilih Menu : ");
        try {
            sMenu = in.readLine();
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
        return sMenu;
    }

}
