package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.entities.Transmission;

import javax.validation.ConstraintViolationException;

/**
 * Transmission DAO
 * @author Simona Tinkova
 */
public interface TransmissionDao {
	/**
	 * New transmission into the database.
	 *
	 * @param transmission represents entity that will be created into database.
	 * @throws ConstraintViolationException if any constraints on columns
	 * are violated.
	 */
	void create(Transmission transmission) throws ConstraintViolationException;

	/**
	 * The transmission will be deleted from database.
	 *
	 * @param transmission represents entity that will be deleted from database
	 * @throws IllegalArgumentException if transmission for removing is not stored
	 * in the database or transmission is null.
	 */
	void delete(Transmission transmission) throws IllegalArgumentException;

	/**
	 * The transmission will be updated in the database.
	 *
	 * @param transmission the transmission that will be updated in the database
	 * @return updated transmission
	 * @throws ConstraintViolationException if any constraints on columns
	 * are violated.
	 * @throws IllegalArgumentException if transmission for updating is not in the database.
	 */
	void update(Transmission transmission) throws ConstraintViolationException, IllegalArgumentException;

	/**
	 * Returns the transmission with the specific id.
	 *
	 * @param id the id of the transmission that will be returned
	 * @return the transmission with the specific id
	 * @throws IllegalArgumentException if id is null.
	 */
	Transmission findById(Long id) throws IllegalArgumentException;

	/**
	 * Returns the transmission with the specific name.
	 *
	 * @param name the name of the transmission that will be returned
	 * @return the transmissioin with the specific name
	 * @throws IllegalArgumentException if name is null.
	 */
	Transmission findByName(String name) throws IllegalArgumentException;

}
