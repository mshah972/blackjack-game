package com.mshah972.blackjack.model;

import com.mshah972.blackjack.model.Deck;
import com.mshah972.blackjack.model.Hand;

/**
 * Represents the current state of a Blackjack game,
 * including the deck, player and dealer hands, and game phase.
 */
public class GameState {

    /** Phases of the game flow. */
    public enum Phase {
        PLAYER_TURN,
        DEALER_TURN,
        FINISHED
    }

    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private Phase phase;

    /**
     * Constructs a new game state and deals initial hands.
     */
    public GameState() {
        reset();
    }

    /**
     * Initializes or resets the game to its starting state:
     * shuffles a new deck, deals two cards each, and sets phase to PLAYER_TURN.
     */
    public void reset() {
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
        phase = Phase.PLAYER_TURN;
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "phase=" + phase +
                ", playerHand=" + playerHand +
                ", dealerHand=" + dealerHand +
                ", deckSize=" + deck.getCards().size() +
                '}';
    }
}
