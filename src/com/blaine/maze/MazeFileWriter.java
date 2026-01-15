package com.blaine.maze;

import com.blaine.maze.cell.Cell;
import com.blaine.maze.util.Position;

import java.io.PrintWriter;

public class MazeFileWriter {

    public static void writeMazeToFile(Maze maze){
        PrintWriter outFile;
        try{
            outFile = new PrintWriter(maze.getFilePath());
        }
        catch (Exception e){
            System.err.println("Error saving maze to path: " + maze.getFilePath() + "\n " + e.toString());
            return;
        }
        int rows = maze.getRows();
        int cols = maze.getCols();

        outFile.println(rows + "," + cols + "," + maze.getSolveTime());
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                Cell currentCell = maze.getCell(new Position(r, c));
                if(currentCell.isStart()){
                    outFile.print("1 0");
                }
                else if (currentCell.isEnd()){
                    outFile.print("0 1");
                }
                else{
                    outFile.print("0 0");
                }

                if(currentCell.isVisited()){
                    outFile.print(" 1");
                }
                else{
                    outFile.print(" 0");
                }
                outFile.print(" ");
                for (Position neighbor : currentCell.getNeighbors()){
                    outFile.print(neighbor.row + "-" + neighbor.col + " ");
                }

                if(c != cols-1)
                    outFile.print(",");
            }
            outFile.print("\n");
        }

        outFile.close();

    }

}
