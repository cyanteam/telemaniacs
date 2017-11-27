package org.cyanteam.telemaniacs.core.helpers;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.enums.ChannelType;

import java.util.List;

/**
 * @author Simona Tinkova
 */
public class ChannelBuilder {
	private Long id;
	private String name;
	private List<TransmissionOccurrence> transmissionOccurrences;
	private ChannelType channelType;
	private String language;

	public ChannelBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public ChannelBuilder name(String name) {
		this.name = name;
		return this;
	}

	public ChannelBuilder transmissionOccurrences(List<TransmissionOccurrence> transmissionOccurrences) {
		this.transmissionOccurrences = transmissionOccurrences;
		return this;
	}

	public ChannelBuilder channelType(ChannelType channelType) {
		this.channelType = channelType;
		return this;
	}


	public ChannelBuilder language(String language){
		this.language = language;
		return this;
	}

	public Channel build(){
		Channel channel = new Channel();
		channel.setTransmissionOccurrences(transmissionOccurrences);
		channel.setName(name);
		channel.setLanguage(language);
		channel.setChannelType(channelType);
		channel.setId(id);
		return channel;
	}

	public static ChannelBuilder nova(){
		return new ChannelBuilder()
				.language("Czech")
				.channelType(ChannelType.COMMERCE)
				.name("Nova");
	}

	public static ChannelBuilder hbo(){
		return new ChannelBuilder()
				.language("English")
				.name("HBO")
				.channelType(ChannelType.MOVIE);
	}

	public static ChannelBuilder disneyChannel(){
		return new ChannelBuilder()
				.language("Czech")
				.name("Disney Channel")
				.channelType(ChannelType.CHILDREN);
        }
        
        
}

