package com.company.io;

import java.io.*;

public class ObjectIo2 {
    public static void main(String[] args) throws Exception {
        T t = new T();
        t.k = 8;
        FileOutputStream fos = new FileOutputStream("d:\\java\\study-java\\src\\com\\company\\objectIo2.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(t);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream("d:\\java\\study-java\\src\\com\\company\\objectIo2.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        T tReader = (T)ois.readObject();
        System.out.println(tReader.i+" "+tReader.j+" "+tReader.d+" "+tReader.k);
    }
    static class T implements Serializable{
        int i =10;
        int j =9;
        double d =2.3;
        int k =0;
    }
}
