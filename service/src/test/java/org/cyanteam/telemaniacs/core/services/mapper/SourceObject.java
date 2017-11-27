package org.cyanteam.telemaniacs.core.services.mapper;

public class SourceObject {
    private String text;

    private int number;

    public SourceObject(String text, int number) {
        this.text = text;
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public SourceObject setText(String text) {
        this.text = text;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public SourceObject setNumber(int number) {
        this.number = number;
        return this;
    }
}
