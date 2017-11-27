package org.cyanteam.telemaniacs.core.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Class represents one user voting in system. 
 * @author Miroslav Kubus
 */
@Entity
@Table(name = "Votings")
public class Voting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    
    @NotNull
    @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.)"
            + "{3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")
    private String ipAddress;
    
    @Min(value = 0)
    @Max(value = 5)
    private int rank;
    
    private String comment;
    
    @ManyToOne(optional = false)
    private User user;
    
    @ManyToOne(optional = false)
    private Transmission transmission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    } 
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        hash = 23 * hash + ((user == null) ? 0 : user.hashCode());
        hash = 23 * hash + rank;
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
        if (!(obj instanceof Voting)) {
            return false;
        }
        
        final Voting other = (Voting) obj;
        
        if((ipAddress != null) 
                ? !ipAddress.equals(other.getIpAddress())
                : other.getIpAddress() != null){
            return false;
        }
        
        if((user != null) 
                ? !user.equals(other.getUser())
                : other.getUser() != null){
            return false;
        }
        
        return rank == other.getRank();
    }

    @Override
    public String toString() {
        return "Voting { " + "id=" + id + ", ipAddress=" + ipAddress + ", rank=" 
               + rank + ", comment=" + comment + ", user=" + user 
               + ", transmission=" + transmission + " }";
    }
}
