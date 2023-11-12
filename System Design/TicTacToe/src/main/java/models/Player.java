package models;

import enums.GameSymbol;

public abstract class Player {
    private String name;
    private GameSymbol symbol;
    private int score;

    // Constructor Definition
    public Player(String name, GameSymbol symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
    }

    // Getters
    public GameSymbol getSymbol() {
        return symbol;
    }

    public String getName() { return name; }

    public int getScore() { return score; }

    // Abstract Methods
    public abstract BoardCell makeMove(Board board);

    // Method Implementations
    public void incrementScore() {
        this.score++;
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
