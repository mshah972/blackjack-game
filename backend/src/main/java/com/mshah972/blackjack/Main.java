package com.mshah972.blackjack;

import com.mshah972.blackjack.model.GameState;
import com.mshah972.blackjack.service.GameService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Start initial game
        gameService.startGame();

        while (!exit) {
            GameState state = gameService.getGameState();
            System.out.println("\n" + state);

            switch (state.getPhase()) {
                case PLAYER_TURN:
                    System.out.print("Choose action - (H)it, (S)tand, (D)ouble, (U) Surrender, (R)eshuffle, (Q)uit: ");
                    break;
                case DEALER_TURN:
                    System.out.println("Dealer's turn...");
                    gameService.dealerTurn();
                    continue;
                case FINISHED:
                    System.out.print("Game finished. (N)ew game or (Q)uit: ");
                    break;
            }

            String input = scanner.nextLine().trim().toUpperCase();
            switch (input) {
                case "H":
                    gameService.playerHit();
                    break;
                case "S":
                    gameService.playerStand();
                    break;
                case "D":
                    gameService.doubleDown();
                    break;
                case "U":
                    gameService.surrender();
                    break;
                case "R":
                    gameService.shuffleDeck();
                    System.out.println("Deck reshuffled.");
                    break;
                case "N":
                    gameService.startGame();
                    break;
                case "Q":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
