package com.github.coryrobertson.resonancecascade;

/**
 * This is a subclass of the generator class and has no end biome or terrain.
 */
public class GeneratorNoEndNoNether extends Generator
{
    public GeneratorNoEndNoNether(int genSeed, String dimName, int minTimeStay, int maxTimeStay)
    {
        super(genSeed, dimName, minTimeStay, maxTimeStay);
        effects = new String[]{"minecraft:overworld"};
        biomeSettings = new String[]{"minecraft:vanilla_layered"};
        genSettings = new String[]{"minecraft:overworld", "minecraft:amplified", "minecraft:caves"};
        generatorName = "No End No Nether";
    }
    public GeneratorNoEndNoNether(int genSeed, String dimName)
    {
        super(genSeed, dimName);
        effects = new String[]{"minecraft:overworld"};
        biomeSettings = new String[]{"minecraft:vanilla_layered"};
        genSettings = new String[]{"minecraft:overworld", "minecraft:amplified", "minecraft:caves"};
        generatorName = "No End No Nether";
    }
}
