package ot;

import java.io.FileOutputStream;
import java.io.File;

public class Test{
    public static void main(String[] args) {
        // System.out.println("Sample");

        File file = new File("test.txt");

        try(FileOutputStream fos = new FileOutputStream(file)){
            String test = "start";
            fos.write(test.getBytes());
        }catch(Exception e){
            System.out.println(e);
        }
    }
}