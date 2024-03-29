package com.github.coryrobertson.resonancecascade;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The FileOutput class has one function, writeFileContents(string, string). This function writes the content do the file
 */
public class FileOutput
{
    /**
     * writes nessecary data to a file given a filename
     * @param filename the name of the file that will be written and the path where the file goes e.g. "./data/stuff/dimension/0.json"
     * @param data the data in the file
     */
    public static void writeFileContents(String path,String filename, String data) throws IOException
    {
        //filename = "./" + filename;

        File dir = new File(path);
        File file = new File(path + filename);

        if(dir.mkdir())//if directory doesn't exist, we create it and print that out.
        {
            System.out.println("Creating directory: " + path);
        }

        try (FileWriter fw = new FileWriter(file))
        {

            fw.write(data);
            System.out.println("Wrote data to file successfully. (" + path + filename + ")");
            fw.close();
        }
        catch (IOException e)
        {
            //System.out.println("ERROR failed to write to file, possible insufficient perms, or missing file structure?");
            e.printStackTrace();
            throw new IOException("ERROR in writeFileContents(), failed to write to file, possible insufficient perms, or missing file structure?");
        }
    }

    /**
     * Writes an array of strings to a file and has the ability to separate them to new lines with a setting
     * @param filename the name of the file (filetype not automatically appended)
     * @param data an array of strings to be written to the file
     * @param separate wheather to separate the array with newlines after each string
     */
    public static void writeArrayToFile(String filename, String[] data, boolean separate) throws IOException
    {
        File file = new File(filename);
        try (FileWriter fw = new FileWriter(file))
        {
            for (int i = 0; i < data.length; i++)
            {
                if(separate)
                {
                    fw.write(data[i] + "\n");
                }
                else
                {
                    fw.write(data[i]);
                }

            }
            System.out.println("Wrote data to file successfully. (" + filename + ")");

        }
        catch (IOException e)
        {
            //System.out.println("ERROR failed to write to file, possible insufficient perms, or missing file structure?");
            e.printStackTrace();
            throw new IOException("ERROR in writeArrayToFile(), failed to write to file, possible insufficient perms, or missing file structure?");
        }
    }
}
