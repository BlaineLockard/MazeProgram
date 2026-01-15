package com.blaine.maze;

import com.blaine.maze.cell.Cell;
import com.blaine.maze.util.Consts;
import com.blaine.maze.util.Position;

import java.util.ArrayList;

public class MazeSolver {

    public static float binarySearch(Maze maze){
        long startTime = System.nanoTime();
        Position currentPosition = maze.getStart();

        if(traverseMaze(maze.getCell(currentPosition), maze)){
            long endTime = System.nanoTime();
            return (float)(startTime - endTime) / Consts.NANOSECONDS;
        }

        return -1.0f;
    }

    private static boolean traverseMaze(Cell cell, Maze maze){
        cell.setVisited(true);
        cell.setPartOfPath(true);
        if(cell.isEnd())
            return true;
        for(Position neighbor : cell.getNeighbors()){
            Cell currentNeighbor = maze.getCell(neighbor);
            if(!currentNeighbor.isVisited()){
                if(traverseMaze(currentNeighbor, maze)){
                    return true;
                }
            }
        }
        cell.setPartOfPath(false);
        return false;
    }

}
