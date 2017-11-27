package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.enums.Sex;

import java.util.List;

/**
 * Class for userDTO.
 * 
 * Created by Simona Tinkova on 11/25/2017.
 * Implemented by kubmir on 11/26/2017.
 */
public class UserDTO {
    private Long id;
    private String username;
    private String passwordHash;
    private Sex sex;
    private int age;
    private String email;
    private boolean isAdmin;
    private List<ChannelDTO> favouriteChannels;
    private List<TransmissionDTO> favouriteTransmissions;
    private List<VotingDTO> voting;

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
        return passwordHash;
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

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<ChannelDTO> getFavouriteChannels() {
        return favouriteChannels;
    }

    public void setFavouriteChannels(List<ChannelDTO> favouriteChannels) {
        this.favouriteChannels = favouriteChannels;
    }

    public List<TransmissionDTO> getFavouriteTransmissions() {
        return favouriteTransmissions;
    }

    public void setFavouriteTransmissions(List<TransmissionDTO> favouriteTransmissions) {
        this.favouriteTransmissions = favouriteTransmissions;
    }

    public List<VotingDTO> getVoting() {
        return voting;
    }

    public void setVoting(List<VotingDTO> voting) {
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
        if (!(obj instanceof UserDTO)) {
            return false;
        }
        
        final UserDTO other = (UserDTO) obj;
        
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
}
