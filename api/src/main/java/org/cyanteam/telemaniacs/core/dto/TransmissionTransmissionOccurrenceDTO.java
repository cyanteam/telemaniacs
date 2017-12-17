package org.cyanteam.telemaniacs.core.dto;

import java.time.LocalDateTime;

/**
 * @author Simona Tinkova
 * @author Michael Le
 */
public class TransmissionTransmissionOccurrenceDTO {
	private Long id;

	private TransmissionChannelDTO channel;

	private String partName;

	private LocalDateTime startDate;

	private boolean isRerun;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TransmissionChannelDTO getChannel() {
		return channel;
	}

	public void setChannel(TransmissionChannelDTO channel) {
		this.channel = channel;
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
		if (!(o instanceof TransmissionTransmissionOccurrenceDTO)) return false;

		TransmissionTransmissionOccurrenceDTO that = (TransmissionTransmissionOccurrenceDTO) o;

		return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
}
