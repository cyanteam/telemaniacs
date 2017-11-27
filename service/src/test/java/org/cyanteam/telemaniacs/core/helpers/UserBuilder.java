package org.cyanteam.telemaniacs.core.helpers;

import java.util.ArrayList;
import java.util.List;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.enums.Sex;

/**
 * Helper class for building User (for test purposes)
 * @author Miroslav Kubus
 */
public class UserBuilder {
  
    private Long id;
    private String username;
    private String passwordHash;
    private Sex sex;
    private int age;
    private String email;
    private boolean isAdmin;
    private List<Channel> favouriteChannels;
    private List<Voting> voting;
    private List<Transmission> favouriteTransmissions;
    
    public UserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }
    
    public UserBuilder passwordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public UserBuilder sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public UserBuilder age(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }
    
    public UserBuilder isAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }
        
    public UserBuilder votings(List<Voting> votings) {
        this.voting = votings;
        return this;
    }
        
    public UserBuilder favouriteChannels(List<Channel> channels) {
        this.favouriteChannels = channels;
        return this;
    }
    
    public UserBuilder favouriteTransmission(List<Transmission> transmissions) {
        this.favouriteTransmissions = transmissions;
        return this;
    }
    
    public User build() {
        User user = new User();
        user.setSex(sex);
        user.setAge(age);
        user.setEmail(email);
        user.setFavoriteChannels(favouriteChannels);
        user.setFavoriteTransmissions(favouriteTransmissions);
        user.setUsername(username);
        user.setVoting(voting);
        user.setPasswordHash(passwordHash);
        
        if(this.isAdmin) {
            user.setAdminRights();
        }
        
        return user;
    }

    public static UserBuilder sampleYoungUserBuilder() {
        return new UserBuilder()
                .sex(Sex.MALE)
                .age(5)
                .email("youngUser@test.com")
                .passwordHash("youngUserPasswordHash")
                .isAdmin(false)
                .votings(new ArrayList<>())
                .favouriteTransmission(new ArrayList<>())
                .favouriteChannels(new ArrayList<>());
    }

    public static UserBuilder sampleAdultUserBuilder() {
        return new UserBuilder()
                .sex(Sex.MALE)
                .age(30)
                .email("adultUser@test.com")
                .passwordHash("adultUserPasswordHash")
                .isAdmin(false)
                .votings(new ArrayList<>())
                .favouriteTransmission(new ArrayList<>())
                .favouriteChannels(new ArrayList<>());
    }

    public static UserBuilder sampleAdminBuilder() {
        return new UserBuilder()
                .sex(Sex.MALE)
                .age(5)
                .email("admin@test.com")
                .passwordHash("adminPasswordHash")
                .isAdmin(true)
                .votings(new ArrayList<>())
                .favouriteTransmission(new ArrayList<>())
                .favouriteChannels(new ArrayList<>());
    }
}
