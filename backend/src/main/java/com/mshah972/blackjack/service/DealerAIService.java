package com.mshah972.blackjack.service;

import com.mshah972.blackjack.model.GameState;
import com.mshah972.blackjack.model.Hand;
import com.mshah972.blackjack.model.Card;
import com.mshah972.blackjack.model.Rank;
import com.mshah972.blackjack.util.HandValueCalculator;

/**
 * Implements dealer decision logic following standard Blackjack house rules,
 * with optional handling of soft-17.
 */
public class DealerAIService {
    private final boolean hitSoft17;

    /**
     * Default constructor: dealer stands on soft 17.
     */
    public DealerAIService() {
        this.hitSoft17 = false;
    }

    /**
     * @param hitSoft17 if true, dealer hits on soft 17; otherwise stands.
     */
    public DealerAIService(boolean hitSoft17) {
        this.hitSoft17 = hitSoft17;
    }

    /**
     * Determines whether the dealer should hit based on current hand value and soft-17 rule.
     *
     * @param hand dealer's hand
     * @return true if dealer should hit, false to stand
     */
    public boolean shouldHit(Hand hand) {
        int value = hand.getValue();
        if (value < 17) {
            return true;
        }
        if (value == 17 && hitSoft17 && isSoft17(hand)) {
            return true;
        }
        return false;
    }

    /**
     * Plays out the dealer's turn by drawing cards while shouldHit() returns true.
     *
     * @param state current game state
     */
    public void playDealer(GameState state) {
        Hand dealerHand = state.getDealerHand();
        while (shouldHit(dealerHand)) {
            Card card = state.getDeck().draw();
            dealerHand.addCard(card);
        }
    }

    /**
     * Checks if the hand is a soft 17 (contains an Ace counted as 11).
     *
     * @param hand dealer's hand
     * @return true if soft 17, false otherwise
     */
    private boolean isSoft17(Hand hand) {
        // sum with all Aces as 1
        int baseSum = hand.getCards().stream()
                .mapToInt(Card::getValue)
                .sum();
        // count Aces
        long aceCount = hand.getCards().stream()
                .filter(c -> c.getRank() == Rank.ACE)
                .count();
        // if exactly one Ace can be counted as 11 to make 17, it's soft 17
        return aceCount > 0 && baseSum + 10 == 17;
    }
}
