package com.github.coryrobertson.resonancecascade;

import java.util.Random;
import java.util.Scanner;
//import java.io.File;
//import java.util.Random;
//import org.json.simple.parser.JSONParser;

/**
 * This class is used to take the user input and translate that to getting the program to run in the most full amount possible.
 * There are no necessary function to call here other than main().
 */
public class Main
{
    //working names for argument parse states
    static final int allValid = 0;
    static final int enoughValid = 1;
    static final int notEnoughValid = 2;
    static final int failedArgs = -1;


    static final String genSettingMessage = "<generation setting> 0 (all biomes and generators),1 (no end),2 (no nether)";
    static final int[] genSettingsAvailable = {0,1,2}; // this final is used to determine what gen settings are allowed in the system that parses the arguments

    /**
     * The main function has 2 inputs:
     * Seed int, for rng generation
     * Generator type int, for deciding preset generation
     *
     *     0 seed is a number between 1 and 999999999
     *     1 generation setting is a number between 0,1,2
     *     2 how many dimensions is a number greater than 1 and capped at (5000)? tentative?
     *     3 min time stay is optional and is at least greater than or equal to 1
     *     4 max time stay is  optional and is at least greater than or equal to 1
     *
     * @param args Expected args are 0<seed> 1<generation setting> 2<how many dimensions> 3<min time stay> 4<max time stay>
     */
    public static void main(String[] args)
    {
        //Scanner in = new Scanner(System.in);
        boolean timeSet; // this is a working variable used to determine if the user has or has not set a specific time to stay in dimensions.

        int minTimeStay = 999; // this is the minimum time the player will remain in a dimension
        int maxTimeStay = 999; // this is the maximum time the player will remain in a dimension
        int seed; // this is the seed in which we used to generate our dimensions
        int genSetting; // this is the setting to be applied to the generator
        int numDims; // this is the variable used to store how many dimensions to generate

        Random rand = new Random(System.currentTimeMillis());

        //TODO: https://stackoverflow.com/questions/2011664/compiling-a-java-program-into-an-executable
        //Expected args are 0<seed> 1<generation setting> 2<how many dimensions> 3<min time stay> 4<max time stay>
        int checkArgs = checkArgs(args);
        switch(checkArgs)//switch on the state of the arguments as they are parsed.
        {
            case allValid://this case is used when there are 5 valid arguments
                    System.out.println("Running with custom time to stay in dimension...");
                    seed = Integer.parseInt(args[0]);
                    genSetting = Integer.parseInt(args[1]);
                    numDims = Integer.parseInt(args[2]);
                    minTimeStay = Integer.parseInt(args[3]);
                    maxTimeStay = Integer.parseInt(args[4]);
                    timeSet = true;
                break;

            case enoughValid://this case is used when there are 3 valid arguments and no extra ones
                System.out.println("Running without custom time to stay in dimension...");
                    seed = Integer.parseInt(args[0]);
                    genSetting = Integer.parseInt(args[1]);
                    numDims = Integer.parseInt(args[2]);
                    timeSet = false;
                break;

            case notEnoughValid://this case is used when there is something wrong with the arguments
            case failedArgs://this case is used when there are extra(>5), or somehow a negative amount of arguments listed?
            default:
                //right here we will initialize all needed variables by asking the user for them
                Scanner in = new Scanner(System.in);
                System.out.println("Not enough command line arguments given, will now initialize them via prompts...\n");

                //prompt for seed
                System.out.println("What seed would you like to set for the generator(0 for random, must be lower than 999999999): ");
                seed = in.nextInt();
                if(seed == 0 || seed > 999999999 || seed < 0) // check if seed is valid
                {
                    seed = rand.nextInt(999999999);
                    System.out.println("Randomized seed: " + seed);
                }

                //prompt for numDims
                System.out.println("How many dimensions would you like to generate(recommend between 5-1000): ");
                numDims = in.nextInt();
                while(numDims <= 0)// this is triggered if the input number of dimensions is less than or equal to 0, we can't make negative dimensions.
                {
                    System.out.println("Enter a positive number for the number of dimensions please.");
                    System.out.println("How many dimensions would you like to generate(recommend between 5-1000): ");
                    numDims = in.nextInt();
                }

                //prompt for genSetting
                System.out.println("What dimension setting would you like to use: ");
                System.out.println(genSettingMessage);
                genSetting = in.nextInt();
                while(!checkEqualToAny(genSetting,genSettingsAvailable))//check if the user inputs a nonvalid generator setting
                {
                    System.out.println("What dimension setting would you like to use: ");
                    System.out.println(genSettingMessage);
                    genSetting = in.nextInt();
                }


                //prompt for time set
                System.out.println("What should the minimum time to stay in the dimensions be(0 for default): ");
                minTimeStay = in.nextInt();
                while(minTimeStay < 0)//check if the user puts a negative number, just in case
                {
                    System.out.println("What should the minimum time to stay in the dimensions be(0 for default): ");
                    minTimeStay = in.nextInt();
                }
                System.out.println("What should the maximum time to stay in the dimensions be(0 for default): ");
                maxTimeStay = in.nextInt();
                if(maxTimeStay <= 0) //check for if the user wants default dimension timing, we also catch less than just incase they do that
                {
                    timeSet = false;
                    minTimeStay = 0;
                    maxTimeStay = 0;
                } else
                {
                    timeSet = true;
                }


                break;

        }


        Generator[] dimensions = new Generator[numDims]; // this is an array of dimensions that gets populated after the coming for loop
        String[] teleportCommands = new String[numDims]; // this is an array of minecraft commands tied to the size of dimensions, we use this to teleport the player around

        int runningCount = 0; // this is a variable used to store how many ticks to add onto each teleport command

        for (int i = 0; i < numDims; i++) {
            //storing the dimensions in an array just in case they are needed
            Generator temp = getGenerator(genSetting, timeSet, seed, i, minTimeStay, maxTimeStay);
            System.out.println("MIN TIME STAY " + minTimeStay);
            System.out.println("MAX TIME STAY " + maxTimeStay);
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
        FileOutput.writeArrayToFile("./data/stuff/functions/dimtele.mcfunction", teleportCommands, true);

        for (Generator dim : dimensions) {
            System.out.println(dim);
        }

    }




    /**
     * Prints the help screen to console
     */
    private static void printHelp() {
        System.out.println("\nUsage: java -jar resonancecascade.jar <seed> <generation setting> <number of dimensions> <min time stay> <max time stay>");
        System.out.println("<seed> is a 9 or less digit number greater than 0");
        System.out.println(genSettingMessage);
        System.out.println("<number of dimensions> is the number of dimensions to generate, must be greater than 0");
        System.out.println("OPTIONAL <min time stay> is how long the player must stay at minimum in each dimension (in increments of ~9 seconds)");
        System.out.println("OPTIONAL <max time stay> is the upper bound of how long the player can be in each dimension (in increments of ~9 seconds)");

    }

    /**
     * This function allows people to easily implement subclasses of the Generator class to more easily make special generators.
     *
     * @return a Generator based on the given parameters
     */
    private static Generator getGenerator(int genSetting, boolean timeSet, int seed, int i, int minTimeStay, int maxTimeStay) {
        //CLEANUP: there has to be a better way to do this
        switch (genSetting)
        {
            case 0:
                if (timeSet)
                    return new Generator(seed + i * seed, i + "", minTimeStay, maxTimeStay);
                else
                    return new Generator(seed + i * seed, i + "");
            case 1:
                if (timeSet)
                    return new GeneratorNoEnd(seed + i * seed, i + "", minTimeStay, maxTimeStay);
                else
                    return new GeneratorNoEnd(seed + i * seed, i + "");
            case 2:
                if (timeSet)
                    return new GeneratorNoNether(seed + i * seed, i + "", minTimeStay, maxTimeStay);
                else
                    return new GeneratorNoNether(seed + i * seed, i + "");

            default:
                System.out.println("Unexpected value: " + genSetting + " check if generator setting is a valid selection.");
                printHelp();
                System.exit(1);
        }
        return null;
    }

    /**
     * Returns 0 if there are all 5 parameters and valid, 1 if there are 3 parameters and valid, and 2 if any amount of parameters but invalid, and -1 if unknown.
     * in the event that args is completely empty (or invalid), we can instead prompt the user for the arguments using java.util.Scanner.
     * @param args read following comments to create proper args
     * @return 0,1,2,-1
     *      0 seed is a number between 1 and 999999999
     *      1 generation setting is a number between 0,1,2
     *      2 how many dimensions is a number greater than 1 and capped at (5000)? tentative?
     *      3 (OPTIONAL)min time stay is optional and is at least greater than or equal to 1
     *      4 (OPTIONAL)max time stay is  optional and is at least greater than or equal to 1
     */
    private static int checkArgs(String[] args)
    {
        if (args.length == 5)
        {
            int seed,genSetting,numDims,minTimeStay,maxTimeStay;
            try
            {
                seed = Integer.parseInt(args[0]);
                genSetting = Integer.parseInt(args[1]);
                numDims = Integer.parseInt(args[2]);
                minTimeStay = Integer.parseInt(args[3]);
                maxTimeStay = Integer.parseInt(args[4]);
            } catch (NumberFormatException e) {return notEnoughValid;}

            //checking each argument if it is valid.
            if(seed <= 0 || seed > 999999999) {return notEnoughValid;}
            if(!checkEqualToAny(genSetting, genSettingsAvailable)) {return notEnoughValid;}
            if(numDims <= 0) {return notEnoughValid;}
            if(minTimeStay < 0) {return notEnoughValid;}
            if(maxTimeStay  < 0) {return notEnoughValid;}


            return allValid;
        }
        else if (args.length == 3)
        {
            int seed,genSetting,numDims;
            try
            {
                seed = Integer.parseInt(args[0]);
                genSetting = Integer.parseInt(args[1]);
                numDims = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {return notEnoughValid;}

            if(seed <= 0 || seed > 999999999) {return notEnoughValid;}
            if(!checkEqualToAny(genSetting, genSettingsAvailable)) {return notEnoughValid;}
            if(numDims <= 0) {return notEnoughValid;}

            return enoughValid; // returned if the args can be worked with
        }
        else if(args.length <= 2)
        {
            //here if there are less than needed amount of args, we print the help command
            printHelp();
            return notEnoughValid; // returned if args are incorrect or incompatible.
        }
        else
        {
            //if returned, we don't know what the user meant, and should probably just help them as if they put incorrect values.
            System.out.println("Unexpected amount of command line arguments, please check following help screen.\n");
            printHelp();
            return failedArgs; // only returns on error
        }

    }

    private static boolean checkEqualToAny(int input, int[] checks)
    {
        for(int i = 0; i < checks.length;i++)
        {
            if(checks[i] == input) {return true;}
        }

        return false;
    }

}
