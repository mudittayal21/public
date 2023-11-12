package models;

import enums.GameSymbol;

import java.util.Scanner;

public class HumanPlayer extends Player {
    // Constructor Definition
    public HumanPlayer (String name, GameSymbol symbol) {
        super(name, symbol);
    }

    // Method Implementation
    @Override
    public BoardCell makeMove(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Cell Number for you next move");

        int cell = sc.nextInt();
        int row = (cell - 1) / board.size;
        int column = (cell - 1) % board.size;

        return new BoardCell(row, column, this.getSymbol());
    }
}
