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
     * Expected args are <seed> <generation setting>
     */
    public static void main(String[] args)
    {

        int numDims = 2;
        int seed = 0;
        Generator[] dimenions = new Generator[numDims];

        try
        {
            //examples of help commands are "h", "-h", "help", "-help" but im just gonna use contains "h" cause that will probably work just great lmao
            if (args[0].contains("h"))
            {
                printHelp();
            }
            else if (Integer.parseInt(args[0]) >= 0 )
            {
                seed = Integer.parseInt(args[0]);

                for (int i = 0; i < numDims; i++)
                {
                    Generator temp = new Generator(seed+i, i + "");
                    //storing the dimensions in an array just in case they are needed
                    dimenions[i] = temp;
                    System.out.println(temp.getDimensionJSON());
                    System.out.println(temp.getDimensionTypeJSON());

                    FileOutput.writeFileContents("./data/stuff/dimension_type/" + temp.getDimName() + ".json", temp.getDimensionTypeJSON());
                    FileOutput.writeFileContents("./data/stuff/dimension/" + temp.getDimName() + ".json", temp.getDimensionJSON());

                }
            }
            else
            {
                printHelp();
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            printHelp();
        }
    }

    private static void printHelp()
    {
        System.out.println("Usage: java -jar resonancecascade.jar <seed> <generation setting>");
        System.out.println("<seed> is a 9 or less digit number greater than 0 and <generation setting> is 0 (for now)");
    }

}
