import com.blaine.maze.Maze;

import java.io.IOException;

public class Main {
    static void main(String[] args){
        Maze maze = new Maze("mazes/maze1.maze");
        try{
            maze.readFile();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
        System.out.println(maze.toString());

    }
}
