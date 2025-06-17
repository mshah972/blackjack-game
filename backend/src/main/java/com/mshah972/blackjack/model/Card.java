package com.mshah972.blackjack.model;

import java.util.Objects;

/**
 * Represents a playing card with a suit and rank.
 * A card is immutable and uniquely identified by its suit and rank.
 *
 * @author Moksh Shah
 */

public class Card {
    private final Suit suit;
    private final Rank rank;

    /**
     * Constructs a Card instance with a specified suit and rank.
     *
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Retrieves the suit of this card.
     *
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Retrieves the rank of this card.
     *
     * @return the rank of the card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Retrieves the numeric value associated with the rank of this card.
     * Number cards return their face value (e.g., TWO returns 2, THREE returns 3, etc.),
     * face cards (JACK, QUEEN, KING) and TEN return 10, and ACE returns 1.
     *
     * @return the numeric value of the card's rank
     * @throws IllegalStateException if the card's rank is unknown
     */
    public int getValue() {
        switch (rank) {
            case ACE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            default:
                throw new IllegalStateException("Unknown rank: " + rank);
        }
    }

    /**
     * Returns a string representation of this card.
     * The returned string contains the rank and suit of the card
     * in the format "[Rank] of [Suit]".
     *
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return rank.toString() + " of " + suit.toString();
    }

    /**
     * Compares this card to the specified object for equality.
     * Two cards are considered equal if and only if they have the same rank and suit.
     *
     * @param o the object to compare this card against
     * @return {@code true} if the specified object is equal to this card, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    /**
     * Generates a hash code for this card based on its suit and rank.
     *
     * @return a hash code value for this card
     */
    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
