package com.mshah972.blackjack.model;

import com.mshah972.blackjack.util.ShuffleUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

/**
 * Represents a deck of playing cards. The deck can be shuffled, reset,
 * and used to draw cards. This implementation initializes a standard
 * deck of cards with all possible combinations of suits and ranks.
 * The deck maintains its state, allowing operations to be performed
 * such as shuffling and drawing cards.
 *
 * @author Moksh Shah
 */

public class Deck {
    private final int numberOfDecks;
    private final List<Card> cards;
    private final Random random;

    /**
     * Default constructor: single deck with 25% penetration threshold.
     */
    public Deck() {
        this(1, 0.25);
    }

    /**
     * Constructs a shoe with given number of decks and reshuffle threshold.
     * @param numberOfDecks count of decks in the shoe
     * @param reshuffleThreshold fraction of penetration before reshuffling (e.g., 0.25)
     */
    public Deck(int numberOfDecks, double reshuffleThreshold) {
        this.numberOfDecks = numberOfDecks;
        this.cards = new ArrayList<>();
        this.random = new Random();
        reset();
    }

    /**
     * Randomizes the order of the cards in the deck.
     * This method modifies the existing list of cards to rearrange
     * them in a randomly determined order.
     * It makes use of a utility function from {@code ShuffleUtil} to shuffle the cards.
     */
    public void shuffle() {
        Collections.shuffle(cards, random);
    }

    /**
     * Removes and returns the top card from the deck. The top card is
     * the card at the last position in the internal list of cards.
     *
     * @return the card that was removed from the top of the deck
     * @throws IllegalStateException if the deck is empty
     */
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Cannot draw from empty deck");
        }

        Card card = cards.remove(cards.size() - 1);
        // Auto-reset when only 4 or fewer cards remain
        if (cards.size() < 3) {
            reset();
            System.out.println("Deck has been reshuffled due to low remaining cards.");
        }
        return card;
    }

    /**
     * Resets the deck to its initial state.
     * This method clears all existing cards from the deck and repopulates it with
     * all possible combinations of suits and ranks, representing a standard deck
     * of playing cards. Afterward, the deck is shuffled to randomize the order
     * of the cards.
     */
    public void reset() {
        cards.clear();
        for (int d = 0; d < numberOfDecks; d++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
        shuffle();
    }

    /**
     * Returns the number of cards currently present in the deck.
     *
     * @return the number of cards in the deck
     */
    public int size() {
        return cards.size();
    }

    /**
     * Retrieves the top card of the deck without removing it. The top
     * card is the card at the last position in the internal list of cards.
     * If the deck is empty, this method returns {@code null}.
     *
     * @return the top card of the deck, or {@code null} if the deck is empty
     */
    public Card peek() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.get(cards.size() - 1);
    }

    /**
     * Returns an unmodifiable list of all cards currently in the deck.
     * The list reflects the current state of the deck and maintains the order
     * of the cards as they appear internally.
     *
     * @return an unmodifiable list of cards in the deck
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
