package com.mshah972.blackjack.util;

import com.mshah972.blackjack.model.Card;
import java.util.List;

/**
 * Utility to calculate the Blackjack value of a hand of cards.
 */
public class HandValueCalculator {

    /**
     * Calculates the best Blackjack value for a list of cards, treating Aces as 1 or 11.
     *
     * @param cards the hand of cards
     * @return the highest valid hand value ≤ 21, or the minimum value if bust
     */
    public static int calculateValue(List<Card> cards) {
        int sum = 0;
        int aceCount = 0;
        // Sum base values, counting each Ace as 1
        for (Card card : cards) {
            int value = card.getValue();
            if (value == 1) { // Ace
                aceCount++;
            }
            sum += value;
        }
        // Try upgrading Aces from 1 to 11 (add 10) as much as possible without busting
        for (int i = 0; i < aceCount; i++) {
            if (sum + 10 <= 21) {
                sum += 10;
            } else {
                break;
            }
        }
        return sum;
    }
}
