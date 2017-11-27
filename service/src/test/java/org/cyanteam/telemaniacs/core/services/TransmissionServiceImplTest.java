package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dao.TransmissionDao;
import org.cyanteam.telemaniacs.core.dao.TransmissionOccurrenceDao;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.cyanteam.telemaniacs.core.helpers.TransmissionBuilder;
import org.cyanteam.telemaniacs.core.helpers.TransmissionOccurrenceBuilder;
import org.cyanteam.telemaniacs.core.helpers.VotingBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.cyanteam.telemaniacs.core.utils.ListUtils.createList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for transmission service
 * @author Michael Le
 */
@ContextConfiguration(classes = ServiceContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TransmissionServiceImplTest {
    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private TransmissionDao transmissionDao;

    @Mock
    private TransmissionOccurrenceDao transmissionOccurrenceDao;

    @Inject
    @InjectMocks
    private TransmissionService transmissionService;

    private LocalDateTime mockDateTime = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
    private Channel channel = new Channel();

    private Transmission transmission1; // Without ID
    private Transmission transmission2;
    private Transmission transmission3;

    private TransmissionOccurrence occurrence1; // Without ID
    private TransmissionOccurrence occurrence2;
    private TransmissionOccurrence occurrence3;

    private Voting voting1;
    private Voting voting2;
    private Voting voting3;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUpTestData() {
        channel.setName("Star Channel");

        transmission1 = TransmissionBuilder.sampleIceAgeBuilder().build();
        transmission2 = TransmissionBuilder.sampleShawshankBuilder().id(1L).build();
        transmission3 = TransmissionBuilder.sampleShawshankBuilder().id(2L).name("The Shawshank Redemption 2").build();

        TransmissionOccurrenceBuilder builder = new TransmissionOccurrenceBuilder()
                .transmission(transmission2).channel(channel);
        occurrence1 = builder.startDate(2017, 1, 1, 20, 0, 0).build();
        occurrence2 = builder.id(1L).startDate(2017, 1, 2, 20, 0, 0).build();
        occurrence3 = builder.id(2L).startDate(2017, 1, 3, 20, 0, 0).build();
        transmission2.setOccurrences(createList(occurrence2, occurrence3));

        voting1 = VotingBuilder.sampleLowVotingBuilder().build();
        voting2 = VotingBuilder.sampleMediumVotingBuilder().build();
        voting3 = VotingBuilder.sampleHighVotingBuilder().build();
        transmission2.setVoting(createList(voting1, voting2, voting3));
    }

    @Test
    public void createTransmissionTest() {
        transmissionService.createTransmission(transmission1);
        verify(transmissionDao).create(transmission1);
    }

    @Test
    public void updateTransmissionTest() {
        transmissionService.updateTransmission(transmission2);
        verify(transmissionDao).update(transmission2);
    }

    @Test
    public void removeTransmissionTest() {
        transmissionService.removeTransmission(transmission2);
        verify(transmissionDao).delete(transmission2);
    }

    @Test
    public void getTransmissionByName() {
        String transmissionName = transmission2.getName();
        when(transmissionDao.findByName(transmissionName)).thenReturn(transmission2);

        Transmission result = transmissionService.getTransmissionByName(transmissionName);
        verify(transmissionDao).findByName(transmissionName);
        assertThat(result).isEqualToComparingFieldByField(transmission2);
    }

    @Test
    public void getTransmissionByType() {
        TransmissionType transmissionType = TransmissionType.MOVIE;
        when(transmissionDao.findByType(transmissionType)).thenReturn(createList(transmission2, transmission3));

        List<Transmission> result = transmissionService.getTransmissionsByType(transmissionType);
        verify(transmissionDao).findByType(transmissionType);
        assertThat(result).containsExactlyInAnyOrder(transmission2, transmission3);
    }

    @Test
    public void addOccurrenceTest() {
        transmissionService.addOccurrence(occurrence1);
        verify(transmissionOccurrenceDao).create(occurrence1);
    }

    @Test
    public void updateOccurrenceTest() {
        transmissionService.updateOccurrence(occurrence2);
        verify(transmissionOccurrenceDao).update(occurrence2);
    }

    @Test
    public void removeOccurrenceTest() {
        transmissionService.removeOccurrence(occurrence2);
        verify(transmissionOccurrenceDao).remove(occurrence2);
    }

    @Test
    public void getOccurrencesTest() {
        List<TransmissionOccurrence> occurrences = transmissionService.getOccurrences(transmission2);
        assertThat(occurrences).containsExactlyInAnyOrder(occurrence2, occurrence3);
    }

    @Test
    public void getUpcomingOccurrencesTest() {
        when(dateTimeService.getCurrent()).thenReturn(mockDateTime);
        when(transmissionOccurrenceDao.findByTransmissionAndDate(transmission2, mockDateTime))
                .thenReturn(createList(occurrence2, occurrence3));

        List<TransmissionOccurrence> result = transmissionService.getUpcomingOccurrences(transmission2);
        verify(transmissionOccurrenceDao).findByTransmissionAndDate(transmission2, mockDateTime);
        assertThat(result).containsExactlyInAnyOrder(occurrence2, occurrence3);
    }


    @Test
    public void getVotingsTest() {
        List<Voting> votings = transmissionService.getVotings(transmission2);
        assertThat(votings).containsExactlyInAnyOrder(voting1, voting2, voting3);
    }

    @Test
    public void getAverageVotingTest() {
        double expected = (double) (voting1.getRank() + voting2.getRank() + voting3.getRank()) / 3;
        Double result = transmissionService.getAverageVoting(transmission2);
        assertThat(result).isCloseTo(expected, within(0.001));
    }

    @Test
    public void getAverageVotingEmptyTest() {
        transmission3.setVoting(new ArrayList<>());
        Double result = transmissionService.getAverageVoting(transmission3);
        assertThat(result).isNull();
    }

    @Test
    public void getAverageVotingNullTest() {
        Double result = transmissionService.getAverageVoting(transmission3);
        assertThat(result).isNull();
    }
}