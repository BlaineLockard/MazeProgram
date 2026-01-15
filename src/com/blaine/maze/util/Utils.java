package com.blaine.maze.util;

import com.blaine.maze.Maze;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

    private static String currentFilePath = "mazes/maze1.maze";
    private static int fileNumber = 1;

    public static boolean hasExtension(String path, String extension ){
        String[] pathComponents = path.split("\\.");
        return pathComponents[pathComponents.length-1].equals(extension);
    }

    public static String getFilePath(){
        Path path = Paths.get(currentFilePath);
        while(Files.exists(path) && Files.isRegularFile(path)){
            System.out.println(currentFilePath);
            fileNumber++;
            currentFilePath = "mazes/maze" + fileNumber + ".maze";
            path = Paths.get(currentFilePath);
        }
        return currentFilePath;
    }
}
