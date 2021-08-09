package com.company;
//import java.util.Scanner;


import java.util.Random;

/**
 * This class is used to take the user input and translate that to getting the program to run in the most full amount possible.
 * There are no nessecery function to call here other than main().
 */
public class Main {

    /**
     * The main function has 2 inputs:
     * Seed int, for rng generation
     * Generator type int, for deciding preset generation
     * @param args
     */
    public static void main(String[] args)
    {
        Generator gen = new Generator(1,"1");
        //Generator gen1 = new Generator(2,"2");

        System.out.println(gen.getDimensionJSON());
        System.out.println(gen.getDimensionTypeJSON());

        //FileOutput fO = new FileOutput();
        //FileOutput.writeFileContents("./dir.json","1");
        FileOutput.writeFileContents("./data/stuff/dimension_type/1.json",gen.getDimensionTypeJSON());
        FileOutput.writeFileContents("./data/stuff/dimension/1.json",gen.getDimensionJSON());

    }


}
