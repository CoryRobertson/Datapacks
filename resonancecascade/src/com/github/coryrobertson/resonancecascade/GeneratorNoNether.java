package com.github.coryrobertson.resonancecascade;

/**
 * This is a subclass of the generator class and has no nether biome or terrain
 */
public class GeneratorNoNether extends Generator
{
    public GeneratorNoNether(int genSeed, String dimName)
    {
        super(genSeed, dimName);
        effects = new String[]{"minecraft:overworld", "minecraft:the_end"};
        biomeSettings = new String[]{"minecraft:the_end", "minecraft:vanilla_layered", "minecraft:checkerboard","minecraft:fixed"};
        genSettings = new String[]{"minecraft:overworld", "minecraft:amplified", "minecraft:caves"};
        generatorName = "No Nether";
    }

    public GeneratorNoNether(int genSeed, String dimName, int minTimeStay, int maxTimeStay)
    {
        super(genSeed, dimName, minTimeStay, maxTimeStay);
        effects = new String[]{"minecraft:overworld", "minecraft:the_end"};
        biomeSettings = new String[]{"minecraft:the_end", "minecraft:vanilla_layered", "minecraft:checkerboard","minecraft:fixed"};
        genSettings = new String[]{"minecraft:overworld", "minecraft:amplified", "minecraft:caves"};
        generatorName = "No Nether";
    }


}
