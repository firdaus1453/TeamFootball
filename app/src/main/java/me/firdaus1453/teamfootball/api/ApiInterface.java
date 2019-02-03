package me.firdaus1453.teamfootball.api;

import me.firdaus1453.teamfootball.model.TeamResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by firdaus1453 on 2/3/2019.
 */
public interface ApiInterface {
//    https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League
//    https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=133604
    @GET("/api/v1/json/1/search_all_teams.php")
    Call<TeamResponse> getTeam(
            @Query("l") String nameLeague
    );

    @GET("/api/v1/json/1/lookupteam.php")
    Call<TeamResponse> getDetailTeam(
            @Query("id") int idTeam
    );


}
