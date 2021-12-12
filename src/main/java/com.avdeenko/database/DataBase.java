package com.avdeenko.database;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

public final class DataBase {
    public static String readFileAsString(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            System.out.print(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void writeStringToFile(String fileName, String data) {
        try {
            FileWriter fWriter = new FileWriter(fileName);
            fWriter.write(data);
            fWriter.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

}
