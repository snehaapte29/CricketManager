package com.snehaapte.cricketmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String name;

    private String birthdate;
    private boolean leftHandBatsman;
    private boolean leftHandBowler;

    @OneToOne
    private PlayerStyle style;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public PlayerStyle getStyle() {
        return style;
    }

    public void setStyle(PlayerStyle style) {
        this.style = style;
    }
}
