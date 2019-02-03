package me.firdaus1453.teamfootball.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.firdaus1453.teamfootball.R;
import me.firdaus1453.teamfootball.api.ApiClient;
import me.firdaus1453.teamfootball.api.ApiInterface;
import me.firdaus1453.teamfootball.helper.Constant;
import me.firdaus1453.teamfootball.model.TeamData;
import me.firdaus1453.teamfootball.model.TeamResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTeamActivity extends AppCompatActivity {

    @BindView(R.id.imgLogoTeam)
    ImageView imgLogoTeam;
    @BindView(R.id.txtNameTeam)
    TextView txtNameTeam;
    @BindView(R.id.txtDesc)
    TextView txtDesc;

    private List<TeamData> teamDataList;
    private ProgressDialog progressDialog;
    private int idTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            idTeam = Integer.valueOf(bundle.getString(Constant.KEY_ID_TEAM));
            Log.i("Cek id team", bundle.getString(Constant.KEY_ID_TEAM));
        }

        getData();
    }

    private void getData() {
        showProgress();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TeamResponse> call = apiInterface.getDetailTeam(idTeam);
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                progressDialog.dismiss();
                TeamResponse teamResponse = response.body();

                if (teamResponse != null) {
                    teamDataList = teamResponse.getTeamData();

                    txtNameTeam.setText(teamDataList.get(0).getNameTeam());
                    txtDesc.setText(teamDataList.get(0).getDescriptionTeam());
                    RequestOptions options = new RequestOptions().placeholder(R.drawable.ic_broken_image).error(R.drawable.ic_broken_image);
                    Glide.with(DetailTeamActivity.this).load(teamDataList.get(0).getLogoTeam()).apply(options).into(imgLogoTeam);
                }

            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable throwable) {
                progressDialog.dismiss();
                Toast.makeText(DetailTeamActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(DetailTeamActivity.this);
        progressDialog.setMessage("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
