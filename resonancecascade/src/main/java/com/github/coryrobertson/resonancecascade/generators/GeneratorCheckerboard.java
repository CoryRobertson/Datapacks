package com.github.coryrobertson.resonancecascade.generators;

public class GeneratorCheckerboard extends Generator {
    public GeneratorCheckerboard(int genSeed, String dimName)
    {
        super(genSeed, dimName);
        biomeSettings = new String[]{"minecraft:checkerboard"};
        generatorName = "Checkerboard";
    }

    public GeneratorCheckerboard(int genSeed, String dimName, int minTimeStay, int maxTimeStay)
    {
        super(genSeed, dimName, minTimeStay, maxTimeStay);
        biomeSettings = new String[]{"minecraft:checkerboard"};
        generatorName = "Checkerboard";
    }
}
