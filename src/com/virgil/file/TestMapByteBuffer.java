package com.virgil.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by liuwujing on 14/12/10.
 */
public class TestMapByteBuffer {
    public static String filePath = "/Users/liuwujing/Downloads/PAY_LOG_TAG_2014-12-10-11.txt";

    public static void main(String[] args) throws Exception {
        readWithScanner(filePath);
    }

    public static void readWithScanner(String filePath) {
        //Create the file object
        File fileObj = new File(filePath);
        try (

                //Scanner object for reading the file
                Scanner scnr = new Scanner(fileObj)
        ) {
            //Reading each line of file using Scanner class
            while (scnr.hasNextLine()) {
                String strLine = scnr.nextLine();
                //print data on console
                System.out.println(strLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void readWithBuffer(String filePath) {
        try (
                //Open input stream for reading the text file MyTextFile.txt
                InputStream is = new FileInputStream(filePath);

                // create new input stream reader
                InputStreamReader instrm = new InputStreamReader(is);

                // Create the object of BufferedReader object
                BufferedReader br = new BufferedReader(instrm);

        ) {
            String strLine;
            // Read one line at a time
            while ((strLine = br.readLine()) != null) {
                // Print line
                System.out.println(strLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
