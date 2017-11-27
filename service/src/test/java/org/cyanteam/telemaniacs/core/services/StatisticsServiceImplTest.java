package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * Tests for statistics service
 * @author Michael Le
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticsServiceImplTest {
    @Inject
    private StatisticsService statisticsService;

    private List<Integer> items;

    @Before
    public void setUp() {
        items = IntStream.range(1, 11).boxed().collect(Collectors.toList());
    }

    @Test
    public void sumTest() {
        assertThat(statisticsService.sum(items)).isEqualTo(55);
    }

    @Test
    public void averageTest() {
        assertThat(statisticsService.average(items)).isCloseTo(5.5d, within(0.001));
    }
}
