package com.blaine.maze;

import com.blaine.maze.cell.Cell;
import com.blaine.maze.util.Position;

import java.util.Scanner;

public class MazeFileReader {

    public static void createMazeWithScanner(Maze maze, Scanner scanner){
        int row = 0;
        int col = 0;
        while(scanner.hasNext()){
            String cellData = scanner.next();
            Scanner lineScanner = new Scanner(cellData);
            boolean isStart = lineScanner.next().trim().equals("1");
            boolean isEnd = lineScanner.next().trim().equals("1");
            boolean partOfPath = lineScanner.next().trim().equals("1");

            // skip neighbors for now

            Cell newCell = new Cell(new Position(row, col), isStart, isEnd, partOfPath);
            System.out.println(newCell.toString());
            maze.addCell(newCell);

            row++;
            if(row >= maze.getRows()){
                row = 0;
                col ++;
            }
        }
    }

}
