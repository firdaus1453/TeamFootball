package me.firdaus1453.teamfootball.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.firdaus1453.teamfootball.R;
import me.firdaus1453.teamfootball.adapter.TeamAdapter;
import me.firdaus1453.teamfootball.api.ApiClient;
import me.firdaus1453.teamfootball.api.ApiInterface;
import me.firdaus1453.teamfootball.helper.Constant;
import me.firdaus1453.teamfootball.model.TeamData;
import me.firdaus1453.teamfootball.model.TeamResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_team)
    RecyclerView rvTeam;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private List<TeamData> teamDataList;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getData();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

    }

    private void getData() {
        showProgress();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TeamResponse> call = apiInterface.getTeam(Constant.LEAGUE_NAME);
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                progressDialog.dismiss();
                swipeRefresh.setRefreshing(false);

                TeamResponse teamResponse = response.body();

                teamDataList = teamResponse.getTeamData();

                rvTeam.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvTeam.setAdapter(new TeamAdapter(MainActivity.this,teamDataList));
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable throwable) {
                progressDialog.dismiss();
                swipeRefresh.setRefreshing(false);

                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
