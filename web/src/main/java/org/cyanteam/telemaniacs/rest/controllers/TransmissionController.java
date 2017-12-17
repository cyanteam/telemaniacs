package org.cyanteam.telemaniacs.rest.controllers;

import org.cyanteam.telemaniacs.core.dto.TransmissionCreateDTO;
import org.cyanteam.telemaniacs.core.dto.TransmissionDTO;
import org.cyanteam.telemaniacs.core.dto.TransmissionOccurrenceDTO;
import org.cyanteam.telemaniacs.core.dto.VotingDTO;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.cyanteam.telemaniacs.core.facade.TransmissionFacade;
import org.cyanteam.telemaniacs.core.facade.UserProfileFacade;
import org.cyanteam.telemaniacs.rest.exceptions.ResourceNotFoundException;
import org.cyanteam.telemaniacs.rest.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Simona Tinkova
 * @author Tomas Milota
 */
@RestController
@RequestMapping(Url.TRANSMISSION)
public class TransmissionController {

	@Inject
	private TransmissionFacade transmissionFacade;
	@Inject
	private UserProfileFacade userProfileFacade;

	private final static Logger log = LoggerFactory.getLogger(TransmissionController.class);

	/**
	 * Creates new transmission.
	 *
	 * @param transmissionCreateDTO transmission that will be created
	 * @return created transmission
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TransmissionDTO createTransmission(@RequestBody @Valid TransmissionCreateDTO transmissionCreateDTO,
											  BindingResult bindingResult) {
		log.debug("rest createTransmission()");

		if (bindingResult.hasErrors()) {
			throw new ValidationException("Invalid transmission state.");
		}

		Long transmissionId = transmissionFacade.create(transmissionCreateDTO);
		return transmissionFacade.findById(transmissionId);
	}

	/**
	 * Updates transmission.
	 *
	 * @param id ID of updated transmission
	 * @param transmissionDTO transmission with modified values
	 * @return updated transmission
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TransmissionDTO updateTransmission(@PathVariable("id") long id,
											  @RequestBody @Valid TransmissionDTO transmissionDTO,
											  BindingResult bindingResult) {
		log.debug("rest updateTransmission({})");

		if (bindingResult.hasErrors()) {
			throw new ValidationException("Invalid transmission state.");
		}

		transmissionDTO.setId(id);
		return transmissionFacade.update(transmissionDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public final void deleteTransmission(@PathVariable("id") long id) throws Exception {
		log.debug("rest deleteTransmission({})", id);

		TransmissionDTO transmissionDTO = transmissionFacade.findById(id);
		if (transmissionDTO == null) {
			throw new ResourceNotFoundException("Transmission", id);
		}

		transmissionFacade.remove(transmissionDTO);
	}

	/**
	 * Retrieves transmission with specific ID
	 *
	 * @param id ID of the transmission
	 * @return transmission with specific ID
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TransmissionDTO getTransmission(@PathVariable("id") long id) {
		log.debug("rest transmission({})");

		TransmissionDTO transmissionDTO = transmissionFacade.findById(id);
		if (transmissionDTO == null) {
			throw new ResourceNotFoundException("Transmission", id);
		}

		return transmissionDTO;
	}

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
	public final List<TransmissionDTO> getTransmissionsByType(@PathVariable("type") String type) {
		List<TransmissionDTO> transmissions = transmissionFacade
				.findByType(TransmissionType.valueOf(type));

		if (transmissions == null) {
			throw new IllegalArgumentException("No transmissions with this type");
		}

		return transmissions;
	}

	/**
	 * Produces list of all transmissions in JSON.
	 *
	 * @return list of transmissions
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<TransmissionDTO> getAllTransmissions() {
		log.debug("rest getAllTransmissions()");

		return transmissionFacade.findAll();
	}

	@RequestMapping(value = "/{transmissionId}/follow/{userId}", method = RequestMethod.POST)
	public void followTransmission(@PathVariable("transmissionId") long transmissionId, @PathVariable("userId") long userId) {
		userProfileFacade.followTransmission(userId, transmissionId);
	}

	@RequestMapping(value = "/{transmissionId}/unfollow/{userId}", method = RequestMethod.POST)
	public void unfollowTransmission(@PathVariable("transmissionId") long transmissionId, @PathVariable("userId") long userId) {
		userProfileFacade.unfollowTransmission(userId, transmissionId);
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

	@RequestMapping(value = "/{id}/votings", method = RequestMethod.GET)
	public List<VotingDTO> getVotings(@PathVariable("id") long id) {
		TransmissionDTO transmissionDTO = transmissionFacade.findById(id);
		if (transmissionDTO == null) {
			throw new ResourceNotFoundException("Transmission", id);
		}

		return transmissionFacade.getVotings(transmissionDTO);
	}

	@RequestMapping(value = "/{id}/rank", method = RequestMethod.GET)
	public Double getAverageRank(@PathVariable("id") long id) {
		TransmissionDTO transmissionDTO = transmissionFacade.findById(id);
		if (transmissionDTO == null) {
			throw new ResourceNotFoundException("Transmission", id);
		}

		return transmissionFacade.getAverageVoting(transmissionDTO);
	}
}