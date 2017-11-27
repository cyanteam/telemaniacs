package org.cyanteam.telemaniacs.core.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simona Tinkova
 */
public class ScheduleDTO {
	private List<ChannelScheduleDTO> channelSchedules = new ArrayList<>();


	public List<ChannelScheduleDTO> getChannelSchedules() {
		return channelSchedules;
	}

	public void setChannelSchedules(List<ChannelScheduleDTO> channelSchedules) {
		this.channelSchedules = channelSchedules;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ScheduleDTO)) return false;

		ScheduleDTO that = (ScheduleDTO) o;

		return getChannelSchedules() != null ? getChannelSchedules().equals(that.getChannelSchedules()) : that.getChannelSchedules() == null;
	}

	@Override
	public int hashCode() {
		return getChannelSchedules() != null ? getChannelSchedules().hashCode() : 0;
	}
}
