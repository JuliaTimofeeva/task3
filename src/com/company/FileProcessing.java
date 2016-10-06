package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class FileProcessing {
    private String inFileName;
    private String outFileName;
    private ArrayList<String> strings;
    private ArrayList countRep;
    private ArrayList<String> strRep;

    public FileProcessing(String inFileName, String outFileName) {
        this.inFileName = inFileName;
        this.outFileName = outFileName;
        strings = new ArrayList<>();
        countRep = new ArrayList();
        strRep = new ArrayList();

    }


    private void intoArrayFromFile(){
        String str;

        try(BufferedReader reader = new BufferedReader (new FileReader(inFileName)))
        {
            while ((str = reader.readLine())!= null){

                strings.add(str.toString());
            }

        }
        catch(IOException ex){
            System.out.print("Нет входного файла");
        }
    }


    private void countingMatches(){
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
    }

    private void intoOutFile(){
        File file = new File(outFileName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter out = new FileWriter(outFileName, true);
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


    public void processFiles(){
        intoArrayFromFile();
        countingMatches();
        intoOutFile();
    }
}
