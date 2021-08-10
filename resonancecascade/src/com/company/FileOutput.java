package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The FileOutput class has one function, writeFileContents(string, string). This function writes the content do the file
 */
public class FileOutput
{
    /**
     * writes nessecery data to a file given a filename
     * @param filename the name of the file that will be written
     * @param data the data in the file
     */
    public static void writeFileContents(String filename, String data)
    {
        filename = "./" + filename;
        File file = new File(filename);
        double time = System.currentTimeMillis();
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(data);
            System.out.println("Wrote data to file successfully.");
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println("ERROR failed to write to file, possible insufficient perms, or missing file structure?");
        }
    }
}
