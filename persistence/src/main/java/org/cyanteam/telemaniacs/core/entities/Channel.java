package org.cyanteam.telemaniacs.core.entities;

import org.cyanteam.telemaniacs.core.enums.ChannelType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class representing one channel in system.
 *
 * @author Tomas Milota
 */
@Entity
@Table(name = "Channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "channel")
    private List<TransmissionOccurrence> transmissionOccurrences = new ArrayList<>();

    @Enumerated
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

    public List<TransmissionOccurrence> getTransmissionOccurrences() {
        return Collections.unmodifiableList(transmissionOccurrences);
    }

    public void setTransmissionOccurrences(List<TransmissionOccurrence> transmissionOccurrences) {
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
        if (this == o) {
            return true;
        }
        if ( !(o instanceof Channel) ) {
            return false;
        }

        Channel channel = (Channel) o;

        if(!Objects.equals(getName(), channel.getName())) {
            return false;
        }
        if(!Objects.equals(getChannelType(), channel.getChannelType())) {
            return false;
        }
        if(!Objects.equals(getLanguage(), channel.getLanguage())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 11;
        hash = 23 * hash + Objects.hashCode(getName());
        hash = 23 * hash + Objects.hashCode(getChannelType());
        hash = 23 * hash + Objects.hashCode(getLanguage());

        return hash;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", channelType=" + channelType +
                ", language='" + language + '\'' +
                '}';
    }
}
