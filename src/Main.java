import com.blaine.maze.Maze;

import java.io.IOException;

public class Main {
    static void main(String[] args){
        Maze maze;
        try{
            maze = new Maze("mazes/maze2.maze");
            maze.readFile();
        }
        catch (IOException e){
            System.err.println(e.toString());
            return;
        }
        catch (NumberFormatException e){
            System.err.println("Error in Maze File: " + e.toString());
            return;
        }

        maze.binarySolve();
        System.out.println(maze.toString());
        maze.writeToFile();
    }
}
