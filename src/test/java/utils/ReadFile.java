package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    public static String getFile(String filePath){

        StringBuilder builder = null;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            builder = new StringBuilder();
            String currentLine = reader.readLine();
            while (currentLine != null) {
                builder.append(currentLine);
                currentLine = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder == null ? null : builder.toString();

    }

}
