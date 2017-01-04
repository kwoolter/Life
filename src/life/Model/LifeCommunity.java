/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.Model;

import java.util.Random;

/**
 *
 * @author KeithW
 */
public class LifeCommunity {

    private int iWidth, iHeight, iGenerations, iAlive, iBorn, iDied;
    private LifeCell[][] community;

    public LifeCommunity(int iWidth, int iHeight) {
        this.iWidth = iWidth;
        this.iHeight = iHeight;

        iGenerations = 1;
        iAlive = iBorn = iDied = 0;

        community = new LifeCell[iWidth][iHeight];
    }

    public int getWidth() {
        return iWidth;
    }

    public int getHeight() {
        return iHeight;
    }

    public int getGenerations() {
        return iGenerations;
    }

    public int getAlive() {
        return iAlive;
    }

    public int getBorn() {
        return iBorn;
    }

    public int getDied() {
        return iDied;
    }

    public LifeCell.LifeState getCellState(int x, int y) {

        return community[x][y].getStateCurrent();

    }

    public LifeCell.LifeStateTransition getCellStateTransition(int x, int y) {

        return community[x][y].getLastStateTransition();

    }

    public void addCell(LifeCell cell, int x, int y) {

        community[x][y] = cell;

        return;
    }

    public void populate() {

        community = new LifeCell[iWidth][iHeight];

        Random rand = new Random();

        for (int x = 0; x < iWidth; x++) {
            for (int y = 0; y < iHeight; y++) {

                LifeCell.LifeState state = LifeCell.LifeState.ALIVE;
                if (rand.nextInt(10) > 3) {
                    state = LifeCell.LifeState.DEAD;
                }
                LifeCell cell = new LifeCell(state);

                community[x][y] = cell;
            }
        }

        this.print();
    }

    public void print() {

        for (int y = 0; y < iHeight; y++) {
            for (int x = 0; x < iWidth; x++) {
                if (isCellAlive(x, y) == true) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");

                }
            }
            System.out.print("\n");
        }
        System.out.print("Generation = " + this.iGenerations + ", Alive = " + this.iAlive + ", Born = " + this.iBorn + ", Died = " + this.iDied);

    }

    public void calculateNextGeneration() {

        for (int x = 0; x < iWidth; x++) {
            for (int y = 0; y < iHeight; y++) {
                community[x][y].setStateNext(countLiveNeighbours(x, y));

                LifeCell.LifeStateTransition transition = community[x][y].getLastStateTransition();

                switch (transition) {
                    case BORN:
                        iBorn++;
                        break;
                    case DIED:
                        iDied++;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void doNextGeneration() {

        this.iAlive = 0;

        for (int x = 0; x < iWidth; x++) {
            for (int y = 0; y < iHeight; y++) {
                community[x][y].changeState();

                if (isCellAlive(x, y) == true) {
                    ++iAlive;
                }
            }
        }

        if (iAlive > 0) {
            ++iGenerations;
        }
    }

    private int countLiveNeighbours(int x, int y) {

        int iCount = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // Don't count this cell itself
                if (dx != 0 || dy != 0) {
                    // Check neighbour is alive...
                    if (isCellAlive(x + dx, y + dy) == true) {
                        iCount++;
                    }
                }
            }
        }

        return iCount;
    }

    public boolean isCellAlive(int x, int y) {

        boolean isAlive = false;

        if (x < 0 || x >= iWidth || y < 0 || y >= iHeight) {
            isAlive = false;
        } else {
            if (community[x][y].getStateCurrent() == LifeCell.LifeState.ALIVE) {
                isAlive = true;
            }
        }

        return isAlive;
    }
}
