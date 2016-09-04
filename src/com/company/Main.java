package com.company;


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        ArrayList<String> strings = new ArrayList<>();
        ArrayList countRep = new ArrayList();
        ArrayList<String> strRep = new ArrayList();
        String str;

        try(BufferedReader reader = new BufferedReader (new FileReader("D:\\inFile.txt")))
        {
            while ((str = reader.readLine())!= null){

                strings.add(str.toString());
            }

        }
        catch(IOException ex){
            System.out.print("Нет входного файла");
        }

        Collections.sort(strings);
        int i = 0;
        int j = 0;
        int count = 0;
        while (i<strings.size()){
            while (j<strings.size()){
                if(strings.get(i).equals(strings.get(j))){
                    count++;
                }
                j++;
            }
            countRep.add(count);
           strRep.add(strings.get(i));
            while ((count >0)&&(strings.size()!=0)){
                strings.remove(0);
                count--;
            }
            j = 0;
            count = 0;
        }

        File file = new File("D:\\outFile.txt");

        try {
            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter out = new FileWriter("D:\\outFile.txt", true);
            String lineSeparator = System.getProperty("line.separator");

            try {
                while ((countRep.size()>0)&&(strRep!=null)) {
                    String a = countRep.get(0).toString();
                    out.append(strRep.get(0) + "[" + a + "]" + lineSeparator);
                    countRep.remove(0);
                    strRep.remove(0);
                }
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
