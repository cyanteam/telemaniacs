package org.cyanteam.telemaniacs.core.dto;

import org.cyanteam.telemaniacs.core.enums.TransmissionType;

import java.util.List;

/**
 * @author Tomas Milota
 */
public class TransmissionCreateDTO {
    private Long id;
    private String name;
    private String description;
    private int length;
    private String language;
    private TransmissionType transmissionType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
