package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.enums.Gender;

import java.util.List;

/**
 * Class for userDTO.
 * 
 * Created by Simona Tinkova on 11/25/2017.
 * Implemented by kubmir on 11/26/2017.
 * @author Michael Le
 */
public class VotingUserDTO {
    private Long id;
    private String username;

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

    @Override
    public int hashCode() {
        int hash = 7;
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
        if (!(obj instanceof VotingUserDTO)) {
            return false;
        }
        
        final VotingUserDTO other = (VotingUserDTO) obj;
        if((username != null) 
                ? !username.equals(other.getUsername())
                : other.getUsername()!= null){
            return false;
        }

        return true;
    }
}
