package com.mshah972.blackjack.service;

import java.util.Scanner;

import com.mshah972.blackjack.util.HandValueCalculator; // if needed

import com.mshah972.blackjack.model.Card;
import com.mshah972.blackjack.model.Hand;
import com.mshah972.blackjack.model.GameState;

/**
 * Manages game flow: dealing, player actions, dealer AI, and card counting.
 */
public class GameService {
    private GameState gameState;
    private final DealerAIService dealerAIService;
    private final CardCountingService cardCountingService;

    public GameService() {
        this.dealerAIService = new DealerAIService();
        this.cardCountingService = new CardCountingService();
        this.gameState = new GameState();
    }

    /**
     * Starts a new round: reuses the shoe, clears hands, deals initial cards, and checks for Blackjack.
     * The deck will auto-reshuffle when penetration threshold is reached.
     */
    public void startGame() {
        // Offer reshuffle if last game finished and shoe is low
        if (gameState != null
            && gameState.getPhase() == GameState.Phase.FINISHED
            && gameState.getDeck().size() <= 12) {
            System.out.println("Only " + gameState.getDeck().size() + " cards remaining in the shoe.");
            System.out.print("Would you like to reshuffle before starting a new round? (Y/N): ");
            Scanner scanner = new Scanner(System.in);
            if ("Y".equalsIgnoreCase(scanner.nextLine().trim())) {
                shuffleDeck();
                System.out.println("Deck reshuffled.");
            } else {
                int remaining = gameState.getDeck().size();
                // If not enough cards to deal two each
                if (remaining < 4) {
                    System.out.println("Not enough cards to start a new round. Only " + remaining + " cards remain.");
                    System.out.print("Would you like to reshuffle now? (Y/N): ");
                    String choice2 = scanner.nextLine().trim().toUpperCase();
                    if ("Y".equals(choice2)) {
                        shuffleDeck();
                        System.out.println("Deck reshuffled.");
                    } else {
                        System.out.println("Game paused. Unable to start a new round.");
                        return;
                    }
                }
            }
        }
        // Initialize or clear hands
        if (gameState == null) {
            gameState = new GameState();
        } else {
            gameState.getPlayerHand().clear();
            gameState.getDealerHand().clear();
        }
        gameState.setPhase(GameState.Phase.PLAYER_TURN);

        // Deal initial two cards to player and dealer
        for (int i = 0; i < 2; i++) {
            Card playerCard = gameState.getDeck().draw();
            gameState.getPlayerHand().addCard(playerCard);
            cardCountingService.updateCount(playerCard);

            Card dealerCard = gameState.getDeck().draw();
            gameState.getDealerHand().addCard(dealerCard);
            cardCountingService.updateCount(dealerCard);
        }

        // Immediate Blackjack check
        if (gameState.getPlayerHand().isBlackjack() || gameState.getDealerHand().isBlackjack()) {
            gameState.setPhase(GameState.Phase.FINISHED);
        }
    }

    /**
     * Player takes a hit: draw a card, update count, and possibly end the round.
     */
    public void playerHit() {
        if (gameState.getPhase() != GameState.Phase.PLAYER_TURN) {
            return;
        }
        Card card = gameState.getDeck().draw();
        System.out.println("Player hits and draws: " + card);
        gameState.getPlayerHand().addCard(card);
        cardCountingService.updateCount(card);

        // If player hits exactly 21, automatically stand
        if (gameState.getPlayerHand().getValue() == 21) {
            System.out.println("Player hit 21! Standing automatically.");
            playerStand();
            return;
        }

        // If player busts or gets Blackjack, end the round
        if (gameState.getPlayerHand().isBust() || gameState.getPlayerHand().isBlackjack()) {
            System.out.println("Player has "
                + (gameState.getPlayerHand().isBust() ? "busted" : "Blackjack!") );
            gameState.setPhase(GameState.Phase.FINISHED);
            return;
        }
    }

    /**
     * Player stands: transition to dealer turn and execute dealer logic.
     */
    public void playerStand() {
        if (gameState.getPhase() == GameState.Phase.PLAYER_TURN) {
            gameState.setPhase(GameState.Phase.DEALER_TURN);
            dealerTurn();
        }
    }

    /**
     * Runs the dealer AI logic and ends the game, notifying each draw and handling busts.
     */
    public void dealerTurn() {
        if (gameState.getPhase() != GameState.Phase.DEALER_TURN) {
            return;
        }
        Hand dealerHand = gameState.getDealerHand();
        // Dealer draws until reaching at least 17
        while (dealerAIService.shouldHit(dealerHand)) {
            System.out.println("Dealer draws a card...");
            Card card = gameState.getDeck().draw();
            dealerHand.addCard(card);
            System.out.println("Dealer draws: " + card);
            cardCountingService.updateCount(card);
            // Check for bust immediately
            if (dealerHand.isBust()) {
                System.out.println("Dealer busted with value: " + dealerHand.getValue());
                gameState.setPhase(GameState.Phase.FINISHED);
                return;
            }
        }
        // Dealer stands if not busted
        System.out.println("Dealer stands with value: " + dealerHand.getValue());
        gameState.setPhase(GameState.Phase.FINISHED);
    }

    /**
     * Returns the current game state.
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Gets the current true count from the card counting service.
     */
    public double getTrueCount() {
        return cardCountingService.getTrueCount();
    }

    /**
     * Allows the player to manually shuffle the remaining shoe and reset the count.
     */
    public void shuffleDeck() {
        gameState.getDeck().shuffle();
        cardCountingService.reset();
    }
}
