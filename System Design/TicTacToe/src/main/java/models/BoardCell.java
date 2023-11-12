package models;

import enums.GameSymbol;

public class BoardCell {
    private int row, column;
    private GameSymbol symbol;

    // Constructor Implementation
    public BoardCell(int row, int column) {
        this.row = row;
        this.column = column;
    }
    public BoardCell(int row, int column, GameSymbol symbol) {
        this.row = row;
        this.column = column;
        this.symbol = symbol;
    }

    // Getters
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public GameSymbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return this.getSymbol() == null ? " " : this.getSymbol().toString();
    }
}
