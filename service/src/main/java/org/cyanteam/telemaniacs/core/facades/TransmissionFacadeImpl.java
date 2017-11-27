package org.cyanteam.telemaniacs.core.facades;

import org.cyanteam.telemaniacs.core.dto.TransmissionDTO;
import org.cyanteam.telemaniacs.core.entities.Transmission;
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
	public TransmissionDTO create(TransmissionDTO transmissionDTO) throws ConstraintViolationException {
		Transmission transmission= prepareTransmission(transmissionDTO);

		transmissionService.createTransmission(transmission);
		transmissionDTO.setId(transmission.getId());
		return transmissionDTO;
	}

	@Override
	public TransmissionDTO delete(TransmissionDTO transmissionDTO) throws IllegalArgumentException {
		Transmission transmission = prepareTransmission(transmissionDTO);

		transmissionService.removeTransmission(transmission);
		return transmissionDTO;
	}

	@Override
	public TransmissionDTO update(TransmissionDTO transmissionDTO) throws ConstraintViolationException, IllegalArgumentException {
		Transmission transmission = prepareTransmission(transmissionDTO);

		transmissionService.updateTransmission(transmission);
		return transmissionDTO;
	}

	@Override
	public TransmissionDTO findById(Long id) throws IllegalArgumentException {
		if(id == null) {
			throw new IllegalArgumentException("Id of transmission cannot be null!");
		}

		Transmission transmission = transmissionService.getTransmissionById(id);

		return objectMapperService.map(transmission, TransmissionDTO.class);
	}

	@Override
	public TransmissionDTO findByName(String name) throws IllegalArgumentException {
		if(name == null) {
			throw new IllegalArgumentException("Name of transmission cannot be null!");
		}

		Transmission transmission = transmissionService.getTransmissionByName(name);

		return objectMapperService.map(transmission, TransmissionDTO.class);
	}

	@Override
	public List<TransmissionDTO> findByType(TransmissionType type) {
		if(type == null) {
			throw new IllegalArgumentException("Type of transmission cannot be null!");
		}

		List<Transmission> transmissions = transmissionService.getTransmissionsByType(type);

		return objectMapperService.map(transmissions, TransmissionDTO.class);
	}

	@Override
	public List<TransmissionDTO> findAll() {
		return objectMapperService.map(transmissionService.findAllTransmissions(), TransmissionDTO.class);
	}

	private Transmission prepareTransmission(TransmissionDTO transmissionDTO) {
		if (transmissionDTO == null) {
			throw new IllegalArgumentException("TransmissionDTO cannot be null!");
		}

		return objectMapperService.map(transmissionDTO, Transmission.class);
	}
}
