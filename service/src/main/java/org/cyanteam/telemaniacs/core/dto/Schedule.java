package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;

import java.util.ArrayList;
import java.util.List;

/**
 * Schedule representation
 * @author Michael Le
 */
public class Schedule {
    private List<ChannelSchedule> channelSchedules = new ArrayList<>();

    public List<ChannelSchedule> getChannelSchedules() {
        return channelSchedules;
    }

    public void setChannelSchedules(List<ChannelSchedule> channelSchedules) {
        this.channelSchedules = channelSchedules;
    }

    public void addChannelSchedule(Channel channel, List<TransmissionOccurrence> transmissionOccurrences) {
        if (channelSchedules == null) {
            channelSchedules = new ArrayList<>();
        }

        channelSchedules.add(new ChannelSchedule(channel, transmissionOccurrences));
    }
}
