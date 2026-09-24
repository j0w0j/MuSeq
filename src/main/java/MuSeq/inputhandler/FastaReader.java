package MuSeq.inputhandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Documentatie TODO
 *
 * https://www.w3schools.com/java/java_bufferedreader.asp
 */
public class FastaReader {
    void readFasta(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}

