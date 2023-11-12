package models;

import enums.GameStatus;
import helpers.ConsoleHelper;

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

        while (this.gameStatus == GameStatus.IN_PROGRESS) {
            this.makeMove();
        }

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
        StringBuilder sb = new StringBuilder();
        sb.append("GAME STATUS: " + this.gameStatus + "\n");

        Player player = this.getNextPlayer();
        boolean isValid = false;

        sb.append("PLAYER: " + player + "'s Turn\n");
        sb.append(this.board.printBoard());

        ConsoleHelper.printData(sb.toString(), true);

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

        StringBuilder sb = new StringBuilder();

        if (isRow || isColumn || isLeftDiagonal || isRightDiagonal) {
            player.incrementScore();
            this.gameStatus = GameStatus.FINISHED;

            sb.append("GAME STATUS: " + this.gameStatus + "\n");
            sb.append(player + " has won the game!!");
        } else {
            // Check Draw
            List<BoardCell> emptyCells = this.board.boardCells.stream()
                    .flatMap(i -> i.stream())
                    .filter(i -> i.getSymbol() == null)
                    .collect(Collectors.toList());

            if (emptyCells.size() == 0) {
                this.gameStatus = GameStatus.DRAW;

                sb.append("GAME STATUS: " + this.gameStatus + "\n");
                sb.append("The game has been a draw!!");
            }
        }

        if (this.gameStatus != GameStatus.IN_PROGRESS) {
            sb.append("\n");
            sb.append(this.board.printBoard());
            ConsoleHelper.printData(sb.toString(), true);
        }
    }

    private Player getNextPlayer() {
        return this.playerList.get(this.nextPlayerIndex);
    }

    public void gameRules() {
        StringBuilder sb = new StringBuilder();

        sb.append("-------------  Game Rules  -----------------\n");
        sb.append("Enter a number that corresponds to the point\n");
        sb.append("on the board as shown in the example below.\n");
        sb.append(this.board.printDefaultBoard() + "\n");
        sb.append("-------------  Game Rules  -----------------");

        ConsoleHelper.printData(sb.toString(), true);
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
