package org.cyanteam.telemaniacs.core.services;

import java.util.Collection;

/**
 * Simple statistics calculation
 *
 * @author Michael Le
 */
public interface StatisticsService {
    /**
     * Sums all items in collection
     *
     * @param items Collection of items
     * @return sum of items
     */
    int sum(Collection<Integer> items);

    /**
     * Calculates average of items in collection
     *
     * @param items Collection of items
     * @return average of items
     */
    double average(Collection<Integer> items);
}
