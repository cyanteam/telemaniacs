package org.cyanteam.telemaniacs.core.dto;

public class DateTimeDTO {
    private String dateTime;

    public DateTimeDTO(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
