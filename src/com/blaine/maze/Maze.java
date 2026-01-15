package com.blaine.maze;

import com.blaine.maze.cell.Cell;
import com.blaine.maze.util.Consts;
import com.blaine.maze.util.Position;
import com.blaine.maze.util.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Maze {

    protected int rows;
    protected int cols;

    protected Cell[][] maze;

    protected String filePath;

    protected MazeSolver solver;

    protected Position start;
    protected Position end;

    protected float solveTime = -1.0f;


    public Maze(){
        rows = Consts.DEFAULT_ROWS;
        cols = Consts.DEFAULT_COLUMNS;
        maze = new Cell[rows][cols];
        filePath = Utils.getFilePath();
    }

    public Maze(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        maze = new Cell[rows][cols];
        filePath = Utils.getFilePath();
    }

    public Maze(String filePath) throws IOException{
        if(Utils.hasExtension(filePath, "maze")) {
            this.filePath = filePath;
        }
        else
            throw new IOException("File path \"" + filePath + "\" is not a valid maze path.");
    }

    public void readFile() throws IOException, NumberFormatException{
        if(filePath == null)
            throw new IOException("Maze has no associated file Path");
        Scanner scanner = new Scanner(new File(filePath));
        // reads header
        scanner.useDelimiter("[\\n,]");
        this.rows = scanner.nextInt();
        this.cols = scanner.nextInt();
        maze = new Cell[rows][cols];

        solveTime = Float.parseFloat(scanner.next());

        MazeFileReader.createCellMatrix(this, scanner);
    }

    public void writeToFile(){
        if (!Utils.hasExtension(filePath, "maze"))
            filePath = Utils.getFilePath();
        MazeFileWriter.writeMazeToFile(this);
    }

    public void writeToFile(String filePath) throws IOException{
        this.filePath = filePath;
        if (!Utils.hasExtension(filePath, "maze"))
            throw new IOException("File path \"" + filePath + "\" is not a valid maze path.");
        MazeFileWriter.writeMazeToFile(this);
    }

    public boolean binarySolve(){
        solveTime = MazeSolver.binarySearch(this);
        if(solveTime == -1.0f)
            return false;
        else if(solveTime < 0.01)
            solveTime = 0.01f;
        return true;
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

    public void addCell(Cell newCell){
        Position pos = newCell.getPosition();
        maze[pos.row][pos.col] = newCell;
    }
    public Cell getCell(Position position){
        return maze[position.row][position.col];
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

    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public Position getEnd() {
        return end;
    }

    public void setEnd(Position end) {
        this.end = end;
    }

    public String getFilePath(){
        return filePath;
    }

    public void setFilePath(String path){
        this.filePath = path;
    }
}