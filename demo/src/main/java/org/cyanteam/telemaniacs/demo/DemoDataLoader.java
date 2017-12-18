package org.cyanteam.telemaniacs.demo;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.enums.AgeAvailability;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.enums.Gender;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.cyanteam.telemaniacs.core.services.ChannelService;
import org.cyanteam.telemaniacs.core.services.FavoriteChannelsService;
import org.cyanteam.telemaniacs.core.services.TransmissionService;
import org.cyanteam.telemaniacs.core.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Named
public class DemoDataLoader {
    private static final Logger logger = LoggerFactory.getLogger(DemoDataLoader.class);
    private static final Random random = new Random();
    private static final LocalDate baseDate = LocalDate.now();
    private static final LocalDateTime baseTime
            = LocalDateTime.of(baseDate, LocalTime.of(0, 0, 0, 0));

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

    private static final Set<String> usedNames = new HashSet<>();
    private static final Integer[] lengths = { 15, 30, 45, 60 };
    private static final AgeAvailability[] availability = { AgeAvailability.AGE12, AgeAvailability.AGE15, AgeAvailability.AGE18, AgeAvailability.UNRESTRICTED };
    private static final String[] languages = { "CZ", "SK", "EN", "D", "FR" };
    private static final TransmissionType[] types = { TransmissionType.MOVIE, TransmissionType.TV_SERIES, TransmissionType.TV_SHOW, TransmissionType.SPORT_EVENT, TransmissionType.DOCUMENTARY };
    private static int counter = 1;

    @Inject
    private UserService userService;

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

        final int transmissionsNumber = channels.length * 4;
        List<Transmission> transmissions = new ArrayList<>();
        Map<Integer, Integer> partMap = new HashMap<>();
        for (int i = 0; i < transmissionsNumber; i++) {
            transmissions.add(createTransmission());
            partMap.put(i, 1);
        }

        for (Channel channel : channels) {
            int relativeTime = 0;
            for (int i = 0; i < 150; i++) {
                int index = random.nextInt(transmissions.size());
                int part = 0;
                if (index > 0.8 * transmissionsNumber) {
                    part = partMap.get(index);
                }

                LocalDateTime time = baseTime.plusMinutes(relativeTime);
                Transmission transmission = transmissions.get(index);
                createOccurrence(channel, transmission, time, part);
                relativeTime += transmission.getLength();
                partMap.put(index, part + 1);
            }
        }

        User admin = createUser("admin", "admin", true);
        User user = createUser("pepa", "novak", false);
        admin.setFavoriteTransmissions(transmissions.subList(0, 10));
        user.setFavoriteTransmissions(transmissions.subList(10, 20));
        userService.update(admin);
        userService.update(user);
    }

    private User createUser(String username, String password, boolean isAdmin) {
        User user = new User();
        user.setUsername(username);
        user.setGender(Gender.MALE);
        user.setAge(30);
        user.setEmail(username + "@telemaniacs.com");
        if (isAdmin) {
            user.setAdminRights();
        }

        userService.create(user, password);
        return user;
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
        transmission.setName(getRandomName());
        transmission.setDescription(getRandomString(80));
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
        if (part > 0) {
            occurrence.setPartName(Integer.toString(part));
        }
        occurrence.setStartDate(start);
        occurrence.setChannel(channel);
        occurrence.setTransmission(transmission);
        occurrence.setRerun(random.nextInt(5) == 1);

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

        return output.toString().trim();
    }

    private String getRandomName() {
        String name = getRandom(adjectives) + " " + getRandom(names);
        if (usedNames.contains(name)) {
            name += " " + counter;
        }

        usedNames.add(name);
        return name;
    }
}
