package org.cyanteam.telemaniacs.core.helpers;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;

import java.time.LocalDateTime;

/**
 * Helper class for building TransmissionOccurrence (for test purposes)
 * @author Michael Le
 */
public class TransmissionOccurrenceBuilder {
    private Long id;
    private Channel channel;
    private Transmission transmission;
    private String partName;
    private LocalDateTime startDate;

    public TransmissionOccurrenceBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TransmissionOccurrenceBuilder channel(Channel channel) {
        this.channel = channel;
        return this;
    }

    public TransmissionOccurrenceBuilder transmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public TransmissionOccurrenceBuilder partName(String partName) {
        this.partName = partName;
        return this;
    }

    public TransmissionOccurrenceBuilder startDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public TransmissionOccurrenceBuilder startDate(int year, int month, int day, int hour, int minute, int second) {
        this.startDate(LocalDateTime.of(year, month, day, hour, minute, second));
        return this;
    }

    public TransmissionOccurrence build() {
        TransmissionOccurrence transmissionOccurrence = new TransmissionOccurrence();
        transmissionOccurrence.setId(id);
        transmissionOccurrence.setChannel(channel);
        transmissionOccurrence.setTransmission(transmission);
        transmissionOccurrence.setPartName(partName);
        transmissionOccurrence.setStartDate(startDate);

        return transmissionOccurrence;
    }
}
