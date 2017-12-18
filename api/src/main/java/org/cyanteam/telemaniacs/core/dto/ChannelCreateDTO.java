package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.enums.ChannelType;

/**
 * Channel Create DTO
 *
 * @author Michael Le
 */
public class ChannelCreateDTO {
	private String name;
	private ChannelType channelType;
	private String language;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
