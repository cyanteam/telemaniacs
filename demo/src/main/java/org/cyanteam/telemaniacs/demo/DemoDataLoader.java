package org.cyanteam.telemaniacs.demo;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.enums.AgeAvailability;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.cyanteam.telemaniacs.core.services.ChannelService;
import org.cyanteam.telemaniacs.core.services.TransmissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.*;

@Named
public class DemoDataLoader {
    private static final Logger logger = LoggerFactory.getLogger(DemoDataLoader.class);
    private static final Random random = new Random();

    private static final String[] adjectives = {
        "Miraculous", "Amazing", "Serious", "Interesting",
        "Good", "First", "Last", "Little", "Young",
        "Strong", "Old", "Special", "Impossible", "Crazy",
        "Funny", "Tiny", "Shiny", "Careless", "Happy"
    };

    private static final String[] names = {
        "Man", "Woman", "John", "Lion", "Spy",
        "Wizard", "Parrot", "Car", "Businessman", "Cop",
        "Ride", "Ball", "Ride", "Wind", "Sun"
    };

    private static final Integer[] lengths = { 15, 30, 60, 120 };
    private static final AgeAvailability[] availability = { AgeAvailability.AGE12, AgeAvailability.AGE15, AgeAvailability.AGE18, AgeAvailability.UNRESTRICTED };
    private static final String[] languages = { "CZ", "SK", "EN", "D", "FR" };
    private static final TransmissionType[] types = { TransmissionType.MOVIE, TransmissionType.TV_SERIES, TransmissionType.TV_SHOW, TransmissionType.SPORT_EVENT, TransmissionType.DOCUMENTARY };
    private static int counter = 1;

    @Inject
    private ChannelService channelService;

    @Inject
    private TransmissionService transmissionService;

    public void load() {
        Channel hbo = createChannel("HBO", ChannelType.MOVIE, "EN");
        Channel cinestar = createChannel("Cinestar", ChannelType.MOVIE, "EN");
        Channel cn = createChannel("Cartoon Network", ChannelType.CHILDREN, "CZ");
        Channel minimax = createChannel("Minimax", ChannelType.CHILDREN, "HU");
        Channel nova = createChannel("Nova", ChannelType.COMMERCE, "CZ");
        Channel joj = createChannel("JOJ", ChannelType.COMMERCE, "SK");
        Channel discovery = createChannel("Discovery Channel", ChannelType.DOCUMENTARY, "EN");
        Channel natgeo = createChannel("Nat Geo", ChannelType.DOCUMENTARY, "EN");
        Channel mtv = createChannel("MTV", ChannelType.MUSIC, "EN");
        Channel vh1 = createChannel("VH1", ChannelType.MUSIC, "EN");
        Channel eurosport = createChannel("Eurosport", ChannelType.SPORT, "EN");
        Channel golf = createChannel("Golf Channel", ChannelType.SPORT, "EE");
        Channel[] channels = { hbo, cinestar, cn, minimax, nova, joj, discovery, natgeo, mtv, vh1, eurosport, golf };

        final int transmissionsNumber = channels.length * 2;
        List<Transmission> transmissions = new ArrayList<>();
        Map<Integer, Integer> partMap = new HashMap<>();
        for (int i = 0; i < transmissionsNumber; i++) {
            transmissions.add(createTransmission());
            partMap.put(i, 1);
        }

        for (Channel channel : channels) {
            LocalDateTime baseTime = LocalDateTime.now();
            int relativeTime = 0;
            for (int i = 0; i < 50; i++) {
                int index = random.nextInt(transmissions.size());

                LocalDateTime time = baseTime.plusSeconds(relativeTime);
                Transmission transmission = transmissions.get(index);
                createOccurrence(channel, transmission, time, partMap.get(index));
                relativeTime += transmission.getLength();
            }

        }
    }

    private Channel createChannel(String name, ChannelType channelType, String language) {
        logger.trace("Channel created: " + name);

        Channel channel = new Channel();
        channel.setName(name);
        channel.setChannelType(channelType);
        channel.setLanguage(language);

        channelService.create(channel);
        return channel;
    }

    private Transmission createTransmission() {
        Transmission transmission = new Transmission();
        transmission.setName(getRandom(adjectives) + " " + getRandom(names) + " " + counter);
        transmission.setDescription(getRandomString(10));
        transmission.setLength(getRandom(lengths));
        transmission.setAgeAvailability(getRandom(availability));
        transmission.setLanguage(getRandom(languages));
        transmission.setTransmissionType(getRandom(types));

        transmissionService.create(transmission);
        counter++;

        return transmission;
    }

    private TransmissionOccurrence createOccurrence(Channel channel, Transmission transmission, LocalDateTime start,
                                                    int part) {
        TransmissionOccurrence occurrence = new TransmissionOccurrence();
        occurrence.setPartName(Integer.toString(part));
        occurrence.setStartDate(start);
        occurrence.setChannel(channel);
        occurrence.setTransmission(transmission);
        occurrence.setRerun(true);

        transmissionService.addOccurrence(occurrence);
        return occurrence;
    }

    private <T> T getRandom(T[] values) {
        int index = random.nextInt(values.length);
        return values[index];
    }

    private String getRandomString(int words) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < words; i++) {
            output.append(getRandom(names));
            output.append(" ");
        }

        return output.toString();
    }
}