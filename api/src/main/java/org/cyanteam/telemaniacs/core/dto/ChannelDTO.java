package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.enums.ChannelType;

import java.util.List;

/**
 * @author Simona Tinkova
 */
public class ChannelDTO {
	private Long id;
	private String name;
	private List<TransmissionOccurrenceDTO> transmissionOccurrences;
	private ChannelType channelType;
	private String language;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TransmissionOccurrenceDTO> getTransmissionOccurrences() {
		return transmissionOccurrences;
	}

	public void setTransmissionOccurrences(List<TransmissionOccurrenceDTO> transmissionOccurrences) {
		this.transmissionOccurrences = transmissionOccurrences;
	}

	public ChannelType getChannelType() {
		return channelType;
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ChannelDTO)) return false;

		ChannelDTO that = (ChannelDTO) o;

		return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
}
