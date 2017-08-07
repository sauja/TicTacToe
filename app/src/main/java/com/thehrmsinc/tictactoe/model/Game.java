package com.thehrmsinc.tictactoe.model;

import android.util.Log;


/**
 * Created by sauja on 8/1/17.
 */

public class Game {
    private static final String TAG=Game.class.getName();
    //a 2d grid of cells to represent the game board
    private Cell[][] cells;
    //the number of rows and columns on the game
    private int gameBoardSize=3;
    //stores the next player of the game
    private static int turnCounter;

    public Game(int gameBoardSize) {
        turnCounter=0;
        this.gameBoardSize=gameBoardSize;
        //create and initialize the game
        cells=new Cell[gameBoardSize][gameBoardSize];
        for(int i=0;i<gameBoardSize;i++)
            for(int j=0;j<gameBoardSize;j++)
                cells[i][j] = new Cell(i,j);
    }





    /*
    * Gets the player who's turn is next
    * */
    public int getCurrentPlayer()
    {
       if((turnCounter%2)==0) return 0;
       return 1;
    }

    /*
    * Check if the winner of the game is found or not
    * */
    public int getWinner()
    {

        CellSymbol checkSymbol;

        boolean bool;
        //Checking for vertical elements
        for(int i=0;i<gameBoardSize;i++)
        {
            checkSymbol=cells[0][i].getCellSymbol();
            bool=true;
            if(checkSymbol!=CellSymbol.BLANK) {
                for (int j = 0; j < gameBoardSize; j++) {
                    if (cells[j][i].getCellSymbol() != checkSymbol) {
                        bool = false;
                        break;
                    }
                }
                if (bool) {
                    Log.d(TAG, "getWinner: winner by vertical "+i);
                    return checkSymbol.ordinal();
                }
            }
        }
        //Checking for horizontal elements
        for(int i=0;i<gameBoardSize;i++)
        {
            checkSymbol=cells[i][0].getCellSymbol();
            bool=true;
            if(checkSymbol!=CellSymbol.BLANK) {
                for (int j = 0; j < gameBoardSize; j++) {
                    if (cells[i][j].getCellSymbol() != checkSymbol) {
                        bool = false;
                        break;
                    }
                }
                if (bool) {
                    Log.d(TAG, "getWinner: winner by horizontal "+i);
                    return checkSymbol.ordinal();
                }
            }
        }
        //Checking for diagonal elements
        bool=true;
        checkSymbol=cells[0][0].getCellSymbol();
        if(checkSymbol!=CellSymbol.BLANK) {
            for (int i = 0; i < gameBoardSize; i++) {
                if (cells[i][i].getCellSymbol() != checkSymbol) {
                    bool = false;
                    break;
                }
            }
            if (bool) {
                Log.d(TAG, "getWinner: winner by right diagonal");
                return cells[0][0].getCellSymbol().ordinal();
            }
        }

        // checking minor diagonal elements
        bool=true;
        checkSymbol=cells[0][gameBoardSize-1].getCellSymbol();
        if(checkSymbol!=CellSymbol.BLANK){
            for( int i=0,j=gameBoardSize-1;i<gameBoardSize;i++,j--)
             {
                if(cells[i][j].getCellSymbol()!=checkSymbol) {
                    bool=false;
                    break;
                }
             }
             if(bool)
             {
                 Log.d(TAG, "getWinner: winner by left diagonal");
                 return checkSymbol.ordinal();
             }
        }
        if (turnCounter>=(gameBoardSize*gameBoardSize))
            return -2;

        return -1;
    }

    /*
    * Perform the moves by player in the 2d grid with respect to turnCounter
    * which specifies who's turn it is
    * */
    public void makeMove(int row,int column)
    {
        if(cells[row][column].getCellSymbol().equals(CellSymbol.BLANK))
        {
            if((turnCounter%2==0)) {
                cells[row][column].setCellStatus(CellSymbol.X);

            }
            else
                cells[row][column].setCellStatus(CellSymbol.O);
            turnCounter++;
        }
    }


    // Getters and Setters
    public int getGameBoardSize() {
        return gameBoardSize;
    }

    public void setGameBoardSize(int gameBoardSize) {
        this.gameBoardSize = gameBoardSize;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public static int getTurnCounter() {
        return turnCounter;
    }

}
