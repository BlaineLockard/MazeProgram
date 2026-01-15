package com.blaine.maze;

import com.blaine.maze.cell.Cell;
import com.blaine.maze.util.Position;

import java.util.Arrays;
import java.util.Scanner;

public class MazeFileReader {

    public static Cell[][] createCellMatrixFromScanner(int rows, int cols, Scanner scanner){
        Cell[][] maze = new Cell[rows][cols];
        int row = 0;
        int col = 0;
        while(scanner.hasNext()){
            String cellData = scanner.next();
            Scanner lineScanner = new Scanner(cellData);
            boolean isStart = lineScanner.next().trim().equals("1");
            boolean isEnd = lineScanner.next().trim().equals("1");
            boolean partOfPath = lineScanner.next().trim().equals("1");

            Cell newCell = new Cell(new Position(row, col), isStart, isEnd, partOfPath);

            while(lineScanner.hasNext()){
                String[] neighborStr = lineScanner.next().split("-");
                Position neighborPos = new Position(Integer.parseInt(neighborStr[0]), Integer.parseInt(neighborStr[1]));

                if(neighborPos.row == row-1 && neighborPos.col == col){ // North
                    newCell.setNorthNeighbor(neighborPos);
                }
                else if(neighborPos.row == row+1 && neighborPos.col == col){ // South
                    newCell.setSouthNeighbor(neighborPos);
                }
                else if(neighborPos.row == row && neighborPos.col == col+1){ // East
                    newCell.setEastNeighbor(neighborPos);
                }
                else if(neighborPos.row == row && neighborPos.col == col-1){ // West
                    newCell.setWestNeighbor(neighborPos);
                }
                else
                    throw new IllegalArgumentException("Neighbor " + neighborPos.toString() + " not allowed");
            }

            maze[row][col] = newCell;

            col++;
            if(col >= cols){
                col = 0;
                row ++;
                if (row >= rows)
                    break;
            }

            lineScanner.close();
        }
        return maze;
    }

}
