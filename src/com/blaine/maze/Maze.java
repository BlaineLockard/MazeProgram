package com.blaine.maze;

import com.blaine.maze.cell.Cell;
import com.blaine.maze.util.Consts;
import com.blaine.maze.util.Position;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Maze {

    protected int rows;
    protected int cols;

    protected Cell[][] maze;

    protected float solveTime = 0.0f;

    protected String filePath;


    public Maze(){
        rows = Consts.DEFAULT_ROWS;
        cols = Consts.DEFAULT_COLUMNS;
        maze = new Cell[rows][cols];
    }

    public Maze(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        maze = new Cell[rows][cols];
    }

    public Maze(String filepath){
        this.filePath = filepath;
    }

    public void readFile() throws IOException{
        if(filePath == null)
            throw new IOException("Maze has no associated file Path");
        Scanner scanner = new Scanner(new File(filePath));
        // reads header
        scanner.useDelimiter("[\\n,]");
        this.rows = scanner.nextInt();
        this.cols = scanner.nextInt();

        solveTime = Float.parseFloat(scanner.next());

        maze = MazeFileReader.createCellMatrixFromScanner(rows, cols, scanner);

    }

    public void addCell(Cell newCell){
        Position pos = newCell.getPosition();
        maze[pos.row][pos.col] = newCell;
    }
    public Cell getCell(Position position){
        return maze[position.row][position.col];
    }

    public String toString(){
        StringBuilder mazeString = new StringBuilder();
        mazeString.append("Maze (").append(rows).append(" x ").append(cols).append(") solved in ").append(solveTime).append(" seconds: \n");
        for(int r = 0; r < rows; r++){
            for(int i = 0; i < 3; i++){
                for(int c = 0; c < cols; c++){
                    if(i == 0){
                        if (maze[r][c] != null && maze[r][c].hasNorthNeighbor()){
                            mazeString.append("  |  ");
                        }
                        else{
                            mazeString.append("     ");
                        }
                    }
                    else if(i == 1){
                        if(maze[r][c] != null && maze[r][c].hasWestNeighbor()){
                            mazeString.append("--");
                        }
                        else{
                            mazeString.append("  ");
                        }

                        if(maze[r][c] == null){
                            mazeString.append("\u001B[30m\u004f\u001B[0m");
                        }
                        else if( maze[r][c].isStart()){
                            mazeString.append("\u001B[32m\u004f\u001B[0m");
                        }
                        else if( maze[r][c].isEnd()){
                            mazeString.append("\u001B[31m\u004f\u001B[0m");
                        }
                        else if( maze[r][c].isPartOfPath()){
                            mazeString.append("\u001B[34m\u004f\u001B[0m");
                        }
                        else{
                            mazeString.append("\u004f");
                        }

                        if(maze[r][c] != null && maze[r][c].hasEastNeighbor()){
                            mazeString.append("--");
                        }
                        else{
                            mazeString.append("  ");
                        }
                    }
                    else if (i == 2){
                        if(maze[r][c] != null && maze[r][c].hasSouthNeighbor()){
                            mazeString.append("  |  ");
                        }
                        else{
                            mazeString.append("     ");
                        }
                    }

                }
                mazeString.append("\n");
            }
        }

        return mazeString.toString();
    }


    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public float getSolveTime() {
        return solveTime;
    }

}