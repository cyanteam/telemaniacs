package org.cyanteam.telemaniacs.core.dto;

import java.time.LocalDateTime;

/**
 * @author Simona Tinkova
 * @author Michael Le
 */
public class ScheduleTransmissionOccurrenceDTO {
	private Long id;

	private ScheduleTransmissionDTO transmission;

	private String partName;

	private String startDate;

	private boolean isRerun;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ScheduleTransmissionDTO getTransmission() {
		return transmission;
	}

	public void setTransmission(ScheduleTransmissionDTO transmission) {
		this.transmission = transmission;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public boolean isRerun() {
		return isRerun;
	}

	public void setRerun(boolean rerun) {
		isRerun = rerun;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ScheduleTransmissionOccurrenceDTO)) return false;

		ScheduleTransmissionOccurrenceDTO that = (ScheduleTransmissionOccurrenceDTO) o;

		return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
}
