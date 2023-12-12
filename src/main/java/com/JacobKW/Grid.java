package com.JacobKW;

import java.util.Random;

public class Grid {

    private int size;
    private int popSize;

    Member[][] myArray;

    // creates the 2D array of type member and populates it, as well as determining its width and height
    public Grid(int width) {
        this.size = width;
        myArray = new Member[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Member member = new Member();
                myArray[i][j] = member;
            }
        }
    }

    // populating the first grid, which is slightly different to every other grid because it needs to include
    // the extra parameter of the starting population
    public Member[][] firstGrid(int width, int startPop) {
        this.size = width;
        if (startPop > 0) {
            this.popSize = startPop;
        }
        myArray = new Member[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Member member = new Member();
                myArray[i][j] = member;
            }
        }
        int infectedCount = 0;
        while (infectedCount < popSize) {
            Random randomNum = new Random();
            int x = randomNum.nextInt(size);
            int y = randomNum.nextInt(size);
            if (myArray[x][y].state == 0) {
                myArray[x][y].infect();
                infectedCount++;
            }
        }
        return myArray;
    }

    // returns the array, which is useful for checking the state of each member of the grid
    public Member[][] getArray() {
        return myArray;
    }

}
