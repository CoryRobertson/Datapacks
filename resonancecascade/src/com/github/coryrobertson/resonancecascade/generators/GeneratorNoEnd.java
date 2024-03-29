package com.github.coryrobertson.resonancecascade.generators;

/**
 * This is a subclass of the generator class and has no end biome or terrain.
 */
public class GeneratorNoEnd extends Generator
{
    public GeneratorNoEnd(int genSeed, String dimName, int minTimeStay, int maxTimeStay)
    {
        super(genSeed, dimName, minTimeStay, maxTimeStay);
        effects = new String[]{"minecraft:overworld", "minecraft:the_nether"};
        biomeSettings = new String[]{"minecraft:vanilla_layered", "minecraft:checkerboard", "minecraft:fixed"};
        genSettings = new String[]{"minecraft:overworld", "minecraft:nether", "minecraft:amplified", "minecraft:caves","minecraft:floating_islands"};
        generatorName = "No End";
    }
    public GeneratorNoEnd(int genSeed, String dimName)
    {
        super(genSeed, dimName);
        effects = new String[]{"minecraft:overworld", "minecraft:the_nether"};
        biomeSettings = new String[]{"minecraft:vanilla_layered", "minecraft:checkerboard", "minecraft:fixed"};
        genSettings = new String[]{"minecraft:overworld", "minecraft:nether", "minecraft:amplified", "minecraft:caves","minecraft:floating_islands"};
        generatorName = "No End";
    }
}
