package com.base.ee001_3_baseCharacter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//https://www.yuque.com/hollis666/rvd7xq/bwxlms
public class ExceptionModifyTest {

    public static void start(){
        throw new RuntimeException("Not able to start");
    }


    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("file.java"));
            while((line = br.readLine()) != null){
                start();
            }
//            start();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Finally");
        }
    }



}
