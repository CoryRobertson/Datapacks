package com.github.coryrobertson.resonancecascade.generators;

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
    private int minTimeStay = 1;
    private int maxTimeStay = 34;
    private int timeToStay = 1;
    private int height = 256;
    private String effect;
    private String biomeSetting;
    private String genSetting;

    //modifiable variables used for subclasses
    protected String generatorName = "Default";
    protected String[] infiniburnBlocks = {"minecraft:infiniburn_overworld"};
    protected String[] effects = {"minecraft:overworld","minecraft:the_nether","minecraft:the_end"};
    protected String[] biomeSettings = {"minecraft:the_end","minecraft:vanilla_layered","minecraft:checkerboard","minecraft:fixed"};
    protected String[] genSettings = {"minecraft:overworld","minecraft:nether","minecraft:amplified","minecraft:caves","minecraft:floating_islands"}; // turns out minecraft:the_end is just too difficult to get working here
    protected String[] allBiomes = {"minecraft:cold_ocean", "minecraft:deep_lukewarm_ocean", "minecraft:deep_ocean", "minecraft:birch_forest_hills", "minecraft:desert_hills", "minecraft:desert_lakes", "minecraft:end_barrens", "minecraft:desert", "minecraft:end_midlands", "minecraft:eroded_badlands", "minecraft:flower_forest", "minecraft:badlands", "minecraft:bamboo_jungle", "minecraft:badlands_plateau", "minecraft:deep_warm_ocean", "minecraft:end_highlands", "minecraft:forest", "minecraft:frozen_ocean", "minecraft:frozen_river", "minecraft:giant_spruce_taiga", "minecraft:giant_spruce_taiga_hills", "minecraft:giant_tree_taiga", "minecraft:giant_tree_taiga_hills", "minecraft:gravelly_mountains", "minecraft:ice_spikes", "minecraft:jungle", "minecraft:jungle_edge", "minecraft:jungle_hills", "minecraft:lukewarm_ocean", "minecraft:modified_badlands_plateau", "minecraft:modified_gravelly_mountains", "minecraft:modified_jungle", "minecraft:modified_jungle_edge", "minecraft:modified_wooded_badlands_plateau", "minecraft:mountain_edge", "minecraft:mountains", "minecraft:mushroom_field_shore", "minecraft:mushroom_fields", "minecraft:nether_wastes", "minecraft:ocean", "minecraft:plains", "minecraft:river", "minecraft:savanna", "minecraft:savanna_plateau", "minecraft:shattered_savanna", "minecraft:shattered_savanna_plateau", "minecraft:small_end_islands", "minecraft:snowy_beach", "minecraft:snowy_mountains", "minecraft:snowy_taiga", "minecraft:snowy_taiga_hills", "minecraft:snowy_taiga_mountains", "minecraft:snowy_tundra", "minecraft:soul_sand_valley", "minecraft:stone_shore", "minecraft:sunflower_plains", "minecraft:swamp", "minecraft:swamp_hills", "minecraft:taiga", "minecraft:taiga_hills", "minecraft:taiga_mountains", "minecraft:tall_birch_forest", "minecraft:tall_birch_hills", "minecraft:the_end", "minecraft:the_void", "minecraft:warm_ocean", "minecraft:warped_forest", "minecraft:wooded_badlands_plateau", "minecraft:wooded_hills", "minecraft:wooded_mountains", "minecraft:deep_cold_ocean", "minecraft:dark_forest_hills", "minecraft:dark_forest", "minecraft:bamboo_jungle_hills", "minecraft:basalt_deltas", "minecraft:beach", "minecraft:birch_forest", "minecraft:crimson_forest", "minecraft:deep_frozen_ocean"};
    //TODO: further understand how minecraft:the_end causes issues when used as a generator setting

    private Random rand;

    /**
     *
     * @param genSeed the seed used to generate a dimension
     * @param dimName the name of the dimension (which will be the filename as well)
     */
    public Generator(int genSeed, String dimName)
    {
        rand = new Random(genSeed);
        this.dimName = dimName;
    }

    /**
     *
     * @param genSeed the seed used to generate a dimension
     * @param dimName the name of the dimension (which will be the filename as well)
     * @param minTimeStay the minimum time to spend in this dimension (in increments of 9 seconds)
     * @param maxTimeStay the maximum time to spend in this dimension (in increments of 9 seconds)
     */
    public Generator(int genSeed, String dimName,int minTimeStay, int maxTimeStay)
    {
        this.minTimeStay = minTimeStay;
        this.maxTimeStay = maxTimeStay;
        rand = new Random(genSeed);
        this.dimName = dimName;

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
        String genSettings = getGenSettings();
        this.genSetting = genSettings;
        gen.put("settings", genSettings);//this is depended on upon by getBiomeSettings();
        gen.put("seed", dimSeed); // sets the seed to the dimension
        gen.put("type", "minecraft:noise");// I don't know of any more interesting noise functions

        //add settings to the biome object
        biome.put("seed", dimSeed);
        biomeSetting = getBiomeSettings(genSettings);
        //biomeSetting = "minecraft:checkerboard";
        biome.put("type", biomeSetting); // this needs to be dependent on the genSettings();


        if(biomeSetting.equals("minecraft:checkerboard"))// if the biome setting is checkerboard
        {
            JSONArray biomeList = new JSONArray();// create a json array
            int biomeCount = rand.nextInt(allBiomes.length - 1) + 1; // decide how many biomes will be put into this array
            //System.out.println("checkerboard");
            for(int i = 0; i < biomeCount; i++)
            {
                //populate this array
                biomeList.add(chooseRandomString(allBiomes));
            }
            biome.put("biomes",biomeList);// put the array into the json file
        }
        else if(biomeSetting.equals("minecraft:fixed"))// if the biome setting is fixed
        {
            //System.out.println("fixed");
            biome.put("biome",chooseRandomString(allBiomes));// pick a random biome and place it into the json file
        }

        // put the biome source object into the generator object
        gen.put("biome_source", biome);

        //put the generator which now has the biome source, and all of its settings, into the dimension object
        dimension.put("generator", gen);
        dimension.put("type","stuff:" + dimName);
        timeToStay = genTimeStay();
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
    private String getBiomeSettings(String genSettings)
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
        effect = getEffects();
        dimType.put("effects", effect);
        dimType.put("infiniburn", getInfiniburn());
        dimType.put("min_y", getMinY());
        height = getHeight();
        dimType.put("height", height);
        dimType.put("logical_height", getLogicalHeight());
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
        double retn = rand.nextDouble() * 1;
        return retn;
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
        return 0;
        //return rand.nextInt(10); //TODO: tentative
    }

    /**
     * The maximum height logical things will treat as the maximum for the dimension e.g. nether portals won't generate above here
     * @return logicalHeight
     */
    private int getLogicalHeight()
    {
        return height;
    }

    /**
     * The maximum height that blocks can exist (16-256)
     * @return height
     */
    private int getHeight()
    {
        //128 - 256
        return (rand.nextInt(8) + 8)* 16;
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

    /**
     * Used to determine how long the player will remain inside this dimension
     * @return a random number between min time stay and maxTimeStay+minTimeStay
     */
    private int genTimeStay()
    {

        int output = rand.nextInt(maxTimeStay)+minTimeStay;
        //System.out.println("timeStay = " + output);
        return output;
    }

    /**
     * generic getter for time to stay in the dimension
     * @return a number generated at the time of getDimensionJSON() called
     */
    public int getTimeStay()
    {
        return timeToStay;
    }

    @Override
    public String toString()
    {
        String retn = "";
        retn += "Name: " + dimName + "\n";
        retn += "Gen Name: " + generatorName + "\n";
        retn += "Effect: " + effect + "\n";
        retn += "Biome Setting: " + biomeSetting + "\n";
        retn += "Gen Setting: " + genSetting + "\n";
        retn += "Time to Stay: " + timeToStay + "\n";

        return retn;
    }

}
