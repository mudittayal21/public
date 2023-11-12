package models;

import enums.GameStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    public static final int DEFAULT_BOARD_SIZE = 3;
    public static final int DEFAULT_PLAYER_COUNT = 2;

    private Board board;
    private List<Player> playerList;
    private int nextPlayerIndex;
    private GameStatus gameStatus;

    // Method Implementations
    public void start() {
        this.board = new Board(this.board.size);
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.nextPlayerIndex = (int) (Math.random() * this.playerList.size());

        this.gameRules();

        System.out.println("------------- Starting Game -----------------");
        while (this.gameStatus == GameStatus.IN_PROGRESS) {
            this.makeMove();
        }

        System.out.println("-------------- Ending Game ------------------");

        this.scoreCard();
    }

    private void scoreCard() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        sb.append("+--------------------+--------------------+\n");
        String playerNames = playerList.stream()
                .map(i -> String.format("%20s", i.getName()))
                .collect(Collectors.joining("|"));
        sb.append("|" + playerNames + "|\n");
        sb.append("+--------------------+--------------------+\n");
        String playerScores = playerList.stream()
                .map(i -> String.format("%20s", i.getScore()))
                .collect(Collectors.joining("|"));
        sb.append("|" + playerScores + "|\n");
        sb.append("+--------------------+--------------------+\n");

        System.out.println(sb.toString());
    }

    private void makeMove() {
        Player player = this.getNextPlayer();
        boolean isValid = false;

        System.out.println(player + "'s Turn");
        this.board.printBoard();

        while (!isValid) {
            BoardCell boardCell = player.makeMove(this.board);

            isValid = this.board.validateCell(boardCell);
            if (!isValid)
                System.out.println("Invalid move!! Try Again.");
            else {
                this.board.update(boardCell);
                this.checkGameStatus(player);
            }
        }

        this.nextPlayerIndex = (this.nextPlayerIndex + 1) % this.playerList.size();
    }

    private void checkGameStatus(Player player) {
        // Check Winner
        boolean isRow = true, isColumn = true;
        boolean isLeftDiagonal = true, isRightDiagonal = true;
        for (int i = 0; i < this.board.size; i++) {
            isRow = isColumn = true;

            for (int j = 0; j < this.board.size; j++) {
                if (this.board.boardCells.get(i).get(j).getSymbol() == player.getSymbol()) {
                    isRow &= true;
                } else {
                    isRow = false;
                }

                if (this.board.boardCells.get(j).get(i).getSymbol() == player.getSymbol()) {
                    isColumn &= true;
                } else {
                    isColumn = false;
                }
            }
            if (isRow || isColumn) break;

            if (this.board.boardCells.get(i).get(i).getSymbol() == player.getSymbol()) {
                isLeftDiagonal &= true;
            } else {
                isLeftDiagonal = false;
            }

            if (this.board.boardCells.get(i).get(this.board.size - 1 - i).getSymbol() == player.getSymbol()) {
                isRightDiagonal &= true;
            } else {
                isRightDiagonal = false;
            }
        }

        if (isRow || isColumn || isLeftDiagonal || isRightDiagonal) {
            player.incrementScore();
            this.gameStatus = GameStatus.FINISHED;
            System.out.println(player + " has won the game!!");
            return;
        }

        // Check Draw
        List<BoardCell> emptyCells = this.board.boardCells.stream()
                .flatMap(i -> i.stream())
                .filter(i -> i.getSymbol() == null)
                .collect(Collectors.toList());

        if (emptyCells.size() == 0) {
            this.gameStatus = GameStatus.DRAW;
            System.out.println("The game has been drawed!!");
        }
    }

    private Player getNextPlayer() {
        return this.playerList.get(this.nextPlayerIndex);
    }

    public void gameRules() {
        System.out.println();
        System.out.println("-------------  Game Rules  -----------------");
        System.out.println("Enter a number that corresponds to the point");
        System.out.println("on the board as shown in the example below.");
        this.board.printDefaultBoard();
        System.out.println("-------------  Game Rules  -----------------");
    }

    // Builder Implementation
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Game game;

        // Constructor Definition
        public Builder() {
            this.game = new Game();
            this.game.playerList = new ArrayList<>();
        }

        // Builder Methods
        public Builder withBoard(int size) {
            this.game.board = new Board(size);
            return this;
        }

        public Builder withPlayer(Player player) {
            this.game.playerList.add(player);
            return this;
        }

        // Build Final Method
        public Game build() {
            this.game.gameStatus = GameStatus.NOT_STARTED;
            return this.game;
        }
    }
}
