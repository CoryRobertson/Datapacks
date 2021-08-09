package com.company;
import org.json.simple.*;

import java.util.Random;

/**
 * This class is meant to be an inherited class. The function of this class is to take an input of seed and output two JSON strings, one being generator type and generator for minecraft.
 */
public class Generator
{
    //default instance variables to generate the data
    private int minecraftSeedMin;
    private int minecraftSeedMax;
    private String dimName;
    private String infiniburnBlock = "minecraft:infiniburn_overworld";
    Random rand;

    public Generator(int genSeed, String dimName)
    {
        rand = new Random(genSeed);
        this.dimName = dimName;
        minecraftSeedMin = 0;
        minecraftSeedMax = 999999999;

    }

    /**
     * This function returns the json file for the dimension in the form of a string after generating all of the necessary data for it
     * @return the json dimension in string format
     */
    public String getDimensionJSON()
    {
        //create dimension, generator, and biome source object
        JSONObject dimension = new JSONObject();
        JSONObject gen = new JSONObject();
        JSONObject biome = new JSONObject();

        int dimSeed = getSeed();
        //String setting = getSettings();
        //add settings to the generator object
        gen.put("settings", getSettings());
        gen.put("seed", dimSeed); // sets the seed to the dimension
        gen.put("type", "minecraft:noise");// I dont know of any more interesting noise functions

        //add settings to the biome object
        biome.put("seed", dimSeed);
        biome.put("type", getSettings());

        // put the biome source object into the generator object
        gen.put("biome_source", biome);

        //put the generator which now has the biome source, and all of its settings, into the dimension object
        dimension.put("generator", gen);
        dimension.put("type","stuff:" + dimName);
        return dimension.toJSONString();
    }

    private int getSeed()
    {
        return rand.nextInt(minecraftSeedMax) + minecraftSeedMin;
    }

    private String getSettings()
    {
        int choice = rand.nextInt(6);
        switch (choice)
        {
            //kept redundancy for readability
            case 0:
                return "minecraft:overworld";
            case 1:
                return "minecraft:amplified";
            case 2:
                return "minecraft:nether";
            case 3:
                return "minecraft:caves";
            case 4:
                return "minecraft:end";
            case 5:
                return "minecraft:floating_islands";
            default:
                return "minecraft:overworld";
        }
    }

    /**
     * This function returns the json file for the dimension type in the form of a string after generating all of the necessary data for it
     * @return the json dimension type in string format
     */
    public String getDimensionTypeJSON()
    {
        JSONObject dimType = new JSONObject();
        dimType.put("ultrawarm", getUltraWarm());
        dimType.put("natural", getNatural());
        dimType.put("piglin_safe", getPiglinSafe());
        dimType.put("respawn_anchor_works", getRespawnAnchorWorks());
        dimType.put("bed_works", getBedWorks());
        dimType.put("has_raids", getHasRaids());
        dimType.put("has_skylight", getHasSkylight());
        dimType.put("has_ceiling", getHasCeiling());
        dimType.put("coordinate_scale", getCoordinateScale());
        dimType.put("ambient_light", getAmbientLight());
        dimType.put("logical_height", getLogicalHeight());
        dimType.put("effects", getEffects());
        dimType.put("infiniburn", getInfiniburn());
        dimType.put("min_y", getMinY());
        dimType.put("height", getHeight());
        //Wont implement FixedTime because I havent decided wheather to allow that to be a generator setting or randomly generated
        return dimType.toJSONString();
    }

    private boolean getUltraWarm()
    {
        return rand.nextBoolean();
    }

    private boolean getNatural()
    {
        return rand.nextBoolean();
    }

    private boolean getPiglinSafe()
    {
        return rand.nextBoolean();
    }

    private boolean getRespawnAnchorWorks()
    {
        return rand.nextBoolean();
    }

    private boolean getBedWorks()
    {
        return rand.nextBoolean();
    }

    private boolean getHasRaids()
    {
        return rand.nextBoolean();
    }

    private boolean getHasSkylight()
    {
        return rand.nextBoolean();
    }

    private boolean getHasCeiling()
    {
        return rand.nextBoolean();
    }

    private double getCoordinateScale()
    {
        return rand.nextDouble()*8 + 0.5;
    }

    private double getAmbientLight()
    {
        return rand.nextDouble() * 1;
    }

    private String getEffects()
    {
        int choice = rand.nextInt(4);
        switch(choice)
        {
            //default switch repeated for readability
            case 0:
                return "";
            case 1:
                return "minecraft:overworld";
            case 2:
                return "minecraft:the_nether";
            case 3:
                return "minecraft:the_end";
            default:
                return "";
        }
    }

    private String getInfiniburn()
    {
        return infiniburnBlock;
    }

    /**
     * The minimum height a block can exist in this dimension type
     * @return minY
     */
    private int getMinY()
    {
        return 0;
    }

    /**
     * The maximum height logical things will treat as the maximum for the dimension e.g. nether portals wont generate above here
     * @return logicalHeight
     */
    private int getLogicalHeight()
    {
        return 256;
    }

    /**
     * The maximum height that blocks can exist
     * @return height
     */
    private int getHeight()
    {
        return 256;
    }
}
