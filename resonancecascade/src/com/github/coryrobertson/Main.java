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
     * Expected args are <seed> <generation setting> <how many dimensions>
     *
     */
    public static void main(String[] args)
    {
        try
        {

            //examples of help commands are "h", "-h", "help", "-help" but im just gonna use contains "h" cause that will probably work just great lmao
            if (args[0].contains("h"))
            {
                printHelp();
            }
            else
            {

                int numDims = Integer.parseInt(args[2]); // this is the variable used to store how many dimensions to generate
                int seed = Integer.parseInt(args[0]); // this is the seed in which we use to generate our dimensions
                Generator[] dimenions = new Generator[numDims]; // this is an array of dimensions that gets populated after the comming for loop
                String[] teleportCommands = new String[numDims]; // this is an array of minecraft commands tied to the size of dimensions, we use this to teleport the player around

                int runningCount = 0; // this is a variable used to store how many ticks to add onto each teleport command

                for (int i = 0; i < numDims; i++)
                {
                    //storing the dimensions in an array just in case they are needed
                    Generator temp = new Generator(seed+i*seed, i + "");
                    dimenions[i] = temp;

                    //write the dimension out to a file where it goes
                    FileOutput.writeFileContents("./data/stuff/dimension_type/" + temp.getDimName() + ".json", temp.getDimensionTypeJSON());
                    FileOutput.writeFileContents("./data/stuff/dimension/" + temp.getDimName() + ".json", temp.getDimensionJSON());

                    //increment the time to stay running count
                    runningCount += temp.getTimeStay();

                    //add the current dimensions teleport command to the array to be written to a file
                    teleportCommands[i] = "execute as @a[scores={dimensionCount=" + i + ",timerEnd=" + runningCount + "..}] in stuff:" + i + " run function stuff:resonate";


                }
                //write the array out to a file
                FileOutput.writeArrayToFile("./data/stuff/functions/dimtele.mcfunction",teleportCommands,true);

            }

        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Missing command line args, showing help command.");
            printHelp();
        }

    }

    /**
     * Prints the help screen to console
     */
    private static void printHelp()
    {
        System.out.println("\nUsage: java -jar resonancecascade.jar <seed> <generation setting> <number of dimensions>");
        System.out.println("<seed> is a 9 or less digit number greater than 0");
        System.out.println("<generation setting> is 0 (for now)");
        System.out.println("<number of dimensions> is the number of dimensions to generate, must be greater than 0");
    }

}
