package org.cyanteam.telemaniacs.core.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

import org.cyanteam.telemaniacs.core.enums.Gender;

/**
 * Class represents one user in system.
 * @author Miroslav Kubus
 */
@Entity
@Table(name = "Users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;
    
    @NotNull
    @Column(nullable = false)
    private String passwordHash;
    
    @NotNull
    @Enumerated
    private Gender gender;
    
    @NotNull
    @Min(value = 0)
    private int age;
    
    @NotNull
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    
    @NotNull
    private boolean isAdmin;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_channel",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id"))
    private List<Channel> favoriteChannels;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_transmission",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "transmission_id"))
    private List<Transmission> favoriteTransmissions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Voting> voting;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdminRights() {
        this.isAdmin = true;
    }
    
    public void unsetAdminRights() {
        this.isAdmin = false;
    }
    
    public List<Channel> getFavoriteChannels() {
        return favoriteChannels;
    }

    public void setFavoriteChannels(List<Channel> favoriteChannels) {
        this.favoriteChannels = favoriteChannels;
    }

    public List<Transmission> getFavoriteTransmissions() {
        return favoriteTransmissions;
    }

    public void setFavoriteTransmissions(List<Transmission> favoriteTransmissions) {
        this.favoriteTransmissions = favoriteTransmissions;
    }

    public List<Voting> getVoting() {
        return voting;
    }

    public void setVoting(List<Voting> voting) {
        this.voting = voting;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + ((email == null) ? 0 : email.hashCode());
        hash = 23 * hash + ((username == null) ? 0 : username.hashCode());
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if(this == obj){
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        
        final User other = (User) obj;
        
        if((email != null) 
                ? !email.equals(other.getEmail())
                : other.getEmail()!= null){
            return false;
        }
        
        if((username != null) 
                ? !username.equals(other.getUsername())
                : other.getUsername()!= null){
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "User { " + username + ", age=" + age + ", email=" + email + 
               ", favouriteChannels=" + favoriteChannels + 
               ", favouritePrograms=" + favoriteTransmissions + " }";
    }   
}
