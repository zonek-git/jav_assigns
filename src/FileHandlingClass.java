/**
 * title: FileHandlingClass.java
 * description: FileHandlingClass class for Assignment 4
 * date: June 6, 2022
 * @author Joshua P.J. Vilcsak
 * @version 1.0
 * @copyright 2022 Joshua P.J.Vilcsak
 */

import java.io.*;
import java.util.HashMap;

public class FileHandlingClass {

    /**
     * System that displays my text files for "story" elements
     * @param filename the filename of the particular txt file to be retrieved
     */
    public void fileOutput(String filename) {
        try {
            File file = new File("src/" + filename + ".txt");
            FileReader readFile = new FileReader(file);
            BufferedReader displayFile = new BufferedReader(readFile);
            String readLine;
            while ((readLine = displayFile.readLine()) != null) {
                System.out.println(readLine);
            }
            displayFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Issue with finding file.");
            e.getStackTrace();
        } catch (IOException e) {
            System.out.println("IO problem with buffered reader method readLine()");
            e.getStackTrace();
        }
    }


    /**
     * Creates hashmap file from txt file. Used for descriptions for items. Follow the proper delimiter in text file of :
     * after the name of the item, and the description file must be only one single line. It is limited to 50 characters
     * per line in order to compact it during display.
      * @param filename the filename of the particular txt file to be retrieved
     * @return returns a hashmap of key value and the description, as per determined via delimiter ":" in the txt file
     * @throws IOException Possible failure to retrieve specified filename
     */
    public HashMap<String, String> assetImport(String filename) throws IOException {
        File fileImport = new File("src" +
                "/" + filename + ".txt");
        FileReader fileRead = new FileReader(fileImport);
        BufferedReader readLine = new BufferedReader(fileRead);
        HashMap<String, String> hashDesc = new HashMap<>();
        String lineContent;

        while ((lineContent = readLine.readLine()) != null) {
            String[] splitUp = lineContent.split(":");
            StringBuilder descFormat = new StringBuilder(splitUp[1]);
            int i = 0;
            while ((i = descFormat.indexOf(" ", i + 50)) != -1) {
                descFormat.replace(i, i + 1, "\n");
            }

            hashDesc.put(splitUp[0], descFormat.toString());
        }
        return hashDesc;
    }
}
