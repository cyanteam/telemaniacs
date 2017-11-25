package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.dto.TransmissionDao;
import org.cyanteam.telemaniacs.core.dto.TransmissionOccurrenceDao;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Transmission service
 * @author Michael Le
 */
@Service
public class TransmissionServiceImpl implements TransmissionService {
    @Inject
    private DateTimeService dateTimeService;

    @Inject
    private StatisticsService statisticsService;

    @Inject
    private TransmissionDao transmissionDao;

    @Inject
    private TransmissionOccurrenceDao transmissionOccurrenceDao;

    @Override
    public void createTransmission(Transmission transmission) {
        transmissionDao.create(transmission);
    }

    @Override
    public void updateTransmission(Transmission transmission) {
        transmissionDao.update(transmission);
    }

    @Override
    public void removeTransmission(Transmission transmission) {
        transmissionDao.delete(transmission);
    }

    @Override
    public Transmission getTransmissionById(Long id) {
        return transmissionDao.findById(id);
    }

    @Override
    public Transmission getTransmissionByName(String name) {
        return transmissionDao.findByName(name);
    }

    @Override
    public List<Transmission> getTransmissionsByType(TransmissionType type) {
        // return transmissionDao.findByType(type);
        return null;
    }

    @Override
    public void addOccurrence(TransmissionOccurrence occurrence) {
        transmissionOccurrenceDao.create(occurrence);
    }

    @Override
    public void updateOccurrence(TransmissionOccurrence occurrence) {
        transmissionOccurrenceDao.update(occurrence);
    }

    @Override
    public void removeOccurrence(TransmissionOccurrence occurrence) {
        transmissionOccurrenceDao.remove(occurrence);
    }

    @Override
    public List<TransmissionOccurrence> getOccurrences(Transmission transmission) {
        return transmission.getOccurrences();
    }

    @Override
    public List<TransmissionOccurrence> getUpcomingOccurrences(Transmission transmission) {
        return transmissionOccurrenceDao.findByTransmissionAndDate(transmission, dateTimeService.getCurrent());
    }

    @Override
    public List<Voting> getVotings(Transmission transmission) {
        return transmission.getVoting();
    }

    @Override
    public Double getAverageVoting(Transmission transmission) {
        List<Voting> votings = getVotings(transmission);
        if (votings.size() == 0) {
            return null;
        }

        Collection<Integer> ranks = votings.stream()
                .map(Voting::getRank)
                .collect(Collectors.toList());
        return statisticsService.Average(ranks);
    }
}
