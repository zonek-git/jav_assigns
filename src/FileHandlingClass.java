import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

public class FileHandlingClass {

    /**
     * System that displays my text files for "story" elements
     *
     * @param path
     */
    public void fileOutput(String path) {
        try {
            File file = new File(path);
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

    public HashMap<String, String> assetImport(String filename) throws IOException {
        File fileImport = new File("resource/" + filename + ".txt");
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

    /**
     * System that loads description files for the items, characters, locations
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public String descFile(String path) throws FileNotFoundException {
        StringBuilder descriptionString = new StringBuilder();
        Scanner scanFiles = new Scanner(new java.io.File(path));
        while (scanFiles.hasNextLine()) {
            descriptionString.append(scanFiles.nextLine());
        }
        scanFiles.close();
        return descriptionString.toString();
    }

    public List<String> actionWords(String path) throws FileNotFoundException {
        Scanner scanFiles = new Scanner(new java.io.File(path));
        List<String> actionList = new ArrayList<String>();
        while (scanFiles.hasNextLine()) {
            actionList.add(scanFiles.nextLine());
        }
        scanFiles.close();
        return actionList;
    }


}
