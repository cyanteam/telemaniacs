package org.cyanteam.telemaniacs.core.services;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Override
    public int sum(Collection<Integer> items) {
        return items.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public double average(Collection<Integer> items) {
        return (double) sum(items) / items.size();
    }
}
