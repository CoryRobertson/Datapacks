package com.github.coryrobertson.resonancecascade;

import org.junit.jupiter.api.*;
import java.util.Random;

class MainTest
{

    final int dimensionCountTest = 250;
    final int genSettingsAvailable = Main.genSettingsAvailable.length;


    @Test
    /**
     *  0 seed is a number between 1 and 999999999
     *  1 generation setting is a number between -1,0,1,2,3,4,5
     *  2 how many dimensions is a number greater than 1 and capped at (5000)? tentative?
     */
    void mainMinArgs()
    {
        Random rand = new Random(System.currentTimeMillis());
        int checkedCorrectGen = 0;
        System.out.println("Gen settings available: " + genSettingsAvailable);

        for(int i = -1; i < genSettingsAvailable - 1; i++)
        {
            System.out.println("Gen setting tested: " + i);
            String[] args = {rand.nextInt(999999999) + "", i + "", dimensionCountTest + ""};
            Main.main(args);
            checkedCorrectGen++;
        }
        Assertions.assertEquals(genSettingsAvailable,checkedCorrectGen);
    }



    @Test
    /**
     *  0 seed is a number between 1 and 999999999
     *  1 generation setting is a number between -1,0,1,2,3,4,5
     *  2 how many dimensions is a number greater than 1 and capped at (5000)? tentative?
     *  3 min time stay is optional and is at least greater than or equal to 1
     *  4 max time stay is  optional and is at least greater than or equal to 1
     */
    void mainMaxArgs()
    {
        Random rand = new Random(System.currentTimeMillis());
        int checkedCorrectGen = 0;
        System.out.println("Gen settings available: " + genSettingsAvailable);
        final int min = 1;
        final int max = 90;
        for(int i = -1; i < genSettingsAvailable - 1;i++)
        {
            int minTimeStayTest = rand.nextInt(max - min) + min;
            int maxTimeStayTest = rand.nextInt(max - min + 1) + min + 1;

            System.out.println("Gen setting tested: " + i);
            String[] args = {rand.nextInt(999999999) + "", i + "", dimensionCountTest + "",minTimeStayTest + "",maxTimeStayTest + ""};
            Main.main(args);
            checkedCorrectGen++;
        }
        Assertions.assertEquals(genSettingsAvailable,checkedCorrectGen);
    }

    @Test
    /**
     *  0 seed is a number between 1 and 999999999
     *  1 generation setting is a number between -1,0,1,2,3,4,5
     *  2 how many dimensions is a number greater than 1 and capped at (5000)? tentative?
     *  3 min time stay is optional and is at least greater than or equal to 1
     *  4 max time stay is  optional and is at least greater than or equal to 1
     */
    void mainManualArgs()
    {
        String[] args = {159 + "",0 + "",dimensionCountTest + "",1 + "",3 + ""};
        Main.main(args);
    }

}