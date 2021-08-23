package com.github.coryrobertson;

//import java.util.Scanner;
//import java.io.File;
//import java.util.Random;
//import org.json.simple.parser.JSONParser;

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

        int numDims = 10;
        int seed = 0;
        Generator[] dimenions = new Generator[numDims];
        //String[] s = new String[]{"a","b","c"};
        //FileOutput.writeArrayToFile("test",s,false);
        String[] teleportCommands = new String[numDims];

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

                    //teleportCommands[i] = "execute as @a";

                    /*
                    Ticks to spend in dimension is the number that each dimension generates itself
                    RunningCount is the amount of ticks of all other previous dimensions spent, this allows us to just use a total amount of ticks in a minecraft world
                    Next dimension is the one next one in the list, so it would start with the vanilla overworld where the player is, and then after that dim0, then dim1, etc
                    This would look something like adding each dimensions own teleport function toa list itself.
                     */
                }
            }
            else
            {
                printHelp();
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("No command line args, showing help command.");
            printHelp();
        }
    }

    /**
     * Prints the help screen to console
     */
    private static void printHelp()
    {
        System.out.println("Usage: java -jar resonancecascade.jar <seed> <generation setting>");
        System.out.println("<seed> is a 9 or less digit number greater than 0 and <generation setting> is 0 (for now)");
    }

}
