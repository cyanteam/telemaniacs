package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;

import java.util.ArrayList;
import java.util.List;

/**
 * Channel schedule representation
 * @author Michael Le
 */
public class ChannelSchedule {
    private Long channelId;
    private String channelName;
    private List<TransmissionOccurrence> transmissionOccurrences = new ArrayList<>();

    public ChannelSchedule() {
    }

    public ChannelSchedule(Channel channel, List<TransmissionOccurrence> transmissionOccurrences) {
        setChannelId(channel.getId());
        setChannelName(channel.getName());
        this.transmissionOccurrences = transmissionOccurrences;
    }

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

    public List<TransmissionOccurrence> getTransmissionOccurrences() {
        return transmissionOccurrences;
    }

    public void setTransmissionOccurrences(List<TransmissionOccurrence> transmissionOccurrences) {
        this.transmissionOccurrences = transmissionOccurrences;
    }
}
