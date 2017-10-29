package org.cyanteam.telemaniacs.core.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

import org.cyanteam.telemaniacs.core.enums.Sex;

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
    private Sex sex;
    
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
    List<Channel> favouriteChannels;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_transmission",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "transmission_id"))
    List<Transmission> favouriteTransmissions;
    
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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
    
    public List<Channel> getFavouriteChannels() {
        return favouriteChannels;
    }

    public void setFavouriteChannels(List<Channel> favouriteChannels) {
        this.favouriteChannels = favouriteChannels;
    }

    public List<Transmission> getFavouriteTransmissions() {
        return favouriteTransmissions;
    }

    public void setFavouriteTransmissions(List<Transmission> favouriteTransmissions) {
        this.favouriteTransmissions = favouriteTransmissions;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + ((email == null) ? 0 : email.hashCode());
        hash = 23 * hash + ((username == null) ? 0 : username.hashCode());
        hash = 23 * hash + ((passwordHash == null) ? 0 : passwordHash.hashCode());
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
        
        if((passwordHash != null) 
                ? !passwordHash.equals(other.getPasswordHash())
                : other.getPasswordHash()!= null){
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
               ", favouriteChannels=" + favouriteChannels + 
               ", favouritePrograms=" + favouriteTransmissions + " }";
    }   
}
