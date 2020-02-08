package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadingSimpleFile {
    static File file = new File("C:\\Users\\taoLen\\Desktop\\JAVA\\UdacityOOP\\files"+"\\text.txt");
    static Scanner scanner;

    static {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ReadingSimpleFile() {
    }

    public static void main(String[] args) {
        int total = 0;
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            total = total + Integer.parseInt(line);
        }
        System.out.println(total);
    }
}
