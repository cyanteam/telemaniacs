package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.dto.TransmissionCreateDTO;
import org.cyanteam.telemaniacs.core.dto.TransmissionDTO;
import org.cyanteam.telemaniacs.core.dto.TransmissionOccurrenceDTO;
import org.cyanteam.telemaniacs.core.dto.VotingDTO;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.cyanteam.telemaniacs.core.facade.TransmissionFacade;
import org.cyanteam.telemaniacs.core.services.ObjectMapperService;
import org.cyanteam.telemaniacs.core.services.TransmissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
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
    public TransmissionOccurrenceDTO addOccurrence(TransmissionOccurrenceDTO occurrenceDTO) {
        TransmissionOccurrence occurence = prepareTransmissionOccurence(occurrenceDTO);

        transmissionService.addOccurrence(occurence);
        occurrenceDTO.setId(occurence.getId());
        return occurrenceDTO;
    }

    @Override
    public TransmissionOccurrenceDTO updateOccurrence(TransmissionOccurrenceDTO occurrenceDTO) {
        TransmissionOccurrence occurence = prepareTransmissionOccurence(occurrenceDTO);

        transmissionService.updateOccurrence(occurence);
        return occurrenceDTO;
    }

    @Override
    public TransmissionOccurrenceDTO removeOccurrence(TransmissionOccurrenceDTO occurrenceDTO) {
        TransmissionOccurrence occurence = prepareTransmissionOccurence(occurrenceDTO);

        transmissionService.removeOccurrence(occurence);
        return occurrenceDTO;
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
    public List<VotingDTO> getVotings(TransmissionDTO transmissionDTO) {
        if (transmissionDTO == null) {
            throw new IllegalArgumentException("Transmission cannot be null!");
        }
        Transmission transmission = prepareTransmission(transmissionDTO);

        List<Voting> votings = transmissionService.getVotings(transmission);

        return objectMapperService.map(votings, VotingDTO.class);
    }

    @Override
    public Double getAverageVoting(TransmissionDTO transmissionDTO) {
        if (transmissionDTO == null) {
            throw new IllegalArgumentException("Transmission cannot be null!");
        }
        Transmission transmission = prepareTransmission(transmissionDTO);

        Double votings = transmissionService.getAverageVoting(transmission);

        return votings;
    }

    @Override
    public TransmissionOccurrenceDTO getOccurranceById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id of occurrance cannot be null!");
        }

        TransmissionOccurrence occurence = transmissionService.getOccurranceById(id);

        return objectMapperService.map(occurence, TransmissionOccurrenceDTO.class);
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
}
