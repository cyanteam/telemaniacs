package org.cyanteam.telemaniacs.core.facade;

import org.cyanteam.telemaniacs.core.dto.ScheduleDTO;

import java.util.List;

/**
 * @author Simona Tinkova
 */
public interface ScheduleFacade {

	/**
	 * Create new entity in the database
	 * @param schedule entity to be persisted
	 */
	void addSchedule(ScheduleDTO schedule);

	/**
	 * Remove entity from the database
	 * @param schedule entity to be deleted
	 */
	void removeSchedule(ScheduleDTO schedule);

	/**
	 * Get list of all spacecrafts in the database
	 * @return list of all spacecrafts in the database
	 */
	List<ScheduleDTO> findAllSchedules();

	/**
	 * Find schedule by its id
	 * @param id to search for in the database
	 * @return reuquested schedule
	 */
	ScheduleDTO findScheduleById(Long id);

	/**
	 * Update this schedule in the database
	 * @param schedule entity to be updated
	 */
	void updateSchedule(ScheduleDTO schedule);
}
