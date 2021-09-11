package com.github.coryrobertson.resonancecascade.generators;

/**
 * This is a subclass of the generator class and has only floating islands
 */
public class GeneratorFloatingIslands extends Generator
{
    public GeneratorFloatingIslands(int genSeed, String dimName)
    {
        super(genSeed, dimName);
        genSettings = new String[]{"minecraft:floating_islands"};
        generatorName = "Floating islands";
    }

    public GeneratorFloatingIslands(int genSeed, String dimName, int minTimeStay, int maxTimeStay)
    {
        super(genSeed, dimName, minTimeStay, maxTimeStay);
        genSettings = new String[]{"minecraft:floating_islands"};
        generatorName = "Floating islands";
    }


}
