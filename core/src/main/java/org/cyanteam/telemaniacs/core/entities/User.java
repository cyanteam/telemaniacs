package org.cyanteam.telemaniacs.core.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    private String passwordHash;
    
    @NotNull
    @Enumerated
    private Sex sex;
    
    @NotNull
    private int age;
    
    @NotNull
    @Column(nullable = false, unique = true)
    @Pattern(regexp=".+@.+\\....?")
    private String email;
    
    @NotNull
    private boolean isAdmin;
    
    @OneToMany(mappedBy = "user")
    List<Channel> favouriteChannels;

    @OneToMany(mappedBy = "user")
    List<Transmission> favouritePrograms;
    
    public User() {
        this.isAdmin = false;
        this.favouriteChannels = new ArrayList<>();
        this.favouritePrograms = new ArrayList<>();
    }
    
    public User(Long id) {
        this.id = id;
        this.isAdmin = false;
        this.favouriteChannels = new ArrayList<>();
        this.favouritePrograms = new ArrayList<>();
    }

    public User(Long id, String username, String passwordHash, Sex sex, int age,
                String email, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.isAdmin = isAdmin;
        this.favouriteChannels = new ArrayList<>();
        this.favouritePrograms = new ArrayList<>();
    }

    public User(Long id, String username, String passwordHash, Sex sex, int age, 
                String email, boolean isAdmin, List<Channel> favouriteChannels, 
                List<Transmission> favouritePrograms) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.isAdmin = isAdmin;
        this.favouriteChannels = favouriteChannels;
        this.favouritePrograms = favouritePrograms;
    }

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

    public List<Transmission> getFavouritePrograms() {
        return favouritePrograms;
    }

    public void setFavouritePrograms(List<Transmission> favouritePrograms) {
        this.favouritePrograms = favouritePrograms;
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
               ", favouritePrograms=" + favouritePrograms + " }";
    }   
}
