package org.cyanteam.telemaniacs.core.services.mapper;

public class DestinationObject {
    private String text;

    private int number;

    public String getText() {
        return text;
    }

    public DestinationObject setText(String text) {
        this.text = text;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public DestinationObject setNumber(int number) {
        this.number = number;
        return this;
    }
}
