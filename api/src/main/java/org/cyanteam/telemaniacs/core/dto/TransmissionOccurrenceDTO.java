package org.cyanteam.telemaniacs.core.dto;

import java.time.LocalDateTime;

/**
 * @author Simona Tinkova
 */
public class TransmissionOccurrenceDTO {
	private Long id;

	private ChannelDTO channel;

	private TransmissionDTO transmission;

	private String partName;

	private LocalDateTime startDate;

	private boolean isRerun;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChannelDTO getChannel() {
		return channel;
	}

	public void setChannel(ChannelDTO channel) {
		this.channel = channel;
	}

	public TransmissionDTO getTransmission() {
		return transmission;
	}

	public void setTransmission(TransmissionDTO transmission) {
		this.transmission = transmission;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
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
		if (!(o instanceof TransmissionOccurrenceDTO)) return false;

		TransmissionOccurrenceDTO that = (TransmissionOccurrenceDTO) o;

		return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
}
