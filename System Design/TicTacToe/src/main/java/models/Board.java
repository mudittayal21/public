package models;

import enums.GameSymbol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    int size;
    List<List<BoardCell>> boardCells;

    // Constructor Definition
    public Board(int size) {
        this.size = size;
        this.boardCells = initiateCells();
    }

    // Method Implementations
    List<List<BoardCell>> initiateCells() {
        List<List<BoardCell>> boardCells = new ArrayList<>();
        IntStream.range(0, this.size).forEach(i -> {
            List<BoardCell> row = new ArrayList<>();
            IntStream.range(0, this.size).forEach(j -> row.add(new BoardCell(i, j)));
            boardCells.add(row);
        });
        return boardCells;
    }

    public String printBoard() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        for (int i = 0; i < this.size; i++) {
            sb.append(" " +
                    this.boardCells.get(i).stream()
                            .map(j -> j.toString())
                            .collect(Collectors.joining(" | "))
                    + "\n"
            );

            if (i < this.size - 1)
                sb.append("---+---+---\n");
        }

        return sb.toString();
    }

    public String printDefaultBoard() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        IntStream.range(0, this.size).forEach(i -> {
            int start = (i * this.size) + 1;

            sb.append(" ");
            sb.append(IntStream.range(start, start + this.size)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" | "))
            );
            sb.append("\n");

            if (i < this.size - 1)
                sb.append("---+---+---\n");
        });

        return sb.toString();
    }

    public boolean validateCell(BoardCell boardCell) {
        int cell = (boardCell.getRow()  * this.size) + boardCell.getColumn() + 1;
        if (cell > this.size * this.size) return false;

        if (this.boardCells
                .get(boardCell.getRow())
                .get(boardCell.getColumn())
                .getSymbol() == null
        ) {
            return true;
        }

        return false;
    }

    public void update(BoardCell boardCell) {
        this.boardCells.get(boardCell.getRow()).set(boardCell.getColumn(), boardCell);
    }
}
