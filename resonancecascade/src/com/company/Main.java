package com.company;
//import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Main {

    public static void main(String[] args)
    {
        //
            writeFileContents(args[0]);
    }
    public static void writeFileContents(String arg)
    {
        arg = "./" + arg;
        File file = new File(arg);
        double time = System.currentTimeMillis();
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(time + "");
            System.out.println("Wrote data to file successfully.");

        }
        catch (IOException e)
        {
            System.out.println("ERROR failed to write to file, possible insufficient perms?");
        }
    }
}
