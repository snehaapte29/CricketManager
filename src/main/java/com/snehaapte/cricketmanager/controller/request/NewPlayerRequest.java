package com.snehaapte.cricketmanager.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewPlayerRequest {
    @NotEmpty
    private String name;
    private String birthdate;
    @NotNull
    private Boolean leftHandBatsman;
    @NotNull
    private Boolean leftHandBowler;
    @NotEmpty
    private String style;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public boolean getLeftHandBatsman() {
        return leftHandBatsman;
    }

    public void setLeftHandBatsman(boolean leftHandBatsman) {
        this.leftHandBatsman = leftHandBatsman;
    }

    public boolean getLeftHandBowler() {
        return leftHandBowler;
    }

    public void setLeftHandBowler(boolean leftHandBowler) {
        this.leftHandBowler = leftHandBowler;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
