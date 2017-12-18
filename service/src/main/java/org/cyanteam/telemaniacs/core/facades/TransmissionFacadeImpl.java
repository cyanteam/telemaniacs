package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.dto.*;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.cyanteam.telemaniacs.core.facade.TransmissionFacade;
import org.cyanteam.telemaniacs.core.services.ChannelService;
import org.cyanteam.telemaniacs.core.services.ObjectMapperService;
import org.cyanteam.telemaniacs.core.services.TransmissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Simona Tinkova
 */
@Service
@Transactional
public class TransmissionFacadeImpl implements TransmissionFacade {
    @Inject
    private TransmissionService transmissionService;

    @Inject
    private ChannelService channelService;

    @Inject
    private ObjectMapperService objectMapperService;

    @Override
    public Long create(TransmissionCreateDTO transmissionCreateDTO) throws ConstraintViolationException {
        if (transmissionCreateDTO == null) {
            throw new IllegalArgumentException("TransmissionCreateDTO cannot be null!");
        }

        Transmission transmission = objectMapperService.map(transmissionCreateDTO, Transmission.class);
        transmissionService.create(transmission);

        return transmission.getId();
    }

    @Override
    public TransmissionDTO remove(TransmissionDTO transmissionDTO) throws IllegalArgumentException {
        Transmission transmission = prepareTransmission(transmissionDTO);

        transmissionService.remove(transmission);
        return transmissionDTO;
    }

    @Override
    public TransmissionDTO update(TransmissionDTO transmissionDTO) throws ConstraintViolationException, IllegalArgumentException {
        Transmission transmission = prepareTransmission(transmissionDTO);

        transmissionService.update(transmission);
        return transmissionDTO;
    }

    @Override
    public TransmissionDTO findById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id of transmission cannot be null!");
        }

        Transmission transmission = transmissionService.findById(id);
        return objectMapperService.map(transmission, TransmissionDTO.class);
    }

    @Override
    public TransmissionDTO findByName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Name of transmission cannot be null!");
        }

        Transmission transmission = transmissionService.findByName(name);

        return objectMapperService.map(transmission, TransmissionDTO.class);
    }

    @Override
    public List<TransmissionDTO> findByType(TransmissionType type) {
        if (type == null) {
            throw new IllegalArgumentException("Type of transmission cannot be null!");
        }

        List<Transmission> transmissions = transmissionService.findAllByType(type);

        return objectMapperService.map(transmissions, TransmissionDTO.class);
    }

    @Override
    public List<TransmissionDTO> findAll() {
        return objectMapperService.map(transmissionService.findAll(), TransmissionDTO.class);
    }

    @Override
    public Long addOccurrence(TransmissionOccurrenceCreateDTO occurrenceDTO) {
        TransmissionOccurrence occurrence = prepareTransmissionOccurenceCreate(occurrenceDTO);
        occurrence.setChannel(channelService.findById(occurrenceDTO.getChannelId()));
        occurrence.setTransmission(transmissionService.findById(occurrenceDTO.getTransmissionId()));

        transmissionService.addOccurrence(occurrence);
        return occurrence.getId();
    }

    @Override
    public void updateOccurrence(TransmissionOccurrenceCreateDTO occurrenceDTO) {
        TransmissionOccurrence occurrence = prepareTransmissionOccurenceCreate(occurrenceDTO);
        occurrence.setChannel(channelService.findById(occurrenceDTO.getChannelId()));
        occurrence.setTransmission(transmissionService.findById(occurrenceDTO.getTransmissionId()));

        transmissionService.updateOccurrence(occurrence);
    }

    @Override
    public void removeOccurrence(Long occurrenceId) {
        TransmissionOccurrence occurrence = transmissionService.getOccurranceById(occurrenceId);
        transmissionService.removeOccurrence(occurrence);
    }

    @Override
    public List<TransmissionOccurrenceDTO> getOccurrences(TransmissionDTO transmissionDTO) {
        if (transmissionDTO == null) {
            throw new IllegalArgumentException("Transmission cannot be null!");
        }
        Transmission transmission = prepareTransmission(transmissionDTO);

        List<TransmissionOccurrence> occurences = transmissionService.getOccurrences(transmission);
        return objectMapperService.map(occurences, TransmissionOccurrenceDTO.class);

    }

    @Override
    public List<TransmissionOccurrenceDTO> getUpcomingOccurrences(TransmissionDTO transmissionDTO) {
        if (transmissionDTO == null) {
            throw new IllegalArgumentException("Transmission cannot be null!");
        }
        Transmission transmission = prepareTransmission(transmissionDTO);

        List<TransmissionOccurrence> occurences = transmissionService.getUpcomingOccurrences(transmission);
        return objectMapperService.map(occurences, TransmissionOccurrenceDTO.class);
    }

    @Override
    public List<VotingDTO> getVotings(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Transmission ID cannot be null!");
        }

        Transmission transmission = transmissionService.findById(id);
        List<Voting> votings = transmissionService.getVotings(transmission);

        return objectMapperService.map(votings, VotingDTO.class);
    }

    @Override
    public Double getAverageVoting(TransmissionDTO transmissionDTO) {
        if (transmissionDTO == null) {
            throw new IllegalArgumentException("Transmission cannot be null!");
        }
        Transmission transmission = prepareTransmission(transmissionDTO);

        Double rank = transmissionService.getAverageVoting(transmission);

        return rank != null ? rank : 0;
    }

    @Override
    public TransmissionOccurrenceCreateDTO getOccurrenceById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id of occurrance cannot be null!");
        }

        TransmissionOccurrence occurence = transmissionService.getOccurranceById(id);
        TransmissionOccurrenceCreateDTO occurrenceDTO = objectMapperService.map(occurence, TransmissionOccurrenceCreateDTO.class);
        occurrenceDTO.setChannelId(occurence.getChannel().getId());
        occurrenceDTO.setTransmissionId(occurence.getTransmission().getId());
        return occurrenceDTO;
    }

    private Transmission prepareTransmission(TransmissionDTO transmissionDTO) {
        if (transmissionDTO == null) {
            throw new IllegalArgumentException("TransmissionDTO cannot be null!");
        }

        return objectMapperService.map(transmissionDTO, Transmission.class);
    }

    private TransmissionOccurrence prepareTransmissionOccurence(TransmissionOccurrenceDTO occurenceDTO) {
        if (occurenceDTO == null) {
            throw new IllegalArgumentException("TransmissionOccurenceDTO cannot be null!");
        }

        return objectMapperService.map(occurenceDTO, TransmissionOccurrence.class);
    }

    private TransmissionOccurrence prepareTransmissionOccurenceCreate(TransmissionOccurrenceCreateDTO occurenceDTO) {
        if (occurenceDTO == null) {
            throw new IllegalArgumentException("TransmissionOccurenceCreateDTO cannot be null!");
        }

        TransmissionOccurrence occurrence = objectMapperService.map(occurenceDTO, TransmissionOccurrence.class);
        if (occurenceDTO.getStartDate() != null) {
            LocalDateTime dateTime = LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(occurenceDTO.getStartDate()));
            occurrence.setStartDate(dateTime);
        }

        return occurrence;
    }
}
