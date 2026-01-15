package com.blaine.maze.cell;

import com.blaine.maze.util.Position;
import java.util.ArrayList;

public class Cell {
    private Position position;

    private boolean isStart = false;
    private boolean isEnd = false;
    private boolean visited = false;
    private boolean partOfPath = false;

    private float weight = 0.0f;

    private Position northNeighbor;
    private Position southNeighbor;
    private Position eastNeighbor;
    private Position westNeighbor;



    public Cell(){
        position = new Position();
    }

    public Cell(Position position, boolean isStart, boolean isEnd, boolean partOfPath) {
        this.position = position;
        this.isStart = isStart;
        this.isEnd = isEnd;
        this.partOfPath = partOfPath;
    }

    public Cell(Position position, boolean isStart, boolean isEnd, float weight) {
        this.position = position;
        this.isStart = isStart;
        this.isEnd = isEnd;
        this.weight = weight;
    }

    public String toString(){
        StringBuilder returnString = new StringBuilder("Cell at " + position.row + "," + position.col + ": ");
        if(isStart)
            returnString.append("is Start, ");
        else if(isEnd)
            returnString.append("is end, ");
        if(partOfPath)
            returnString.append("is part of path, ");

        returnString.append("weight of ").append(weight);

        return returnString.toString();
    }

    public ArrayList<Position> getNeighbors(){
        ArrayList<Position> neighbors = new ArrayList<>();
        if (northNeighbor != null) neighbors.add(northNeighbor);
        if (southNeighbor != null) neighbors.add(southNeighbor);
        if (eastNeighbor != null) neighbors.add(eastNeighbor);
        if (westNeighbor != null) neighbors.add(westNeighbor);
        return neighbors;
    }

    public int degree(){
        int dg = 0;
        if (northNeighbor != null) dg++;
        if (southNeighbor != null) dg++;
        if (eastNeighbor != null) dg++;
        if (westNeighbor != null) dg++;
        return dg;
    }

    public boolean hasNorthNeighbor(){return northNeighbor != null;}
    public boolean hasEastNeighbor(){return eastNeighbor != null;}
    public boolean hasWestNeighbor(){return westNeighbor != null;}
    public boolean hasSouthNeighbor(){return southNeighbor != null;}

    public Position getPosition() {
        return position;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean isVisited() {
        return visited;
    }

    public boolean isPartOfPath() {
        return partOfPath;
    }

    public Position getNorthNeighbor() {
        return northNeighbor;
    }

    public Position getSouthNeighbor() {
        return southNeighbor;
    }

    public Position getEastNeighbor() {
        return eastNeighbor;
    }

    public Position getWestNeighbor() {
        return westNeighbor;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setPartOfPath(boolean partOfPath) {
        this.partOfPath = partOfPath;
    }

    public void setNorthNeighbor(Position northNeighbor) {
        this.northNeighbor = northNeighbor;
    }

    public void setSouthNeighbor(Position southNeighbor) {
        this.southNeighbor = southNeighbor;
    }

    public void setEastNeighbor(Position eastNeighbor) {
        this.eastNeighbor = eastNeighbor;
    }

    public void setWestNeighbor(Position westNeighbor) {
        this.westNeighbor = westNeighbor;
    }
}
