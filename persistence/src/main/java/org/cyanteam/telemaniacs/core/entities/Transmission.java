package org.cyanteam.telemaniacs.core.entities;

import org.cyanteam.telemaniacs.core.enums.AgeAvailability;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * Entity representing a transmission
 * @author Simona Tinkova
 */

@Entity
@Table(name = "Transmission")
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    private int length;

    @Enumerated
    private AgeAvailability ageAvailability;

    private String language;

    @Enumerated
    private TransmissionType transmissionType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transmission")
    private List<TransmissionOccurrence> occurrences;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transmission")
    private List<Voting> voting;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setAgeAvailability(AgeAvailability ageAvailability) {
        this.ageAvailability = ageAvailability;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLength() {
        return length;
    }

    public AgeAvailability getAgeAvailability() {
        return ageAvailability;
    }

    public String getLanguage() {
        return language;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public List<TransmissionOccurrence> getOccurrences() {
        if (occurrences == null) {
            return null;
        }

        return Collections.unmodifiableList(occurrences);
    }

    public void setOccurrences(List<TransmissionOccurrence> occurrences) {
        this.occurrences = occurrences;
    }

    public List<Voting> getVoting() {
        if (voting == null) {
            return voting;
        }

        return voting;
    }

    public void setVoting(List<Voting> voting) {
        this.voting = voting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transmission)) return false;

        Transmission that = (Transmission) o;

        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", length=" + length +
                ", ageAvailability=" + ageAvailability +
                ", language='" + language + '\'' +
                ", transmissionType=" + transmissionType +
                '}';
    }
}
