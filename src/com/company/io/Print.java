package com.company.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Print {
    public static void main(String[] args) {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("d:\\java\\study-java\\src\\com\\company\\log.dat");
            ps = new PrintStream(fos);
        }catch (IOException e){
            e.printStackTrace();
        }
        if (ps != null){
            System.setOut(ps);
        }
        int in = 0;
        for (char c = 0;c <= 6000;c++){
            System.out.print(c+"");
            if (in++ >=100){
                System.out.println();
                in = 0;
            }
        }
        ps.close();
    }
}
