package com.company;


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        FileProcessing fileProcessing = new FileProcessing(args[0],args[1]);

        fileProcessing.processFiles();
    }
}
