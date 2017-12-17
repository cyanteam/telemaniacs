package org.cyanteam.telemaniacs.core.dto;

import java.util.List;

/**
 * @author Simona Tinkova
 * @author Michael Le
 */
public class ScheduleChannelDTO {
	private Long channelId;
	private String channelName;
	private List<ScheduleTransmissionOccurrenceDTO> transmissionOccurrences;

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public List<ScheduleTransmissionOccurrenceDTO> getTransmissionOccurrences() {
		return transmissionOccurrences;
	}

	public void setTransmissionOccurrences(List<ScheduleTransmissionOccurrenceDTO> transmissionOccurrences) {
		this.transmissionOccurrences = transmissionOccurrences;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ScheduleChannelDTO)) return false;

		ScheduleChannelDTO that = (ScheduleChannelDTO) o;

		if (getChannelId() != null ? !getChannelId().equals(that.getChannelId()) : that.getChannelId() != null) return false;
		return getTransmissionOccurrences() != null ? getTransmissionOccurrences().equals(that.getTransmissionOccurrences()) : that.getTransmissionOccurrences() == null;
	}

	@Override
	public int hashCode() {
		int result = getChannelId() != null ? getChannelId().hashCode() : 0;
		result = 31 * result + (getTransmissionOccurrences() != null ? getTransmissionOccurrences().hashCode() : 0);
		return result;
	}
}
