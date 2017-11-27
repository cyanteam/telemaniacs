package org.cyanteam.telemaniacs.core.helpers;

import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.enums.AgeAvailability;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;

import java.util.List;

/**
 * Helper class for building Transmission (for test purposes)
 * @author Michael Le
 */
public class TransmissionBuilder {
    private Long id;
    private String name;
    private String description;
    private int length;
    private AgeAvailability ageAvailability;
    private String language;
    private TransmissionType transmissionType;
    private List<TransmissionOccurrence> occurrences;
    private List<Voting> votings;

    public TransmissionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TransmissionBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TransmissionBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TransmissionBuilder length(int length) {
        this.length = length;
        return this;
    }

    public TransmissionBuilder ageAvailability(AgeAvailability ageAvailability) {
        this.ageAvailability = ageAvailability;
        return this;
    }

    public TransmissionBuilder language(String language) {
        this.language = language;
        return this;
    }

    public TransmissionBuilder type(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
        return this;
    }

    public TransmissionBuilder occurrences(List<TransmissionOccurrence> occurrences) {
        this.occurrences = occurrences;
        return this;
    }

    public TransmissionBuilder votings(List<Voting> votings) {
        this.votings = votings;
        return this;
    }

    public Transmission build() {
        Transmission transmission = new Transmission();
        transmission.setId(id);
        transmission.setName(name);
        transmission.setDescription(description);
        transmission.setLength(length);
        transmission.setAgeAvailability(ageAvailability);
        transmission.setLanguage(language);
        transmission.setTransmissionType(transmissionType);
        transmission.setOccurrences(occurrences);
        transmission.setVoting(votings);

        return transmission;
    }

    public static TransmissionBuilder sampleShawshankBuilder() {
        return new TransmissionBuilder()
                .name("The Shawshank Redemption")
                .description("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.")
                .length(142)
                .ageAvailability(AgeAvailability.AGE15)
                .language("EN")
                .type(TransmissionType.MOVIE);
    }

    public static TransmissionBuilder sampleFootballBuilder() {
        return new TransmissionBuilder()
                .name("Football")
                .description("Ball pushed by a foot.")
                .length(120)
                .ageAvailability(AgeAvailability.AGE12)
                .language("GR")
                .type(TransmissionType.SPORT_EVENT);
    }

    public static TransmissionBuilder sampleIceAgeBuilder() {
        return new TransmissionBuilder()
                .name("Ice Age 10")
                .description("Sage about hard life of a squirrel.")
                .length(360)
                .ageAvailability(AgeAvailability.AGE18)
                .language("CZ")
                .type(TransmissionType.DOCUMENTARY);
    }
}
