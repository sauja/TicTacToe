package com.thehrmsinc.tictactoe.model;

/**
 * Created by sauja on 8/1/17.
 */

public class Cell {

    // Cell Symbol can be X, O or blank
    private CellSymbol cellSymbol;
    //stores row of the cell in grid
    private int row;
    //stores column of the cell in grid
    private int column;

    public Cell(int row, int column) {
        this.cellSymbol = CellSymbol.BLANK;
        this.row = row;
        this.column = column;
    }

    public CellSymbol getCellSymbol() {
        return cellSymbol;
    }

    public void setCellStatus(CellSymbol cellStatus) {
        this.cellSymbol = cellStatus;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
