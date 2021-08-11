package com.company;
import org.json.simple.*;

import java.util.Random;

/**
 * This class is meant to be an inherited class. The function of this class is to take an input of seed and output two JSON strings, one being generator type and generator for minecraft.
 */
public class Generator
{
    //default instance variables to generate the data
    private int minecraftSeedMin = 0;
    private int minecraftSeedMax = 999999999;
    private String dimName;


    private String[] infiniburnBlocks = {"minecraft:infiniburn_overworld"};
    private String[] effects = {"minecraft:overworld","minecraft:the_nether","minecraft:the_end"};
    private String[] biomeSettings = {"minecraft:the_end","minecraft:vanilla_layered","minecraft:vanilla_layered"};
    private String[] genSettings = {"minecraft:overworld","minecraft:the_end","minecraft:nether","minecraft:amplified","minecraft:caves"};


    Random rand;

    /**
     *
     * @param genSeed the seed used to generate a dimension
     * @param dimName the name of the dimension (which will be the filename as well)
     */
    public Generator(int genSeed, String dimName)
    {
        rand = new Random(genSeed);
        this.dimName = dimName;
        //infiniburnBlocks[0] = "minecraft:infiniburn_overworld";
    }

    /**
     * This function returns the json file for the dimension in the form of a string after generating all the necessary data for it
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
        gen.put("settings", getGenSettings());
        gen.put("seed", dimSeed); // sets the seed to the dimension
        gen.put("type", "minecraft:noise");// I don't know of any more interesting noise functions

        //add settings to the biome object
        biome.put("seed", dimSeed);
        biome.put("type", getBiomeSettings());

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

    /**
     *
     * @return a generator setting chosen at random from genSettings[]
     */
    private String getGenSettings()
    {
        /*
        minecraft:the_end
        minecraft:nether
        minecraft:floating_islands
        minecraft:amplified
        minecraft:caves
        minecraft:overworld
         */
        String retn = chooseRandomString(genSettings);

        return retn;
    }

    /**
     *
     * @return a biome setting chosen at random from biomeSettings[]
     */
    private String getBiomeSettings()
    {
        String retn = chooseRandomString(biomeSettings);

        return retn;
    }

    /**
     * This function returns the json file for the dimension type in the form of a string after generating all the necessary data for it
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
        //Won't implement FixedTime because I haven't decided whether to allow that to be a generator setting or randomly generated
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

    /**
     *
     * @return a coordinate scale randing from 0.5d to 8.5d
     */
    private double getCoordinateScale()
    {
        return rand.nextDouble()*8 + 0.5;
    }

    /**
     *
     * @return a light value ranging from 0.0d to 1.0d
     */
    private double getAmbientLight()
    {
        return rand.nextDouble() * 1;
    }

    /**
     *
     * @return an effect chosen randomly from effects[]
     */
    private String getEffects()
    {
        String retn = chooseRandomString(effects);

        return retn;
    }

    /**
     *
     * @return a block chosen randomly from infiniburnBlocks[]
     */
    private String getInfiniburn()
    {
        String retn = chooseRandomString(infiniburnBlocks);

        return retn;
    }

    /**
     * The minimum height a block can exist in this dimension type
     * @return minY
     */
    private int getMinY()
    {
        //TODO: add a range that this can be generated randomly in more interesting ways
        return 0;
    }

    /**
     * The maximum height logical things will treat as the maximum for the dimension e.g. nether portals won't generate above here
     * @return logicalHeight
     */
    private int getLogicalHeight()
    {
        //TODO: add a range that this can be generated randomly in more interesting ways
        return 256;
    }

    /**
     * The maximum height that blocks can exist
     * @return height
     */
    private int getHeight()
    {
        //TODO: add a range that this can be generated randomly in more interesting ways
        return 256;
    }

    /**
     * Returns the dimension name for name keeping purposes
     * @return the dimension name
     */
    public String getDimName()
    {
        return dimName;
    }

    /**
     * Chooses a random string from an array of strings to return
     * @param args, an array of strings
     * @return Single string chosen from input array
     */
    private String chooseRandomString(String[] args)
    {
        int n = rand.nextInt(args.length); // generate a random number between 0 and the length of the array

        return args[n];
    }

}
