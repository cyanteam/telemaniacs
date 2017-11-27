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
    private Channel channel;

    private List<TransmissionOccurrence> transmissionOccurrences = new ArrayList<>();

    public ChannelSchedule() {
    }

    public ChannelSchedule(Channel channel, List<TransmissionOccurrence> transmissionOccurrences) {
        this.channel = channel;
        this.transmissionOccurrences = transmissionOccurrences;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<TransmissionOccurrence> getTransmissionOccurrences() {
        return transmissionOccurrences;
    }

    public void setTransmissionOccurrences(List<TransmissionOccurrence> transmissionOccurrences) {
        this.transmissionOccurrences = transmissionOccurrences;
    }
}
