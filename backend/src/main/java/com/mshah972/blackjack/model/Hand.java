package com.mshah972.blackjack.model;

import com.mshah972.blackjack.util.HandValueCalculator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a player's or dealer's hand in Blackjack.
 */
public class Hand {
    private final List<Card> cards;

    /**
     * Constructs an empty hand.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Adds a card to this hand.
     *
     * @param card the Card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Returns an unmodifiable view of the cards in this hand.
     *
     * @return list of cards
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    /**
     * Calculates the current value of the hand, handling Aces as 1 or 11 optimally.
     *
     * @return the hand value
     */
    public int getValue() {
        return HandValueCalculator.calculateValue(cards);
    }

    /**
     * Checks if the hand is a Blackjack (exactly two cards totaling 21).
     *
     * @return true if Blackjack, false otherwise
     */
    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    /**
     * Checks if the hand has busted (value exceeds 21).
     *
     * @return true if bust, false otherwise
     */
    public boolean isBust() {
        return getValue() > 21;
    }

    /**
     * Clears all cards from the hand.
     */
    public void clear() {
        cards.clear();
    }

    @Override
    public String toString() {
        return cards.toString() + " (Value: " + getValue() + ")";
    }
}
