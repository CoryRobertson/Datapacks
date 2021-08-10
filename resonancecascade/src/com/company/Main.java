package com.company;
//import java.util.Scanner;


import java.io.File;
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
//        Generator gen = new Generator(1,"1");
        //Generator gen1 = new Generator(2,"2");

//        System.out.println(gen.getDimensionJSON());
//        System.out.println(gen.getDimensionTypeJSON());

        //FileOutput fO = new FileOutput();
        //FileOutput.writeFileContents("./dir.json","1");
//        FileOutput.writeFileContents("./data/stuff/dimension_type/1.json",gen.getDimensionTypeJSON());
//        FileOutput.writeFileContents("./data/stuff/dimension/1.json",gen.getDimensionJSON());

        //TODO: implement the use of a help statement as well as a way to check if the user inputs a valid command line arguement e.g. expect a seed, if none specified, tell the user to input one as well as a generator type.




        //TODO: implement use of args via command line to seed these dimensions e.g. dim 0 = seed, dim 1 = seed+i
        for (int i = 0; i < 10; i++)
        {
            Generator temp = new Generator(i,i + "");

            System.out.println(temp.getDimensionJSON());
            System.out.println(temp.getDimensionTypeJSON());
            FileOutput.writeFileContents("./data/stuff/dimension_type/" + i + ".json",temp.getDimensionTypeJSON());
            FileOutput.writeFileContents("./data/stuff/dimension/" + i + ".json",temp.getDimensionJSON());

        }
    }


}
