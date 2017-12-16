package org.cyanteam.telemaniacs.rest.controllers;

import org.cyanteam.telemaniacs.core.dto.TransmissionDTO;
import org.cyanteam.telemaniacs.core.dto.TransmissionOccurrenceDTO;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.cyanteam.telemaniacs.core.facade.TransmissionFacade;
import org.cyanteam.telemaniacs.rest.exceptions.ValidationException;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Simona Tinkova
 */
@RestController
@RequestMapping(Url.TRANSMISSION)
public class TransmissionController {

	@Inject
	private TransmissionFacade transmissionFacade;

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final TransmissionDTO getTransmissionByName(@PathVariable("name") String name) {
		TransmissionDTO transmissions = transmissionFacade
				.findByName(name);

		if (transmissions == null) {
			throw new IllegalArgumentException("No transmission with this name");
		}

		return transmissions;
	}

	@RequestMapping(value = "/type/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<TransmissionDTO> getTransmissionsByType(@PathVariable("type") TransmissionType type) {
		List<TransmissionDTO> transmissions = transmissionFacade
				.findByType(type);

		if (transmissions == null) {
			throw new IllegalArgumentException("No transmissions with this type");
		}

		return transmissions;
	}

	@RequestMapping(value = "/occurrence/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TransmissionOccurrenceDTO createOccurrence(@RequestBody @Valid TransmissionOccurrenceDTO occurenceDTO,
	                                               BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new ValidationException("Invalid ocuurence state.");
		}

		TransmissionOccurrenceDTO occurrence = transmissionFacade.addOccurrence(occurenceDTO);
		return occurrence;
	}

	@RequestMapping(value = "/occurrence/{occurrenceId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TransmissionOccurrenceDTO updateOccurrence(@PathVariable("id") long id,
	                                @RequestBody @Valid TransmissionOccurrenceDTO occurrenceDTO,
	                                BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new ValidationException("Invalid occurrence state.");
		}

		occurrenceDTO.setId(id);
		transmissionFacade.updateOccurrence(occurrenceDTO);
		return occurrenceDTO;
	}

	@RequestMapping(value = "/occurrence/{occurrenceId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TransmissionOccurrenceDTO removeOccurrence(@PathVariable("id") long id,
	                                                  @RequestBody @Valid TransmissionOccurrenceDTO occurrenceDTO,
	                                                  BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new ValidationException("Invalid occurrence state.");
		}

		transmissionFacade.removeOccurrence(occurrenceDTO);
		return occurrenceDTO;
	}
}
