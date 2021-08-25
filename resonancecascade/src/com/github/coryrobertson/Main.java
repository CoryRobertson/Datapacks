package com.github.coryrobertson;

//import java.util.Scanner;
//import java.io.File;
//import java.util.Random;
//import org.json.simple.parser.JSONParser;

/**
 * This class is used to take the user input and translate that to getting the program to run in the most full amount possible.
 * There are no necessary function to call here other than main().
 */
public class Main
{



    /**
     * The main function has 2 inputs:
     * Seed int, for rng generation
     * Generator type int, for deciding preset generation
     * @param args
     * Expected args are 0<seed> 1<generation setting> 2<how many dimensions> 3<min time stay> 4<max time stay>
     *
     */
    public static void main(String[] args)
    {
        boolean timeSet;
        int minTimeStay;
        int maxTimeStay;

        try
        {
            minTimeStay = Integer.parseInt(args[3]);
            maxTimeStay = Integer.parseInt(args[4]);
            timeSet = true;
            System.out.println("Running with custom time to stay in dimension...");
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Running without custom time to stay in dimension...");

            timeSet = false;
            minTimeStay = 0;
            maxTimeStay = 0;
        }

        try
        {

            //examples of help commands are "h", "-h", "help", "-help" but im just going to use contains "h" because that will probably work just great lmao
            if (args[0].contains("h"))
            {
                printHelp();
            }
            else // past this statement is reached by not putting in a help command
            {

                //if we fail any of these we immediately return an invalid command println
                int numDims = Integer.parseInt(args[2]); // this is the variable used to store how many dimensions to generate
                int seed = Integer.parseInt(args[0]); // this is the seed in which we used to generate our dimensions

                Generator[] dimensions = new Generator[numDims]; // this is an array of dimensions that gets populated after the coming for loop
                String[] teleportCommands = new String[numDims]; // this is an array of minecraft commands tied to the size of dimensions, we use this to teleport the player around

                int runningCount = 0; // this is a variable used to store how many ticks to add onto each teleport command

                for (int i = 0; i < numDims; i++)
                {
                    //storing the dimensions in an array just in case they are needed
                    Generator temp = getGenerator(Integer.parseInt(args[1]),timeSet,seed,i,minTimeStay,maxTimeStay);

                    dimensions[i] = temp;

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

                for (Generator dim:dimensions)
                {
                    System.out.println(dim);
                }

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
        System.out.println("\nUsage: java -jar resonancecascade.jar <seed> <generation setting> <number of dimensions> <min time stay> <max time stay>");
        System.out.println("<seed> is a 9 or less digit number greater than 0");
        System.out.println("<generation setting> 0 (all biomes and generators),1 (no end),2 (no nether)");
        System.out.println("<number of dimensions> is the number of dimensions to generate, must be greater than 0");
        System.out.println("OPTIONAL <min time stay> is how long the player must stay at minimum in each dimension (in increments of ~9 seconds)");
        System.out.println("OPTIONAL <max time stay> is the upper bound of how long the player can be in each dimension (in increments of ~9 seconds)");

    }

    /**
     *This function allows people to easily implement subclasses of the Generator class to more easily make special generators.
     * @return a Generator based on the given parameters
     */
    private static Generator getGenerator(int genSetting, boolean timeSet, int seed, int i,int minTimeStay,int maxTimeStay)
    {
        //CLEANUP: there has to be a better way to do this
        switch(genSetting)
        {
            case 0:
                if(timeSet)
                    return new Generator(seed + i * seed, i + "",minTimeStay,maxTimeStay);
                else
                    return new Generator(seed + i * seed, i + "");
            case 1:
                if(timeSet)
                    return new GeneratorNoEnd(seed + i * seed, i + "",minTimeStay,maxTimeStay);
                else
                    return new GeneratorNoEnd(seed + i * seed, i + "");
            case 2:
                if(timeSet)
                    return new GeneratorNoNether(seed + i * seed, i + "",minTimeStay,maxTimeStay);
                else
                    return new GeneratorNoNether(seed + i * seed, i + "");

            default:
                System.out.println("Unexpected value: " + genSetting + " check if generator setting is a valid selection.");
                printHelp();
                throw new IllegalStateException();
        }
    }

}
