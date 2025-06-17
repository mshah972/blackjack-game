
package com.mshah972.blackjack.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleUtil {
    private static final Random RANDOM = new Random();

    /**
     * Shuffles the given list in-place using the Fisher–Yates algorithm.
     *
     * @param list the list to shuffle
     * @param <T>  the type of list elements
     */
    public static <T> void shuffle(List<T> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            Collections.swap(list, i, j);
        }
    }
}
