package org.cyanteam.telemaniacs.core.dto;

import java.util.List;

/**
 * @author Simona Tinkova
 */
public class ChannelScheduleDTO {
	private ChannelDTO channel;
	private List<TransmissionOccurrenceDTO> transmissionOccurrences;

	public ChannelDTO getChannel() {
		return channel;
	}

	public void setChannel(ChannelDTO channel) {
		this.channel = channel;
	}

	public List<TransmissionOccurrenceDTO> getTransmissionOccurrences() {
		return transmissionOccurrences;
	}

	public void setTransmissionOccurrences(List<TransmissionOccurrenceDTO> transmissionOccurrences) {
		this.transmissionOccurrences = transmissionOccurrences;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ChannelScheduleDTO)) return false;

		ChannelScheduleDTO that = (ChannelScheduleDTO) o;

		if (getChannel() != null ? !getChannel().equals(that.getChannel()) : that.getChannel() != null) return false;
		return getTransmissionOccurrences() != null ? getTransmissionOccurrences().equals(that.getTransmissionOccurrences()) : that.getTransmissionOccurrences() == null;
	}

	@Override
	public int hashCode() {
		int result = getChannel() != null ? getChannel().hashCode() : 0;
		result = 31 * result + (getTransmissionOccurrences() != null ? getTransmissionOccurrences().hashCode() : 0);
		return result;
	}
}
