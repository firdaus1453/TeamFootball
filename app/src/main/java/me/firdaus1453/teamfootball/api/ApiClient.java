package me.firdaus1453.teamfootball.api;

import me.firdaus1453.teamfootball.helper.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by firdaus1453 on 2/3/2019.
 */
public class ApiClient {
//    https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League
    private static Retrofit retrofit = null;
    public static Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
