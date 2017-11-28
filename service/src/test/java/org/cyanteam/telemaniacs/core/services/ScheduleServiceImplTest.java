package org.cyanteam.telemaniacs.core.services;


import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dto.ChannelSchedule;
import org.cyanteam.telemaniacs.core.dto.Schedule;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.helpers.ChannelBuilder;
import org.cyanteam.telemaniacs.core.helpers.TransmissionBuilder;
import org.cyanteam.telemaniacs.core.helpers.TransmissionOccurrenceBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cyanteam.telemaniacs.core.utils.ListUtils.createList;

/**
 *
 * @author Tomas Milota
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ScheduleServiceImplTest {

    @Inject
    private ScheduleService scheduleService;

    private TransmissionOccurrence occurrence1;
    private TransmissionOccurrence occurrence2;
    private TransmissionOccurrence occurrence3;
    private Transmission transmission1;
    private Transmission transmission2;
    private Channel hbo;
    private Channel nova;

    @Before
    public void setUp() {
        transmission1 = TransmissionBuilder
                .sampleShawshankBuilder()
                .length(100 * 60)
                .build();
        transmission2 = TransmissionBuilder.sampleIceAgeBuilder().build();

        hbo = ChannelBuilder.hbo().build();
        nova = ChannelBuilder.nova().build();

        TransmissionOccurrenceBuilder builder = new TransmissionOccurrenceBuilder()
                .transmission(transmission1)
                .channel(hbo);
        occurrence1 = builder.id(1L).startDate(2017, 1, 2, 20, 0, 0).build();
        occurrence2 = builder.id(2L).startDate(2017, 1, 3, 15, 0, 0).build();
        hbo.setTransmissionOccurrences(createList(occurrence1, occurrence2));

        builder = new TransmissionOccurrenceBuilder()
                .transmission(transmission2)
                .channel(nova);
        occurrence3 = builder.id(3L).startDate(2017, 1, 13, 15, 0, 0).build();
        nova.setTransmissionOccurrences(createList(occurrence3));
    }

    @Test
    public void getScheduleTest() {
        LocalDateTime start = LocalDateTime.of(2017, 1, 3,10, 0);
        LocalDateTime end = LocalDateTime.of(2017, 11, 27, 15, 30);
        List<Channel> channels = createList(hbo, nova);

        Schedule result = scheduleService.getSchedule(channels, start, end);
        List<ChannelSchedule> actualChannelSchedules = result.getChannelSchedules();
        assertThat(actualChannelSchedules).hasSize(2);
        assertThat(actualChannelSchedules.get(0).getChannel())
                .isEqualToComparingFieldByFieldRecursively(hbo);
        assertThat(actualChannelSchedules.get(0).getTransmissionOccurrences())
                .containsExactly(occurrence2);
        assertThat(actualChannelSchedules.get(1).getChannel())
                .isEqualToComparingFieldByFieldRecursively(nova);
        assertThat(actualChannelSchedules.get(1).getTransmissionOccurrences())
                .containsExactly(occurrence3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getScheduleWithNullStartTest() {
        LocalDateTime start = null;
        LocalDateTime end = LocalDateTime.of(2017, 11, 27, 15, 30);
        List<Channel> channels = createList(hbo, nova);
        scheduleService.getSchedule(channels, start, end);
    }

    @Test
    public void getEndDateTest() {
        LocalDateTime expectedEndDate = occurrence1.getStartDate().plusSeconds(transmission1.getLength());
        LocalDateTime actualEndDate = scheduleService.getEndDate(occurrence1);
        assertThat(actualEndDate.compareTo(expectedEndDate)).isEqualTo(0);
    }

}
