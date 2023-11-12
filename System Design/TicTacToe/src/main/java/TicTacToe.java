import enums.GameSymbol;
import helpers.ConsoleHelper;
import models.BotPlayer;
import models.Game;
import models.HumanPlayer;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        // Creating a New Game
        Game game = createNewGame();

        // Start Game
        startGame(game);
    }

    private static void startGame(Game game) {
        Scanner sc = new Scanner(System.in);
        String response = "Y";

        do {
            game.start();

            System.out.println("Do you want to start a New Game? (Y/N)");
            response = sc.nextLine();
        } while (response.equals("Y"));
    }

    private static HumanPlayer createNewHumanPlayer() {
        Scanner sc = new Scanner(System.in);

        ConsoleHelper.printData("Enter your Name", true);
        String name = sc.nextLine();

        ConsoleHelper.printData("\nSelect your Symbol (X, O)", false);
        String symbol = sc.nextLine();
        GameSymbol gameSymbol = GameSymbol.valueOf(symbol);

        return new HumanPlayer(name, gameSymbol);
    }

    private static GameSymbol getAvailableSymbol(GameSymbol gameSymbol) {
        return gameSymbol == GameSymbol.O ? GameSymbol.X : GameSymbol.O;
    }

    private static Game createNewGame() {
        HumanPlayer humanPlayer = createNewHumanPlayer();

        return Game.builder()
                .withBoard(Game.DEFAULT_BOARD_SIZE)
                .withPlayer(humanPlayer)
                .withPlayer(BotPlayer.builder()
                        .withSymbol(getAvailableSymbol(humanPlayer.getSymbol()))
                        .build()
                )
                .build();
    }
}
