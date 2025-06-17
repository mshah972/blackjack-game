package com.mshah972.blackjack.service;

import com.mshah972.blackjack.model.Card;

/**
 * Implements a Hi-Lo card counting system.
 */
public class CardCountingService {
    private int runningCount = 0;
    private int cardsSeen = 0;
    private final int cardsPerDeck = 52;
    private final int numberOfDecks;

    /**
     * Constructs the counter assuming a single deck by default.
     */
    public CardCountingService() {
        this(1);
    }

    /**
     * Constructs the counter for the given number of decks.
     *
     * @param numberOfDecks number of decks in play
     */
    public CardCountingService(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
    }

    /**
     * Updates the running count based on the Hi-Lo values:
     * 2–6 => +1, 7–9 => 0, 10/Ace => -1.
     *
     * @param card the Card dealt or drawn
     */
    public void updateCount(Card card) {
        int value = card.getValue();
        if (value >= 2 && value <= 6) {
            runningCount++;
        } else if (value == 10 || value == 1) {
            runningCount--;
        }
        cardsSeen++;
    }

    /**
     * Returns the current running count.
     *
     * @return running count
     */
    public int getRunningCount() {
        return runningCount;
    }

    /**
     * Calculates the true count: running count divided by decks remaining.
     *
     * @return true count as a double
     */
    public double getTrueCount() {
        double decksRemaining = ((numberOfDecks * cardsPerDeck) - cardsSeen) / (double) cardsPerDeck;
        if (decksRemaining <= 0) {
            return runningCount;
        }
        return runningCount / decksRemaining;
    }

    /**
     * Resets the count and cards seen to start a new shoe.
     */
    public void reset() {
        runningCount = 0;
        cardsSeen = 0;
    }
}
