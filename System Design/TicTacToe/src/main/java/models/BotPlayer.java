package models;

import enums.GameSymbol;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BotPlayer extends Player {
    // Constructor Definition
    public BotPlayer (String name, GameSymbol symbol) {
        super(name, symbol);
    }

    // Method Implementation
    @Override
    public BoardCell makeMove(Board board) {
        List<BoardCell> emptyCells = board.boardCells.stream()
                .flatMap(i -> i.stream())
                .filter(i -> i.getSymbol() == null)
                .collect(Collectors.toList());

        int randomCell = (int) (Math.random() * emptyCells.size());
        BoardCell selected = emptyCells.get(randomCell);

        return new BoardCell(selected.getRow(), selected.getColumn(), this.getSymbol());
    }

    // Builder Implementation
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GameSymbol gameSymbol;

        // Builder Methods
        public Builder withSymbol(GameSymbol gameSymbol) {
            this.gameSymbol = gameSymbol;
            return this;
        }

        // Build Player
        public BotPlayer build() {
            Random random = new Random();
            int botCode = random.nextInt(100000);

            return new BotPlayer(
                "Bot #" + String.format("%08d", botCode),
                this.gameSymbol
            );
        }
    }
}
