package me.firdaus1453.teamfootball.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by firdaus1453 on 2/3/2019.
 */
public class TeamData {
    @SerializedName("idTeam")
    private String idTeam;

    @SerializedName("strTeam")
    private String nameTeam;

    @SerializedName("strDescriptionEN")
    private String descriptionTeam;

    @SerializedName("strTeamBadge")
    private String logoTeam;

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getDescriptionTeam() {
        return descriptionTeam;
    }

    public void setDescriptionTeam(String descriptionTeam) {
        this.descriptionTeam = descriptionTeam;
    }

    public String getLogoTeam() {
        return logoTeam;
    }

    public void setLogoTeam(String logoTeam) {
        this.logoTeam = logoTeam;
    }
}
