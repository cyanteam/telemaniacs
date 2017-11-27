package org.cyanteam.telemaniacs.core.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Dto for user authentication.
 * @author Miroslav Kubus
 */
public class UserAuthenticationDTO {
    
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String email;

    @NotNull
    @Size(min = 1, max = 50)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
}
