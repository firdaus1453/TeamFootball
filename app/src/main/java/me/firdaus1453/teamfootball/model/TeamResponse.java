package me.firdaus1453.teamfootball.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by firdaus1453 on 2/3/2019.
 */
public class TeamResponse {
    @SerializedName("teams")
    private List<TeamData> teamData;

    public List<TeamData> getTeamData() {
        return teamData;
    }

    public void setTeamData(List<TeamData> teamData) {
        this.teamData = teamData;
    }
}
